import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Ticket {
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

	
	//-----------------------------------------Member Methods
	//-----------------------------------------Constructor
	
	private double getPrice(boolean isStudent,boolean isElder){
//		int date=Integer.parseInt(showDate.substring(0,1));
//		int month=Integer.parseInt(showDate.substring(3, 4));
//		int year=Integer.parseInt(showDate.substring(6, 7));
//		int hour=Integer.parseInt(showTime.substring(0, 1));
//		int min=Integer.parseInt(showTime.substring(3, 4));
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = showTime+" "+showDate+":00";
		Date date = null;
		try {
			date = sdf.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int day = date.getDay();
		
		if(Integer.parseInt(showTime.substring(0,1))<18){
			if(isStudent){
				return 7;
			}
			else if(isElder){
				return 4;
			}
			else{
				return 8.5;
			}
		}
		else{
			return 8.5;
		}
	}
	
	public Ticket(long TransactionID, String Type ,String ClassOfMovie,String TicketNo,String ShowDate,String ShowTime,String Venue,String Movie,boolean isStudent,boolean isElder){
		transactionID= TransactionID;
		typeOfMovie = Type;
		classOfMovie = ClassOfMovie;
		ticketNo = TicketNo;
		showDate = ShowDate;
		showTime = ShowTime;
		venue = Venue;
		movie = Movie;
		price = getPrice(isStudent,isElder);
	}
	public Ticket(long TransactionID,String Type ,String ClassOfMovie,String TicketNo,String ShowDate,String ShowTime,String Venue,String Movie,double Price){
		transactionID= TransactionID;
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
	public long getTransactionID(){return transactionID;}
	public String getType(){return typeOfMovie;}
	public String getClassOfMovie(){return classOfMovie;}
	public double getPrice(){return price;}
	public String getVenue(){return venue;}
	public String getTicketNo(){return ticketNo;}
	public int getscreenNo(){return screenNo;}
	public String getShowDate(){return showDate;}
	public String getShowTime(){return showTime;}
	public String getMovie(){return movie;}
	
	
	public void setType(String Type){typeOfMovie= Type;}
	public void setClass(String Class){classOfMovie= Class;}
	public void setVenue(String Venue){venue= Venue;}
	public void setTicketNo(String TicketNo){ticketNo= TicketNo;}
	public void setPrice(double Price){price= Price;}
	public void setScreenNo(int ScreenNo){screenNo= ScreenNo;}
	public void setShowDate(String ShowDate){showTime= ShowDate;}
	public void setShowTime(String ShowTime){showTime= ShowTime;}
	public void setMovie(String Movie){movie = Movie; }
	
	//-----------------------------------------Other Methods
	

}