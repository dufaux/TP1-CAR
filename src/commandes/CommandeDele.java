package commandes;

import java.io.File;

import serveur.FtpRequest;
import serveur.GestionnaireFichier;

public class CommandeDele extends Commande {

	private GestionnaireFichier gestionnaire;
	
	public CommandeDele(FtpRequest requete, GestionnaireFichier gest, String ligne) {
		super(requete, ligne);
		this.gestionnaire = gest;
	}

	@Override
	public void lance() {
		
		String fileName = "."+this.gestionnaire.getDirectory()+"/"+laLigne.substring(5);
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
