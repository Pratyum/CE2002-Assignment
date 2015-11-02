import java.io.Serializable;

public class Movie implements Serializable {
	public static final int MAX_REVIEW = 10; //define max number of reviews
	private int movieId;
	private String movieTitle ;
	private String type ;
	private String rating;
	private String status ;
	private String synopsis ;
	private String director ;
	private String cast;
	private int ticketSales;
	private int totalRating;
	private int ratingNum;
	private float avgRating;
	private String duration;
	 // typically rating can only be added with a review, unless it is reset using setRating()
	private Review[] review;

	public Movie(int MovieId,String Movietitle, String Type, String Rating, String Status, String Synopsis,String Director, String Cast,String Duration){
		this.movieId=MovieId;
		this.movieTitle=Movietitle;
		this.type=Type;
		this.rating = Rating;
		this.status=Status;
		this.synopsis=Synopsis;
		this.director=Director;
		this.cast=Cast;
		this.duration =Duration;
	} 
	
	public Movie(int MovieId,String Movietitle, String Type, String Rating, String Status, String Synopsis,String Director, String Cast, String TicketSales,String TotalRating, String RatingNum,String Duration)  {
		this.movieId=MovieId;
		this.movieTitle=Movietitle;
		this.type=Type;
		this.rating = Rating;
		this.status=Status;
		this.synopsis=Synopsis;
		this.director=Director;
		this.cast=Cast;
		this.ticketSales = Integer.valueOf(TicketSales);
		this.totalRating=Integer.valueOf(TotalRating);
		this.ratingNum=Integer.valueOf(RatingNum);
		this.duration= Duration;

	} 
	
	public Movie(){
		this.movieId=0;
		this.movieTitle="";
		this.type="";
		this.status="";
		this.synopsis="";
		this.director="";
		this.cast="";
		this.ticketSales= 0;
		this.totalRating=0;
		this.ratingNum=0;
		this.avgRating=0;
	}
	
	public int getMovieId() { return movieId ; }
	public String getMovietitle() { return movieTitle ; }
	public String getType() { return type ; }
	public String getRating(){return rating;}
	public String getStatus() { return status ; }
	public String getSynopsis() { return synopsis ; }
	public String getDirector() { return director ; }
	public String getCast() { return cast ; }
	public int getTicketSales(){return ticketSales;}
	public float getAvgRating() { return avgRating ; }
	public String getDuration(){return duration;}
	public void setMovietitle(String Movietitle) { this.movieTitle=Movietitle; }
	public void setType(String Type) { this.type=Type; }
	public void setRating(float AvgRating) { this.avgRating=AvgRating; }
	public void setStatus(String Status) { this.status=Status; }
	public void setSynopsis(String Synopsis) { this.synopsis=Synopsis; }
	public void setDirector(String Director) { this.director=Director; }
	public void setCast(String Cast) { this.cast=Cast; }
	public void setRating(String rating) {this.rating = rating;}
	public void incTicketSales(){this.ticketSales ++;}
	public void addReview(String ReviewText, int ReviewRating){ // construct a new review and add as movie attribute.
		Review custReview =  new Review(ReviewText, ReviewRating,movieId) ;
		this.review[this.ratingNum]= custReview;
		addRating(custReview.getReviewRating());
		this.ratingNum += 1;
	} 
	public void addRating(int rating){
		this.ratingNum++;
		this.totalRating += rating;
		avgRating = totalRating/ratingNum;
		this.rating = Float.toString(this.avgRating);
	}

	public boolean equals(Object o) {
		if (o instanceof Movie) {
			Movie m = (Movie)o;
			return (getMovietitle().equals(m.getMovietitle()));
		}
		return false;
	}
}