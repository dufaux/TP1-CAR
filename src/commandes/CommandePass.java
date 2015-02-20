package commandes;

import serveur.Authentification;
import serveur.FtpRequest;

/**
 * this command, identify the user and check
 * if this password is correct
 * @author julien
 *
 */
public class CommandePass extends Commande {
	
	private Authentification authentificator;
	
	/**
	 * constructor commandePass
	 * @param requete : the requete received
	 * @param auth : the authentification
	 * @param ligne : the line received
	 */
	public CommandePass(FtpRequest requete, Authentification auth, String ligne){
		super(requete, ligne);
		this.authentificator = auth;
	}

	/**
	 * Check if the password is correct
	 */
	public void lance() {
		String password = this.laLigne.substring(5);
		
		if(this.authentificator.passwordCorrect(this.laRequete.getUser().getName(), password)){
			this.laRequete.authentifieUser();
			this.laRequete.ecrireMessage("230", "Mot de passe OK");
			this.laRequete.ecrireLog("Mot de passe OK");
		}
		else{
			this.laRequete.ecrireMessage("530", "Incorrect password");
			this.laRequete.ecrireLog("Incorrect password");
		}
	}

}
