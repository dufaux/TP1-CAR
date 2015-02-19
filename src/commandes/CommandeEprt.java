package commandes;

import java.io.IOException;

import serveur.FtpRequest;

public class CommandeEprt extends Commande{
	
	/**
	 * constructor CommandeEprt
	 * @param requete : tge requete received
	 * @param ligne : the line received
	 */
	public CommandeEprt(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	/**
	 * receive port and IP adress and open a dataSocketet
	 */
	public void lance() {
		String[] params = this.laLigne.substring(5).split("\\|");
		if(params.length != 4){
			this.laRequete.ecrireLog("Erreur de format EPRT");
			this.laRequete.ecrireMessage("501", "Erreur format EPRT");
		}else{
			String add = params[2];
			String port = params[3];

				try {
					this.laRequete.ouvreDataSocket(Integer.parseInt(port), add);
					this.laRequete.ecrireMessage("200", "Socket data effectue sur le port "+port);
					this.laRequete.ecrireLog("Socket data effectue sur le port "+port);

				} catch (NumberFormatException e) {
					this.laRequete.ecrireMessage("501", "Erreur format port");
					throw new RuntimeException(e);
				} catch (IOException e) {
					this.laRequete.ecrireMessage("501", "Erreur adresse IP");
					throw new RuntimeException(e);
				}
		}
	}

}
