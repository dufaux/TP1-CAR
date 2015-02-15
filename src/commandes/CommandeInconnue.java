package commandes;

import serveur.FtpRequest;

public class CommandeInconnue extends Commande {


	public CommandeInconnue(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	@Override
	public void lance() {
		this.laRequete.ecrireMessage("202"," Commande Non implementee"); //502 ou 202?
		this.laRequete.ecrireLog("Commande Non implementee");
	}
}
