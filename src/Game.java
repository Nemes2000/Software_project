import java.util.ArrayList;

public class Game
{
	private Map map;
	ArrayList<? extends Entity> entity;
	ArrayList<GeneticCode> allGeneticCode;
	
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
	
}


