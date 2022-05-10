package whut;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonListContainer extends JPanel {
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	public ButtonListContainer() {
		this.setLayout(new FlowLayout());
	}
	
	public void addButton(String buttonT) {
		JButton tmp = new JButton();
		tmp.setText(buttonT);
		tmp.setActionCommand(buttonT);
		
		buttons.add(tmp);
	}
	
	public void draw() {
		this.removeAll();
		for (JButton b : buttons) {
			this.add(b);
		}
	}
	
	class ButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent ae) {
			if (ae.getActionCommand().equals("Save")) {}
			if (ae.getActionCommand().equals("New Game")) {}
			if (ae.getActionCommand().equals("FinishTurn")) {}
		}
	}

}
