package tests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;

import serveur.FtpRequest;
import serveur.GestionnaireFichier;

import commandes.Commande;
import commandes.CommandeCdup;
import commandes.CommandeCwd;
import commandes.CommandeEprt;
import commandes.CommandeInconnue;
import commandes.CommandeList;
import commandes.CommandeQuit;
import commandes.CommandeRetr;
import commandes.CommandeSyst;


public class CommandeTest {
	
	private FtpRequest ftpreq;
	private GestionnaireFichier gest;
	
	@Before
	public void setUp(){
		ftpreq = mock(FtpRequest.class);
		gest = mock(GestionnaireFichier.class);
	}
	
	@Test
	public void testCdup() {
		Commande cmd = new CommandeCdup(this.ftpreq, gest, "CDUP");
		String newdir = "/rep";
		
		when(this.gest.getDirectory()).thenReturn("/rep/sousrep").thenReturn(newdir);
		
		cmd.lance();
		verify(this.gest).setDirectory(newdir);
		verify(this.ftpreq).ecrireMessage("250","repertoire correctement changé == "+newdir);
		verify(this.ftpreq).ecrireLog("CDUP "+newdir);
	}
	
	
	@Test
	public void testCwd() {
		Commande cmd = new CommandeCwd(this.ftpreq, gest, "CWD petitrep");
		String newdir = "/rep/sousrep/petitrep";
		
		when(this.gest.getDirectory()).thenReturn("/rep/sousrep").thenReturn("/rep/sousrep/petitrep");	
		
		cmd.lance();
		verify(this.gest).setDirectory(newdir);
		verify(this.ftpreq).ecrireMessage("250","repertoire correctement changé");
		verify(this.ftpreq).ecrireLog("CWD "+newdir);
		
	}
	
	@Test
	public void testEprt() throws UnknownHostException, IOException {
		Commande cmd = new CommandeEprt(this.ftpreq, "EPRT |2|192.168.0.23|29340");

		cmd.lance();
		verify(this.ftpreq).ouvreDataSocket(29340,"192.168.0.23");
		verify(this.ftpreq).ecrireMessage("200", "Socket data effectue sur le port 29340");
		verify(this.ftpreq).ecrireLog("Socket data effectue sur le port 29340");
		
	}
	
	@Test
	public void testInconnue() {
		Commande cmd = new CommandeInconnue(this.ftpreq, "KACL odki");
		
		cmd.lance();
		verify(this.ftpreq).ecrireMessage("202"," Commande Non implementee");
		verify(this.ftpreq).ecrireLog("Commande Non implementee");
	}
	
	
	@Test
	public void testList() {
		String descriptif = "repertoire1 ok xxxcc \r\n repertoire2 ok xxcd \r\n";
		String dir = "/rep/sousrep";
		
		Commande cmd = new CommandeList(this.ftpreq, gest, "LIST");
		when(this.gest.lireListeDirectory()).thenReturn(descriptif);
		when(this.gest.getDirectory()).thenReturn(dir);
		
		cmd.lance();
		verify(this.gest).lireListeDirectory();
		verify(this.ftpreq).ecrireLog("Liste demandé : ");
		verify(this.ftpreq).ecrireMessage("150", "Liste en cours "+dir);
		verify(this.ftpreq).ecrireData(descriptif.getBytes(Charset.forName("UTF-8")));
		verify(this.ftpreq).ecrireMessage("226", "Liste envoyée");
		verify(this.ftpreq).fermeDataSocket();
		verify(this.ftpreq).ecrireLog("Liste envoyé : ");
		
	}
	
	
	@Test
	public void testPass() {
		//TO DO
		assertTrue(false);
	}
	
	@Test
	public void testUser() {
		//TO DO
		assertTrue(false);
	}	
	
	
	@Test
	public void testQuit() {
		Commande cmd = new CommandeQuit(this.ftpreq, "QUIT");
		
		cmd.lance();
		
		verify(this.ftpreq).fermeConnexion();
		verify(this.ftpreq).ecrireMessage("221"," Deconnexion");
		verify(this.ftpreq).ecrireLog("Deconnexion");
	}
	
	
	@Test
	public void testRetr() {
		String filename = "readme.txt";
		
		Commande cmd = new CommandeRetr(this.ftpreq, gest, "RETR "+filename);
		String dir = "/rep/sousrep";
		String fichier = "."+dir+"/"+filename;
		byte[] contenu = "Voici un exemple de contenu de fichier. je prend du texte \r\n pour plus de facilité!".getBytes();
		
		when(this.gest.getDirectory()).thenReturn(dir);	
		when(this.gest.LireFichierLocal(fichier)).thenReturn(contenu);
		
		cmd.lance();
		
		verify(this.ftpreq).ecrireLog("Fichier demandé : "+fichier);
		verify(this.ftpreq).ecrireMessage("150", "Reception de "+fichier+" en cours");
		verify(this.ftpreq).ecrireData(contenu);
		verify(this.ftpreq).ecrireMessage("226", fichier+" envoyé");
		verify(this.ftpreq).fermeDataSocket();
		verify(this.ftpreq).ecrireLog(fichier+" envoyé : ");
	}
	
	
	@Test
	public void testStor() {
		//TO DO (methode enregistrerFichier?)
		assertTrue(false);
	}
	
	
	@Test
	public void testSyst() {
		Commande cmd = new CommandeSyst(this.ftpreq, "SYST");
		
		cmd.lance();
		
		verify(this.ftpreq).ecrireMessage("215","UNIX Type: L8");
		verify(this.ftpreq).ecrireLog("Syt UNIX Type: L8");
	}
}
