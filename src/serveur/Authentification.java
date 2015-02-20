package serveur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import exceptions.FichierUserException;
 /**
  * authantificate a user for check if the user exist
  * @author julien
  *
  */
public class Authentification {
	
	private String namefile;
	private HashMap<String, String> idConnexion;
	
	/**
	 * Constructor
	 * create the hashmap from a file.
	 * @param file : le chemin du fichier, the file path
	 */
	public Authentification(String file){
		this.namefile = file;
		this.idConnexion = new HashMap<String, String>();
		
		try
		{
			BufferedReader buff = new BufferedReader(new FileReader(file));
			
			String line;
			String name, pass;
			while((line = buff.readLine()) != null){
				String[] elems = line.split(",");
				try{
					name = elems[0];
					pass = elems[1];
					this.idConnexion.put(name, pass);
				}
				catch(Exception e){
					throw new FichierUserException(line+" Incorrect dans le fichier "+file);
				}
			}
				
		}
		catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * constructor used to test the server with only one identifier (to use without file).
	 * @param nom
	 * @param password
	 */
	public Authentification(String nom, String password){
		this.idConnexion = new HashMap<String, String>();
		this.idConnexion.put(nom,password);
	}
	
	/**
	 * check if the user exist
	 * @param user : the user name
	 */
	public boolean exist(String user){
		if(idConnexion.get(user) != null)
			return true;
		else
			return false;	
	}
	

	
	/**
	 * check if the password is correct
	 * @param user : the pseudo
	 * @param pass : this password
	 */
	public boolean passwordCorrect(String user, String pass){
		if(this.idConnexion.get(user).compareTo(pass) == 0)
			return true;
		else
			return false;
	}
	
}
