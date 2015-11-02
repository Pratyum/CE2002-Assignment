import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.util.List;
//import java.util.ArrayList;

public class ShowTime {
private String movieTitle;
private String cineplexName;
private int cinemaId;
private Date date;
private Date time;
private String strDate;
private String strTime;

int numEmptySeat;


//Seat[] seat=new Seat[7];
//ArrayList<Seat> list= new ArrayList<Seat>();

public ShowTime(String movieTitle,String cineplexName,int cinemaId,String strDate,String strTime ){
this.movieTitle=movieTitle;
this.cinemaId=cinemaId;
this.cineplexName=cineplexName;
this.strDate=strDate;
this.strTime=strTime;


}

public void showSeats(){}
public String getMovieTitle(){return movieTitle;}
public String getCineplexName(){return cineplexName;}
public int getCinemaId(){return cinemaId;}
public String getStrDate(){return strDate;}
public String getStrTime(){return strTime;}

public Date getDate() throws ParseException{
	SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy");
	Date date=dayFormat.parse(strDate);
	return date;}

public Date getTime() throws ParseException{
	SimpleDateFormat dayFormat = new SimpleDateFormat("HH:mm");
	Date time=dayFormat.parse(strTime);
	return time;}

public void setMovieTitle(String movieTitle){this.movieTitle=movieTitle;}
public void setCineplexName(String cineplexName){this.cineplexName=cineplexName;}
public void setCinemaId(int cinemaId){this.cinemaId=cinemaId;}
public void setDate(String strDate){this.strDate=strDate;}
public void setTime(String strTime){this.strTime=strTime;}

	
}
