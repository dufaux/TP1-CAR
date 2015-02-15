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
	
	public FtpRequest(Socket client) {
		this.sock = client;
	}

	
	@Override
	public void run() {
		System.out.println("Thread run FtpRequest");
		try {
			buffwrit = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			buffread = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			EcrireMessage("220", "Connexion établie");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		processRequest();
	}


	//effectuant des traitements généraux concernant une 
	//requête entrante et déléguant le traitement des commandes.
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
	
	
	public void fermeConnexion(){
		try {
			buffwrit.close();
			sock.close();
			EcrireLog("Socket client fermé");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void CreeUser(String utilisateur){
		this.user = utilisateur;
	}
	
	public String LireLigne(){
		try {
			return this.buffread.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void EcrireMessage(String code, String message){
		try {
			buffwrit.write(code+" "+message+"\r\n");
			buffwrit.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void EcrireLog(String message){
		System.out.println("["+this.user+"] "+message);
	}
}