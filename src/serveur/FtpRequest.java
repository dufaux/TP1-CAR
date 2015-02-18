package serveur;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import commandes.Commande;
import commandes.CommandeFactory;

public class FtpRequest implements Runnable{

	private Socket sock;
	private GestionnaireFichier gestionnaire;
	private User theUser;
	
	private BufferedWriter buffwrit;
	private BufferedReader buffread;
	
	private Socket datasock;
	private OutputStream dataOutputStream;
	private InputStream dataInputStream;
	/**
	 * Constructor
	 * @param client : the socket used
	 */
	public FtpRequest(Socket client, GestionnaireFichier gest, BufferedWriter bw, BufferedReader br) {
		this.sock = client;
		this.gestionnaire = gest;

		this.buffwrit = bw;
		this.buffread = br;
	}

	
	/**
	 * run the processRequest. 
	 */
	public void run() {
		ecrireMessage("220", "Connexion établie");
		ecrireLog("Connexion établie");
		processRequest();
	}


	/**
	 * read incoming command while the socket is open.
	 */
	public void processRequest(){
		
		while(!sock.isClosed()){
			
			String ligneCommande = lireLigne();
			
			if(ligneCommande != null){
				//this.EcrireLog("Commande : "+ligneCommande);
				Commande laCommande = CommandeFactory.CreeUneCommande(this, this.gestionnaire, ligneCommande);
				laCommande.lance();
			}
		}
		
		ecrireLog("Socket client fermé");
	}
	
	/**
	 * close the socket
	 */
	public void fermeConnexion(){
		try {
			buffwrit.close();
			sock.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * set user variable
	 * @param user : the objet user
	 */
	public void definiUser(User user){
		this.theUser = user;
	}
	
	/**
	 * set authentifie with true.
	 */
	public void authentifieUser(){
		if(this.theUser != null){
			this.theUser.authentifie();
		}
	}
	
	
	
	/**
	 * call readLine() from the bufferedReader
	 * @return the new line read
	 */
	public String lireLigne(){
		try {
			return this.buffread.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * write on the socket the message with the return code before.
	 * @param code : code 
	 * @param message : the text send
	 */
	public void ecrireMessage(String code, String message){
		try {
			buffwrit.write(code+" "+message+"\r\n");
			buffwrit.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void ouvreDataSocket(int port, String adresse) throws IOException{
		this.datasock = new Socket(adresse, port);
		this.dataInputStream = datasock.getInputStream();
		this.dataOutputStream = datasock.getOutputStream();
	}
	
	public void fermeDataSocket(){
			try {
				this.dataInputStream.close();
				this.dataOutputStream.close();
				this.datasock.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	}
	
	
	public void ecrireData(byte[] donnees){
		try {
			this.dataOutputStream.write(donnees);
			this.dataOutputStream.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public byte[] lireData(BufferedOutputStream bos){
		
		int count;
		byte[] fileInByte = new byte[1024];
		try {
			while ((count = this.dataInputStream.read(fileInByte)) > 0) {
		        bos.write(fileInByte, 0, count);
		    }

		} catch (SocketException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return fileInByte;
		
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