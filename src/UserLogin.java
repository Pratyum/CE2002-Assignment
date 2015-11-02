
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
	
	public static void main(String[] args) throws ParseException {
		String filename = "admin1.txt" ;
	    Scanner input = new Scanner(System.in);
        User check;
	    String username,password;
	    int choice,choice1;
	    int mainOption,subOption;
	    
	    System.out.println("====Menu====");
	    System.out.println("1.Staff");
	    System.out.println("2.Customer");
	    System.out.println("3.View All Movies with Information");
	    System.out.println("4.Exit");
	    
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
			    User admin=new User();	    
			    System.out.println();
			    System.out.println("1.Create/Update/Remove Movie Listing");
			    System.out.println("2.Create/Update/Remove cinema showtime and movie to be shown");
			    System.out.println("3.Configure System");
			    System.out.println("0.Exit");
			    System.out.print("Choice: ");	    
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
								continue;
						    }
						    }
							catch(InputMismatchException e){
								System.out.println("Please enter a value input!");	
								input.next();
								continue;
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
					    
					    admin.CreateMovie(movietitle,type,rating,status,synopsis,director,cast,duration);
			            break;
			            	            
			        //edit movie    
					case 2:
					{	
						
						System.out.println();
						System.out.println("1.Show movies showing now");
						System.out.println("2.Show archived movies");
						System.out.println("3.Show all movies");								
						choice= input.nextInt();
						input.nextLine();					
			            admin.ShowMovie(choice);	            
						System.out.println("Enter movie ID or 0 to return to previous menu:");
						choice= input.nextInt();
						input.nextLine();
						if (choice==-1)
							break;
						admin.ChooseEditMovie(choice);					
					    System.out.println("Choose category to edit:");			    
					    int category=input.nextInt();
					    input.nextLine();
					    admin.UpdateMovie(category, choice);
					    break;
		          
					}			
					    
					//delete movie (set movie to end of showing)    
					case 3:
						int codeToRemove;
					
						System.out.println();
						admin.ShowMovie(3);
						System.out.println("Enter movie code or 0 to return to previous menu:");				
						codeToRemove=input.nextInt();
						input.nextLine();
						if(codeToRemove!=0)
							admin.RemoveMovie(codeToRemove);
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
						  System.out.println();
							  System.out.println("All current showtime");
							  try {
								  admin.showShowTime();
							      }
							  catch (IOException e) {
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
									break;
								System.out.println("Enter cinema ID: ");
								int cinemaId=input.nextInt();
								input.nextLine();					
								
								//show movies showing now
								admin.ShowMovie(1);
								System.out.println("Enter movie ID");
								int movieId=input.nextInt();
								input.nextLine();
								System.out.println("Enter date in dd-mm-yyyy");
								String date=input.nextLine();
								System.out.println("Enter time in hh:mm");
								String time=input.nextLine();
								
								admin.createShowTime(cineplexId,cinemaId,movieId,date,time);
							  	break;
					  
					  //update ShowTime	
					  case 2:
							try {
								System.out.println();
								admin.showShowTime();
							} catch (IOException e) {
								e.printStackTrace();
							}
							System.out.println("Enter show time ID you wish to edit: ");
							System.out.println("OR");
							System.out.println("Enter 0 to return to previous menu");
							choice=input.nextInt();
							input.nextLine();
							if (choice==0)
								break;
							admin.EditShowTime(choice);			  
							break;
						  
					  //remove ShowTime
					  case 3:
						  System.out.println();
							 System.out.println("All current showtime");
							 try {
							 admin.showShowTime();
							 } 	catch (IOException e) {
							 e.printStackTrace();
							 }
							System.out.println("Enter show time ID you wish to delete: ");
							System.out.println("OR");
							System.out.println("Enter 0 to return to previous menu");
							choice=input.nextInt();
							input.nextLine();
							if (choice==0)
								break;
							admin.DeleteShowTime(choice);		
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
			  custOption=Integer.parseInt(input.nextLine());
			if(custOption==1){
				System.out.println("(1) Choose by Movie");
				System.out.println("(2) Choose by CinePlex");
				subOption = Integer.parseInt(input.nextLine());
				if(subOption==1){
					//Display All movies
					
					ArrayList ListOfMovies = null;
					MovieStorage ms = new MovieStorage();
					try {
						ListOfMovies = ms.readObject();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("File Not Found!" + e.getMessage());
					}
					for(int i=0;i<ListOfMovies.size();++i){
						Movie temp = (Movie) ListOfMovies.get(i);
						if(temp.getStatus().substring(8).compareTo("Coming Soon")!=0){
							System.out.println("("+temp.getMovieId()+")"+temp.getMovietitle());
						}
					}
					System.out.println("Please Choose the Movie ID:");
					int movieChoice = Integer.parseInt(input.nextLine());
					int movieI = 0;
					for(int i=0;i<ListOfMovies.size();++i){
						Movie temp = (Movie) ListOfMovies.get(i);
						if(temp.getMovieId()== movieChoice){
							movieI = i;
							break;
						}
					}
					//Print All the Cineplexes with the movie running since we know the movie name as parameter 
					System.out.println("(1) GV Jurong Point");
					System.out.println("(2) GV Marina Square");
					System.out.println("(3) GV Suntec City");
					System.out.println("Choose CinePlex:");
					int cineplexChoice = Integer.parseInt(input.nextLine());
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
					// Display Showtimes and Availablity of seats
					ShowTimeStorage sts = new ShowTimeStorage();
					ArrayList ListOfShowTimes = new ArrayList<>();
					try {
						ListOfShowTimes = sts.readObject(Cineplex);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ArrayList ListOfMovieTimes = new ArrayList<>();
					for(int i=0;i<ListOfShowTimes.size();++i){
						ShowTime temp = (ShowTime) ListOfShowTimes.get(i);
						if(temp.getMovieTitle().compareTo(((Movie)ListOfMovies.get(movieI)).getMovietitle())==0){
							ListOfMovieTimes.add(temp);
						}
					}
					for(int i=0;i<ListOfMovieTimes.size();++i){
						System.out.println("("+(i+1)+") Show Time: "+((ShowTime)ListOfMovieTimes.get(i)).getStrTime());
					}
					System.out.println("Choose ShowTime:");
					int showTimeChoice = Integer.parseInt(input.nextLine());
					//Go to Seating arrangement to book the seats
					
					System.out.println("Please Choose your Seats ");
					int screenNo = ((ShowTime)ListOfMovieTimes.get(showTimeChoice-1)).getCinemaId();
					ScreenStorage ss = new ScreenStorage();
					ArrayList<Screen> ListOfScreen = new ArrayList<>();
					try {
						ListOfScreen = ss.readObject();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int screenI =0;
					if(ListOfScreen.size()!=0){
					for(int i=0;i<ListOfScreen.size();++i){
						if(ListOfScreen.get(i).getScreenNo()== screenNo)
							screenI = i;
					}
					
					}
					else{
						ListOfScreen.add(new Screen(screenNo));
					}
					System.out.println("Enter the No of Tickets: ");
					int noOfTickets = Integer.parseInt(input.nextLine());
					if(ListOfScreen.get(screenI).getSeatsFree
							() <noOfTickets){
						System.out.println("There is only"+ListOfScreen.get(screenI).getSeatsFree()+" seats free! Please try again!" );
					}else{
					ArrayList<Ticket> ListOfTickets = new ArrayList<>();
					for(int i=0;i<noOfTickets;++i){
					ListOfScreen.get(screenI).printScreen();
					System.out.println("Enter the Row character:");
					char rowNo = input.nextLine().charAt(0);
					System.out.println("Enter the Seat Number:");
					int seatno = Integer.parseInt(input.nextLine());
					boolean isStudent = false, isElder = false;
					if(ListOfScreen.get(screenI).AssignSeat(rowNo, seatno)){
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
						Ticket ticket = new Ticket(((Movie)ListOfMovies.get(movieI)).getType(), "Platinum", String.valueOf(rowNo).concat(String.valueOf(seatno)), ((ShowTime)ListOfShowTimes.get(showTimeChoice-1)).getStrDate(), ((ShowTime)ListOfShowTimes.get(showTimeChoice-1)).getStrTime(), ((ShowTime)ListOfShowTimes.get(showTimeChoice-1)).getCineplexName(), ((ShowTime)ListOfShowTimes.get(showTimeChoice-1)).getMovieTitle(), isStudent, isElder);
						System.out.println("Seat Assigned!");
						ListOfTickets.add(ticket);
					}else{
						System.out.println("Please Try Again!");
						i--;
					}
					}
					try {
						ss.saveObject("screen.txt", ListOfScreen);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// Book Tickets and Get Details for ticket and print inventory.
					Random randomGen = new Random();
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
					b.writeFile(String.valueOf(date.getTime()), name, email, number,ListOfTickets);
					System.out.println("Done! ");
					}
				}
			}else if (custOption==3){
				//Display All Movies
				ArrayList ListOfMovies = null;
				MovieStorage ms = new MovieStorage();
				try {
					ListOfMovies = ms.readObject();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("File Not Found!" + e.getMessage());
				}
				for(int i=0;i<ListOfMovies.size();++i){
					Movie temp = (Movie) ListOfMovies.get(i);
					if(temp.getStatus()!="Coming Soon"){
						System.out.println("("+temp.getMovieId()+")"+temp.getMovietitle());
					}
				}
				System.out.println("Please Choose the Movie ID:");
				int movieChoice = Integer.parseInt(input.nextLine());
				int movieI = 0;
				for(int i=0;i<ListOfMovies.size();++i){
					Movie temp = (Movie) ListOfMovies.get(i);
					if(temp.getMovieId()== movieChoice){
						movieI = i;
						break;
					}
				}
				System.out.println("Enter Rating for Movie(1-5)");
				int Rating = Integer.parseInt(input.nextLine());
				if(Rating<=5&& Rating>=1){
					System.out.println("Enter Review(Only 1 Para): ");
					String review = input.nextLine();
					((Movie)ListOfMovies.get(movieI)).addReview(review, Rating);
				}
			}
			break;
		case 3:
			System.out.println("Movies on Show!");
			ArrayList ListOfMovies = null;
			MovieStorage ms = new MovieStorage();
			try {
				ListOfMovies = ms.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("File Not Found!" + e.getMessage());
			}
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
