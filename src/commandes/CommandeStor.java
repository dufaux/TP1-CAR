package commandes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import serveur.FtpRequest;

public class CommandeStor extends Commande {
	
	public CommandeStor(FtpRequest requete, String ligne){
		super(requete, ligne);
	}

	@Override
	public void lance() {
		
		String fileName = "."+this.laRequete.getDirectory()+"/"+laLigne.substring(5);
		
		FileOutputStream fos = null;
	    BufferedOutputStream bos = null;

        try {
    		fos = new FileOutputStream(fileName);
    		bos = new BufferedOutputStream(fos);
            this.laRequete.lireFichierData(bos);
            bos.flush();
            bos.close();
            fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		this.laRequete.ecrireLog("Fichier demandé : "+fileName);
		this.laRequete.ecrireMessage("150", "Envoi de "+fileName+" en cours");
		this.laRequete.ecrireMessage("226", fileName+" recuperé par le serveur");
		this.laRequete.fermeDataSocket();
		this.laRequete.ecrireLog(fileName+" envoyé : ");
		
	}
}
