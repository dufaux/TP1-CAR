package commandes;

import serveur.Authentification;
import serveur.FtpRequest;
import serveur.User;

public class CommandeUser extends Commande {
	
	private Authentification authentificator;
	
	public CommandeUser(FtpRequest requete, Authentification auth, String ligne) {
		super(requete, ligne);
		this.authentificator = auth;
	}

	public void lance() {
		String username = laLigne.substring(5);
			
		if(this.authentificator.exist(username)){
			this.laRequete.definiUser(new User(username));
			this.laRequete.ecrireMessage("331", "Correct name, need password");
			this.laRequete.ecrireLog("Correct name, need password");	
		}
		else if(username.compareTo("anonymous") == 0){
			this.laRequete.definiUser(new User(username));
			this.laRequete.authentifieUser();
			this.laRequete.ecrireMessage("230", "Connected as anonymous");
			this.laRequete.ecrireLog("Connected as anonymous");	
		}
		else{
			this.laRequete.ecrireMessage("530", "Incorrect name");
			this.laRequete.ecrireLog("Incorrect name");
		}
	}
}
