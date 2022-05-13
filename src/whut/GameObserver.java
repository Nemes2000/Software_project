package whut;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameObserver implements Observer{
	
	private Game game;
	private ButtonListContainer blc = new ButtonListContainer();
	private MoveContainer mc= new MoveContainer();
	private TouchContainer tc= new TouchContainer();
	private JFrame frame;
	
	public GameObserver(Game g) {
		game = g;
		frame = new JFrame(); 
		
		setFrame();
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
		mc.setBackground(Color.ORANGE);
		tc.setBackground(Color.ORANGE);
		MyRunnable.setFrame(frame);
		MyRunnable.getCurrentVir().myNotify();
		MyRunnable.getCurrentVir().getField().myNotify();
		JPanel p=new JPanel(new FlowLayout());
		p.add(mc);
		p.add(tc);
		p.setBackground(Color.ORANGE);
		frame.add(blc, BorderLayout.SOUTH);
		frame.add(p, BorderLayout.NORTH);
		
		
		frame.pack();
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void drawGame() {
		frame.getContentPane().removeAll();
		/*setFrame();
		blc.clearButtons();
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
		 
		mc = new MoveContainer(fields);
		tc = new TouchContainer();
		*/
	}

}
