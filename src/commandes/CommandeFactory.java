package commandes;
import serveur.FtpRequest;

public class CommandeFactory {
	
	public static Commande CreeUneCommande(FtpRequest requete, String ligne){

		String message[] = ligne.split(" ", 2);
		String commande = message[0];
		
		requete.EcrireLog("Commande ---- : "+ligne);
		
		if(commande.compareTo("USER") == 0){
			return new CommandeUser(requete, ligne);
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
		}else{
			return new CommandeInconnue(requete, ligne);
		}
	}
}