import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Medic {
	private JFrame window;
	private JButton[] but = new JButton[3];
	
	Medic() {
		setButtons();
		setWindow();
	}
	
	private void setButtons() {
		for(int i = 0; i < but.length; i++) {
			but[i] = new JButton();
			but[i].setBackground(new Color(135, 233, 144));
			but[i].setForeground(Color.black);
			but[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		but[0].setText("Parafa");
		but[1].setText("Vizualizare Istoric");
		but[2].setText("Log Out");
		addListeners();
	}
	
	private void setWindow() {
		window = new JFrame("Medic");
		for(JButton x : but) {
			window.add(x);
		}
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.getContentPane().requestFocusInWindow();
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.setSize(600, 480);
		window.setLayout(new GridLayout(but.length, 0));
	}
	
	private void addListeners() {
		but[0].addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent e) {
				MedicRec x = new MedicRec(1);
			}
		});
		
		but[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicRec x = new MedicRec(2);
			}
		});
	}
	
}
