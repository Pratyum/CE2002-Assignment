import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class SystemStorage extends StorageHandler{
	public static final String SEPARATOR1 = "|";
	public static final String SEPARATOR2 = ":";
	// an example of reading
		public static ArrayList readPrice(String filename) throws IOException {
			// read String from text file
			ArrayList stringArray = (ArrayList)read(filename);
			ArrayList alr = new ArrayList() ;// to store Professors data

	        for (int i = 0 ; i < stringArray.size() ; i++) {
					String st = (String)stringArray.get(i);
					// get individual 'fields' of the string separated by SEPARATOR
					StringTokenizer star = new StringTokenizer(st , SEPARATOR2);	// pass in the string to the string tokenizer using delimiter ","
	                while(star.hasMoreTokens()){
					String  pricetype = star.nextToken().trim();	// first token
					double  price = Double.parseDouble(star.nextToken().trim());	// second token
					
					// create Professor object from file data
					Price p=new Price(pricetype,price);
					// add to Professors list
					alr.add(p) ;
	                }
				}
			
			
				return alr ;
		
		}
		
		// an example of saving
		  public static void saveupdatedPrice(String filename, List al) throws IOException {
		  		List alw = new ArrayList() ;// to store Professors data

		          for (int i = 0 ; i < al.size() ; i++) {
		  				Price p = (Price)al.get(i);
		  				StringBuilder st =  new StringBuilder() ;
		  			
		  					st.append(p.getPriceType().trim());
		  					st.append(SEPARATOR2);
		  					st.append(String.valueOf(p.getPrice()));
		  				
		  				
		  				alw.add(st.toString()) ;
		  			}
		  			write(filename,alw);
		  	}
		  
		  // an example of reading
			public static ArrayList readHoliday(String filename) throws IOException {
				// read String from text file
				ArrayList stringArray = (ArrayList)read(filename);
				ArrayList alr = new ArrayList() ;// to store Professors data

		        for (int i = 0 ; i < stringArray.size() ; i++) {
						String st = (String)stringArray.get(i);
						// get individual 'fields' of the string separated by SEPARATOR
						StringTokenizer star = new StringTokenizer(st , SEPARATOR1);	// pass in the string to the string tokenizer using delimiter ","
		                while(star.hasMoreTokens()){
		                int HolidayId=Integer.parseInt(star.nextToken().trim());//first token
						String  HolidayName = star.nextToken().trim();	// second token
						String  HolidayDate = star.nextToken().trim();	// third token

						// create Professor object from file data
						Holiday h=new Holiday(HolidayId,HolidayName,HolidayDate);
						// add to Professors list
						alr.add(h) ;
		                }
					}
				
				
					return alr ;
			
			}
			
			// an example of saving
			  public static void saveupdatedHoliday(String filename, List al) throws IOException {
			  		List alw = new ArrayList() ;// to store Professors data

			          for (int i = 0 ; i < al.size() ; i++) {
			  				Holiday h = (Holiday)al.get(i);
			  				StringBuilder st =  new StringBuilder() ;
			  						  			
			  				st.append(h.getHolidayId());
							st.append(SEPARATOR1);
							st.append(h.getHolidayName());
							st.append(SEPARATOR1);
							st.append(h.getHolidayDate());
			  				
			  				
			  				alw.add(st.toString()) ;
			  			}
			  			write(filename,alw);
			  	}

			@Override
			public ArrayList readObject() throws IOException, ParseException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void saveFile() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void print(ArrayList a) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void saveObject(String filename, List al) throws IOException {
				// TODO Auto-generated method stub
				
			}
}
