import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MovieStorage extends StorageHandler{
	public static final String SEPARATOR = " ";
	public static final String SEPARATOR1 = "|";
	
	public ArrayList<Movie> readObject() throws IOException {
		// read String from text file
		ArrayList<String> stringArray = (ArrayList<String>)read("movie.txt");
		ArrayList<Movie> alr = new ArrayList<>();// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR1);	// pass in the string to the string tokenizer using delimiter ","
                while(star.hasMoreTokens()){
                int movieId=Integer.parseInt(star.nextToken().trim());
				String  movietitle = star.nextToken().trim();	
				String  type = star.nextToken().trim();	
				String  rating = star.nextToken().trim();	
				String  status = star.nextToken().trim();	
				String  synopsis = star.nextToken().trim();	
				String  director = star.nextToken().trim();	
				String  cast = star.nextToken().trim();	
				String duration = star.nextToken().trim();
				Movie m = new Movie(movieId,movietitle,type,rating,status,synopsis,director,cast,duration);
				alr.add(m) ;
                }
			}		
			return alr ;
	
	}
	
	
	 public static void saveupdatedMovie(String filename, List al) throws IOException {
	  		List alw = new ArrayList() ;

	          for (int i = 0 ; i < al.size() ; i++) {
	  				Movie m = (Movie)al.get(i);
	  				
	  					StringBuilder st =  new StringBuilder() ;
  						st.append(m.getMovieId());
  						st.append(SEPARATOR1);	  					
	  					st.append(m.getMovietitle().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getType().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getRating().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getStatus().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getSynopsis().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getDirector().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getCast().trim());
	  					st.append(SEPARATOR1);
	  					st.append(m.getDuration().trim());
	  					st.append(SEPARATOR1);
	  				
	  				alw.add(st.toString()) ;
	  			}
	  			write(filename,alw);
	  	}

	 public void printMovieTitle(){
			try {
				ArrayList al=new ArrayList();
				MovieStorage ms=new MovieStorage();
				al = ms.readObject() ;
				
				for (int i = 0 ; i < al.size() ; i++) {
						Movie m = (Movie)al.get(i);
						System.out.println(i+1+") "+m.getMovietitle());
						
				}
				
				
			}catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
		}
	 public void saveObject(String filename, List al) throws IOException {
			List alw = new ArrayList() ;

		    for (int i = 0 ; i < al.size() ; i++) {
					Movie m = (Movie)al.get(i);
					StringBuilder st =  new StringBuilder() ;
					if(i==al.size()-1){
						st.append(m.getMovieId());
						st.append(SEPARATOR1);
						st.append("Movie: "+m.getMovietitle().trim());
						st.append(SEPARATOR1);
						st.append("Type: "+m.getType().trim());
						st.append(SEPARATOR1);
						st.append("Rating: ").append(m.getRating().trim());
						st.append(SEPARATOR1);
						st.append("Status: ").append(m.getStatus().trim());
						st.append(SEPARATOR1);
						st.append("Synopsis: ").append(m.getSynopsis().trim());
						st.append(SEPARATOR1);
						st.append("Director: ").append(m.getDirector().trim());
						st.append(SEPARATOR1);
						st.append("Cast: ").append(m.getCast().trim());
						st.append(SEPARATOR1);
						st.append("Duration: ").append(m.getDuration().trim());
	  					st.append(SEPARATOR1);
					}
					else{
						st.append(m.getMovieId());
						st.append(SEPARATOR1);
						st.append(m.getMovietitle().trim());
						st.append(SEPARATOR1);
						st.append(m.getType().trim());
						st.append(SEPARATOR1);
						st.append(m.getRating().trim());
						st.append(SEPARATOR1);
						st.append(m.getStatus().trim());
						st.append(SEPARATOR1);
						st.append(m.getSynopsis().trim());
						st.append(SEPARATOR1);
						st.append(m.getDirector().trim());
						st.append(SEPARATOR1);
						st.append(m.getCast().trim());
						st.append(SEPARATOR1);
						st.append(m.getDuration().trim());
	  					st.append(SEPARATOR1);
					}
					alw.add(st.toString()) ;
				}
				write(filename,alw);
		}
	 
	 
	@Override
	public void saveFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void print(ArrayList a) {
		// TODO Auto-generated method stub
		
	}

}
