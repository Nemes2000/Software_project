package whut;

import java.util.ArrayList;

public class GameObserver implements Observer{
	
	private Game game;
	private Virologus currentVirologus;
	private ButtonListContainer blc;
	private MoveContainer mc;
	private TouchContainer tc;
	
	public GameObserver(Game g, Virologus cv) {
		game = g;
		currentVirologus = cv;
	}
	
	public Virologus getCurrentVirologus() {
		return currentVirologus;
	}
	
	public void update() {
		drawGame();
	}
	
	public void drawGame() {
		blc = new ButtonListContainer();
		blc.addButton("Save");
		blc.addButton("New Game");
		blc.addButton("Finishturn");
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Field> fs = currentVirologus.getField().getNeighbourhood();
		for (Field f : fs) {
			fields.add("f"+MyRunnable.getFieldSzam(f));
		}
		mc = new MoveContainer(fields);
		
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<AgensUsable> vs = currentVirologus.getField().getVirologusok();
		for (AgensUsable a : vs) {
			Virologus v = (Virologus)a;
			players.add("v"+MyRunnable.getVirologusSzam(v));
		}
		tc = new TouchContainer(players);
	}

}
