package commandes;

import java.io.File;

import serveur.FtpRequest;
import serveur.GestionnaireFichier;
/**
 * delete the selected file
 * @author julien
 *
 */
public class CommandeDele extends Commande {

	private GestionnaireFichier gestionnaire;
	
	/**
	 * constructor CommandeDele
	 * @param requete : the requete received
	 * @param gest : the gestionnaire
	 * @param ligne : the line received
	 */
	public CommandeDele(FtpRequest requete, GestionnaireFichier gest, String ligne) {
		super(requete, ligne);
		this.gestionnaire = gest;
	}

	/**
	 * delete the selected file in the directory
	 */
	public void lance() {
		
		String fileName = "."+this.gestionnaire.getDirectory()+"/"+laLigne.substring(5);
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
