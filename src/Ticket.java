import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Ticket implements DisplayInterface{
	//-----------------------------------------Data Members
	private long transactionID;
	private String typeOfMovie;
	private String classOfMovie;
	private double price;
	private String ticketNo;
	private String showTime;
	private int screenNo;
	private String venue;
	private String movie;
	private String showDate;
	private String BookingID;
	//-----------------------------------------Member Methods
	//-----------------------------------------Constructor
	
	private double getPrice(boolean isStudent,boolean isElder) throws IOException{
//		int date=Integer.parseInt(showDate.substring(0,1));
//		int month=Integer.parseInt(showDate.substring(3, 4));
//		int year=Integer.parseInt(showDate.substring(6, 7));
//		int hour=Integer.parseInt(showTime.substring(0, 1));
//		int min=Integer.parseInt(showTime.substring(3, 4));
		SystemStorage ss = new SystemStorage();
		ArrayList<Price> prices = new ArrayList<>();
		if(typeOfMovie.compareTo("3")>0){
			prices = ss.readPrice("3DPrice.txt");
		}
		else{
			prices = ss.readPrice("NormalPrice.txt");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		String dateInString = showDate+" "+showTime+":00";
		Date date = null;
		try {
			date = sdf.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int day = date.getDay();
		if(classOfMovie.compareTo("Platinum")>0){
			return prices.get(5).getPrice();
		}
		if(day ==0 || day == 6){
			return prices.get(4).getPrice();
		}
		else{
			if(Integer.parseInt(showTime.substring(0,1))<18){
				if(isStudent){
					return prices.get(0).getPrice();
				}
				else if(isElder){
					return prices.get(2).getPrice();
				}
				else{
					return prices.get(3).getPrice();
				}
			}
			else{
				if(isStudent){
					return prices.get(1).getPrice();
				}
				return prices.get(3).getPrice();
			}
		}
	}
	
	public Ticket(long TransactionID,String Type ,String ClassOfMovie,String TicketNo,String ShowDate,String ShowTime,String Venue,String Movie,boolean isStudent,boolean isElder){
		transactionID = TransactionID;
		typeOfMovie = Type;
		classOfMovie = ClassOfMovie;
		ticketNo = TicketNo;
		showDate = ShowDate;
		showTime = ShowTime;
		venue = Venue;
		movie = Movie;
		try {
			price = getPrice(isStudent,isElder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Ticket(long TransactionID,String Type ,String ClassOfMovie,String TicketNo,String ShowDate,String ShowTime,String Venue,String Movie,double Price){
		transactionID = TransactionID;
		typeOfMovie = Type;
		classOfMovie = ClassOfMovie;
		ticketNo = TicketNo;
		showDate = ShowDate;
		showTime = ShowTime;
		venue = Venue;
		movie = Movie;
		price = Price;
	}
	public Ticket(){
		typeOfMovie = "Regular";
		classOfMovie = "Silver";
		price = 15.00;
		ticketNo = "";
		showTime = null;
		movie =null;
	}
	
	//-----------------------------------------Member Functions
	//-----------------------------------------Get-set Methods

	public String getType(){return typeOfMovie;}
	public String getClassOfMovie(){return classOfMovie;}
	public double getPrice(){return price;}
	public String getVenue(){return venue;}
	public String getTicketNo(){return ticketNo;}
	public int getscreenNo(){return screenNo;}
	public String getShowDate(){return showDate;}
	public String getShowTime(){return showTime;}
	public String getMovie(){return movie;}
	public long getTransactionID(){return transactionID;}
	
	public void setType(String Type){typeOfMovie= Type;}
	public void setClass(String Class){classOfMovie= Class;}
	public void setVenue(String Venue){venue= Venue;}
	public void setTicketNo(String TicketNo){ticketNo= TicketNo;}
	public void setPrice(double Price){price= Price;}
	public void setScreenNo(int ScreenNo){screenNo= ScreenNo;}
	public void setShowDate(String ShowDate){showTime= ShowDate;}
	public void setShowTime(String ShowTime){showTime= ShowTime;}
	public void setMovie(String Movie){movie = Movie; }
	public void setTranactionID(long TransactionID){transactionID = TransactionID;}
	
	
	//-----------------------------------------Other Methods
	public void display(){	
		System.out.println("Movie: "+getMovie());
		System.out.println("Show Date: "+ getShowDate());
		System.out.println("Show Time: "+ getShowTime());		
		System.out.println("Screen No: "+ getscreenNo());
		System.out.print("Seats :");

	}

}