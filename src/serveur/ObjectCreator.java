package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Kind of Factory, used to build class withouth use "new class" in other class.
 * @author dufaux
 */
public class ObjectCreator {

	public Socket createSocket(int port, String add) throws UnknownHostException, IOException{
		return new Socket(add, port);
	}
	
	public BufferedWriter createBufferedWriter(Socket sock) throws IOException{
		return new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
	}
	
	public BufferedReader createBufferedReader(Socket sock) throws IOException{
		return new BufferedReader(new InputStreamReader(sock.getInputStream()));
	}

	public ServerSocket createServerSocket(int port) throws IOException{
		return new ServerSocket(port);
	}
	
}
