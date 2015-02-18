package commandes;

import serveur.FtpRequest;

public class CommandeCwd extends Commande {

	public CommandeCwd(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	public void lance() {
		String dossier = laLigne.substring(4);
		String newdir;
		if(dossier.contains("/")){ //parfois envoie "sousdossier" parfois "/dossier/sousdossier"...?
			newdir = dossier;
		}
		else{
			newdir = this.laRequete.getDirectory()+"/"+dossier;
		}

		this.laRequete.setDirectory(newdir);
		this.laRequete.ecrireMessage("250","repertoire correctement changé");
		this.laRequete.ecrireLog("CWD "+this.laRequete.getDirectory());	
	}

}
