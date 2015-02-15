package commandes;

import serveur.FtpRequest;

public class CommandePass extends Commande {
	
	public CommandePass(FtpRequest requete, String ligne){
		super(requete, ligne);
	}

	@Override
	public void lance() {
		String password = this.laLigne.substring(5);
		this.laRequete.EcrireMessage("230", "Mot de passe OK");
		this.laRequete.EcrireLog("Mot de passe OK");
	}

}
