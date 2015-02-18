package tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

import serveur.Authentification;
import serveur.FtpRequest;
import serveur.GestionnaireFichier;

public class FtpRequestTest {

	private FtpRequest ftpreq;
	private Socket sock;
	private GestionnaireFichier gest;
	private Authentification auth;
	private BufferedWriter bw;
	private BufferedReader br;
	
	@Before
	public void setUp(){
		this.sock = mock(Socket.class);
		this.gest = mock(GestionnaireFichier.class);
		this.auth = mock(Authentification.class);
		this.bw = mock(BufferedWriter.class);
		this.br = mock(BufferedReader.class);
		ftpreq = new FtpRequest(this.sock, this.gest, this.auth, this.bw, this.br);
	}

	
	@Test
	public void testFermeConnexion() throws IOException{
		
		ftpreq.fermeConnexion();

		
		verify(this.bw).close();
		verify(this.sock).close();
	}
	
	
	@Test
	public void testLireLigne() throws IOException{
		
		ftpreq.lireLigne();
		
		verify(this.br).readLine();
	}
	
	@Test
	public void testEcrireMessage() throws IOException {
		String code = "333";
		String message = "le message";
		ftpreq.ecrireMessage(code, message);
		
		verify(this.bw).write(code+" "+message+"\r\n");
		verify(this.bw).flush();
		
	}

}
