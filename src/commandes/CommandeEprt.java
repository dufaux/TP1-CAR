package commandes;

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
			//String af = params[1];
			String add = params[2];
			String port = params[3];
			
			this.laRequete.ouvreDataSocket(Integer.parseInt(port), add);
			this.laRequete.ecrireMessage("200", "Socket data effectue sur le port "+port);
			this.laRequete.ecrireLog("Socket data effectue sur le port "+port);
		}
	}

}
