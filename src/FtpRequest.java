import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class FtpRequest implements Runnable{

	private Socket sock;
	private BufferedWriter buffwrit;
	private BufferedReader buffread;
	
	public FtpRequest(Socket client) {
		this.sock = client;
	}

	
	public void run() {
		System.out.println("Thread run FtpRequest");
	
		
		try {
			buffwrit = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			buffread = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			buffwrit.write("220 \r\n");
			buffwrit.write("coucou \r\n");
			buffwrit.flush();
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		processRequest();
		
		
		System.out.println("1");
		try {
			buffwrit.close();
			sock.close();
			System.out.println("Socket client fermé");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("2");
	}

	
	//effectuant des traitements généraux concernant une 
	//requête entrante et déléguant le traitement des commandes.
	public void processRequest(){
		try {
			System.out.println("3");
			while(true){
				String txt = buffread.readLine();
				if(txt != null){
					String cmd = txt.substring(0,4);
					System.out.println(cmd);
					if(cmd.compareTo("USER") == 0){
						System.out.println("user!");
					}	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void processUSER(){
		//se charge de traiter la commande USER
	}

	public void processPASS(){
		//se charge de traiter la commande USER
	}
	
	public void processRETR(){
		//se charge de traiter la commande USER
	}
	
	public void processSTOR(){
		//se charge de traiter la commande USER
	}
	
	public void processLIST(){
		//se charge de traiter la commande USER
	}
	
	public void processQUIT(){
		//se charge de traiter la commande USER
	}
	
	
}
