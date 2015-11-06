
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class User {
private String username;
private String password;
private String Movietitle ;
private String Type ;
private String Rating ;
private String Status ;
private String Synopsis ;
private String Director ;
private String Cast;

public User(){
	
}
public User(String user, String pass){
    username = user;
    password = pass;
}

public boolean auth(){
	UserStorage s =new UserStorage("admin1.txt","admin1.txt",username,password);
    if(s.readFile())
        return true;
    else
        return false;
}
public void writefile(){
	UserStorage s =new UserStorage("admin1.txt","admin1.txt",username,password);
	s.writeFile();
}


public void CreateMovie(String movietitle,String type,String rating,int status,String synopsis,String director,String cast,String duration) throws ParseException{
	try {
		
	    
		ArrayList al=new ArrayList();
		StorageHandler ms=new MovieStorage();
		al=ms.readObject();	
		int movieId=(al.size())+1;
		String strStatus="";
		if(status==1){
			strStatus="Coming Soon";			
		}
		else if(status==2){
			strStatus="Now Showing";			
		}
		
		
		Movie m1 = new Movie(movieId,movietitle,type,rating,strStatus,synopsis,director,cast,duration);
		al.add(m1);		
		ms.saveObject("movie.txt", al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}


public void ShowMovie(int choice) throws ParseException{
	try {
		ArrayList al=new ArrayList();
		StorageHandler ms=new MovieStorage();
		al=ms.readObject();	
		
		//show current movies
		if(choice==1){
		for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(i);
				if(m.getStatus().compareTo("Status: Now Showing")==0){
				System.out.println("Movie Id: " +m.getMovieId()+" "+ m.getMovietitle()+ " "+m.getStatus());}
			}		
		}
		
		//show archived movies
		else if(choice==2){
		for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(i);
				if(m.getStatus().compareTo("Status: End of Showing")==0)
				System.out.println("Movie Id: " +m.getMovieId()+" "+ m.getMovietitle()+" "+m.getStatus());
				
			}		
		}
		
		//show all movies
		else if(choice==3){
		for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(i);
				System.out.println("Movie Id: " +m.getMovieId()+" "+ m.getMovietitle()+" "+m.getStatus());
			}		
		}
		
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}



public void ChooseEditMovie(int moviechoice){
	try {
		Scanner input=new Scanner(System.in);
		ArrayList al=new ArrayList();
		MovieStorage ms=new MovieStorage();
		al = ms.readObject() ;
		
		for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(moviechoice-1);
				if (m.getMovieId()==moviechoice){
				System.out.println("Movie Id: "+m.getMovieId());
				System.out.println(i+1+") "+m.getMovietitle());
				System.out.println(i+2+") "+m.getType());
				System.out.println(i+3+") "+m.getRating());
				System.out.println(i+4+") "+m.getStatus());
				System.out.println(i+5+") "+m.getSynopsis());
				System.out.println(i+6+") "+m.getDirector());
				System.out.println(i+7+") "+m.getCast());
				}
				break;				
		}
		
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}
public void UpdateMovie(int attribute,int moviechoice){
	Scanner input = new Scanner(System.in);
	try {
		ArrayList al=new ArrayList();
		MovieStorage ms=new MovieStorage();
		// read file containing Professor records.
		al = ms.readObject() ;
		int id=0;
		String mo=null,ty=null,ra=null,st=null,sy=null,di=null,ca= null,dur=null;		
		
		for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(moviechoice-1);
				id=m.getMovieId();
				mo=m.getMovietitle();
				ty=m.getType();
				ra=m.getRating();
				st=m.getStatus();
				sy=m.getSynopsis();
				di=m.getDirector();
				ca=m.getCast();
				dur=m.getDuration();
				switch(attribute){
				
				case 1:				
					System.out.println("Enter Movie Title: ");
					String movietitle=input.nextLine();
					mo=mo.replace(mo, movietitle);
					mo="Movie: "+mo;					
					break;		
				case 2:
					System.out.println("Enter Type: ");
					String type=input.nextLine();
					ty=ty.replace(ty, type);
					ty="Type: "+ty;
					break;
				case 3:				
					System.out.println("Enter Rating: ");
					String rating=input.nextLine();
					ra=ra.replace(ra,rating);
					ra="Rating: "+ra;
					break;
				case 4:
					System.out.println("Enter Status: ");
					String status=input.nextLine();
					st=st.replace(st, status);
					st="Status: "+st;
					break;
				case 5:
					System.out.println("Enter Synopsis: ");
					String synopsis=input.nextLine();
					sy=sy.replace(sy, synopsis);
					sy="Synopsis: "+sy;
					break;
				case 6:
					System.out.println("Enter Director: ");
					String director=input.nextLine();
					di=di.replace(di, director);
					di="Director: "+di;
					break;
				case 7:
					System.out.println("Enter Cast: ");
					String cast=input.nextLine();
					ca=ca.replace(ca, cast);
					ca="Cast: "+ca;
					break;
				case 8:
					System.out.println("Enter Duration(in Mins): ");
					String duration=input.nextLine();
					dur=dur.replace(dur,duration);
					dur="Duration(in Mins): "+dur;
					break;
				}
		}
		
		al.remove(moviechoice-1);
		Movie newmovie=new Movie(id,mo,ty,ra,st,sy,di,ca,dur);
		al.add(moviechoice-1, newmovie);		
		ms.saveupdatedMovie("movie.txt", al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}

public void RemoveMovie(int moviechoice){
	Scanner input = new Scanner(System.in);
	try {
		ArrayList al=new ArrayList();
		MovieStorage ms=new MovieStorage();
		al = ms.readObject();
		int id=0;
		String mo=null,ty=null,ra=null,st=null,sy=null,di=null,ca= null,dur=null;

		for (int i = 0 ; i < al.size() ; i++) {
				Movie m = (Movie)al.get(moviechoice-1);
				id=m.getMovieId();
				mo=m.getMovietitle();
				ty=m.getType();
				ra=m.getRating();
				st=m.getStatus();
				sy=m.getSynopsis();
				di=m.getDirector();
				ca=m.getCast();
				dur=m.getDuration();
				st=st.replace(st, "End of Showing");
				st="Status: "+st;
				break;
			
		}
		al.remove(moviechoice-1);
		Movie newmovie=new Movie(id,mo,ty,ra,st,sy,di,ca,dur);
		al.add(moviechoice-1, newmovie);
		
		// write Professor record/s to file.
		MovieStorage.saveupdatedMovie("movie.txt", al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}



public void showShowTime() throws IOException, ParseException{
	SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	ShowTimeStorage sts= new ShowTimeStorage();
	ArrayList al=new ArrayList();
	al = sts.readObject() ;	
	
	for(int i=0;i<al.size();i++){
	ShowTime st=(ShowTime)al.get(i);
	System.out.println("Showtime ID :" +(i+1));
	st.display();
	System.out.println("---------------------------------------");		
	}	
	}
	

public void createShowTime(int cineplexId,int cinemaId,int movieId,String strDate,String strTime) throws ParseException{
	try {
	Scanner input = new Scanner(System.in);
	ArrayList movieal=new ArrayList();
	ArrayList showtimeal=new ArrayList();
	
	//get movie name from id
	StorageHandler ms=new MovieStorage();
	movieal = ms.readObject();
	Movie movie=(Movie)movieal.get(movieId-1);
	String movieTitle=movie.getMovietitle();
	
	//get cineplex from userinput
	String cineplex="";
	if(cineplexId==1) {
		cineplex= "GV Jurong Point";}
	else if(cineplexId==2){
		cineplex= "GV Marina Square";}
	else if(cineplexId==3){
		cineplex="GV Suntec City";		
	}
	
	StorageHandler sts=new ShowTimeStorage();
	showtimeal=sts.readObject();
	ShowTime st = new ShowTime(movieTitle,cineplex,cinemaId,strDate,strTime);	
	showtimeal.add(st);		
	sts.saveObject("showtime.txt", showtimeal);		
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
	}


public void EditShowTime(int showTimeId) throws ParseException{
	ShowTimeStorage sts= new ShowTimeStorage();
	ArrayList al=new ArrayList();
	SimpleDateFormat dayFormat = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	String strchoice="";

	try {
		al = sts.readObject() ;
	} catch (IOException e) {
		e.printStackTrace();
	}
	
		Scanner input = new Scanner(System.in);
		
		//get the ShowTime user wants to edit
		ShowTime st=(ShowTime)al.get(showTimeId-1);
		st.display();
		System.out.println("Enter the field you wish to edit :");
		int choice=input.nextInt();
		input.nextLine();
		
		//let user choose which field he wants to edit
		switch(choice){
			case 1:			
				System.out.println("Enter new cineplex");
				System.out.println("1) GV Jurong Point");
				System.out.println("2) GV Marina Square");
				System.out.println("3) GV Suntec City");
				choice=input.nextInt();
				input.nextLine();
				
				//get cineplex from userinput
				String strCineplex="";
				if(choice==1)
					strCineplex="GV Jurong Point";
				else if(choice==2)
					strCineplex="GV Marina Square";
				else if(choice==3)
					strCineplex="GV Suntec City";
				st.setCineplexName(strCineplex);
				System.out.println("Cineplex changed!");
				break;
		
		
			//user decides to edit hall number of ShowTime
			case 2:			
				System.out.println("Enter new hall number");
				choice=input.nextInt();
				input.nextLine();
				st.setCinemaId(choice);
				System.out.println("Hall number changed!");
				break;
			
			//user decides to edit movie of ShowTime
			case 3:
				System.out.println("Enter ID of the new movie");
				//ShowMovie shows all movies with 'Showing Now' status
				ShowMovie(1);
				ArrayList movieal=new ArrayList();
				StorageHandler ms=new MovieStorage();
				try {movieal = ms.readObject();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				choice=input.nextInt();
				input.nextLine();
			
			//get movie title according to user's input of movieId
			Movie movie=(Movie)movieal.get(choice-1);
			String movieTitle=movie.getMovietitle();
			st.setMovieTitle(movieTitle);
			System.out.println("Movie changed!");
			break;
			
		case 4:			
			System.out.println("Enter new date in dd-mm-yyyy");
			String inputDate=input.nextLine();
			Date date = (Date)dayFormat.parse(inputDate);
			st.setDate(inputDate);
			System.out.println("Date changed!");
			break;

		case 5:
			System.out.println("Enter new time in hh:mm");
			String inputTime=input.nextLine();
			Date time = (Date)timeFormat.parse(inputTime);
			st.setTime(inputTime);
			System.out.println("Date changed!");
			break;
			
		default:
			System.out.println("Please enter a valid choice");
		}
		
		al.set(showTimeId-1, st);
		
		try {
			sts.saveObject("showtime.txt", al);
		} catch (IOException e) {
			e.printStackTrace();
		}

}


public void DeleteShowTime(int showTimeId) throws ParseException{
	ShowTimeStorage sts= new ShowTimeStorage();
	ArrayList al=new ArrayList();
	String strchoice="";
	try {
		al = sts.readObject() ;
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	al.remove(showTimeId-1);
		
		try {
			sts.saveObject("showtime.txt", al);
		} catch (IOException e) {
			e.printStackTrace();
		}

}
}