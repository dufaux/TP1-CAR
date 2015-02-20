package commandes;


import serveur.FtpRequest;

public class CommandePasv extends Commande{

	/**
	 * Constructor CommandePasv
	 * @param requete : the requete received
	 * @param ligne : the line received
	 */
	public CommandePasv(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	/**
	 * lance le mode passif pour ipv4
	 */
	public void lance() {
		
		this.laRequete.passiveMode(false);
	}

}
