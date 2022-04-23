package whut;
import java.util.ArrayList;

import javax.swing.text.html.parser.Entity;

public class Game
{
	private Map map;
	ArrayList<Entity> entity;
	ArrayList<GeneticCode> allGeneticCode;
	
	
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
	}
	
	public void oneRound() // egy kör, összes entity
	{
		System.out.println(">[:Game].oneRound()");
		for(int i = 0; i < entity.size();++i)
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
	
}


