package commandes;

import serveur.FtpRequest;

public class CommandeQuit extends Commande {
	
	public CommandeQuit(FtpRequest requete, String ligne){
		super(requete, ligne);
	}

	@Override
	public void lance() {
		this.laRequete.fermeConnexion();
		this.laRequete.EcrireMessage("221"," Deconnexion");
		this.laRequete.EcrireLog("Deconnexion");
	}

}
