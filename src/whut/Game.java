package whut;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game implements Serializable {
	private Map map;
	ArrayList<Entity> entity;
	ArrayList<GeneticCode> allGeneticCode;
	ArrayList<? extends AgensUsable> au;
	
	
	public Entity getEntityAt(int index) {
		if(index<entity.size()&&index>=0) {
			return entity.get(index);
		}
		return null;
	}
	
	public Game()
	{
		initGame();
	}
	
	public void initGame() //játék inicializálás
	{
		System.out.println(">[:Game].initGame()");
		map = new Map();
		entity = new ArrayList<Entity>();
		allGeneticCode = new ArrayList<GeneticCode>();
		allGeneticCode.add(new ForgetCode());
		allGeneticCode.add(new StunCode());
		allGeneticCode.add(new ProtectionCode());
		allGeneticCode.add(new VitusdanceCode());
		if(MyRunnable.getStart()) {
			System.out.println("Szerente egy random pályát? Igen:y , Nem : n");
			Scanner sc = new Scanner(System.in);
			String random = sc.nextLine();
			if(random.equals("y"))
				createGame();
		}
		MyRunnable.setStart(false);
		
	}
	
	public void createGame() {
		Random r = new Random();
		int players = r.nextInt(5) + 1;
		for(int i = 0; i < players; i++)
			entity.add(new Virologus());
		int items = 0;
		int anyagok = 0;
		int genetic = 0;
		int fields = r.nextInt(10) + 1;
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
				if(items%4 == 0)
					map.getField(i).addItem(new Cloak());
				else if(items%4 == 1)
					map.getField(i).addItem(new Glove());
				else if(items%4 == 2)
					map.getField(i).addItem(new Sack());
				else 
					map.getField(i).addItem(new Axe());
				items++;
			}
			else if(i%5 == 3) {
				map.addField(new Storage());
				for(int k = 0; k<2; k++) {
					map.getField(i).getPacket().addMaterial(new Aminosav());
					map.getField(i).getPacket().addMaterial(new Nukleotid());
				}
			}
			else
				map.addField(new EvilLab());
			
			map.getField(0).setNeighbour(map.getField(i));
		}
		
		for(int i = 0; i < entity.size(); i++)
			map.getField(i % map.getSize()).accept(entity.get(i));

	}
	
	public void oneRound() // egy kör, összes entity
	{
		System.out.println(">[:Game].oneRound()");
		for(int i = 0;i<entity.size();++i)
		{
			  entity.get(i).step();
		
		}
	}
	
	public void endGame(ArrayList<GeneticCode> all) //játék végét ellenőrzi, genetikai kódókat hasonlít össze
	{
		System.out.println(">[:Game].endGame(all)");
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
		
		if(nem)
			MyRunnable.log("You won :)!");
		
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
	
}


