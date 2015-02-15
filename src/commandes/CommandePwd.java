package commandes;

import serveur.FtpRequest;

public class CommandePwd extends Commande {

	public CommandePwd(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	@Override
	public void lance() {
		
		this.laRequete.ecrireMessage("257","\""+this.laRequete.getDirectory()+"\"");
		this.laRequete.ecrireLog("PWD "+this.laRequete.getDirectory());
	}

}
