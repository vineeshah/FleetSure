package proj;

public class Car extends Vehicle{
	private String ftype;
	private boolean forSale;
	private int condition;
	
	public Car(String model, String brand, boolean isAvailable, double pricePerSession, String ftype, boolean forSale, int condition) {
		super(model, brand, isAvailable, pricePerSession);
		this.ftype = ftype;
		this.forSale = forSale;
		this.condition = condition;
	}
	
	@Override
	public boolean envFriendly(Vehicle vehicle) {
		if(ftype=="Electric" && vehicle.isAvailable()==true) {
			return true;
		}else {
			return false;
		}
	}
	
	public void repair() {
		if(condition<5) {
			condition +=5;
			System.out.print("imp condition is: "+ condition);
		}else {
			condition = 10;
			System.out.print("new condition is: 10");
		}
		
	}
	
	
	
}


