package serveur;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * class used to administrate a data socket
 * provide 1 function to init a new socket on ip and port
 * 1 other to init from a socketserver
 * 2 function to read and write on socket
 * and 1 to close and reset the socket
 * @author dufaux
 *
 */
public class DataSocketAdministrator {

	private Socket sock;
	private OutputStream dataOutputStream;
	private InputStream dataInputStream;
	private ObjectCreator creator;
	
	public DataSocketAdministrator(ObjectCreator creat){
		this.sock = null;
		this.dataOutputStream = null;
		this.dataInputStream = null;
		this.creator = creat;
	}
	
	
	/**
	 * init a new socket to connect with a client
	 * @param port : the port
	 * @param adresse : the client address
	 */
	public void initNew(int port, String adresse){
		try {
			this.sock = this.creator.createSocket(port, adresse);
			this.dataInputStream = sock.getInputStream();
			this.dataOutputStream = sock.getOutputStream();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * init socket from serversocket (used in passiv mode)
	 * @param srv : the socketserver
	 */
	public void initNewPassive(ServerSocket srv){
		
		try {
			this.sock = srv.accept();
			this.dataInputStream = sock.getInputStream();
			this.dataOutputStream = sock.getOutputStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * write data on socket
	 * @param data : array of byte
	 */
	public void write(byte[] data){
		try {
			this.dataOutputStream.write(data);
			this.dataOutputStream.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * read in the socket and copy it on the bufferedoutputstream
	 * @param bos : the buffer where the data are copied.
	 */
	public void read(BufferedOutputStream bos){
		int count;
		byte[] fileInByte = new byte[1024];
		try {
			while ((count = this.dataInputStream.read(fileInByte)) > 0) {
		        bos.write(fileInByte, 0, count);
		    }

		} catch (SocketException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * close the current socket and set null the variables.
	 */
	public void close(){
		try {
			this.dataInputStream.close();
			this.dataOutputStream.close();
			this.sock.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		this.sock = null;
		this.dataOutputStream = null;
		this.dataInputStream = null;
	}
}
