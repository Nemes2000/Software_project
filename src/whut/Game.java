package whut;
import java.io.Serializable;
import java.util.ArrayList;

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
	
	public int getEntitySize(){
		return entity.size();
	}
	
}


