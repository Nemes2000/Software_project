package whut;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameObserver implements Observer{
	
	private Game game;
	private ButtonListContainer blc;
	private MoveContainer mc;
	private TouchContainer tc;
	private JFrame frame;
	
	public GameObserver(Game g) {
		game = g;
		frame = new JFrame(); 
		setFrame();
	}
	
	public void update() {
		drawGame();
	}
	
	public void setFrame() {
		frame.getContentPane().removeAll();
		frame.setPreferredSize( new Dimension(800, 600));
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.pack();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void drawGame() {
		blc = new ButtonListContainer();
		blc.addButton("Save");
		blc.addButton("New Game");
		blc.addButton("Finishturn");
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Field> fs = MyRunnable.getCurrentVir().getField().getNeighbourhood();
		for (Field f : fs) {
			fields.add("f"+MyRunnable.getFieldSzam(f));
		}
		mc = new MoveContainer(fields);
		
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<AgensUsable> vs =  MyRunnable.getCurrentVir().getField().getVirologusok();
		for (AgensUsable a : vs) {
			Virologus v = (Virologus)a;
			players.add("v"+MyRunnable.getVirologusSzam(v));
		}
		tc = new TouchContainer(players);
	}

}
