package whut;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MoveContainer extends JPanel {
	private JLabel label = new JLabel("Move to: ");
	private JComboBox<String> cb = new JComboBox<String>();
	private JButton button = new JButton();
	
	public MoveContainer(ArrayList<String> where) {
		for (String field : where)
			cb.addItem(field);
		button.setText("Move");
		button.setActionCommand("move");
		this.setLayout(new FlowLayout());
	}
	
	public void draw(){
		this.removeAll();
		this.add(label);
		this.add(cb);
		this.add(button);
	}
	
	class moveActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("move")) {
				
			}
		}
	}

}
