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
	
	public TouchContainer(ArrayList<String> players) {
		for (String player : players)
			cb.addItem(player);
		button.setText("Touch");
		button.setActionCommand("touch");
		this.setLayout(new FlowLayout());
	}
	
	public void draw(){
		this.add(button);
		this.removeAll();
		this.add(label);
		this.add(cb);
	}
	
	class moveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("touch")) {
				
			}
		}
	}

}
