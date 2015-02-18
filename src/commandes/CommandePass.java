package commandes;

import serveur.Authentification;
import serveur.FtpRequest;
import serveur.User;

public class CommandePass extends Commande {
	
	private Authentification authentificator;
	
	public CommandePass(FtpRequest requete, Authentification auth, String ligne){
		super(requete, ligne);
		this.authentificator = auth;
	}

	@Override
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
