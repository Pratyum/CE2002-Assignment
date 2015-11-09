
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
	private ArrayList<Booking> ListOfBooking;
	private ArrayList<Ticket> ListOfTickets;
	public Scanner input = new Scanner(System.in);
	
	private void loadData(){
		MovieStorage ms = new MovieStorage();
		ScreenStorage ss = new ScreenStorage();
		BookingStorage bs = new BookingStorage();
		TicketStorage ts = new TicketStorage();
		try {
			ListOfScreen = ss.readObject();
			System.out.println("Screens Loaded!");
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
		try {
			ListOfBooking= bs.readObject();
			System.out.println("Booking Loaded!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Booking File Not Found!" + e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ListOfTickets= ts.readObject();
			System.out.println("Tickets Loaded!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Ticket File Not Found!" + e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	    System.out.println("3.Configure System");
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
        try {
			check.ShowMovie(choice);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	            
		System.out.println("Enter movie ID or 0 to return to previous menu:");
		choice= input.nextInt();
		if (choice==0)
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
			System.out.println("Enter movie ID: ");
			int movieId=input.nextInt();
			input.nextLine();
			System.out.println("Enter time in hh:mm");
			String time=input.nextLine();
			
			try {
				check.createShowTime(cineplexId, cinemaId, movieId, time);
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
		System.out.println("//////////////////////////////////////////////////");
		System.out.println("// Movie Id	Movie Name	Rating //");
		System.out.println("//----------------------------------------------//");
		for(int i=0;i<ListOfMovies.size();++i){
			Movie temp = (Movie) ListOfMovies.get(i);
			if(temp.getStatus().equals("Now Showing")){
				System.out.println("//    "+temp.getMovieId()+"		"+temp.getMovietitle()+"	"+temp.getAvgRating()+"	//");
			}
		}
		System.out.println("//////////////////////////////////////////////////");
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
		System.out.println("//////////////////////////////////////////");
		System.out.println("// Sno		     Venue		//");
		System.out.println("//--------------------------------------//");
		System.out.println("//  1		GV Jurong Point		//");
		System.out.println("//  2		GV Marina Square	//");
		System.out.println("//  3		GV Suntec City		//");
		System.out.println("//////////////////////////////////////////");
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
			//  Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			//  Auto-generated catch block
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
	
	private ShowTime chooseShowTime(String CinePlex , Movie movie,String ShowDate){
		ArrayList<ShowTime> ListOfMovieTimes = loadMovieShowTime(CinePlex, movie);
		if(ListOfMovieTimes.size()!=0){
		for(int i=0;i<ListOfMovieTimes.size();++i){
			System.out.println("("+(i+1)+") Show Time: "+((ShowTime)ListOfMovieTimes.get(i)).getStrTime());
		}
		System.out.println("Choose ShowTime:");
		int showTimeChoice = input.nextInt();
		ListOfMovieTimes.get(showTimeChoice-1).setDate(ShowDate);
		return ListOfMovieTimes.get(showTimeChoice-1);
		}
		else{
			System.out.println("Sorry No Shows Available!");
			return null;
		}
	}
	
	private Screen chooseScreen(int screenNo,String ShowDate){
		if(ListOfScreen.size()!=0){
			for(int i=0;i<ListOfScreen.size();++i){
				if((ListOfScreen.get(i).getScreenNo()== screenNo)&&(ListOfScreen.get(i).getStrDate().equals(ShowDate)))
					return ListOfScreen.get(i);
			}
		}
		ListOfScreen.add(new Screen(screenNo, ShowDate));
		return ListOfScreen.get(ListOfScreen.size()-1);
	}
	
	private ArrayList<Ticket> chooseTickets(Screen screen , int noOfTickets,Movie movie,ShowTime showTime){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date();
		String TID = String.valueOf(date.getDate()).concat(String.valueOf(date.getMonth()).concat(String.valueOf(date.getYear()))); 
		long TransID = Long.valueOf(TID.toString());
		if(screen.getSeatsFree() <noOfTickets){
			
			System.out.println("There is only"+screen.getSeatsFree()+" seats free! Please try again!" );
		}else{
		ArrayList<Ticket> ListOfTickets = new ArrayList<>();
		for(int i=0;i<noOfTickets;++i){
		screen.printScreen();
		System.out.println("Enter the Row character:");
		char rowNo= input.next().charAt(0);
		System.out.println("Enter the Seat Number:");
		int seatno = input.nextInt();
		boolean isStudent = false, isElder = false;
		if(screen.AssignSeat(rowNo, seatno)){
			System.out.println("Are You a Student(Y/N)");
			char details =input.next().charAt(0);
			if(details=='Y'){
				isStudent = true;
			}else{
				System.out.println("Are you above 65? (Y/N)");
				details = input.next().charAt(0);
				if(details=='Y'){
					isElder = true;
				}
			}
			Ticket ticket = new Ticket(TransID,movie.getType(), "Platinum", String.valueOf(rowNo).concat(String.valueOf(seatno)),showTime.getStrDate() , showTime.getStrTime(), showTime.getCineplexName(), showTime.getMovieTitle(), isStudent, isElder);
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
			//  Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private double calculatePrice(ArrayList<Ticket> tickets){
		float total= 0;
		for(int i=0;i<tickets.size();++i){
			total+=tickets.get(i).getPrice();
		}
		return total;
	}
	
	private Booking chooseBooking(ArrayList<Ticket> tickets){
		System.out.println("Please Enter Your Details");
		System.out.println("NAME: ");
		String name = input.next();
		System.out.println("Email: ");
		String email = input.next();
		System.out.println("Phone Number: ");
		int number = input.nextInt();
		TicketStorage ts = new TicketStorage();
		try {
			ListOfTickets.addAll(tickets);
			ts.saveObject("ticket.txt",ListOfTickets);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Booking booking = new Booking(name, email, number,calculatePrice(tickets),tickets.get(0).getTransactionID());
		return booking;
	}
	
	private void printBooking(Booking booking,ArrayList<Ticket> tickets){
		// Create pretty print of Booking
		booking.display();
		System.out.println("-----------------Ticket Details-----------------");
		tickets.get(0).display();	
		for(int i=0;i<tickets.size();++i){
			System.out.print(tickets.get(i).getTicketNo()+" ");
		}
		System.out.println();
		System.out.println("-----------------Payment Details-----------------");
		System.out.println("Total Price: $"+calculatePrice(tickets)+" /-");
		System.out.println("Thanks For Shopping with us!Hope to see you soon!");
		System.out.println("------------------------------------------------");
		
	}
	
	private void displayDetailMovie(){
		System.out.println("-----------------Movies on Show!-----------------");
		System.out.println("-------------------------------------------------");
		for(int i=0;i<ListOfMovies.size();++i){
			Movie temp = (Movie) ListOfMovies.get(i);
			if(temp.getStatus().equals("Now Showing")){
				temp.display();	
			}
		}
	}
	
	private void updateList(Movie movie){
		for(int i=0;i<ListOfMovies.size();++i){
			if(ListOfMovies.get(i).getMovieId()==movie.getMovieId()){
				ListOfMovies.set(i, movie);
				break;
			}
		}
		MovieStorage ms  = new MovieStorage();
		try {
			ms.saveObject("movie.txt", ListOfMovies);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void displayReview(int MovieId){
		for(int i=0;i<ListOfMovies.size();++i){
			if(ListOfMovies.get(i).getMovieId()==MovieId){
				if(ListOfMovies.get(i).getReviews().size()==0){
					System.out.println("Sorry There are no Reviews :( !");
				}
				else{
					System.out.println("Reviews for "+ListOfMovies.get(i).getMovietitle());
					for(int j=0;j<ListOfMovies.get(i).getReviews().size();++j){
						System.out.println("Rating: "+ ListOfMovies.get(i).getReviews().get(j).getReviewRating());
						System.out.println("Review: "+ ListOfMovies.get(i).getReviews().get(j).getReviewText());
						System.out.println("----------------------------------------------------------");
					}
				}
			}
		}
	}
	
	private void updateMovieSales(Movie movie , int ticketSales){
		for(int i=0;i<ListOfMovies.size();++i){
			if(movie.getMovieId()==ListOfMovies.get(i).getMovieId()){
				ListOfMovies.get(i).incTicketSales(ticketSales);
				MovieStorage ms = new MovieStorage();
				try {
					ms.saveObject("movie.txt", ListOfMovies);
				} catch (IOException e) {
					// Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private void updateBooking(Booking booking){
		BookingStorage bs = new BookingStorage();
		ListOfBooking.add(booking);
		try {
			bs.saveObject("booking.txt", ListOfBooking);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void checkBooking(int phoneNo){
		boolean flag= true;
		  for(int i=0;i<ListOfBooking.size();++i){
			  if(ListOfBooking.get(i).getNumber()==phoneNo){
				  flag = false;
				  ListOfBooking.get(i).display();
				  System.out.println("-----------------Ticket Details-----------------");
				  for(int j=0;j<ListOfTickets.size();++j){
					  if(ListOfTickets.get(j).getTransactionID()==ListOfBooking.get(i).getTID()){
						  //TODO Add the text
						  ListOfTickets.get(j).display();
						  System.out.println(ListOfTickets.get(j).getTicketNo());
						  System.out.println("------------------------------------------------");
					  }
				  }
			  }
		  }
		  if(flag){
			  System.out.println("Sorry! No such Number exists!");
		  }
	}
	
	public void Login() throws ParseException {
		Scanner input = new Scanner(System.in);
		String filename = "admin1.txt" ;
	    String username,password;
	    int choice,choice1;
	    int mainOption,subOption;
	    loadData();
	    this.printMenuOptions();
	    
//	    login function
	    do{
			  System.out.print("Choice: ");
			  choice = input.nextInt();
			  switch(choice){
			  case 1:
				  do{
		        	System.out.println("Log in:");
		      	    System.out.println("Username: ");
		      	    username = input.next();
		      	    System.out.println("Password: ");
		      	    password = input.next();
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
						else
							createShowTime();
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
					case 0:
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
					  case 0:
						  break;
					default:
						 System.out.println("Please enter a valid option");
						 break;
							 }		
					  }	while(choice1!=0);
					  break;
				case 3:	
					// Configure
					int tickettype;
					int choice13;
					do
					{
					System.out.println("Please choose setting to configure:");
					System.out.println("1.Ticket Price Rate");
					System.out.println("2.Public Holidays");
					System.out.println("0.Back");
					System.out.print("Choice: ");
				    choice13 = input.nextInt();
					input.nextLine();
					switch(choice13)
					{
					case 1:
						do
						{
							User p=new User();
							System.out.println("====Current Ticket Price====");
							System.out.println("1) Normal Price");
							System.out.println("============================");
						    p.ShowNormalPrice();
						    System.out.println("2) 3D Price");
						    System.out.println("============================");
						    p.Show3DPrice();
						    System.out.println("============================");
						    System.out.println("3) Back");
							System.out.println("============================");
							System.out.println("Please select index for the type of ticket price you want to change:");
							tickettype=input.nextInt();
							input.nextLine();
							if(tickettype==1)
							{
								System.out.println("Normal Price");
								System.out.println("============");
								p.ShowNormalPrice();
								System.out.println("============");
								p.ShowPriceAttribute(tickettype);
								System.out.println("Select Attribute to change:");
								int ticketattribute=input.nextInt();
								input.nextLine();
								p.UpdateNormalPrice(ticketattribute);
								System.out.println("Ticket Price Updated");
							}	
							if(tickettype==2)
							{
								System.out.println("3D Price");
								System.out.println("========");
								p.Show3DPrice();
								System.out.println("============");
								p.ShowPriceAttribute(tickettype);
								System.out.println("Select Attribute to change:");
								int ticketattribute=input.nextInt();
								input.nextLine();
								p.Update3DPrice(ticketattribute);
								System.out.println("Ticket Price Updated");
							}	
						}while(tickettype<3);
						break;
					case 2:
						int holidaychoice;
						do
						{
							User holiday=new User();
							System.out.println("Current List of Holiday:");
							System.out.println("ID      Name           Date");
							System.out.println("===========================");
							holiday.ShowHoliday();
							System.out.println();
							System.out.println("===========================");
							System.out.println("1.Create New Public Holiday");
							System.out.println("2.Remove a Public Holiday");
							System.out.println("3.Back");
							System.out.println("Enter Choice:");
							holidaychoice=input.nextInt();
							input.nextLine();
							switch(holidaychoice)
							{
							case 1:
								System.out.println("Please enter a Date(dd/mm/yy):");
								String holidaydate=input.nextLine();
								System.out.println("Please enter Holiday Name:");
								String holidayname=input.nextLine();
								User d=new User();
								d.CreateHoliday(holidayname, holidaydate);
								System.out.println("Holiday Created!");
								break;
							case 2:
								User rh=new User();
								System.out.println("Current List of Holiday:");
								System.out.println("ID      Name           Date");
								System.out.println("===========================");
								rh.ShowHoliday();
								System.out.println();
								System.out.println("===========================");
								System.out.println("Enter holiday id to remove:");
								int idtoremove=input.nextInt();
								rh.RemoveHoliday(idtoremove);
								System.out.println("Holiday Removed");
								break;
							
							}		
						}while(holidaychoice<3);
						break;
					case 0:
						break;
					default:
			 			System.out.println("Please enter a valid choice");
					}
					}while(choice13!=0);
					break;
				case 4:
					break;	
				case 0:
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
			  do{
			  System.out.println("Pleas enter your choice");
			  System.out.println("1) Make a booking");
			  System.out.println("2) View history");
			  System.out.println("3) Rate and Review a Movie");
			  System.out.println("0) Exit");
			  custOption=input.nextInt();
			  if(custOption==0){
				  System.out.println("Program Terminating...");
				  System.exit(0);
			  }
			  else if(custOption==1){
				Movie movie=null;
				String Cineplex=null;
				System.out.println("(1) Choose by Movie");
				System.out.println("(2) Choose by CinePlex");
				
				do{
					subOption = input.nextInt();
					if(subOption==1){
					//Display Showing movies
					movie=chooseMovie();
					//Print All the Cineplexes with the movie running since we know the movie name as parameter 
					Cineplex = chooseCinePlex();
					// Choose Date
					}
				else if(subOption==2){
					//Print All the Cineplexes with the movie running since we know the movie name as parameter 
					Cineplex = chooseCinePlex();
					//Display Showing movies
					movie=chooseMovie();
				}
				else{
					continue;
				}
				}while(subOption>2);
					//ShowDate
					System.out.println("Enter the date for Shows(dd-mm-yyyy):");
					String showDate = input.next();
					// Display Showtimes and Availablity of seats
					ShowTime showTime = chooseShowTime(Cineplex,movie,showDate);
					if(showTime==null){
						System.out.println();
						continue;
					}
					//Go to Seating arrangement to book the seats
					System.out.println("Please Choose your Seats ");
					int screenNo = showTime.getCinemaId();
					Screen screen = chooseScreen(screenNo,showDate);
					System.out.println("Enter the No of Tickets: ");
					int noOfTickets = input.nextInt();
					
					ArrayList<Ticket> tickets= chooseTickets(screen,noOfTickets,movie,showTime);
					// Book Tickets and Get Details for ticket and print inventory.
					Booking booking  = chooseBooking(tickets);
					updateMovieSales(movie,tickets.size());
					updateBooking(booking);
					printBooking(booking,tickets);
				}
			  else if (custOption ==2){
				  int phoneNo;
				  do{
				  System.out.println("Enter you Phone No or 0 to go back: ");
				  phoneNo = input.nextInt();
				  if(phoneNo!=0)
					  checkBooking(phoneNo);
				  }while(phoneNo!=0);
			  }
			  else if (custOption==3){
				//Display All Movies
				Movie movie = chooseMovie();
				System.out.println("Enter Rating for Movie(1-5)");
				int Rating = input.nextInt();
				if(Rating<=5&& Rating>=1){
					System.out.println("Enter Review(Only 1 Para): ");
					input.nextLine();
					String review = input.nextLine();
					movie.addReview(review, Rating);
					updateList(movie);
					System.out.println("Thanks for the Review!");
				}
			}
			  }while(custOption!=0);
			break;
		case 3:
			int movieID,choice31=1;
			do{
			displayDetailMovie();
			System.out.println("Enter the Movie ID for Seeing thier Reviews or 0 to go back!");
			movieID = input.nextInt();
			if(movieID!=0){
			do{
			displayReview(movieID);
			System.out.println("Press 0 to go back!");
			choice31 = input.nextInt();
			}while(choice31!=0);
			}
			}while(movieID!=0);
			break;
		case 4:
			System.out.println("Program terminating...");
			System.exit(0);
			break;
		default:
			System.out.println("Please enter a valid choice");
			break;
		}
		}while(choice<1 || choice >3);
	 }//End of Login
}
