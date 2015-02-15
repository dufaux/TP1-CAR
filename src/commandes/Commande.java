package commandes;

import serveur.FtpRequest;

public abstract class Commande {

	protected FtpRequest laRequete;
	protected String laLigne;

	public Commande(FtpRequest requete, String ligne){
		this.laRequete = requete;
		this.laLigne = ligne;
	}
	
	public abstract void lance();
}
