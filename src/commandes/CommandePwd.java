package commandes;

import serveur.FtpRequest;
import serveur.FileAdministrator;

/**
 * display the path of current Directory
 * @author julien
 *
 */
public class CommandePwd extends Commande {

	private FileAdministrator fileAdmin;
	
	/**
	 * Constructor CommandePwd
	 * @param requete : the requete received
	 * @param gest : the gestionnaireFichier
	 * @param ligne : the line received
	 */
	public CommandePwd(FtpRequest requete, FileAdministrator gest, String ligne) {
		super(requete, ligne);
		this.fileAdmin = gest;
	}

	/**
	 * return the path of current Directory
	 */
	public void lance() {
		
		this.laRequete.ecrireMessage("257","\""+this.fileAdmin.getDirectory()+"\"");
		this.laRequete.ecrireLog("PWD "+this.fileAdmin.getDirectory());
	}

}
