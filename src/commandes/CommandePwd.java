package commandes;

import serveur.FtpRequest;
import serveur.GestionnaireFichier;

/**
 * display the path of current Directory
 * @author julien
 *
 */
public class CommandePwd extends Commande {

	private GestionnaireFichier gestionnaire;
	
	/**
	 * Constructor CommandePwd
	 * @param requete : the requete received
	 * @param gest : the gestionnaireFichier
	 * @param ligne : the line received
	 */
	public CommandePwd(FtpRequest requete, GestionnaireFichier gest, String ligne) {
		super(requete, ligne);
		this.gestionnaire = gest;
	}

	/**
	 * return the path of current Directory
	 */
	public void lance() {
		
		this.laRequete.ecrireMessage("257","\""+this.gestionnaire.getDirectory()+"\"");
		this.laRequete.ecrireLog("PWD "+this.gestionnaire.getDirectory());
	}

}
