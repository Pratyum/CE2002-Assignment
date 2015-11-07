import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {

	public static void main (String []args){
	
	ArrayList <Booking>ListOfBooking=new ArrayList<>() ;
	BookingStorage b=new BookingStorage();

	try {
		ListOfBooking =b.readBooking();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ArrayList<Ticket>ListOfTicket=new ArrayList<>();
	TicketStorage t=new TicketStorage();
	try {
		ListOfTicket=t.readObject();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
		Scanner sc=new Scanner(System.in);
		int handPhoneNo;
		System.out.println("Phone Number");
		handPhoneNo=sc.nextInt();
		boolean match;
		long TID=0;
		for(int i=0;i<ListOfBooking.size();i++){
			System.out.print("i" + i);
			TID=-1;
			match=false;
			if(ListOfBooking.get(i).getNumber()==handPhoneNo){
			System.out.print("Name:" + ListOfBooking.get(i).getName() + " ");
			System.out.print("Email:" + ListOfBooking.get(i).getEmail()+ " ");
			System.out.print("Number:" + ListOfBooking.get(i).getNumber()+ " ");
			System.out.println("TID:" + ListOfBooking.get(i).getTID()+ " ");
			TID=ListOfBooking.get(i).getTID();
			match=true;
			}			
			
			if(match==true){
				
			for(int j=0;j<ListOfTicket.size();j++){
				if(ListOfTicket.get(j).getTransactionID()==TID){
				
				System.out.print("TID:" + ListOfTicket.get(j).getTransactionID()+ " ");
				System.out.print("Type:" + ListOfTicket.get(j).getType()+ " ");
				System.out.print("Class:" + ListOfTicket.get(j).getClass()+ " ");
				System.out.print("Seat/Ticket No:" + ListOfTicket.get(j).getTicketNo()+ " ");
				System.out.print("Show Date:" + ListOfTicket.get(j).getShowDate()+ " ");
				System.out.print("Show Time:" + ListOfTicket.get(j).getShowTime()+ " ");
				System.out.print("Cineplex:" + ListOfTicket.get(j).getVenue()+ " ");
				System.out.print("Movie: " + ListOfTicket.get(j).getMovie()+ " ");
				System.out.println("Price: " + ListOfTicket.get(j).getPrice()+ " ");
			}
			}
			
		}
			
		
}
	
	
	
}
}
