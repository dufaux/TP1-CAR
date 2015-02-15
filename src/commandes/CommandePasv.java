package commandes;

import serveur.FtpRequest;

public class CommandePasv extends Commande{

	public CommandePasv(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	@Override
	public void lance() {
	}

}
