import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer; 

public class UserStorage extends StorageHandler{
	private String readFile, writeFile, user, password;
	public static final String SEPARATOR = " ";
	public static final String SEPARATOR1 = "|";
	
	public UserStorage(String readFile, String writeFile, String user, String password) {
		this.readFile = readFile;
		this.writeFile = writeFile;
		this.user=user;
		this.password=password;
	}
	
	public boolean readFile(){
		boolean counter=false;
			try {
	            FileReader reader = new FileReader(readFile);
	            BufferedReader bufferedReader = new BufferedReader(reader);
	 
	            String line;
	            
	            while ((line = bufferedReader.readLine()) != null) {
	            	StringTokenizer st = new StringTokenizer(line,SEPARATOR);
		                if (st.nextToken().equals(user) && st.nextToken().equals(password)) {
		                	counter=true;
		                	return counter;
		                }
		                else break;
	            }
	            reader.close();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 return counter;
	}
	public void writeFile() {
        try {
            FileWriter writer = new FileWriter(writeFile, true);
            writer.write(user + " " + password);
            writer.write("\r\n");   // write new line
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }

@Override
public ArrayList readObject() throws IOException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void saveFile() {
	// TODO Auto-generated method stub
	
}

@Override
public void print(ArrayList a) {
	// TODO Auto-generated method stub
	
}

@Override
public void saveObject(String filename, List al) throws IOException {
	// TODO Auto-generated method stub
	
}
}
