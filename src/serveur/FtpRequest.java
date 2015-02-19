package serveur;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import commandes.Commande;
import commandes.CommandeFactory;

/**
 * Class used to manage the ftp request through few administrator class.
 *
 */
public class FtpRequest implements Runnable{

	private FileAdministrator fileAdmin;
	private User theUser;
	private Authentification authentificator;
		
	private ConnectionSocketAdministrator connectSockAdmin;
	private DataSocketAdministrator dataSockAdmin;
	
	private ObjectCreator creator;
	
	/**
	 * Constructor
	 * @param client : the socket used
	 */
	public FtpRequest(ConnectionSocketAdministrator sockadmin, FileAdministrator gest, Authentification auth, DataSocketAdministrator datasockadmin, ObjectCreator creator) {
		this.connectSockAdmin = sockadmin;
		this.fileAdmin = gest;
		this.authentificator = auth;
		this.dataSockAdmin = datasockadmin;
		this.creator = creator;
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
		
		while(!connectSockAdmin.isClosed()){
			
			String ligneCommande = this.connectSockAdmin.readLine();
			
			if(ligneCommande != null){
				//this.EcrireLog("Commande : "+ligneCommande);
				Commande laCommande = CommandeFactory.CreeUneCommande(this, this.fileAdmin, this.authentificator, ligneCommande);
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
			connectSockAdmin.close();
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
	
	public User getUser(){
		return this.theUser;
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
	 * write on the socket the message with the return code before.
	 * @param code : code 
	 * @param message : the text send
	 */
	public void ecrireMessage(String code, String message){
		try {
			connectSockAdmin.write(code+" "+message+"\r\n");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * open a new socket used to send byte (for example list or file)
	 * @param port : port of client
	 * @param adresse : adress of client
	 * @throws IOException
	 */
	public void ouvreDataSocket(int port, String adresse) throws IOException{
		this.dataSockAdmin.initNew(port, adresse);
	}
	
	/**
	 * close the datasocket
	 */
	public void fermeDataSocket(){
		this.dataSockAdmin.close();
	}
	
	/**
	 * write on datasocket
	 * @param data writed on the socket
	 */
	public void ecrireData(byte[] data){
		this.dataSockAdmin.write(data);
	}
	
	/**
	 * read data on datasocket and write it on the bufferedoutputstream
	 * @param bos : buffer where data are writted
	 */
	public void lireData(BufferedOutputStream bos){
		this.dataSockAdmin.read(bos);
		
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


	/**
	 * open a serversocket and write to client the response
	 * @param epsv : boolean to know the response to write.
	 */
	public void passiveMode(boolean epsv) {			
		int i;
		ServerSocket srv;
		String separateur;
		try {
			srv = this.creator.createServerSocket(0);
			
			int port = srv.getLocalPort();
			String add = srv.getInetAddress().getLocalHost().getHostAddress();
			String retour = "";
			String[] elems = add.split("\\.");
			for(i=0;i<elems.length;i++){
				retour+=elems[i]+",";
			}
			retour+=String.valueOf(port/256);
			retour+=","+String.valueOf(port%256);
			
			if(epsv){
				this.ecrireMessage("229","Entering Extended Passive Mode (|||"+port+"|)");
				this.ecrireLog("EPSV passive mod enter "+retour);
			}else{
				this.ecrireMessage("227","Entering Passive Mode ("+retour+")");
				this.ecrireLog("PASV passive mod enter "+retour);
			}
			
			this.dataSockAdmin.initNewPassive(srv);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}