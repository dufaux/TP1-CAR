package commandes;

import java.nio.charset.Charset;

import serveur.FtpRequest;
import serveur.GestionnaireFichier;

public class CommandeList extends Commande {
	
	private GestionnaireFichier gestionnaire;
	
	/**
	 * constructor CommandeList
	 * @param requete : the requete received
	 * @param gest : the gestionnaire
	 * @param ligne : the line received
	 */
	public CommandeList(FtpRequest requete, GestionnaireFichier gest, String ligne){
		super(requete, ligne);
		this.gestionnaire = gest;
	}

	/**
	 * affiche la liste des dossiers et fichier dans le répertoir courant
	 */
	public void lance() {
		
		    
		    String liste = this.gestionnaire.lireListeDirectory();
		    
			this.laRequete.ecrireLog("Liste demandé : ");
			this.laRequete.ecrireMessage("150", "Liste en cours "+this.gestionnaire.getDirectory());
			this.laRequete.ecrireData(liste.getBytes(Charset.forName("UTF-8")));
			this.laRequete.ecrireMessage("226", "Liste envoyée");
			this.laRequete.fermeDataSocket();
			this.laRequete.ecrireLog("Liste envoyé : ");
	}

}
