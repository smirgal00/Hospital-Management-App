import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Hr {
	
	private JFrame window;
	private JButton[] but = new JButton[4];
	
	Hr() {
		setFrame();
		addListener();
	}
	
	private void setFrame() {
		window = new JFrame("HR");
		for(int i = 0; i < but.length; i++) {
			but[i] = new JButton();
			but[i].setBackground(new Color(135, 233, 144));
			but[i].setForeground(Color.black);
			window.add(but[i]);
		}
		but[0].setText("Vizualizare Informatii Angajat");
		but[1].setText("Inserare Angajat");
		but[2].setText("Concediere Angajat");
		but[3].setText("Log Out");
		
		window.getContentPane().requestFocusInWindow();
		window.setLocationRelativeTo(null);
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.setSize(600, 480);
		window.setResizable(false);
		window.setVisible(true);
		window.setLayout(new GridLayout(4, 0));
	}
	
	private void addListener() {
		but[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HrRec x = new HrRec(1);
			}
		});
		
		but[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HrRec x = new HrRec(2);
			}
		});
		
		but[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HrRec x = new HrRec(3);
			}
		});
		
		but[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
