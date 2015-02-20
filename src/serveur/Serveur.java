package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * main class
 *
 */
public class Serveur {
	
	public static void main(String[] args) {
		ServerSocket srv = null;
		Socket client = null;
		int port = Integer.parseInt(args[0]);
		try {
			srv = new ServerSocket(port);
			System.out.println("Serveur lancé sur le port "+port);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		try {
			while(true){ //peut peut-être être amélioré
				client = srv.accept();
				FileAdministrator gest = new FileAdministrator("/dossier", Runtime.getRuntime());
				Authentification auth;
				if(args.length == 4 && args[1].compareTo("-i") == 0){
					auth = new Authentification(args[2],args[3]);
				}
				else{
					auth = new Authentification("./users.txt");
				}
				ObjectCreator objcr = new ObjectCreator();
				DataSocketAdministrator datasockadmin = new DataSocketAdministrator(objcr);
				
				Thread th = new Thread(new FtpRequest(new ConnectionSocketAdministrator(client, objcr), gest, auth, datasockadmin, objcr));
				th.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
