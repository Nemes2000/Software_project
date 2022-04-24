package whut;
import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable
{
	private Map map;
	ArrayList<Entity> entity;
	ArrayList<GeneticCode> allGeneticCode;
	ArrayList<? extends AgensUsable> au;
	ArrayList<GeneticCode> geneticCodes;
	
	
	public Entity getEntityAt(int index) {
		if(index<entity.size()&&index>=0) {
			return entity.get(index);
		}
		return null;
	}
	public GeneticCode getGeneticCodeAt(int index) {
		if(index<geneticCodes.size()&&index>=0) {
			return geneticCodes.get(index);
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
		geneticCodes = new ArrayList<GeneticCode>();
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
		for(int i = 0;i<all.size();++i)
		{
			if(all.containsAll(allGeneticCode));
				System.out.println("A játékos megtanulta az összes genetikai kódot, és megnyerte a játékot.");
		}
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


