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
import serveur.ConnectionSocketAdministrator;
import serveur.DataSocketAdministrator;
import serveur.FtpRequest;
import serveur.FileAdministrator;
import serveur.ObjectCreator;

public class FtpRequestTest {

	private FtpRequest ftpreq;
	private ConnectionSocketAdministrator connectSockAdmin;
	private FileAdministrator gest;
	private Authentification auth;
	private DataSocketAdministrator datasock;
	private ObjectCreator objcr;
	
	@Before
	public void setUp(){
		this.connectSockAdmin = mock(ConnectionSocketAdministrator.class);
		this.gest = mock(FileAdministrator.class);
		this.auth = mock(Authentification.class);
		this.datasock = mock(DataSocketAdministrator.class);
		this.objcr = mock(ObjectCreator.class);
		ftpreq = new FtpRequest(this.connectSockAdmin, this.gest, this.auth, this.datasock, this.objcr);
	}

	
	@Test
	public void testFermeConnexion() throws IOException{
		
		ftpreq.fermeConnexion();
		verify(this.connectSockAdmin).close();
	}
	
	
	@Test
	public void testEcrireMessage() throws IOException {
		String code = "333";
		String message = "le message";
		ftpreq.ecrireMessage(code, message);
		verify(this.connectSockAdmin).write(code+" "+message+"\r\n");
		
	}

}
