import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;



public class ShowTimeStorage extends StorageHandler{
public static final String SEPARATOR = "|";
SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

public ArrayList readObject(String cineplex) throws IOException, ParseException {
	ArrayList stringArray = (ArrayList)read("showtime.txt");
	ArrayList alr = new ArrayList() ;
	

	
    for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);			
			String movieTitle = star.nextToken().trim();	
			String cineplexName=star.nextToken().trim();
			int cinemaId = Integer.parseInt(star.nextToken().trim());	
			String date = star.nextToken().trim();
			String time = star.nextToken().trim();
	
			ShowTime show = new ShowTime(movieTitle,cineplexName,cinemaId,date,time);
			
			if(show.getCineplexName().compareTo(cineplex)==0)
			alr.add(show);
		}   
		return alr ;
}



public ArrayList readObject() throws IOException, ParseException {
	ArrayList stringArray = (ArrayList)read("showtime.txt");
	ArrayList alr = new ArrayList() ;

    for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);			
			String movieTitle = star.nextToken().trim();	
			String cineplexName=star.nextToken().trim();
			int cinemaId = Integer.parseInt(star.nextToken().trim());	
			String date = star.nextToken().trim();
			String time = star.nextToken().trim();
	
			ShowTime show = new ShowTime(movieTitle,cineplexName,cinemaId,date,time);
			
			alr.add(show);
		}   
		return alr ;
}




@Override
public void saveFile() {
	// TODO Auto-generated method stub
	
}

@Override



public void saveObject(String filename, List al) throws IOException {
	List alw = new ArrayList() ;

    for (int i = 0 ; i < al.size() ; i++) {
			ShowTime showtime = (ShowTime)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			//if(i==al.size()-1){
				st.append(showtime.getMovieTitle());
				st.append(SEPARATOR);
				st.append(showtime.getCineplexName());
				st.append(SEPARATOR);
				st.append(showtime.getCinemaId());
				st.append(SEPARATOR);
				st.append(showtime.getStrDate());
				st.append(SEPARATOR);
				st.append(showtime.getStrTime());
				st.append(SEPARATOR);
				/*					}
			else{
				st.append(showtime.getMovieTitle());
				st.append(SEPARATOR1);
				st.append(showtime.getCineplexName());
				st.append(SEPARATOR1);
				st.append(showtime.getCinemaId());
				st.append(SEPARATOR1);
				st.append(showtime.getDate());
				st.append(SEPARATOR1);
				st.append(showtime.getTime());
				st.append(SEPARATOR1);
			}*/
			alw.add(st.toString()) ;
		}
		write(filename,alw);
}

@Override
public void print(ArrayList a) {
	// TODO Auto-generated method stub
	
}








	
}