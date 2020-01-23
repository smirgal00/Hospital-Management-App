import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Asistent{

	private JFrame window;
	private JButton[] but = new JButton[3];

	Asistent() {

		window = new JFrame("Asistent");

		for (int i = 0; i < but.length; i++) {
			but[i] = new JButton();
			window.add(but[i]);
		}

		setButtons();

		window.setVisible(true);
		window.setResizable(false);
		window.setSize(600, 480);
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.requestFocusInWindow(null);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.setLayout(new GridLayout(but.length, 0));
		
	}

	public void setButtons() {
		
		but[2].setText("Log Out");
		but[2].setBackground(new Color(135, 233, 144));
		but[2].setForeground(Color.black);

		but[1].setText("Completare Raport");
		but[1].setBackground(new Color(135, 233, 144));
		but[1].setForeground(Color.black);

		but[0].setText("Vizualizare Pacienti");
		but[0].setBackground(new Color(135, 233, 144));
		but[0].setForeground(Color.black);

		but[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				multi(0);
			}
		});
		
		but[1].addActionListener(new ActionListener() {
			
			@Override 
			public void actionPerformed(ActionEvent e) {
				multi(1);
			}
		});
	}


	private void multi(int i) {
		AsistentRec x = new AsistentRec(i);
	}

}
