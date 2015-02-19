package commandes;
import serveur.Authentification;
import serveur.FtpRequest;
import serveur.GestionnaireFichier;
/**
 * display in the server the commands used 
 * @author julien
 *
 */
public class CommandeFactory {
	
	/**
	 * execute a command based on a received message
	 * @param requete : the requete received
	 * @param gest : the gestionnaire
	 * @param auth : the authentification
	 * @param ligne : the line received
	 * @return a commande used
	 */
	public static Commande CreeUneCommande(FtpRequest requete, GestionnaireFichier gest, Authentification auth, String ligne){

		String message[] = ligne.split(" ", 2);
		String commande = message[0];
		
		requete.ecrireLog("Commande ---- : "+ligne);
		
		if(commande.compareTo("USER") == 0){
			return new CommandeUser(requete, auth, ligne);
		}else if(commande.compareTo("CDUP") == 0){
			return new CommandeCdup(requete, gest, ligne);
		}else if(commande.compareTo("CWD") == 0){
			return new CommandeCwd(requete, gest, ligne);
		}else if(commande.compareTo("DELE") == 0){
			return new CommandeDele(requete, gest, ligne);
		}else if(commande.compareTo("EPRT") == 0){
			return new CommandeEprt(requete, ligne);
		}else if(commande.compareTo("PASS") == 0){
			return new CommandePass(requete, auth, ligne);
		}else if(commande.compareTo("PORT") == 0){
			return new CommandePort(requete, ligne);
		}else if(commande.compareTo("RETR") == 0){
			return new CommandeRetr(requete, gest, ligne);
		}else if(commande.compareTo("STOR") == 0){
			return new CommandeStor(requete, gest, ligne);
		}else if(commande.compareTo("LIST") == 0){
			return new CommandeList(requete, gest, ligne);
		}else if(commande.compareTo("QUIT") == 0){
			return new CommandeQuit(requete, ligne);
		}else if(commande.compareTo("PWD") == 0){
			return new CommandePwd(requete, gest, ligne);
		}else if(commande.compareTo("SYST") == 0){
			return new CommandeSyst(requete, ligne);
		}else{
			return new CommandeInconnue(requete, ligne);
		}
	}
}