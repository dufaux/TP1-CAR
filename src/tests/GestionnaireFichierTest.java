package tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import serveur.GestionnaireFichier;

public class GestionnaireFichierTest {
	
	private GestionnaireFichier gestionnaire;
	private Runtime runner;
	@Before
	public void setUp(){
		runner = mock(Runtime.class); 
		gestionnaire = new GestionnaireFichier("/dossier",runner);
	}
	
	@Test
	public void testLireListeDirectory() throws IOException{
		String cmdunix = "ls -n ./dossier";
		String liste = "test.txt 5604 18/02/2013 drwxr \r\n autre.txt 154 24/03/2013 drrwxr \r\n";
		String retour;
		Process p = mock(Process.class);
		InputStream ipsmock = new ByteArrayInputStream(liste.getBytes() );
		

		when(this.runner.exec(cmdunix)).thenReturn(p);
		when(p.getInputStream()).thenReturn(ipsmock);
		
		
		retour = gestionnaire.lireListeDirectory();
		

		verify(this.runner).exec(cmdunix);
		assertEquals(liste, retour);
		
	}


}
