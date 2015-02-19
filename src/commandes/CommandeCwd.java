package commandes;

import serveur.FtpRequest;
import serveur.FileAdministrator;

/**
 * allow to user changed directory 
 * @author julien
 *
 */
public class CommandeCwd extends Commande {

	private FileAdministrator fileAdmin;
	
	/**
	 * Constructor CommandeCwd
	 * @param requete : The requete received
	 * @param gest : the gestionnaire
	 * @param ligne : the line received
	 */
	public CommandeCwd(FtpRequest requete, FileAdministrator gest, String ligne) {
		super(requete, ligne);
		this.fileAdmin = gest;
	}

	/**
	 * change the directory 
	 */
	public void lance() {
		String dossier = laLigne.substring(4);
		String newdir;
		if(dossier.contains("/")){ //parfois envoie "sousdossier" parfois "/dossier/sousdossier"...?
			newdir = dossier;
		}
		else{
			newdir = this.fileAdmin.getDirectory()+"/"+dossier;
		}

		this.fileAdmin.setDirectory(newdir);
		this.laRequete.ecrireMessage("250","repertoire correctement changé");
		this.laRequete.ecrireLog("CWD "+this.fileAdmin.getDirectory());	
	}

}
