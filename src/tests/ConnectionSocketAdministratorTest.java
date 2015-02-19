package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import serveur.ConnectionSocketAdministrator;
import serveur.FileAdministrator;
import serveur.ObjectCreator;

public class ConnectionSocketAdministratorTest {

	private ConnectionSocketAdministrator admin;
	private Socket sock;
	private ObjectCreator creator;
	private BufferedWriter bw = mock(BufferedWriter.class);
	private BufferedReader br = mock(BufferedReader.class);
	
	@Before
	public void setUp(){
		this.sock = mock(Socket.class); 
		this.creator = mock(ObjectCreator.class);
		
		try {
			when(this.creator.createBufferedWriter(this.sock)).thenReturn(bw);
			when(this.creator.createBufferedReader(this.sock)).thenReturn(br);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		admin = new ConnectionSocketAdministrator(sock, creator);
		
	}
	
	
	@Test
	public void isClosedTest() {
		boolean attendu = true;
		when(this.sock.isClosed()).thenReturn(attendu);
		
		boolean result = this.admin.isClosed();

		verify(this.sock).isClosed();
		assertEquals(attendu,result);
	}
	
	
	@Test
	public void closeTest() throws IOException{

		this.admin.close();
				
		verify(bw).close();
		verify(br).close();
		verify(this.sock).close();
	}
	
	
	@Test
	public void readLineTest() throws IOException{
		String txt = "Texte à lire!";
		
		when(this.br.readLine()).thenReturn(txt);
		
		assertEquals(txt,this.admin.readLine());
	}
	
	@Test
	public void writeTest() throws IOException{
		String txt = "text a ecrire";
		
		this.admin.write(txt);
		verify(this.bw).write(txt);
		verify(this.bw).flush();
	}
}
