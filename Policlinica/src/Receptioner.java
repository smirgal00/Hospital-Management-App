import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class Receptioner {
	private JFrame window;
	private JButton newProgramare;
	private JButton newPacient;
	private JButton newBon;
	private JButton logOut;
	private JButton gdpr;
	
	Receptioner() {
		window = new JFrame("Receptioner");
		newProgramare = new JButton();
		newPacient = new JButton();
		newBon = new JButton();
		logOut = new JButton();
		setButtons();
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.add(newProgramare);
		window.add(newPacient);
		window.add(newBon);
		window.add(logOut);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setResizable(false);
		window.setSize(600, 480);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setLayout(new GridLayout(4, 0));
		window.setVisible(true);
	}
	
	private void setButtons() {
		newProgramare.setText("Adauga programare");
		newPacient.setText("Adauga pacient");
		newBon.setText("Emitere bon");
		logOut.setText("Log Out");
		newProgramare.setBackground(new Color(135, 233, 144));
		newBon.setBackground(new Color(135, 233, 144));
		newPacient.setBackground(new Color(135, 233, 144));
		logOut.setBackground(new Color(135, 233, 144));
		newBon.setForeground(Color.black);
		newPacient.setForeground(Color.black);
		newProgramare.setForeground(Color.black);
		logOut.setForeground(Color.black);
		
		alProg();
		alPacient();
		alBon();
	}
	
	private void alProg() {
		newProgramare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActiuniRec x = new ActiuniRec(0);
			}
		});
	}
	
	private void alPacient() {
		newPacient.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				ActiuniRec x = new ActiuniRec(1);
			}
		});
	}
	
	private void alBon() {
		newBon.addActionListener(new ActionListener() {
			@Override 
			public void actionPerformed(ActionEvent e) {
				ActiuniRec x = new ActiuniRec(2);
			}
		});
	}
}
