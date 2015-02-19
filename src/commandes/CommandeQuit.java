package commandes;

import serveur.FtpRequest;
/**
 * allow to deconnexion 
 * @author julien
 *
 */
public class CommandeQuit extends Commande {
	
	/**
	 * constructor CommandeQuit
	 * @param requete : the requete received
	 * @param ligne : the line received
	 */
	public CommandeQuit(FtpRequest requete, String ligne){
		super(requete, ligne);
	}

	/**
	 * return a message and dexonnexxion of server
	 */
	public void lance() {
		this.laRequete.ecrireMessage("221"," Deconnexion");
		this.laRequete.fermeConnexion();
		this.laRequete.ecrireLog("Deconnexion");
	}

}
