package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

import serveur.ConnectionSocketAdministrator;
import serveur.DataSocketAdministrator;
import serveur.ObjectCreator;

public class DataSocketAdministratorTest {

	private DataSocketAdministrator admin;
	private OutputStream dataOutputStream;
	private ObjectCreator creator;
	
	@Before
	public void setUp(){
		this.creator = mock(ObjectCreator.class);
		
		this.admin = new DataSocketAdministrator(creator);
	}
	
	
	@Test
	public void writeTest() throws IOException{
		byte[] data = "text à ecrire".getBytes();
		int port = 2122;
		String add = "1.2.3.4";
		Socket sock = mock(Socket.class);
		
		OutputStream dos = mock(OutputStream.class);
		InputStream dis = mock(InputStream.class);
		
		when(this.creator.createSocket(port, add)).thenReturn(sock);
		
		when(sock.getOutputStream()).thenReturn(dos);
		when(sock.getInputStream()).thenReturn(dis);
		
		verify(dos).write(data);
		verify(dos).flush();
	}

}
