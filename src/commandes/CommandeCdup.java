package commandes;

import serveur.FtpRequest;

public class CommandeCdup extends Commande{

	public CommandeCdup(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	@Override
	public void lance() {
		String curdir = this.laRequete.getDirectory();
		String newdir = curdir.substring(0,curdir.lastIndexOf("/"));
		this.laRequete.setDirectory(newdir);
		this.laRequete.ecrireMessage("250","repertoire correctement changé == "+this.laRequete.getDirectory());
		this.laRequete.ecrireLog("CWD "+this.laRequete.getDirectory());	
	}

}
