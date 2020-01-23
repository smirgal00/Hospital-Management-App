import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActiuniRec implements Mysql {
	JFrame window;
	JTextField[] info = new JTextField[10];
	JButton adauga;
	JComboBox<String> id;

	public ActiuniRec(int option) {
		switch (option) {
		case 0:
			id = new JComboBox<String>();
			try {
				setCombo();
			} catch (SQLException e1) {
				System.out.println("N-a gasit numele");
			}
			adauga = new JButton("Adauga");
			window = new JFrame();
			window.getContentPane().setBackground(new Color(135, 233, 144));
			window.add(id);
			for (int i = 0; i < 8; i++) {
				info[i] = new JTextField(10);
				final int temp = i;
				info[i].addFocusListener(new FocusListener() {

					String ph;

					@Override
					public void focusGained(FocusEvent e) {
						ph = info[temp].getText();
						placeHolder(temp, 3);
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
			placeHolder(0, option);
			adauga.addActionListener(new ActionListener() {
				
				@Override 
				public void actionPerformed(ActionEvent e) {
					addProg();
				}
			});
			window.add(adauga);
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			window.setSize(600, 100);
			window.setLocationRelativeTo(null);
			window.setLayout(new FlowLayout());
			window.pack();
			window.getContentPane().requestFocusInWindow();
			window.setVisible(true);
			break;
		case 1:
			adauga = new JButton("Adauga");
			adauga.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					addPacient();
				}
			});
			window = new JFrame();
			window.getContentPane().setBackground(new Color(135, 233, 144));
			for (int i = 0; i < 7; i++) {
				info[i] = new JTextField(10);
				final int temp = i;
				info[i].addFocusListener(new FocusListener() {

					String ph;

					@Override
					public void focusGained(FocusEvent e) {
						ph = info[temp].getText();
						placeHolder(temp, 3);
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
			placeHolder(0, option);
			window.add(adauga);
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			window.setSize(600, 100);
			// adauga.requestFocusInWindow();
			window.setLocationRelativeTo(null);
			window.setLayout(new FlowLayout());
			window.setVisible(true);
			break;

		case 2:
			adauga = new JButton("Adauga");
			adauga.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					generateBon();
				}
			});
			window = new JFrame();
			window.getContentPane().setBackground(new Color(135, 233, 144));
			for (int i = 0; i < 4; i++) {
				info[i] = new JTextField(10);
				final int temp = i;
				info[i].addFocusListener(new FocusListener() {

					String ph;

					@Override
					public void focusGained(FocusEvent e) {
						ph = info[temp].getText();
						placeHolder(temp, 3);
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
			placeHolder(0, option);
			window.add(adauga);
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			window.setSize(600, 100);
			// adauga.requestFocusInWindow();
			window.setLocationRelativeTo(null);
			window.setLayout(new FlowLayout());
			window.setVisible(true);
			break;
		}
	}

	private void placeHolder(int i, int option) {
		switch (option) {
		case 0:
			info[0].setText("ID Pacient");
			info[0].setEditable(false);
			info[1].setText("ID Receptioner");
			info[2].setText("CNP Pacient");
			info[2].setEditable(false);
			info[3].setText("Data Programare");
			info[4].setText("ID Serviciu");
			info[5].setText("ID Medic");
			info[6].setText("Durata");
			info[7].setText("ID Spital");
			break;

		case 1:
			info[0].setText("Nume");
			info[1].setText("Prenume");
			info[2].setText("Adresa");
			info[3].setText("Oras");
			info[4].setText("Sex");
			info[5].setText("Grupa");
			info[6].setText("CNP");
			break;

		case 2:
			info[0].setText("Data Emitere");
			info[1].setText("Servicii");
			info[2].setText("ID Receptioner");
			info[3].setText("Pret");
			break;

		case 3:
			info[i].setText("");
			break;
		}

	}

	private void setCombo() throws SQLException {
		ResultSet res = func.vizPac();
		id.addItem("Nr. Pacient");
		while (res.next()) {
			id.addItem(res.getString("idPacient"));
		}

		id.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (!id.getSelectedItem().toString().equals("Nr. Pacient")) {
					try {
						fillKnown(Integer.valueOf(id.getSelectedItem().toString()));
					} catch (NumberFormatException e1) {
						System.out.println("nfe");
					} catch (SQLException e1) {
						System.out.println("sqle");
					}
				}
			}
		});
	}

	private void fillKnown(int id) throws SQLException {
		ResultSet res = func.vizPac(id);
		res.next();
		info[0].setText(res.getString("idPacient"));
		info[2].setText(res.getString("CNP"));
	}

	private void addProg() {
		func.adaugareProgramare(new String[] { getText(0), getText(1), getText(2), getText(3), getText(4), getText(5),
				getText(6), getText(7)});
	}

	private void addPacient() {
		func.adaugaPacientProc(
				new String[] { getText(0), getText(1), getText(2), getText(3), getText(4), getText(5), getText(6)});
	}

	private void generateBon() {
		func.emitereBon(new String[] { getText(0), getText(1), getText(2), getText(3)});

	}

	private String getText(int i) {
		return info[i].getText();
	}

}
