package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import commandes.Commande;
import commandes.CommandeFactory;

public class FtpRequest implements Runnable{

	private Socket sock;
	private BufferedWriter buffwrit;
	private BufferedReader buffread;
	private String user;
	
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
		EcrireMessage("220", "Connexion établie");
		EcrireLog("Connexion établie");
		processRequest();
	}


	/**
	 * boucle sur la socket ouverte pour lire la ligne et executer les commandes.
	 */
	public void processRequest(){
		
		while(!sock.isClosed()){
			
			String ligneCommande = LireLigne();
			
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
			EcrireLog("Socket client fermé");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * défini la variable user
	 * @param utilisateur : le nom de l'utilisateur
	 */
	public void CreeUser(String utilisateur){
		this.user = utilisateur;
	}
	
	/**
	 * appel readLine() du bufferedReader
	 * @return la ligne
	 */
	public String LireLigne(){
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
	public void EcrireMessage(String code, String message){
		try {
			buffwrit.write(code+" "+message+"\r\n");
			buffwrit.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Ecrit sur le log (ici le s.o.p) le messsage précédé du nom d'utilisateur
	 * @param message : message à ecrire
	 */
	public void EcrireLog(String message){
		System.out.println("["+this.user+"] "+message);
	}
}