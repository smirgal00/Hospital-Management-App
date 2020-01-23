
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Superadmin {
	protected JFrame window;
	private JButton[] roles = new JButton[5];
	
	Superadmin() {
		window = new JFrame("Superadmin");
		
		for(int i = 0; i < roles.length; i++) {
			roles[i] = new JButton();
			roles[i].setBackground(new Color(135, 233, 144));
			roles[i].setForeground(Color.black);
			window.add(roles[i]);
		}
		
		roles[0].setText("Receptioner");
		roles[1].setText("Medic");
		roles[2].setText("Asistent");
		roles[3].setText("Contabil");
		roles[4].setText("HR");
		
		window.setVisible(true);
		window.setSize(600, 480);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setLayout(new GridLayout(5, 0));
		
		setButtons();
	}

	private void setButtons() {
		roles[0].addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent e) {
				Receptioner x = new Receptioner();
			}
		});
		
		roles[3].addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent e) {
				Contabil x = new Contabil();
			}
		});
		
		roles[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Asistent x = new Asistent();
			}
		});
		
		roles[1].addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent e) {
				Medic x = new Medic();
			}
		});
		
		roles[4].addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent e) {
				Hr x = new Hr();
			}
		});
	}
	
}

/**
 * modificare conturi
 * 
**/