import javax.swing.*;
import java.sql.*;
import com.mysql.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.FlowLayout;

public class Login implements ActionListener {
	
	boolean save = false;

	protected JButton login;
	protected JTextField user;
	protected JPasswordField pass;
	protected JFrame startScreen;

	public Login() {
		login = new JButton();
		user = new JTextField();
		pass = new JPasswordField(10);
		startScreen = new JFrame("Welcome!");
		login.addActionListener(this);
		login.setText("Login");
		user.setColumns(10);
		startScreen.getContentPane().setBackground(new Color(135, 233, 144));
		startScreen.add(user);
		startScreen.add(pass);
		startScreen.add(login);
		startScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startScreen.setResizable(false);
		startScreen.setSize(400, 100);
		startScreen.setLocationRelativeTo(null);
		startScreen.setLayout(new FlowLayout());
		startScreen.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Accounts acc = new Accounts();

		if (acc.searchAccount(user.getText(), String.valueOf(pass.getPassword())) && user.getText().equals("roger")) {
			Receptioner x = new Receptioner();
			startScreen.setVisible(false);
			save = true;
		} 

		if (acc.searchAccount(user.getText(), String.valueOf(pass.getPassword())) && user.getText().equals("superadmin")) {
			Superadmin x = new Superadmin();
			startScreen.setVisible(false);
			save = true;
		} 
		
		if (acc.searchAccount(user.getText(), String.valueOf(pass.getPassword())) && user.getText().equals("asmed")) {
			Asistent x = new Asistent();
			startScreen.setVisible(false);
			save = true;
		}
		
		if (acc.searchAccount(user.getText(), String.valueOf(pass.getPassword())) && user.getText().equals("medic")) {
			Medic x = new Medic();
			startScreen.setVisible(false);
			save = true;
		}
		
		if (acc.searchAccount(user.getText(), String.valueOf(pass.getPassword())) && user.getText().equals("eichar")) {
			Hr x = new Hr();
			startScreen.setVisible(false);
			save = true;
		}
		
		if (acc.searchAccount(user.getText(), String.valueOf(pass.getPassword())) && user.getText().equals("economic")) {
			Contabil x = new Contabil();
			startScreen.setVisible(false);
			save = true;
		}
		
		if(!save) {
			JOptionPane.showMessageDialog(null, "Credentiale gresite!");
			user.setText("");
			pass.setText("");
		}
	}
}