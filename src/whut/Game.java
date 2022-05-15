package whut;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Game extends View implements Serializable {
	private Map map;
	ArrayList<Entity> entity;
	ArrayList<GeneticCode> allGeneticCode;
	ArrayList<? extends AgensUsable> au;
	private int players;
	private boolean megy = true;
	
	public Entity getEntityAt(int index) {
		if(index<entity.size()&&index>=0) {
			return entity.get(index);
		}
		return null;
	}
	
	public Game(int _players)
	{
		players = _players;
		initGame();
	}
	
	public void initGame() //játék inicializálás
	{
		map = new Map();
		entity = new ArrayList<Entity>();
		allGeneticCode = new ArrayList<GeneticCode>();
		allGeneticCode.add(new ForgetCode());
		allGeneticCode.add(new StunCode());
		allGeneticCode.add(new ProtectionCode());
		allGeneticCode.add(new VitusdanceCode());
		
		createGame();
		MyRunnable.setGame(this);
		attach(new GameObserver(this));
		
		
		//MyRunnable.startInfo();
		//MyRunnable.getInputFirstAct();
	}
	
	public void BearAll() {
		System.out.print("vklnnroebrne........");
		boolean vanJozan = false;
		for (Entity e : entity) {
			Virologus v = (Virologus)e;
			if (!v.isBear()) vanJozan = true;
		}
		
		if(!vanJozan) {
			megy = false;
			((GameObserver)observer.get(0)).drawEnd("Everybody lost!");
		}
	}
	public void mapThird(){
		for(int i = 0; i < players; i++)
			entity.add(new Virologus());
		map.addField(new Field());
		map.addField(new Lab());
		map.getField(1).setGeneticCode(new VitusdanceCode());
		map.addField(new Shelter());
		map.getField(2).addItem(new Cloak());
		map.getField(2).addItem(new Cloak());
		map.getField(2).addItem(new Axe());
		map.addField(new Field());
		map.addField(new Lab());
		map.getField(4).setGeneticCode(new StunCode());
		map.getField(0).setNeighbour(map.getField(1));
		map.getField(0).setNeighbour(map.getField(3));
		map.getField(1).setNeighbour(map.getField(3));
		map.getField(1).setNeighbour(map.getField(2));
		map.getField(2).setNeighbour(map.getField(4));
		map.addField(new Lab());
		map.getField(5).setGeneticCode(new ProtectionCode());
		map.getField(5).setNeighbour(map.getField(0));
		map.addField(new Lab());
		map.getField(6).setGeneticCode(new ForgetCode());
		map.getField(6).setNeighbour(map.getField(5));
		map.getField(6).setNeighbour(map.getField(4));
		map.addField(new Storage());
		map.getField(7).getPacket().addMaterial(new Nukleotid());
		map.getField(7).getPacket().addMaterial(new Aminosav());
		map.getField(7).setNeighbour(map.getField(5));
		map.getField(7).setNeighbour(map.getField(2));
		map.getField(7).setNeighbour(map.getField(4));
		map.addField(new Storage());
		map.getField(8).getPacket().addMaterial(new Nukleotid());
		map.getField(8).getPacket().addMaterial(new Aminosav());
		map.getField(8).setNeighbour(map.getField(4));
		map.addField(new Shelter());
		map.getField(9).addItem(new Glove());
		map.getField(9).addItem(new Axe());
		map.getField(9).addItem(new Axe());
		map.getField(9).setNeighbour(map.getField(8));
		map.addField(new EvilLab());
		map.getField(10).setGeneticCode(new StunCode());
		map.getField(10).setNeighbour(map.getField(9));
		map.getField(10).setNeighbour(map.getField(2));
		map.getField(10).setNeighbour(map.getField(1));
		map.getField(10).setNeighbour(map.getField(3));
		map.addField(new Field());
		map.addField(new Field());
		map.addField(new Shelter());
		map.addField(new Field());
		map.addField(new Field());
		map.getField(11).setNeighbour(map.getField(6));
		map.getField(11).setNeighbour(map.getField(12));
		map.getField(12).setNeighbour(map.getField(13));
		map.getField(13).setNeighbour(map.getField(14));
		map.getField(14).setNeighbour(map.getField(15));
		map.getField(15).setNeighbour(map.getField(3));
		map.getField(14).setNeighbour(map.getField(3));
		for(int i = 0; i < entity.size(); i++)
			map.getField(i % map.getSize()).accept(entity.get(i));
	}


	public void createGame() {
		MyRunnable.setLeft(49);
		Random r = new Random();
		for(int i = 0; i < players; i++)
			entity.add(new Virologus());
		int items = 0;
		int anyagok = 0;
		int genetic = 2;
		int fields = 5;
		for(int i = 0; i < fields; i++) {
			
			if(i % 5 == 0)
				map.addField(new Field());
			else if(i % 5 == 1) {
				map.addField(new Lab());
				if(genetic%4 == 0)
					map.getField(i).setGeneticCode(new ForgetCode());
				else if(genetic%4 == 1)
					map.getField(i).setGeneticCode(new StunCode());
				else if(genetic%4 == 2)
					map.getField(i).setGeneticCode(new ProtectionCode());
				else 
					map.getField(i).setGeneticCode(new VitusdanceCode());
				items++;
				genetic++;
			}
			else if(i%5 == 2) {
				map.addField(new Shelter());
				
				/*if(items%4 == 0)
					map.getField(i).addItem(new Cloak());
				else if(items%4 == 1)
					map.getField(i).addItem(new Glove());
				else if(items%4 == 2)
					map.getField(i).addItem(new Sack());
				else 
					map.getField(i).addItem(new Axe());
				items++;*/
				
				map.getField(i).addItem(new Cloak());
				map.getField(i).addItem(new Glove());
				map.getField(i).addItem(new Sack());
				map.getField(i).addItem(new Axe());
				items+=4;
			}
			else if(i%5 == 3) {
				map.addField(new Storage());
				for(int k = 0; k<2; k++) {
					map.getField(i).getPacket().addMaterial(new Aminosav());
					map.getField(i).getPacket().addMaterial(new Nukleotid());
				}
			}
			else {
				map.addField(new EvilLab());
				
			}
			
			map.getField(0).setNeighbour(map.getField(i));
		}
	
		//map.getField(0).setNeighbour(map.getField(0));
		/*
		map.addField(new Lab());
		map.getField(0).setGeneticCode(new ForgetCode());
		map.addField(new Lab());
		map.getField(1).setGeneticCode(new ProtectionCode());
		map.addField(new Lab());
		map.getField(2).setGeneticCode(new VitusdanceCode());
		map.addField(new Lab());
		map.getField(3).setGeneticCode(new StunCode());
		for(int i =1; i < 4; i++) {
			map.getField(0).setNeighbour(map.getField(i));
		}*/
		
		for(int i = 0; i < entity.size(); i++)
			//map.getField(i % map.getSize()).accept(entity.get(i));
			map.getField(0).accept(entity.get(i));

	}
	
	public void oneRound() // egy kör, összes entity
	{
		for(int i = 0;i<entity.size();++i)
		{
			  entity.get(i).step();
			  //myNotify();
		}
	}
	
	public void endGame(ArrayList<GeneticCode> all) //játék végét ellenőrzi, genetikai kódókat hasonlít össze
	{
		boolean[] megvannak = {false,false,false,false};
		for(int i = 0;i<all.size();++i)
		{
			for(int j = 0; j<allGeneticCode.size(); j++)
				if(all.get(i).toString().equals(allGeneticCode.get(j).toString()))
					megvannak[i] = true;
		}
		
		boolean nem = true;
		for (int i = 0; i< megvannak.length; i++)
			if(!megvannak[i])
				nem = false;
		
		if(nem) {
			MyRunnable.log("You won :)!");
			((GameObserver)observer.get(0)).drawEnd("You won!");
			megy = false;
			
		}
		
	}
	
	public boolean getMegy() {
		return megy;
	}
	
	
	public void run() {
		boolean megy = true;
		while(megy) {
			oneRound();
		}
	}
	
	public void removePlayer(Virologus v) {
		entity.remove(v);
	}
	
	public void addPlayer(Virologus v) {
		entity.add(v);
	}
	
	public Map getMap() {
		return map;
	}
	
	public ArrayList<Entity> getEntity(){
		return entity;
	}
	
	public void mapSecond() {
		for(int i = 0; i < players; i++)
			entity.add(new Virologus());
		map.addField(new Lab());
		map.getField(0).setGeneticCode(new VitusdanceCode());
		map.addField(new Field());
		map.addField(new Shelter());
		map.getField(2).addItem(new Sack());
		map.addField(new Storage());
		map.addField(new Shelter());
		map.getField(4).addItem(new Axe());
		
		map.addField(new Storage());
		map.addField(new Lab());
		map.getField(6).setGeneticCode(new ForgetCode());
		map.addField(new Field());
		map.addField(new Field());
		map.addField(new Lab());
		map.getField(9).setGeneticCode(new ProtectionCode());
		
		map.addField(new Field());
		map.addField(new Shelter());
		map.getField(11).addItem(new Glove());
		map.addField(new EvilLab());
		map.addField(new Storage());
		map.addField(new Shelter());
		map.getField(11).addItem(new Glove());
		
		map.addField(new Field());
		map.addField(new Storage());
		map.addField(new Field());
		map.addField(new Lab());
		map.getField(18).setGeneticCode(new StunCode());
		map.addField(new Shelter());
		map.getField(19).addItem(new Cloak());
		
		map.addField(new Shelter());
		map.getField(20).addItem(new Axe());
		map.addField(new Lab());
		map.getField(21).setGeneticCode(new ForgetCode());
		map.addField(new Storage());
		map.addField(new Field());
		map.addField(new Storage());
		
		map.getField(0).setNeighbour(map.getField(1));
		map.getField(0).setNeighbour(map.getField(5));
		map.getField(1).setNeighbour(map.getField(2));
		map.getField(1).setNeighbour(map.getField(6));
		map.getField(2).setNeighbour(map.getField(3));
		map.getField(2).setNeighbour(map.getField(7));
		map.getField(3).setNeighbour(map.getField(4));
		map.getField(3).setNeighbour(map.getField(8));
		map.getField(4).setNeighbour(map.getField(9));
		map.getField(5).setNeighbour(map.getField(6));
		map.getField(5).setNeighbour(map.getField(10));
		map.getField(6).setNeighbour(map.getField(7));
		map.getField(6).setNeighbour(map.getField(11));
		map.getField(7).setNeighbour(map.getField(8));
		map.getField(7).setNeighbour(map.getField(12));
		map.getField(8).setNeighbour(map.getField(9));
		map.getField(8).setNeighbour(map.getField(13));
		map.getField(9).setNeighbour(map.getField(14));
		map.getField(10).setNeighbour(map.getField(11));
		map.getField(10).setNeighbour(map.getField(15));
		map.getField(11).setNeighbour(map.getField(12));
		map.getField(11).setNeighbour(map.getField(16));
		map.getField(12).setNeighbour(map.getField(13));
		map.getField(12).setNeighbour(map.getField(17));
		map.getField(13).setNeighbour(map.getField(14));
		map.getField(13).setNeighbour(map.getField(18));
		map.getField(14).setNeighbour(map.getField(19));
		map.getField(15).setNeighbour(map.getField(16));
		map.getField(15).setNeighbour(map.getField(20));
		map.getField(16).setNeighbour(map.getField(17));
		map.getField(16).setNeighbour(map.getField(21));
		map.getField(17).setNeighbour(map.getField(18));
		map.getField(17).setNeighbour(map.getField(22));
		map.getField(18).setNeighbour(map.getField(19));
		map.getField(18).setNeighbour(map.getField(23));
		map.getField(19).setNeighbour(map.getField(24));
		map.getField(20).setNeighbour(map.getField(21));
		map.getField(21).setNeighbour(map.getField(22));
		map.getField(22).setNeighbour(map.getField(23));
		map.getField(23).setNeighbour(map.getField(24));
		
		for(int i = 0; i < entity.size(); i++)
			map.getField(i % map.getSize()).accept(entity.get(i));
		
	}
	
}


