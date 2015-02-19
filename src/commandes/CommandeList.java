package commandes;

import java.nio.charset.Charset;

import serveur.FtpRequest;
import serveur.FileAdministrator;

/**
 * display the file list 
 * @author julien
 *
 */
public class CommandeList extends Commande {
	
	private FileAdministrator fileAdmin;
	
	/**
	 * constructor CommandeList
	 * @param requete : the requete received
	 * @param gest : the gestionnaire
	 * @param ligne : the line received
	 */
	public CommandeList(FtpRequest requete, FileAdministrator gest, String ligne){
		super(requete, ligne);
		this.fileAdmin = gest;
	}

	/**
	 * display the file list in the curent directory
	 * 
	 */
	public void lance() {
		
		    
		    String liste = this.fileAdmin.lireListeDirectory();
		    
			this.laRequete.ecrireLog("Liste demandé : ");
			this.laRequete.ecrireMessage("150", "Liste en cours "+this.fileAdmin.getDirectory());
			this.laRequete.ecrireData(liste.getBytes(Charset.forName("UTF-8")));
			this.laRequete.ecrireMessage("226", "Liste envoyée");
			this.laRequete.fermeDataSocket();
			this.laRequete.ecrireLog("Liste envoyé : ");
	}

}
