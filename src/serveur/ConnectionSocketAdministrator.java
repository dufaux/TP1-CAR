package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

/**
 * class used to administrate a socket connection
 * provide 2 function to read and write on socket
 * and 1 to close the socket.
 * @author dufaux
 *
 */
public class ConnectionSocketAdministrator {

	private Socket sock;
	private BufferedWriter buffwrit;
	private BufferedReader buffread;
	
	/**
	 * constructor
	 * @param socket the socket
	 * @param creator the objectcreator for never use new on class.
	 */
	public ConnectionSocketAdministrator(Socket socket, ObjectCreator creator){
		this.sock = socket;
		try {
			this.buffwrit = creator.createBufferedWriter(this.sock);
			this.buffread = creator.createBufferedReader(this.sock);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean isClosed(){
		return this.sock.isClosed();
	}
	
	public void close() throws IOException{
		this.buffread.close();
		this.buffwrit.close();
		this.sock.close();
	}
	
	public String readLine(){
		try {
			return this.buffread.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public void write(String txt) throws IOException{
		buffwrit.write(txt);
		buffwrit.flush();
	}
}
