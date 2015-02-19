package commandes;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import serveur.FtpRequest;
import serveur.GestionnaireFichier;
/**
 * deposite a file from the local directory to the remote directory
 * @author julien
 *
 */
public class CommandeStor extends Commande {
	
	private GestionnaireFichier gestionnaire;
	
	/**
	 * constructor cmmandeStor
	 * @param requete : the requete received
	 * @param gest : the gestionnaire
	 * @param ligne : the line received
	 */
	public CommandeStor(FtpRequest requete, GestionnaireFichier gest, String ligne){
		super(requete, ligne);
		this.gestionnaire = gest;
	}

	/**
	 * allow to deposite a file
	 * and received a message
	 */
	public void lance() {
		
		String fileName = "."+this.gestionnaire.getDirectory()+"/"+laLigne.substring(5);
		
		
		//this.gestionnaire.enregistrerFichierLocal(fileName);
		FileOutputStream fos = null;
	    BufferedOutputStream bos = null;

        try {
    		fos = new FileOutputStream(fileName);
    		bos = new BufferedOutputStream(fos);
            this.laRequete.lireData(bos);
            bos.flush();
            bos.close();
            fos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		this.laRequete.ecrireLog("Fichier demandé : "+fileName);
		this.laRequete.ecrireMessage("150", "Envoi de "+fileName+" en cours");
		this.laRequete.ecrireMessage("226", fileName+" recuperé par le serveur");
		this.laRequete.fermeDataSocket();
		this.laRequete.ecrireLog(fileName+" envoyé : ");
		
	}
}
