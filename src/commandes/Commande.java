package commandes;

import serveur.FtpRequest;
/**
 * abstract class commande 
 * @author julien
 *
 */
public abstract class Commande {

	protected FtpRequest laRequete;
	protected String laLigne;

	/**
	 * Constructor de Commande
	 * @param requete : the FtpRequest linked at the command
	 * @param ligne : the message line received by the client
	 */
	public Commande(FtpRequest requete, String ligne){
		this.laRequete = requete;
		this.laLigne = ligne;
	}
	
	/**
	 * Execut the command
	 * @throws Exception 
	 */
	public abstract void lance();
}
