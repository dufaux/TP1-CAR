package commandes;

import java.io.File;

import serveur.FtpRequest;
import serveur.FileAdministrator;
/**
 * delete the selected file
 * @author julien
 *
 */
public class CommandeDele extends Commande {

	private FileAdministrator fileAdmin;
	
	/**
	 * constructor CommandeDele
	 * @param requete : the requete received
	 * @param gest : the gestionnaire
	 * @param ligne : the line received
	 */
	public CommandeDele(FtpRequest requete, FileAdministrator gest, String ligne) {
		super(requete, ligne);
		this.fileAdmin = gest;
	}

	/**
	 * delete the selected file in the directory
	 */
	public void lance() {
		
		String fileName = "."+this.fileAdmin.getDirectory()+"/"+laLigne.substring(5);
		File myFile = new File (fileName);
		
		if(myFile.delete()){
			this.laRequete.ecrireMessage("250", "Fichier "+fileName+" supprimé");
			this.laRequete.ecrireLog("Fichier "+fileName+" supprimé");
		}else{
			this.laRequete.ecrireMessage("450", "Error, Fichier non supprimé");
			this.laRequete.ecrireLog("Identification");
		}
	}

}
