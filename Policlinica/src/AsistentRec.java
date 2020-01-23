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

public class AsistentRec implements AsistentInt {

	private JFrame window;
	private JTextField[] info = new JTextField[8];
	private JComboBox<String> id;
	private JButton but;

	AsistentRec(int opt) {
		if (opt == 0) {
			setViz();
		} else if (opt == 1) {
			setAdd();
		}
	}

	private void setViz() {
		window = new JFrame("Vizualizare Pacienti");
		id = new JComboBox<String>();
		window.add(id);
		for (int i = 0; i < info.length; i++) {
			info[i] = new JTextField();
			info[i].setEditable(false);
			info[i].setForeground(Color.black);
			info[i].setBackground(new Color(135, 233, 144));
			window.add(info[i]);
		}
		id.addItem("Selectati numarul pacientului");
		setCombo();
		id.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (!id.getSelectedItem().toString().equals("Selectati numarul pacientului")) {
					vizPacienti();
				}
			}

		});
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setSize(600, 480);
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.requestFocusInWindow(null);
		window.setLayout(new GridLayout(9, 1));
		window.setVisible(true);

	}

	private void setAdd() {
		window = new JFrame("Completare Raport");
		window.getContentPane().setBackground(new Color(135, 233, 144));
		but = new JButton("Adauga");
		id = new JComboBox<String>();
		window.add(id);
		window.getContentPane().requestFocusInWindow();
		setCombo();
		
		for(int i = 0; i < 2; i++) {
			info[i] = new JTextField(10);
			window.add(info[i]);
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
		}
		info[0].setText("Durata");
		info[1].setText("Diagnostic");
		window.add(but);
		but.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				complRaport();
			}
		});
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setSize(600, 100);
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.requestFocusInWindow(null);
		window.setLayout(new FlowLayout());
		window.setVisible(true);
	}

	@Override
	public void complRaport() {
		func.completareRaport(id.getSelectedItem().toString(), new String[] {info[0].getText(), info[1].getText()});
	}

	@Override
	public void vizPacienti() {
		complInfo(Integer.valueOf(id.getSelectedItem().toString()));
	}

	private void setCombo() {
		ResultSet res = func.vizPac();
		ResultSetMetaData resMD = null;
		try {
			resMD = res.getMetaData();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "A intervenit o eroare in preluarea de metadata");
		}

		ResultSet temp = res;
		try {
			while (temp.next()) {
				id.addItem(res.getString("idPacient"));
			}
		} catch (SQLException e) {
			System.out.println("A intervenit o eroare in preluarea pacientului");
		}
	}

	private void complInfo(int id) {
		ResultSet res = func.vizPac(id);
		ResultSetMetaData resMD = null;
		try {
			if (res.next()) {
				try {
					resMD = res.getMetaData();
				} catch (SQLException e) {
					System.out.println("A intervenit o eroare in preluarea de metadata");
				}
			}
		} catch (SQLException e1) {

		}

		try {
			int len = 0;
			for (int i = 1; i <= resMD.getColumnCount(); i++) {
				info[len++].setText(resMD.getColumnName(i) + ": " + res.getString(i));
			}

		} catch (SQLException e) {
			System.out.println("Error 404");
		}

	}
}
