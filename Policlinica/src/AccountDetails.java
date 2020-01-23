import java.sql.*;

import javax.swing.JOptionPane;

import com.mysql.*;

public class AccountDetails {
	Connection connection = null;

	public AccountDetails() {
		connect();
	}

	private void connect() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/proiect?user=root&password=Lenesu2@");
			System.out.println("S-a conectat");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu se poate conecta la baza de date!");
		}
	}

	public void adaugaPacientProc(String[] info) {
		Statement call = null;
		try {
			call = connection.createStatement();
			call.execute("call Adauga_pacient('" + info[0] + "','" + info[1] + "','" + info[2] + "" + "','" + info[3]
					+ "','" + info[4] + "','" + info[5] + "','" + info[6] + "');");
			JOptionPane.showMessageDialog(null, "S-au inserat");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu s-au inserat");
		}
	}

	public void emitereBon(String[] info) {
		Statement call = null;
		try {
			call = connection.createStatement();
			call.execute(
					"call emitere_bon('" + info[0] + "', '" + info[1] + "', '" + info[2] + "', '" + info[3] + "');");
			JOptionPane.showMessageDialog(null, "S-a emis bonul");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu s-a emis bonul");
		}
	}

	public ResultSet vizPac() {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("call viz_pacienti();");
			res = call.getResultSet();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "A intervenit o eroare in preluarea pacientilor");
		}
		return res;
	}

	public ResultSet vizPac(int id) {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("call viz_pac_ex(" + id + ");");
			res = call.getResultSet();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu s-au putut prelua datele despre acest pacient");
		}
		return res;
	}

	public void adaugareProgramare(String[] info) {
		Statement call = null;
		try {
			call = connection.createStatement();
			call.execute("call Adauga_programare2('" + info[0] + "', '" + info[1] + "', '" + info[2] + "', '" + info[3]
					+ "', '" + info[4] + "', '" + info[5] + "', '" + info[6] + "', '" + info[7] + "');");
			JOptionPane.showMessageDialog(null, "Programarea a fost efectuata!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu s-a putut crea programarea");
		}
	}

	public void completareRaport(String x, String[] info) {
		Statement call = null;
		try {
			call = connection.createStatement();
			call.execute("call completare_raport('" + x + "', '" + info[0] + "', '" + info[1] + "');");
			JOptionPane.showMessageDialog(null, "S-a completat raportul!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu s-a putut completa raportul!");
		}
	}

	public ResultSet vizProg() {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("select idProgramare from programare;");
			res = call.getResultSet();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu s-au putut prelua programarile");
		}
		return res;
	}

	public void parafa(int x, String y, String bool) {
		Statement call = null;
		try {
			call = connection.createStatement();
			call.execute("call parafa(" + x + ", '" + y + "', " + bool + ");");
			JOptionPane.showMessageDialog(null, "Parafa a fost pusa");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu s-a putut adauga parafa");
		}
	}

	public ResultSet getNume() {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("select nume, prenume from pacient");
			res = call.getResultSet();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu s-au putut prelua pacientii");
		}
		return res;
	}

	public ResultSet getIstoric(String nume, String prenume) {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("call viz_istoric_pacient('" + nume + "', '" + prenume + "');");
			res = call.getResultSet();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu s-a putut prelua istoricul");
		}

		return res;
	}
	
	public ResultSet getAngajati() {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("select id from angajat");
			res = call.getResultSet();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu se pot prelua angajatii");
		}
		return res;
	}
	
	public ResultSet infoAngajati(int i) {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("call viz_angajat(" + i + ");");
			res = call.getResultSet();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu se pot prelua angajatii");
		}
		return res;
	}
	
	public void insAngajat(String[] info) {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("call ins_angajat('" + info[0] + "', '" + info[1] + "', '" + info[2] + "', '" + info[3] + "');");
			JOptionPane.showMessageDialog(null, "Angajatul a fost inserat");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu se poate insera angajatul");
		}
	}
	
	public void concAngajat(int i) {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("call delete_angajat(" + i + ");");
			JOptionPane.showMessageDialog(null, "Angajatul a fost concediat");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Nu se poate concedia angajatul");
		}
	}
	
	public void addEco(int id, String luna, float venit, float cheltuieli) {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("call adaugare_economie(" + id + ", '" + luna + "', " + venit + ", " + cheltuieli + ");");
			JOptionPane.showMessageDialog(null, "Informatiile au fost adaugate");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Informatiile nu au fost adaugate");
		}
	}
	
	public ResultSet getProfit(int i) {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("call cautare_profitSpital(" + i + ");");
			res = call.getResultSet();
			JOptionPane.showMessageDialog(null, "Profitul a fost preluat");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Profitul nu a putut fi preluat");
		}
		return res;
	}
	
	public ResultSet getSalariu(int i) {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("call cautare_salariu(" + i + ");");
			res = call.getResultSet();
			JOptionPane.showMessageDialog(null, "Salariul a fost preluat");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Salariul nu a putut fi preluat");
		}
		return res;
	}
	
	public void addSalariu(int id, int sal) {
		Statement call = null;
		ResultSet res = null;
		try {
			call = connection.createStatement();
			call.execute("call insert_salariu(" + id + ", " + sal + ");");
			res = call.getResultSet();
			JOptionPane.showMessageDialog(null, "Salariul a fost adaugat");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Salariul nu a putut fi adaugat");
		}
	}

}
