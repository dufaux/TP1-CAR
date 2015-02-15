package serveur;

public class User {
	
	private String name;
	private String directory;
	private boolean authentification;
	
	public User(String theName, String theDirectory){
		this.name = theName;
		this.directory = theDirectory;
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

	public String getDirectory() {
		return this.directory;
	}
	
	public void setDirectory(String dir) {
		this.directory = dir;
	}
}
