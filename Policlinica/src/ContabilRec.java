import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
public class ContabilRec implements Mysql{
	
	private JFrame window;
	private JTextField tf;
	private JButton but;
	private JComboBox<String> spitale;
	private JComboBox<String> luni;
	
	ContabilRec(int opt){
		switch(opt) {
		case 0 : 
			adaugareSalar();
			break;
		case 1 :
			cautareSalar();
			break;
		case 2 :
			cautareProfit();
			break;
		case 3 :
			adaugareEconomii();
			break;
		}
	}

	private void adaugareEconomii() {
		window = new JFrame("Adaugare Economii");
		spitale = new JComboBox<String>();
		luni = new JComboBox<String>();
		luni.setForeground(Color.black);
		luni.setBackground(new Color(135, 233, 144));
		luni.addItem("Ianuarie");
		luni.addItem("Februarie");
		luni.addItem("Martie");
		luni.addItem("Aprilie");
		luni.addItem("Mai");
		luni.addItem("Iunie");
		luni.addItem("Iulie");
		luni.addItem("August");
		luni.addItem("Septembrie");
		luni.addItem("Octombrie");
		luni.addItem("Noiembrie");
		luni.addItem("Decembrie");
		but = new JButton();
		but.setText("Adauga");
		
		JTextField[] info = new JTextField[2];
		for(int i = 0; i < info.length; i++) {
			info[i] = new JTextField();
			info[i].setForeground(Color.black);
			info[i].setBackground(new Color(135, 233, 144));
			final int temp = i;
			info[i].addFocusListener(new FocusListener() {
				String ph;
				public void focusGained(FocusEvent e) {
					ph = info[temp].getText();
					info[temp].setText("");
				}
				
				public void focusLost(FocusEvent e) {
					if (info[temp].getText().equals("")) {
						info[temp].setText(ph);
					}
				}
			});
			window.add(info[i]);
		}
		info[0].setText("Venituri");
		info[1].setText("Cheltuieli");
		
		but.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				func.addEco(Integer.valueOf(spitale.getSelectedItem().toString()), luni.getSelectedItem().toString(), Integer.valueOf(info[0].getText()), Integer.valueOf(info[1].getText()));
			}
		});
		
		for(int i =1; i <= 10; i++) {
			spitale.addItem(""+i);
		}
		
		window.add(spitale);
		window.add(luni);
		window.add(but);
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.setVisible(true);
		window.setSize(600,300);
		window.setLayout(new GridLayout(5,0));
		
	}

	private void cautareProfit() {
		window= new JFrame("Profit Spital");
		but = new JButton("Cautare");
		tf = new JTextField();
		tf.setForeground(Color.black);
		tf.setBackground(new Color(135, 233, 144));
		tf.setEditable(false);
		spitale =  new JComboBox<String>();
		spitale.setForeground(Color.black);
		spitale.setBackground(new Color(135, 233, 144));
		spitale.addItem("Alegeti spitalul");
		
		for(int i = 1; i <= 10; i++) {
			spitale.addItem(String.valueOf(i));
		}
		
		spitale.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					ResultSet res = func.getProfit(Integer.valueOf(spitale.getSelectedItem().toString()));
					try {
						while(res.next()) {
							tf.setText(res.getString("Profit_spital"));
						}
					} catch (SQLException e1) {
	
					}
				}
			}
		});
		
		window.add(spitale);
		window.add(tf);
		window.setSize(300,150);
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.setResizable(false);
		window.setVisible(true);
		window.setLayout(new GridLayout(2,0));
		
	}

	private void cautareSalar() {
		window= new JFrame("Cautare salariu");
		but = new JButton("Cautare");
		tf = new JTextField();
		tf.setForeground(Color.black);
		tf.setBackground(new Color(135, 233, 144));
		JTextField tr = new JTextField();
		tr.setEditable(false);
		tr.setForeground(Color.black);
		tr.setBackground(new Color(135, 233, 144));
		tf.setEditable(false);
		spitale =  new JComboBox<String>();
		spitale.setForeground(Color.black);
		spitale.setBackground(new Color(135, 233, 144));
		spitale.addItem("Alegeti angajatul");
		
		ResultSet res = func.getAngajati();
		try {
			while(res.next()) {
				spitale.addItem(res.getString("id"));
			}
		} catch (SQLException e2) {
			
		}
		
		spitale.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					ResultSet res = func.getSalariu(Integer.valueOf(spitale.getSelectedItem().toString()));
					try {
						while(res.next()) {
							tf.setText("NET: " + res.getString("net"));
							tr.setText("BRUT: " + res.getString("brut"));
						}
					} catch (SQLException e1) {
	
					}
				}
			}
		});
		
		window.add(spitale);
		window.add(tr);
		window.add(tf);
		window.setSize(300,150);
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.setResizable(false);
		window.setVisible(true);
		window.setLayout(new GridLayout(3,0));
		
	}

	private void adaugareSalar() {
		window= new JFrame("Adaugare salariu");
		but = new JButton("Adauga");
		tf = new JTextField();
		tf.setEditable(true);
		tf.setForeground(Color.black);
		tf.setBackground(new Color(135, 233, 144));
		spitale =  new JComboBox<String>();
		spitale.setForeground(Color.black);
		spitale.setBackground(new Color(135, 233, 144));
		spitale.addItem("Alegeti angajatul");
		
		ResultSet res = func.getAngajati();
		try {
			while(res.next()) {
				spitale.addItem(res.getString("id"));
			}
		} catch (SQLException e2) {
			
		}
		
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				func.addSalariu(Integer.valueOf(spitale.getSelectedItem().toString()), Integer.valueOf(tf.getText()));
			}
			
		});
		
		window.add(spitale);
		window.add(tf);
		window.add(but);
		window.setSize(300,150);
		window.getContentPane().setBackground(new Color(135, 233, 144));
		window.setResizable(false);
		window.setVisible(true);
		window.setLayout(new GridLayout(3,0));
		
	}
	
}
