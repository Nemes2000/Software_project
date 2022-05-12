package whut;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TouchContainer extends JPanel {
	private JLabel label = new JLabel("Players you touched: ");
	private JComboBox<String> cb = new JComboBox<String>();
	private JButton button = new JButton();
	
	public TouchContainer() {
	
		
		button.setText("Touch");
		button.setActionCommand("touch");
		button.addActionListener(new touchActionListener()); 
		this.setLayout(new FlowLayout());
		this.add(label);
		this.add(cb);
		this.add(button);
	}
	
	public void draw(){
		this.removeAll();
		this.add(button);
		this.add(label);
		this.add(cb);
	}
	
	class itemActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String valami=cb.getSelectedItem().toString();
			String sub = valami.substring(1);
			MyRunnable.setSelected((Virologus)MyRunnable.getGame().getEntityAt(Integer.parseInt(sub))); //szep.
			MyRunnable.getGame().getEntityAt(Integer.parseInt(sub)).myNotify();
		}
	}
	
	class touchActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("touch")) {
				cb.removeAllItems();
				ArrayList<String> players = new ArrayList<String>();
				ArrayList<AgensUsable> vs =  MyRunnable.getCurrentVir().getField().getVirologusok();
				for (AgensUsable a : vs) {
					Virologus v = (Virologus)a;
					players.add("v"+MyRunnable.getVirologusSzam(v));
				}
				for (String player : players)
					cb.addItem(player);
				MyRunnable.getGame().myNotify();
				MyRunnable.setSelected((Virologus)vs.get(0));
				vs.get(0).myNotify();
			}
			
		}
	}
	
	

}
