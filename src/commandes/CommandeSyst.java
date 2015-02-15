package commandes;

import serveur.FtpRequest;

public class CommandeSyst extends Commande{

	public CommandeSyst(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	@Override
	public void lance() {
		this.laRequete.ecrireMessage("215","UNIX Type: L8");
		this.laRequete.ecrireLog("Syt UNIX Type: L8");
	}

}
