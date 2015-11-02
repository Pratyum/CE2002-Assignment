public class Booking{
	private String name,email,TID;
	private int number;
	
	public Booking(String TID, String name,String email,int number) {	
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
	
	public String getCurrentTime() {
		return TID;
	}

}
