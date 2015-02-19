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
		try {
			srv = new ServerSocket(2121);
			System.out.println("Serveur lancé sur le port 2121");
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		try {
			while(true){ //peut peut-être être amélioré
				client = srv.accept();
				FileAdministrator gest = new FileAdministrator("/dossier", Runtime.getRuntime());
				Authentification auth = new Authentification("./users.txt");
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
