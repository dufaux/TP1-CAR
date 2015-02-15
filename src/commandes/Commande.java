package commandes;

import serveur.FtpRequest;

public abstract class Commande {

	protected FtpRequest laRequete;
	protected String laLigne;

	/**
	 * Constructeur de Commande
	 * @param requete : la FtpRequest lié à la commande
	 * @param ligne : la ligne de message reçue par le client
	 */
	public Commande(FtpRequest requete, String ligne){
		this.laRequete = requete;
		this.laLigne = ligne;
	}
	
	/**
	 * Execute la commande
	 */
	public abstract void lance();
}
