package commandes;

import serveur.FtpRequest;
import serveur.GestionnaireFichier;

public class CommandeRetr extends Commande {
	
	private GestionnaireFichier gestionnaire;
	
	public CommandeRetr(FtpRequest requete, GestionnaireFichier gest, String ligne){
		super(requete, ligne);
		this.gestionnaire = gest;
	}

	@Override
	public void lance() {
		
		String fileName = "."+this.gestionnaire.getDirectory()+"/"+laLigne.substring(5);
		
        byte[] myByteArray = this.gestionnaire.LireFichierLocal(fileName);
		
		
		this.laRequete.ecrireLog("Fichier demandé : "+fileName);
		this.laRequete.ecrireMessage("150", "Reception de "+fileName+" en cours");
        this.laRequete.ecrireData(myByteArray);
		this.laRequete.ecrireMessage("226", fileName+" envoyé");
		this.laRequete.fermeDataSocket();
		this.laRequete.ecrireLog(fileName+" envoyé : ");
	}

}
