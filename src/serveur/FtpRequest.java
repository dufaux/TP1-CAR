package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import commandes.Commande;
import commandes.CommandeFactory;

public class FtpRequest implements Runnable{

	private Socket sock;
	private BufferedWriter buffwrit;
	private BufferedReader buffread;
	private User theUser;
	
	private Socket datasock;
	private OutputStream dataOutputStream;
	private InputStream dataInputStream;
	/**
	 * Constructeur
	 * @param client : la socket concernée
	 */
	public FtpRequest(Socket client) {
		this.sock = client;
		
		try {
			buffwrit = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			buffread = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

	
	/**
	 * lance le processRequest. 
	 */
	public void run() {
		ecrireMessage("220", "Connexion établie");
		ecrireLog("Connexion établie");
		processRequest();
	}


	/**
	 * boucle sur la socket ouverte pour lire la ligne et executer les commandes.
	 */
	public void processRequest(){
		
		while(!sock.isClosed()){
			
			String ligneCommande = lireLigne();
			
			if(ligneCommande != null){
				//this.EcrireLog("Commande : "+ligneCommande);
				Commande laCommande = CommandeFactory.CreeUneCommande(this, ligneCommande);
				laCommande.lance();
			}
		}
		
	}
	
	/**
	 * ferme la socket
	 */
	public void fermeConnexion(){
		try {
			buffwrit.close();
			sock.close();
			ecrireLog("Socket client fermé");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * défini la variable user
	 * @param utilisateur : le nom de l'utilisateur
	 */
	public void definiUser(User user){
		this.theUser = user;
	}
	
	/**
	 * met authentifié de l'utilisateur a true
	 */
	public void authentifieUser(){
		if(this.theUser != null){
			this.theUser.authentifie();
		}
	}
	
	/**
	 * retourne le repertoir de l'utilisateur
	 * @return the directory
	 */
	public String getDirectory(){
		return this.theUser.getDirectory();
	}
	
	public void setDirectory(String dir){
		this.theUser.setDirectory(dir);
	}
	
	/**
	 * appel readLine() du bufferedReader
	 * @return la ligne
	 */
	public String lireLigne(){
		try {
			return this.buffread.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Ecrit sur la socket le message précédé du code
	 * @param code : le code reponse en premier
	 * @param message : le message
	 */
	public void ecrireMessage(String code, String message){
		try {
			buffwrit.write(code+" "+message+"\r\n");
			buffwrit.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void ouvreDataSocket(int port, String adresse){
		try {
			this.datasock = new Socket(adresse, port);
			this.dataInputStream = datasock.getInputStream();
			this.dataOutputStream = datasock.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fermeDataSocket(){
			try {
				this.dataInputStream.close();
				this.dataOutputStream.close();
				this.datasock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	public void ecrireData(byte[] donnees){
		try {
			this.dataOutputStream.write(donnees);
			this.dataOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * Ecrit sur le log (ici le s.o.p) le messsage précédé du nom d'utilisateur
	 * @param message : message à ecrire
	 */
	public void ecrireLog(String message){
		String nom = null;
		if(this.theUser != null){
			nom = this.theUser.getName();
		}
		System.out.println("["+nom+"] "+message);
	}
}