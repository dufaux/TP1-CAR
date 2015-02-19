package commandes;

import serveur.FtpRequest;
/**
 * allow to check if the command is implemented
 * @author julien
 *
 */
public class CommandeInconnue extends Commande {

/**
 * constructor CommandeInconnue
 * @param requete : the requete received
 * @param ligne : the line received
 */
	public CommandeInconnue(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	/**
	 * return a error, the command is not implemented
	 */
	public void lance() {
		this.laRequete.ecrireMessage("202"," Commande Non implementee"); //502 ou 202?
		this.laRequete.ecrireLog("Commande Non implementee");
	}
}
