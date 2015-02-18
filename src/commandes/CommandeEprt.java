package commandes;

import java.io.IOException;

import serveur.FtpRequest;

public class CommandeEprt extends Commande{

	public CommandeEprt(FtpRequest requete, String ligne) {
		super(requete, ligne);
	}

	@Override
	public void lance() {
		String[] params = this.laLigne.substring(5).split("\\|");
		if(params.length != 4){
			this.laRequete.ecrireLog("Erreur de format EPRT");
		}else{
			String add = params[2];
			String port = params[3];

				try {
					this.laRequete.ouvreDataSocket(Integer.parseInt(port), add);
					this.laRequete.ecrireMessage("200", "Socket data effectue sur le port "+port);
					this.laRequete.ecrireLog("Socket data effectue sur le port "+port);

				} catch (NumberFormatException e) {
					this.laRequete.ecrireMessage("200", "Erreur format port");
					throw new RuntimeException(e);
				} catch (IOException e) {
					this.laRequete.ecrireMessage("200", "Erreur adresse IP");
					throw new RuntimeException(e);
				}
		}
	}

}
