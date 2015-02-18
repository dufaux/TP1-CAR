package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	
	public static void main(String[] args) {
		ServerSocket srv = null;
		Socket client = null;

		try {
			srv = new ServerSocket(2121);
			System.out.println("Serveur lancé sur le port 2121");
			//srv = new ServerSocket(Integer.parseInt(args[1]));
			//System.out.println("Serveur lancé sur le port "+args[1]);
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		try {
			while(true){ //peut peut-être être amélioré
				client = srv.accept();
				GestionnaireFichier gest = new GestionnaireFichier("/dossier", Runtime.getRuntime());
				System.out.println("Un client est connecté");
				
				BufferedWriter buffwrit = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
				BufferedReader buffread = new BufferedReader(new InputStreamReader(client.getInputStream()));
				
				Thread th = new Thread(new FtpRequest(client, gest, buffwrit, buffread));
				th.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
