	import java.io.IOException;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BookingStorage {
	
public static final String SEPARATOR = "|";

	public BookingStorage() {
	}
	public static ArrayList readBooking() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read();
		ArrayList alr = new ArrayList();

        for (int i = 0 ; i <stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				String TID = star.nextToken().trim();
				String  name = star.nextToken().trim();	// first token
				String  email = star.nextToken().trim();	// second token
				int  number = Integer.parseInt(star.nextToken().trim()); // third token
				Booking b = new Booking(TID,name,email,number);
				alr.add(b);
			}
			return alr ;
	}
	public static ArrayList readTicket() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read();
		ArrayList alr = new ArrayList();

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				String TID = star.nextToken().trim();
				String temp = (TID.substring(0, TID.indexOf(':',0)));
				String Venue = temp.substring(0, temp.length()- 2);
				for(int j=0; j<3; j++) star.nextToken().trim();
				String Type = star.nextToken().trim();//forth token
				String Class = star.nextToken().trim();
				String movieTitle = star.nextToken().trim(); 
				double price = Double.parseDouble(star.nextToken().trim());
				String ShowDate = star.nextToken().trim();
				String showTime = star.nextToken().trim();
				int noOfTicket = Integer.parseInt(star.nextToken().trim());
				for(int j=0;i<noOfTicket;++i){
				String ticketNo = star.nextToken().trim();
				Ticket s = new Ticket(Type, Class, ticketNo, ShowDate, showTime,Venue,movieTitle, price);
				alr.add(s);
				}
			}
			return alr ;
	}
	public static void saveBooking(List al, List as) throws IOException {
		List alw = new ArrayList() ;

        for (int i = 0 ; i < al.size(); i++) {
        		StringBuilder st =  new StringBuilder() ;
				Booking b = (Booking)al.get(i);
				Ticket s = (Ticket)as.get(i);
				if (s.getVenue()!=null) {
					st.append(s.getVenue().trim()+b.getCurrentTime().trim());
					st.append(SEPARATOR);
					st.append(b.getName().trim());
					st.append(SEPARATOR);
					st.append(b.getEmail().trim());
					st.append(SEPARATOR);
					st.append(b.getNumber());
					st.append(SEPARATOR);
					st.append(s.getType().trim());
					st.append(SEPARATOR);
					st.append(s.getClassOfMovie().trim());
					st.append(SEPARATOR);
					st.append(s.getMovie().trim());
					st.append(SEPARATOR);
					st.append(s.getPrice());
					st.append(SEPARATOR);
					st.append(s.getShowDate().trim());
					st.append(SEPARATOR);
					st.append(s.getShowTime().trim());
					st.append(SEPARATOR);
					
					
					alw.add(st.toString());
				}
				else {
					st.append(b.getCurrentTime().trim());
					st.append(SEPARATOR);
					st.append(b.getName().trim());
					st.append(SEPARATOR);
					st.append(b.getEmail().trim());
					st.append(SEPARATOR);
					st.append(b.getNumber());
					st.append(SEPARATOR);
					st.append(s.getMovie().trim());
					st.append(SEPARATOR);
					st.append(s.getPrice());
					st.append(SEPARATOR);
					st.append(s.getTicketNo().trim());
					st.append(SEPARATOR);
					st.append(s.getShowTime().trim());
					alw.add(st.toString());
				}
		}
        	
			write(alw);
	}

  /** Write fixed content to the given file. */
  public static void write(List data) throws IOException  {
    PrintWriter out = new PrintWriter(new FileWriter("booking.txt"));

    try {
		for (int i =0; i < data.size() ; i++) {
      		out.println((String)data.get(i));
		}
    }
    finally {
      out.close();
    }
  }
  public static List read() throws IOException {
		List data = new ArrayList() ;
	    Scanner scanner = new Scanner(new FileInputStream("booking.txt"));
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

  	public void print(int number)  {
			try {
				int transcation = 1;
				ArrayList al = BookingStorage.readBooking();
				ArrayList as = BookingStorage.readTicket();
				
				for (int i = 0 ; i < al.size() ; i++) {
						Booking b = (Booking)al.get(i);
						Ticket s = (Ticket)as.get(i);
							if(number == b.getNumber()){
							System.out.println("TID : " + b.getCurrentTime());
							System.out.println("Name : " + b.getName());
							System.out.println("Transcation ID : " + transcation);
							System.out.println("Email : " + b.getEmail() );
							System.out.println("Contact : " + b.getNumber() );
							System.out.println("Movie Title : " + s.getMovie());
							System.out.println("Price : $" + s.getPrice());
							System.out.println("Ticket No : " + s.getTicketNo());
							System.out.println("ShowTime : " + s.getShowTime());
							transcation++;
							}
				}

			}catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
	  }
  	public void writeFile(String TID, String name,String email,int number,ArrayList<Ticket> s) {
		try {
			ArrayList  al = BookingStorage.readBooking();
			ArrayList  as = BookingStorage.readTicket();
			Booking b = new Booking(TID,name,email,number);
			al.add(b);
			for(int i=0;i<s.size();++i){
			as.add(s.get(i));
			}
			BookingStorage.saveBooking(al,as);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

  	}


}