
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public abstract class StorageHandler {
	
	  public static List read(String fileName) throws IOException {
			List data = new ArrayList() ;
		    Scanner scanner = new Scanner(new FileInputStream(fileName));
		    try {
		      while (scanner.hasNextLine()){
		        data.add(scanner.nextLine());
		      }
		    }
		    finally{
		      scanner.close();
		    }
		    return data;
		  }
	  
	    public static void write(String fileName, List data) throws IOException  {
	    PrintWriter out = new PrintWriter(new FileWriter(fileName));

	    try {
			for (int i =0; i < data.size() ; i++) {
	      		out.println((String)data.get(i));
			}
	    }
	    finally {
	      out.close();
	    }
	  }
	    public void Write(String fileName,List<String> lines) {
			try {
				File file = new File(fileName);

				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				for (String line : lines) {
	                bw.write(line+"\n");
	            }
				bw.close();

				System.out.println("Writing Done");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	   
	    
	    public abstract ArrayList readObject() throws IOException, ParseException;
	    public abstract void saveFile();
	    public abstract void print(ArrayList a);
	    public abstract void saveObject(String filename, List al) throws IOException;
}
