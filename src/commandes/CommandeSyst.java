package commandes;

import serveur.FtpRequest;

/**
 * 
 * @author mallet
 *
 */
public class CommandeSyst extends Commande{

	/**
	 * constructor CommandeSyst
	 * @param requete : the requete received
	 * @param ligne : the line received
	 */
	public CommandeSyst(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	/**
	 * return the name System UNIX
	 */
	public void lance() {
		this.laRequete.ecrireMessage("215","UNIX Type: L8");
		this.laRequete.ecrireLog("Syt UNIX Type: L8");
	}

}
