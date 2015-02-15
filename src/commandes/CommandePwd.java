package commandes;

import serveur.FtpRequest;

public class CommandePwd extends Commande {

	public CommandePwd(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	@Override
	public void lance() {

	}

}
