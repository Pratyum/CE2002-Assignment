public class Booking{
	private String name,email;
	private long TID;
	private int number;
	
	public Booking(String name,String email, int number, long TID) {	
		this.name=name;
		this.email=email;
		this.number=number;
		this.TID = TID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getNumber() {
		return number;
	}
	
	public long getTID() {
		return TID;
	}

}
