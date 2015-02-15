package commandes;

import serveur.FtpRequest;

public class CommandeUser extends Commande {
	
	public CommandeUser(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	public void lance() {
		String user = laLigne.substring(5);
		this.laRequete.CreeUser(user);
		this.laRequete.EcrireMessage("331", "Nom correct");
		this.laRequete.EcrireLog("Identification");
	}
}
