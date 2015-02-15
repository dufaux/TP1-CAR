package commandes;

import serveur.FtpRequest;

public class CommandeInconnue extends Commande {


	public CommandeInconnue(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	@Override
	public void lance() {
		this.laRequete.EcrireMessage("202"," Commande Non implementee");
		this.laRequete.EcrireLog("Commande Non implementee");
	}
}
