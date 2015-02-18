package commandes;
import serveur.FtpRequest;

public class CommandeFactory {
	
	public static Commande CreeUneCommande(FtpRequest requete, String ligne){

		String message[] = ligne.split(" ", 2);
		String commande = message[0];
		
		requete.ecrireLog("Commande ---- : "+ligne);
		
		if(commande.compareTo("USER") == 0){
			return new CommandeUser(requete, ligne);
		}else if(commande.compareTo("CDUP") == 0){
			return new CommandeCdup(requete, ligne);
		}else if(commande.compareTo("CWD") == 0){
			return new CommandeCwd(requete, ligne);
		}else if(commande.compareTo("DELE") == 0){
			return new CommandeDele(requete, ligne);
		}else if(commande.compareTo("EPRT") == 0){
			return new CommandeEprt(requete, ligne);
		}else if(commande.compareTo("PASS") == 0){
			return new CommandePass(requete, ligne);
		}else if(commande.compareTo("RETR") == 0){
			return new CommandeRetr(requete, ligne);
		}else if(commande.compareTo("STOR") == 0){
			return new CommandeStor(requete, ligne);
		}else if(commande.compareTo("LIST") == 0){
			return new CommandeList(requete, ligne);
		}else if(commande.compareTo("QUIT") == 0){
			return new CommandeQuit(requete, ligne);
		}else if(commande.compareTo("PWD") == 0){
			return new CommandePwd(requete, ligne);
		}else if(commande.compareTo("SYST") == 0){
			return new CommandeSyst(requete, ligne);
		}else{
			return new CommandeInconnue(requete, ligne);
		}
	}
}