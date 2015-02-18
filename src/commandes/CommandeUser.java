package commandes;

import serveur.FtpRequest;
import serveur.User;

public class CommandeUser extends Commande {
	
	public CommandeUser(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	public void lance() {
		String username = laLigne.substring(5);
		this.laRequete.definiUser(new User(username));
		this.laRequete.ecrireMessage("331", "Nom correct");
		this.laRequete.ecrireLog("Identification");
	}
}
