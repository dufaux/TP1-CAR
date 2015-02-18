package commandes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import serveur.FtpRequest;

public class CommandeList extends Commande {
	
	public CommandeList(FtpRequest requete, String ligne){
		super(requete, ligne);
	}

	@Override
	public void lance() {
		
		    
		    String liste = this.laRequete.lireListeDirectory();
		    
			this.laRequete.ecrireLog("Liste demandé : ");
			this.laRequete.ecrireMessage("150", "Liste en cours "+this.laRequete.getDirectory());
			this.laRequete.ecrireData(liste.getBytes(Charset.forName("UTF-8")));
			this.laRequete.ecrireMessage("226", "Liste envoyée");
			this.laRequete.fermeDataSocket();
			this.laRequete.ecrireLog("Liste envoyé : ");
	}

}
