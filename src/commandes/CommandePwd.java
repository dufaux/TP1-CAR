package commandes;

import serveur.FtpRequest;
import serveur.GestionnaireFichier;

public class CommandePwd extends Commande {

	private GestionnaireFichier gestionnaire;
	
	public CommandePwd(FtpRequest requete, GestionnaireFichier gest, String ligne) {
		super(requete, ligne);
		this.gestionnaire = gest;
	}

	@Override
	public void lance() {
		
		this.laRequete.ecrireMessage("257","\""+this.gestionnaire.getDirectory()+"\"");
		this.laRequete.ecrireLog("PWD "+this.gestionnaire.getDirectory());
	}

}
