package commandes;

import serveur.FtpRequest;
import serveur.GestionnaireFichier;

public class CommandeCdup extends Commande{

	private GestionnaireFichier gestionnaire;
	
	/**
	 * constructor CommandeCdup
	 * @param requete :  the requete received
	 * @param gest : gestionnaireFichier
	 * @param ligne : the line received
	 */
	public CommandeCdup(FtpRequest requete, GestionnaireFichier gest, String ligne) {
		super(requete, ligne);
		this.gestionnaire = gest;
	}

	/**
	 * 
	 */
	public void lance(){
		String curdir = this.gestionnaire.getDirectory();
		
		if(curdir.contains("/")){
			String newdir = curdir.substring(0,curdir.lastIndexOf("/"));

			this.gestionnaire.setDirectory(newdir);
			this.laRequete.ecrireMessage("250","repertoire correctement changé == "+this.gestionnaire.getDirectory());
			this.laRequete.ecrireLog("CDUP "+this.gestionnaire.getDirectory());	
		}
		else{
			
			this.laRequete.ecrireMessage("501","repertoire racine atteint");
			this.laRequete.ecrireLog("CDUP INCORRECT");	
		}
		
	}

}
