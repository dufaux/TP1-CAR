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
		
		Process p;
		try {
			
			String cmdunix = "ls -n ."+this.laRequete.getDirectory();
			//execute ls -n sur le dossier courrant.
			p = Runtime.getRuntime().exec(cmdunix);
			
		    p.waitFor();
			 
		    BufferedReader reader = 
		         new BufferedReader(new InputStreamReader(p.getInputStream()));
		 
		    String line = "";
		    String liste = "";
		    while ((line = reader.readLine())!= null) {
		    	liste+=line+" \r\n";
		    }
		    
		    
			this.laRequete.ecrireLog("Liste demandé : "+cmdunix);
			this.laRequete.ecrireMessage("150", "Liste en cours");
			this.laRequete.ecrireData(liste.getBytes(Charset.forName("UTF-8")));
			this.laRequete.ecrireMessage("226", "Liste envoyée");
			this.laRequete.fermeDataSocket();
			this.laRequete.ecrireLog("Liste envoyé : ");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
