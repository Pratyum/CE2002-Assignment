import java.io.IOException;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TicketStorage extends StorageHandler{
	public static final String SEPARATOR = "|";
	public ArrayList readObject() throws IOException,ParseException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read("ticket.txt");
		ArrayList<Ticket> alr = new ArrayList();

        for (int i = 0 ; i <stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				long TID = Long.parseLong(star.nextToken().trim());
				String  type = star.nextToken().trim();	// first token
				String  screenClass = star.nextToken().trim();	// second token
				String seat = star.nextToken().trim();	
				String date = star.nextToken().trim();
				String time = star.nextToken().trim();
				String cineplex = star.nextToken().trim();
				String movie = star.nextToken().trim();
				double price=Double.parseDouble(star.nextToken().trim());
				Ticket t=new Ticket(TID,type,screenClass,seat,date,time,cineplex,movie,price);
				alr.add(t);
			}
			return alr ;
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