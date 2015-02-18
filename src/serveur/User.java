package serveur;

public class User {
	
	private String name;
	private boolean authentification;
	
	public User(String theName){
		this.name = theName;
		this.authentification = false;
	}
	
	public void authentifie(){
		this.authentification = true;
	}
	
	public boolean estAuthentifie(){
		return authentification;
	}
	
	public String getName(){
		return this.name;
	}

}
