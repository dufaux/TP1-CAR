package serveur;

import java.io.IOException;
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
			e.printStackTrace();
		}
		
		try {
			while(true){ //peut peut-être être amélioré
				client = srv.accept();
				System.out.println("Un client est connecté");
				Thread th = new Thread(new FtpRequest(client));
				th.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
