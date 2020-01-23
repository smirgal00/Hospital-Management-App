
public class Accounts {
	private String[] users = new String[10];
	private String[] passwords = new String[10];
	
	public Accounts() {
		for(int i = 0; i < users.length; i++) {
			users[i] = "";
		}
		users[0] = "roger";
		passwords[0] = "receptioner";
		
		users[1] = "asmed";
		passwords[1] = "asistent";
		
		users[2] = "medic";
		passwords[2] = "doctor";
		
		users[3] = "superadmin";
		passwords[3] = "super";
		
		users[4] = "economic";
		passwords[4] = "contabil";
		
		users[5] = "eichar";
		passwords[5] = "om";
	}
	
	public String getUser(int x) {
		return users[x];
	}
	
	public String getPassword(int x) {
		return passwords[x];
	}
	
	public boolean searchAccount(String user, String pass) {
		for(int i = 0; i < users.length && !users[i].equals(""); i++) { 
			if(users[i].equals(user) && passwords[i].equals(pass)) {
				return true;
			}
		}
		return false;
	}
}
