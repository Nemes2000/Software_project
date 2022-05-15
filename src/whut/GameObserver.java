package whut;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.*;

public class GameObserver implements Observer{
	
	private Game game;
	private ButtonListContainer blc = new ButtonListContainer();
	private MoveContainer mc= new MoveContainer();
	private TouchContainer tc;
	private JFrame frame;
	
	public GameObserver(Game g) {
		game = g;
		frame = new JFrame(); 
		setFrame();
		drawGame();
	}
		
	
	@Override
	public void update() {
		drawGame();
	}
	
	public void setFrame() {
		frame.setPreferredSize( new Dimension(1000, 600));
		frame.getContentPane().setBackground(Color.ORANGE);
		//update();
		blc.setBackground(Color.ORANGE);
		MyRunnable.setFrame(frame);
	//	MyRunnable.getCurrentVir().myNotify();
	//	MyRunnable.getCurrentVir().getField().myNotify();
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}
	
	public void drawGame() {
		System.out.print("DRAWGAMEHIVODIK");
		frame.getContentPane().removeAll();
		frame.repaint();
		
		blc = new ButtonListContainer();
		blc.addButton("Save");
		blc.addButton("New Game");
		blc.addButton("Finishturn");
		blc.draw();
		MyRunnable.getCurrentVir().myNotify();
		if(MyRunnable.getSelected() != null)
			MyRunnable.getSelected().myNotify();
		
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<Field> fs = MyRunnable.getCurrentVir().getField().getNeighbourhood();
		for (Field f : fs) {
			fields.add("f"+MyRunnable.getFieldSzam(f));
		}
		
		JPanel p = new JPanel(new FlowLayout());
		p.setBackground(Color.ORANGE);
		
		
		mc = new MoveContainer(fields);
		
		System.out.println(MyRunnable.getTouched());
		if(!MyRunnable.getTouched())
			tc = new TouchContainer();
		else {
			MyRunnable.getCurrentVir().getField().myNotify();
		}
		
		mc.setBackground(Color.ORANGE);
		tc.setBackground(Color.ORANGE);
		p.add(new JLabel("Remaining steps: "+Integer.toString(MyRunnable.getLeft())+"            "));
		p.add(mc);
		p.add(tc);
		frame.add(blc, BorderLayout.SOUTH);
		frame.add(p, BorderLayout.NORTH);
		frame.revalidate();
	}

}
