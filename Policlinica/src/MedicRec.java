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


public class MedicRec implements Mysql {
	private JFrame window;
	private JTextField dec;
	private JCheckBox parafa;
	private JComboBox<String> pac;
	private JButton add;
	private JTextField[] info = new JTextField[8];
	private JComboBox<String> nume;

	MedicRec(int opt) {
		switch (opt) {
		case 1:
			closeRaport();
			break;
		case 2:
			vizIstoric();
			break;
		case 3:
			vizDP();
			break;
		}
	}

	private void closeRaport() {
		window = new JFrame("Completare Raport");
		pac = new JComboBox<String>();
		addCombo();
		add = new JButton();
		add.setText("Adauga");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				callRap();
			}

		});
		dec = new JTextField(10);
		dec.setText("Decizie");
		window.getContentPane().setBackground(new Color(135, 233, 144));
		parafa = new JCheckBox();
		dec.addFocusListener(new FocusListener() {

			String ph;

			@Override
			public void focusGained(FocusEvent e) {
				ph = dec.getText();
				dec.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (dec.getText().equals("")) {
					dec.setText(ph);
				}
			}

		});
		window.add(pac);
		window.add(dec);
		window.add(parafa);
		window.add(add);
		window.setVisible(true);
		window.setSize(300, 100);
		window.setResizable(false);
		window.getContentPane().requestFocusInWindow();
		window.setLocationRelativeTo(null);
		window.getContentPane().requestFocusInWindow();
		window.setLayout(new FlowLayout(3));
	}

	private void addCombo() {
		ResultSet res = func.vizProg();
		try {
			while (res.next()) {
				pac.addItem(res.getString("idProgramare"));
			}
		} catch (SQLException e) {
			System.out.println("caca");
		}
	}

	private void callRap() {
		if (parafa.isSelected()) {
			func.parafa(Integer.valueOf(pac.getSelectedItem().toString()), dec.getText(), "true");
		} else {
			func.parafa(Integer.valueOf(pac.getSelectedItem().toString()), dec.getText(), "false");
		}

	}

	private void vizIstoric() {
		window = new JFrame("Vizualizare Istoric");
		nume = new JComboBox<String>();
		add = new JButton("Next");
		ResultSet res = func.getNume();
		window.add(nume);
		nume.addItem("Selectati pacientul");
		try {
			while (res.next()) {
				nume.addItem(res.getString("nume") + " " + res.getString("prenume"));
			}
		} catch (SQLException e) {
			System.out.println("nu se baga");
		}

		for (int i = 0; i < info.length; i++) {
			info[i] = new JTextField();
			info[i].setEditable(false);
			info[i].setForeground(Color.black);
			info[i].setBackground(new Color(135, 233, 144));
			window.add(info[i]);
		}
		
		window.add(add);
		nume.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(!nume.getSelectedItem().toString().equals("Selectati pacientul")) {
					if(e.getStateChange() == ItemEvent.SELECTED) {
						String x = nume.getSelectedItem().toString();
						final ResultSet resx = func.getIstoric(x.split(" ")[0], x.split(" ")[1]);
						add.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									resx.next();
								} catch (SQLException e1) {
									System.out.println("pula");
								}
								fillInfo(resx);
							}
							
						});
					}
				}
			}
		});

		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.setVisible(true);
		window.setSize(600, 480);
		window.setResizable(false);
		window.getContentPane().requestFocusInWindow();
		window.setLocationRelativeTo(null);
		window.setLayout(new GridLayout(10, 0));

	}
	
	private void fillInfo(ResultSet res) {
		try {
			ResultSetMetaData rsmd = res.getMetaData();
			int len = 0;
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				info[len++].setText(rsmd.getColumnName(i) + ": " + res.getString(i));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu se poate prelua isotricul");
		}
	}
	
	private void next(ResultSet res) {
		try {
			res.next();
		} catch (SQLException e) {
			System.out.println("pula");
		}
	}

	private void vizDP() {

	}
}
