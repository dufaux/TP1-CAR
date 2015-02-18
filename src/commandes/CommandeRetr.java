package commandes;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import serveur.FtpRequest;
import serveur.User;

public class CommandeRetr extends Commande {
	
	public CommandeRetr(FtpRequest requete, String ligne){
		super(requete, ligne);
	}

	@Override
	public void lance() {
		
		String fileName = "."+this.laRequete.getDirectory()+"/"+laLigne.substring(5);
		
		File myFile = new File (fileName);
		FileInputStream fis;
		BufferedInputStream bis;
        byte [] myByteArray  = new byte [(int)myFile.length()];
        try {
            fis = new FileInputStream(myFile);
            bis = new BufferedInputStream(fis);
			bis.read(myByteArray,0,myByteArray.length);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		this.laRequete.ecrireLog("Fichier demandé : "+fileName);
		this.laRequete.ecrireMessage("150", "Reception de "+fileName+" en cours");
        this.laRequete.ecrireData(myByteArray);
		this.laRequete.ecrireMessage("226", fileName+" envoyé");
		this.laRequete.fermeDataSocket();
		this.laRequete.ecrireLog(fileName+" envoyé : ");
	}

}
