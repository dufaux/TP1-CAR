package serveur;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 *
 */
public class GestionnaireFichier {
	
	private String directory;
	private Runtime runner;
	
	public GestionnaireFichier(String dir, Runtime run){
		this.directory = dir;
		this.runner = run;
	}
	
	/**
	 * @return the directory
	 */
	public String getDirectory(){
		return this.directory;
	}
	
	/**
	 * @param dir : set the directory
	 */
	public void setDirectory(String dir){
		this.directory = dir;
	}
	
	/**
	 * execute "ls -n" on the current directory
	 * @return the respons of the unix command as string.
	 */
	public String lireListeDirectory(){
		String liste = null;
		String cmdunix = "ls -n ."+this.getDirectory();
		Process p;
		
		try {
			p = this.runner.exec(cmdunix);
		    p.waitFor();
			 
		    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		 
		    liste = "";
		    String line = "";
		    while ((line = reader.readLine())!= null) {
		    	liste+=line+"\r\n";
		    }
		    
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	    
	    return liste;
	}
	
	
	/**
	 * read local file and return it as byte array.
	 * @param fileName the file pathname to read
	 * @return the file in byte array.
	 */
	public byte[] LireFichierLocal(String fileName){
		File myFile = new File (fileName);
		FileInputStream fis;
		BufferedInputStream bis;
        byte [] myByteArray  = new byte [(int)myFile.length()];
        try {
            fis = new FileInputStream(myFile);
            bis = new BufferedInputStream(fis);
			bis.read(myByteArray,0,myByteArray.length);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
        
        return myByteArray;
	}

	
}
