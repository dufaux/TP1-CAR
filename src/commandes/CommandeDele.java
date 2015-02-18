package commandes;

import java.io.File;

import serveur.FtpRequest;

public class CommandeDele extends Commande {

	public CommandeDele(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	@Override
	public void lance() {
		
		String fileName = "."+this.laRequete.getDirectory()+"/"+laLigne.substring(5);
		File myFile = new File (fileName);
		
		if(myFile.delete()){
			this.laRequete.ecrireMessage("250", "Fichier "+fileName+" supprimé");
			this.laRequete.ecrireLog("Fichier "+fileName+" supprimé");
		}else{
			this.laRequete.ecrireMessage("450", "Error, Fichier non supprimé");
			this.laRequete.ecrireLog("Identification");
		}
	}

}
