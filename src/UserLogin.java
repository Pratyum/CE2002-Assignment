
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
public class UserLogin {
	public User check=null;
	private String username;
	private String password;
	private ArrayList<Movie> ListOfMovies;
	private ArrayList<ShowTime> ListOfShowTimes;
	private ArrayList<Screen> ListOfScreen ;
	public Scanner input = new Scanner(System.in);
	
	private void loadData(){
		MovieStorage ms = new MovieStorage();
		ScreenStorage ss = new ScreenStorage();
		
		try {
			ListOfScreen = ss.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ListOfMovies = ms.readObject();
			System.out.println("Movies Loaded!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Movie File Not Found!" + e.getMessage());
		}
	}
	
	private void printMenuOptions(){
	    System.out.println("====Menu====");
	    System.out.println("1.Staff");
	    System.out.println("2.Customer");
	    System.out.println("3.View All Movies with Information");
	    System.out.println("4.Exit");
	}
	private void printAdminMainOptions(){
		System.out.println("==============WELCOME ADMIN===============");
	    System.out.println("1.Create/Update/Remove Movie Listing");
	    System.out.println("2.Create/Update/Remove cinema showtime and movie to be shown");
	    System.out.println("3.Configure System(In Progress)");
	    System.out.println("0.Exit");
	}
	private boolean createMovie(){
		System.out.println();
		System.out.println("Enter movie:");
	    String movietitle=input.nextLine();	    
		System.out.println("Enter type:");
		String type=input.nextLine();		
		System.out.println("Enter rating:");
		String rating=input.nextLine();				
		int status=0;						
		try{
			System.out.println();
			System.out.println("Enter status:");
			System.out.println("1) Coming Soon");
			System.out.println("2) Showing Now");
		    status=input.nextInt();
		    if(status!=1 &&status!=2){
		    	System.out.println("Please enter a value input!");
				return false;
		    }
		    }
			catch(InputMismatchException e){
				System.out.println("Please enter a value input!");	
				input.next();
				return false;
			}
		input.nextLine();				
	    System.out.println("Enter synopsis:");
	    String synopsis=input.nextLine();	    
	    System.out.println("Enter director:");
	    String director=input.nextLine();	    
	    System.out.println("Enter cast:");
	    String cast=input.nextLine();			    
	    System.out.println("Enter Duration(in Mins):");
	    String duration=input.nextLine();			    
	    
	    try {
			check.CreateMovie(movietitle,type,rating,status,synopsis,director,cast,duration);
			return true;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return false;
	}
	
	private void editMovie(){
		int choice;
		System.out.println();
		System.out.println("1.Show movies showing now");
		System.out.println("2.Show archived movies");
		System.out.println("3.Show all movies");								
		choice= input.nextInt();
		input.nextLine();					
        try {
			check.ShowMovie(choice);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	            
		System.out.println("Enter movie ID or 0 to return to previous menu:");
		choice= input.nextInt();
		input.nextLine();
		if (choice==-1)
			return;
		check.ChooseEditMovie(choice);					
	    System.out.println("Choose category to edit:");			    
	    int category=input.nextInt();
	    input.nextLine();
	    check.UpdateMovie(category, choice);
	}
	private void removeMovie(){
		int codeToRemove;
		System.out.println();
		try {
			check.ShowMovie(3);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//show all movies
		System.out.println("Enter movie code or 0 to return to previous menu:");				
		codeToRemove=input.nextInt();
		input.nextLine();
		if(codeToRemove!=0)
			check.RemoveMovie(codeToRemove);
	}
	
	private void createShowTime(){
		  System.out.println();
		  System.out.println("All current showtime");
		  try {
			  check.showShowTime();
		      }
		  catch (IOException e) {
			  e.printStackTrace();
		  	  } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println();
		  	System.out.println("Enter cineplex");
		  	System.out.println("1.GV Jurong Point");
			System.out.println("2.GV Marina Square");
			System.out.println("3.GV Suntec City");
			System.out.println("0.Return to previous menu");
			int cineplexId=input.nextInt();
			input.nextLine();
			if(cineplexId==0)
				return;
			System.out.println("Enter cinema ID: ");
			int cinemaId=input.nextInt();
			input.nextLine();					
			//show movies showing now
			try {
				check.ShowMovie(1); //Shows only running movies
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Enter movie ID");
			int movieId=input.nextInt();
			input.nextLine();
			System.out.println("Enter date in dd-mm-yyyy");
			String date=input.nextLine();
			System.out.println("Enter time in hh:mm");
			String time=input.nextLine();
			
			try {
				check.createShowTime(cineplexId,cinemaId,movieId,date,time);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void updateShowTime(){
		int choice;
		try {
			System.out.println();
			check.showShowTime();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter show time ID you wish to edit: ");
		System.out.println("OR");
		System.out.println("Enter 0 to return to previous menu");
		choice=input.nextInt();
		input.nextLine();
		if (choice==0)
			return;
		try {
			check.EditShowTime(choice);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			  
		return;
	  
	}
	
	private void removeShowTime(){
		int choice;
		System.out.println("All current showtime");
		 try {
		 check.showShowTime();
		 } 	catch (IOException e) {
		 e.printStackTrace();
		 } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Enter show time ID you wish to delete: ");
		System.out.println("OR");
		System.out.println("Enter 0 to return to previous menu");
		choice=input.nextInt();
		input.nextLine();
		if (choice==0)
			return;
		try {
			check.DeleteShowTime(choice);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	private void displayShowingMovies(){
		for(int i=0;i<ListOfMovies.size();++i){
			Movie temp = (Movie) ListOfMovies.get(i);
			if(temp.getStatus().substring(8).compareTo("Coming Soon")!=0){
				System.out.println("("+temp.getMovieId()+")"+temp.getMovietitle());
			}
		}
	}
	
	private Movie chooseMovie(){
		displayShowingMovies();
		System.out.println("Please Choose the Movie ID:");
		int movieChoice = input.nextInt();
		int movieI = 0;
		for(int i=0;i<ListOfMovies.size();++i){
			Movie temp = (Movie) ListOfMovies.get(i);
			if(temp.getMovieId()== movieChoice){
				return temp;
			}
		}
		return null;
	}
	
	private String chooseCinePlex(){
		System.out.println("(1) GV Jurong Point");
		System.out.println("(2) GV Marina Square");
		System.out.println("(3) GV Suntec City");
		System.out.println("Choose CinePlex:");
		int cineplexChoice = input.nextInt();
		String Cineplex= null;
		switch(cineplexChoice){
		case 1:
			Cineplex= "GV Jurong Point";
			break;
		case 2:
			Cineplex= "GV Marina Square";
			break;
		case 3:
			Cineplex= "GV Suntec City";
			break;
		}
		return Cineplex;
	}
	
	private void loadShowTimes(String CinePlex){
		ShowTimeStorage sts = new ShowTimeStorage();
		try {
			ListOfShowTimes = sts.readObject(CinePlex);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
	private ArrayList<ShowTime> loadMovieShowTime(String CinePlex,Movie movie){
		loadShowTimes(CinePlex);
		ArrayList<ShowTime>ListOfMovieTimes = new ArrayList<>();
		for(int i=0;i<ListOfShowTimes.size();++i){
			ShowTime temp = (ShowTime) ListOfShowTimes.get(i);
			if(temp.getMovieTitle().compareTo(movie.getMovietitle())==0){
				ListOfMovieTimes.add(temp);
			}
		}
		return ListOfMovieTimes;
	}
	
	private ShowTime chooseShowTime(String CinePlex , Movie movie){
		ArrayList<ShowTime> ListOfMovieTimes = loadMovieShowTime(CinePlex, movie);
		for(int i=0;i<ListOfMovieTimes.size();++i){
			System.out.println("("+(i+1)+") Show Time: "+((ShowTime)ListOfMovieTimes.get(i)).getStrTime());
		}
		System.out.println("Choose ShowTime:");
		int showTimeChoice = input.nextInt();
		return ListOfMovieTimes.get(showTimeChoice-1);
	}
	
	private Screen chooseScreen(int screenNo){
		if(ListOfScreen.size()!=0){
			for(int i=0;i<ListOfScreen.size();++i){
				if(ListOfScreen.get(i).getScreenNo()== screenNo)
					return ListOfScreen.get(i);
			}
		}else{
				ListOfScreen.add(new Screen(screenNo));
				return ListOfScreen.get(ListOfScreen.size()-1);
		}
		return null;
	}
	
	private ArrayList<Ticket> chooseTickets(Screen screen , int noOfTickets,Movie movie,ShowTime showTime){
		if(screen.getSeatsFree() <noOfTickets){
			System.out.println("There is only"+screen.getSeatsFree()+" seats free! Please try again!" );
		}else{
		ArrayList<Ticket> ListOfTickets = new ArrayList<>();
		for(int i=0;i<noOfTickets;++i){
		screen.printScreen();
		System.out.println("Enter the Row character:");
		char rowNo = input.nextLine().charAt(0);
		System.out.println("Enter the Seat Number:");
		int seatno = Integer.parseInt(input.nextLine());
		boolean isStudent = false, isElder = false;
		if(screen.AssignSeat(rowNo, seatno)){
			System.out.println("Are You a Student(Y/N)");
			String details =input.nextLine();
			if(details.charAt(0)=='Y'){
				isStudent = true;
			}else{
				System.out.println("Are you above 65? (Y/N)");
				details = input.nextLine();
				if(details.charAt(0)=='Y'){
					isElder = true;
				}
			}
			Ticket ticket = new Ticket(movie.getType(), "Platinum", String.valueOf(rowNo).concat(String.valueOf(seatno)), showTime.getStrDate(), showTime.getStrTime(), showTime.getCineplexName(), showTime.getMovieTitle(), isStudent, isElder);
			System.out.println("Seat Assigned!");
			ListOfTickets.add(ticket);
		}else{
			System.out.println("Please Try Again!");
			i--;
		}
		}
		if(updateScreen(screen)){
			return ListOfTickets;	
		}
		}
		return null;
	}
	
	private boolean updateScreen(Screen screen){
		ScreenStorage ss = new ScreenStorage();
		for(int i=0;i<ListOfScreen.size();++i){
			if(ListOfScreen.get(i).getScreenNo()==screen.getScreenNo()){
				ListOfScreen.set(i, screen);
				break;
			}
		}
		try {
			ss.saveObject("screen.txt", ListOfScreen);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private Booking chooseBooking(ArrayList<Ticket> tickets){
		System.out.println("Please Enter Your Details");
		System.out.println("NAME: ");
		String name = input.nextLine();
		System.out.println("Email: ");
		String email = input.nextLine();
		System.out.println("Phone Number: ");
		int number = Integer.parseInt(input.nextLine());
		BookingStorage b = new BookingStorage();
		DateFormat dateFormat = new SimpleDateFormat("YYYYMMDDhhmm");
		Date date = new Date();
		b.writeFile(String.valueOf(date.getTime()), name, email, number,tickets);
		Booking booking = new Booking(date.toString(), name, email, number);
		return booking;
	}
	
	private void printBooking(Booking booking){
		//TODO Create pretty print of Booking
	}
	
	private void displayDetailMovie(){
		System.out.println("Movies on Show!");
		for(int i=0;i<ListOfMovies.size();++i){
			Movie temp = (Movie) ListOfMovies.get(i);
			if(temp.getStatus().substring(8).compareTo("Coming Soon")!=0){
				System.out.println(temp.getMovietitle());
				System.out.println(temp.getType());
				System.out.println(temp.getDuration()+" minutes");
				System.out.println("Average Rating: "+ temp.getAvgRating());
				System.out.println("Ticket Sales : "+ temp.getTicketSales());
				System.out.println(temp.getDirector());
				System.out.println(temp.getCast());
				System.out.println("Synopsis");
				System.out.println(temp.getSynopsis().substring(10));
				System.out.println("-----------------------------------------------------------------");
			}
		}
	}
	
	public void Login() throws ParseException {
		String filename = "admin1.txt" ;
	    String username,password;
	    int choice,choice1;
	    int mainOption,subOption;
	    loadData();
	    this.printMenuOptions();
	    
//	    login function
	    do{
			  System.out.print("Choice: ");
			  choice = Integer.parseInt(input.nextLine());
			  switch(choice){
			  case 1:
				  do{
		        	System.out.println("Log in:");
		      	    System.out.println("Username: ");
		      	    username = input.nextLine();
		      	    System.out.println("Password: ");
		      	    password = input.nextLine();
		      	    check = new User(username, password);
		      	    if (check.auth()!=true)
		      	    		System.out.println("Please enter a valid id/password");
		      	   
		        }while(check.auth()!=true);		  

			    if(check.auth()){ 
			        System.out.println("You are logged in");
			    do{	    
			    printAdminMainOptions();
			    mainOption = input.nextInt();
			    input.nextLine();
				switch(mainOption){
				
				case 1:
					do{   System.out.println();
				    	  System.out.println("1.Create movie");
						  System.out.println("2.Update movie");
						  System.out.println("3.Remove Movie");
						  System.out.println("0.Return to previous menu");
						  System.out.print("Choice:");
						  subOption = input.nextInt();
						  input.nextLine();
						  
					switch(subOption){
					//create movie
					case 1:
						if(!createMovie())
							continue;
			            break;
			            	            
			        //edit movie    
					case 2:
						editMovie();
					    break;
					//delete movie (set movie to end of showing)    
					case 3:
						removeMovie();
						break;
					//return admin main menu
					case 4:
						break;
					default:
			 			System.out.println("Please enter a valid choice");
			 			break;
					}
					}while(subOption!=0);
					break;
				
					
				//create update remove ShowTime
				case 2:
					  do{
					 System.out.println();
			    	  System.out.println("1.Create showtime for movie");
					  System.out.println("2.Update showtime for movie");
					  System.out.println("3.Remove showTime for movie");
					  System.out.println("0.Return to previous menu");
					  System.out.print("Choice: ");			  
					  choice1 = input.nextInt();
					  input.nextLine();
					  
					  switch(choice1){			  
					  //create ShowTime
					  case 1:			   
						  createShowTime();
						  break;
					  //update ShowTime	
					  case 2:
						  updateShowTime();
						  break;
					  //remove ShowTime
					  case 3:
						  System.out.println();
						  removeShowTime();
						  break;
							
					  case 4:
						  break;
					default:
						 System.out.println("Please enter a valid option");
						 break;
							 }		
					  }	while(choice1!=0);
					  
				case 3:			
					break;
				case 4:
					break;			
				default:
		 			System.out.println("Please enter a valid choice");
		 			break;
				}
		
			}while(mainOption!=0);
	    
			    }
			break;
			
		case 2:
			  int custOption;
			  System.out.println("Pleas enter your choice");
			  System.out.println("1) Make a booking");
			  System.out.println("2) View history");
			  System.out.println("3) Rate and Review a Movie");
			  System.out.println("4) Exit");
			  custOption=input.nextInt();
			  if(custOption==1){
				System.out.println("(1) Choose by Movie");
				System.out.println("(2) Choose by CinePlex");
				subOption = Integer.parseInt(input.nextLine());
				if(subOption==1){
					//Display Showing movies
					Movie movie=chooseMovie();
					//Print All the Cineplexes with the movie running since we know the movie name as parameter 
					String Cineplex = chooseCinePlex();
					// Display Showtimes and Availablity of seats
					ShowTime showTime = chooseShowTime(Cineplex,movie);
					//Go to Seating arrangement to book the seats
					
					System.out.println("Please Choose your Seats ");
					int screenNo = showTime.getCinemaId();
					Screen screen = chooseScreen(screenNo);
					System.out.println("Enter the No of Tickets: ");
					int noOfTickets = Integer.parseInt(input.nextLine());
					ArrayList<Ticket> tickets= chooseTickets(screen,noOfTickets,movie,showTime);
					
					// Book Tickets and Get Details for ticket and print inventory.
					Booking booking  = chooseBooking(tickets);
					printBooking(booking);
					System.out.println("Done! ");
					}
				else if(subOption==2){
					//Print All the Cineplexes with the movie running since we know the movie name as parameter 
					String Cineplex = chooseCinePlex();
					//Display Showing movies
					Movie movie=chooseMovie();
					// Display Showtimes and Availablity of seats
					ShowTime showTime = chooseShowTime(Cineplex,movie);
					//Go to Seating arrangement to book the seats
					System.out.println("Please Choose your Seats ");
					int screenNo = showTime.getCinemaId();
					Screen screen = chooseScreen(screenNo);
					System.out.println("Enter the No of Tickets: ");
					int noOfTickets = Integer.parseInt(input.nextLine());
					ArrayList<Ticket> tickets= chooseTickets(screen,noOfTickets,movie,showTime);
					// Book Tickets and Get Details for ticket and print inventory.
					Booking booking  = chooseBooking(tickets);
					printBooking(booking);
					System.out.println("Done! ");
					}
				}
			  else if (custOption==3){
				//Display All Movies
				Movie movie = chooseMovie();
				System.out.println("Enter Rating for Movie(1-5)");
				int Rating = Integer.parseInt(input.nextLine());
				if(Rating<=5&& Rating>=1){
					System.out.println("Enter Review(Only 1 Para): ");
					String review = input.nextLine();
					movie.addReview(review, Rating);
				}
			}
			break;
		case 3:
			displayDetailMovie();
			break;
		case 4:
			System.out.println("Program terminating...");
			System.exit(0);
		default:
			System.out.println("Please enter a valid choice");
			break;
		}
		}while(choice<1 || choice >3);
	 }//End of Main
}
