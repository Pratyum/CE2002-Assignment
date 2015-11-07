

public class Price {
	private String PriceType;
	private double Price;
	private double StudentPrice ;
	private double SeniorCitizenPrice ;
	private double WeekdayPrice;
	private double WeekendPrice ;
	private double PublicHolidayPrice ;
	

	public Price(String PriceType,double Price){
		this.PriceType=PriceType;
		this.Price=Price;
	}
	public Price(double StudentPrice,double SeniorCitizenPrice,double WeekdayPrice,double WeekendPrice,double PublicHolidayPrice)  {
		this.StudentPrice=StudentPrice;
		this.SeniorCitizenPrice=SeniorCitizenPrice;
		this.WeekdayPrice=WeekdayPrice;
		this.WeekendPrice=WeekendPrice;
		this.PublicHolidayPrice=PublicHolidayPrice;
	}
	//get set method
	public String getPriceType() { return PriceType ; }
	public double getPrice() { return Price ; }
	
	public double getStudentPrice() { return StudentPrice ; }
	public double getSeniorCitizenPrice() { return SeniorCitizenPrice ; }
	public double getWeekdayPrice() { return WeekdayPrice ; }
	public double getWeekendPrice() { return WeekendPrice ; }
	public double getPublicHolidayPrice() { return PublicHolidayPrice ; }

	public void setPriceType(String PriceType) { this.PriceType=PriceType; }
	public void setPrice(double Price) { this.Price=Price; }

	public boolean equals(Object o) {
		if (o instanceof Price) {
			Price p = (Price)o;
			return (getPriceType().equals(p.getPriceType()));
		}
		return false;
	}
}