package commandes;

import serveur.FtpRequest;
import serveur.FileAdministrator;

/**
 * 
 *
 */
public class CommandeCdup extends Commande{

	private FileAdministrator fileAdmin;
	
	/**
	 * constructor CommandeCdup
	 * @param requete :  the requete received
	 * @param gest : gestionnaireFichier
	 * @param ligne : the line received
	 */
	public CommandeCdup(FtpRequest requete, FileAdministrator gest, String ligne) {
		super(requete, ligne);
		this.fileAdmin = gest;
	}

	/**
	 * allow to verify if the directory is changed
	 */
	public void lance(){
		String curdir = this.fileAdmin.getDirectory();
		
		if(curdir.contains("/")){
			String newdir = curdir.substring(0,curdir.lastIndexOf("/"));

			this.fileAdmin.setDirectory(newdir);
			this.laRequete.ecrireMessage("250","repertoire correctement changé == "+this.fileAdmin.getDirectory());
			this.laRequete.ecrireLog("CDUP "+this.fileAdmin.getDirectory());	
		}
		else{
			
			this.laRequete.ecrireMessage("501","repertoire racine atteint");
			this.laRequete.ecrireLog("CDUP INCORRECT");	
		}
		
	}

}
