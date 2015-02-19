package commandes;

import java.io.IOException;

import serveur.FtpRequest;

public class CommandePort extends Commande{

	/**
	 * Constructor CommandePort
	 * @param requete : the requete received
	 * @param ligne : the line received
	 */
	public CommandePort(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	/**
	 * receive port and IP adress and open a dataSocketet
	 */
	public void lance() {
		String[] params = this.laLigne.substring(5).split(",");
		if(params.length != 6){
			this.laRequete.ecrireLog("Erreur de format PORT");
		}else{
			String add = params[0] + "." + params[1] + "." + params[2] + "." + params[3];
			int resultPort = (Integer.parseInt(params[4]) * 256) + Integer.parseInt(params[5]) ;
			String port = Integer.toString(resultPort);

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
