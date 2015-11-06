public class Booking{
	private String name,email,TID;
	private int number;
	
	public Booking(String TID, String name,String email,int number) implements DisplayInterface {	
		this.name=name;
		this.email=email;
		this.number=number;
		this.TID = TID;
	}
	
	public String getName() {return name;}
	public String getEmail() {return email;}
	public int getNumber() {return number;}
	public String getCurrentTime() {return TID;}
	public void setName(String Name){name =Name;}
	public void setEmail(String Email){email =Email;}
	public void setTID(String tId){TID =tId;}
	public void setName(int Number){number =Number;}
	public void display(){
	System.out.println("-----------------Booking Details-----------------");
	System.out.println("Transaction ID: "+ getCurrentTime());
	System.out.println("Name: "+getName());
	System.out.println("Phone: "+getNumber());
	System.out.println("Email: "+getEmail());
	}
}
