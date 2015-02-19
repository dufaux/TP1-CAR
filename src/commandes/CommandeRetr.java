package commandes;

import serveur.FtpRequest;
import serveur.FileAdministrator;
/**
 * pull a file from the local directory to the remote directory
 * @author julien
 *
 */
public class CommandeRetr extends Commande {
	
	private FileAdministrator fileAdmin;
	
	/**
	 * constructor CommandeRetr
	 * @param requete : the requet received
	 * @param gest : the gestionnaire
	 * @param ligne : the line received
	 */
	public CommandeRetr(FtpRequest requete, FileAdministrator gest, String ligne){
		super(requete, ligne);
		this.fileAdmin = gest;
	}

	/**
	 * allow to pull a file
	 * and return a message 
	 */
	public void lance() {
		
		String fileName = "."+this.fileAdmin.getDirectory()+"/"+laLigne.substring(5);
		
        byte[] myByteArray = this.fileAdmin.LireFichierLocal(fileName);
		
		
		this.laRequete.ecrireLog("Fichier demandé : "+fileName);
		this.laRequete.ecrireMessage("150", "Reception de "+fileName+" en cours");
        this.laRequete.ecrireData(myByteArray);
		this.laRequete.ecrireMessage("226", fileName+" envoyé");
		this.laRequete.fermeDataSocket();
		this.laRequete.ecrireLog(fileName+" envoyé : ");
	}

}
