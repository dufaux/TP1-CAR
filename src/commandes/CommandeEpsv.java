package commandes;

import serveur.FtpRequest;

public class CommandeEpsv extends Commande{

	/**
	 * constructor CommandeEpsv
	 * @param requete : the requete received
	 * @param ligne : the line received
	 */
	public CommandeEpsv(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	@Override
	public void lance() {
		
	}

}
