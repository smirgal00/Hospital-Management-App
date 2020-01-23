import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.*;

public class HrRec implements Mysql {
	private JFrame window;
	private JComboBox<String> id;
	
	
	HrRec(int cond) {
		switch(cond) {
		case 1:
			infoAng();
			break;
		case 2:
			insAng();
			break;
		case 3:
			concAng();
			break;
		}
	}
	
	private void infoAng() {
		JTextField[] info = new JTextField[4];
		id = new JComboBox<String>();
		id.setForeground(Color.black);
		id.setBackground(new Color(135, 233, 144));
		window = new JFrame("Informatii angajat");
		window.add(id);
		for(int i = 0; i < info.length; i++) {
			info[i] = new JTextField(10);
			info[i].setEditable(false);
			info[i].setForeground(Color.black);
			info[i].setBackground(new Color(135, 233, 144));
			window.add(info[i]);
		}
		
		ResultSet res = func.getAngajati();
		try {
			while(res.next()) {
				id.addItem(res.getString("id"));
			}
		} catch (SQLException e) {
			System.out.println("eroare angajati");
		}
		
		id.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					ResultSet resx = func.infoAngajati(Integer.valueOf(id.getSelectedItem().toString()));
					try {
						ResultSetMetaData md = resx.getMetaData();
						while(resx.next()) {
							int len = 0;
							for(int i = 2; i <= md.getColumnCount(); i++) {
								info[len++].setText(md.getColumnName(i) + ": " + resx.getString(i));
							}
						}
					} catch (SQLException e1) {
						
					}
				}
			}
		});
		
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.getContentPane().requestFocusInWindow();
		window.setLocationRelativeTo(null);
		window.setSize(640, 480);
		window.setResizable(false);
		window.setVisible(true);
		window.setLayout(new GridLayout(5, 0));
	}
	
	private void insAng() {
		JButton but = new JButton("Adauga");
		window = new JFrame("Inserare angajat");
		JTextField[] info = new JTextField[4];
		for(int i = 0; i < info.length; i++) {
			info[i] = new JTextField(10);
			final int temp = i;
			info[i].addFocusListener(new FocusListener() {

				String ph;

				@Override
				public void focusGained(FocusEvent e) {
					ph = info[temp].getText();
					info[temp].setText("");
				}

				@Override
				public void focusLost(FocusEvent e) {
					if (info[temp].getText().equals("")) {
						info[temp].setText(ph);
					}
				}

			});
			window.add(info[i]);
		}
		window.add(but);
		
		info[0].setText("Nume");
		info[1].setText("Prenume");
		info[2].setText("Functie");
		info[3].setText("Specializare");
		
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				func.insAngajat(new String[] {info[0].getText(), info[1].getText(), info[2].getText(), info[3].getText()});
			}
		});
		
		
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.getContentPane().requestFocusInWindow();
		window.setLocationRelativeTo(null);
		window.setSize(600, 100);
		window.setResizable(false);
		window.setVisible(true);
		window.setLayout(new FlowLayout(5));
	}
	
	private void concAng() {
		id = new JComboBox<String>();
		JButton but = new JButton("FIRE!");
		window = new JFrame("Concediere angajat");
		ResultSet res = func.getAngajati();
		try {
			while(res.next()) {
				id.addItem(res.getString("id"));
			}			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu s-au putut preua angajatii");
		}
		
		window.add(id);
		window.add(but);
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				func.concAngajat(Integer.valueOf(id.getSelectedItem().toString()));
			}
		});
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.getContentPane().requestFocusInWindow();
		window.setLocationRelativeTo(null);
		window.setSize(200, 100);
		window.setResizable(false);
		window.setVisible(true);
		window.setLayout(new FlowLayout());
	}
}
