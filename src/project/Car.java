package project;

public class Car extends Vehicle implements ForSale{
	private double currentPrice;
	private double totalValue;
	private boolean isGoodCondition;
	Customer owner;
	Store location;
	public Car(String VIN, String brand, String model, int year, double mileage, Customer Owner, Store location) {
		super(VIN, brand, model, year, mileage, location);
		this.currentPrice = 0;
		this.totalValue = 0;
		this.isGoodCondition = true;
		this.owner = null;
	}
	
	// Setters and Getters
	public double getCurrentPrice() {
		return currentPrice;
	}
	
	public void setCurrentPrice(double newPrice) {
		this.currentPrice = newPrice;
	}
	
	public double getTotalValue() {
		return totalValue;
	}
	
	public void setTotalValue(double newValue) {
		this.totalValue = newValue;
	}
	
	public boolean isGoodCondition() {
		return isGoodCondition;
	}
	
	public void setGoodCondition(boolean newCondition) {
		this.isGoodCondition = newCondition;
	}
	
	public Customer getOwner() {
		return this.owner;
	}

	public void setOwner(Customer newOwner) {
		this.owner = newOwner;
	}
	
	public Store getLocation() {
		return this.location;
	}
	
	public void setLocation(Store newLocation) {
		this.location = newLocation;
	}
	
	//Methods Unique to Car Class
	public boolean negociatePrice(double offer) {
		this.setCurrentPrice(this.appraiseValue());
		double currentPrice = this.getCurrentPrice();
		if(Math.random() > .5 && Math.abs(currentPrice - offer) < currentPrice*.05) {
			System.out.println("Congratulations! We've Accepted your offer.");
			return true;
		} else {
			System.out.println("My apologies, unfortunately we will have to deny your offer. ");
			return false;
		}
	}
	
	private void markAsSold() {
		Store location = this.getLocation();
		Inventory inventory = location.getInventory();
		inventory.getAllVehicles().remove(this);
	}
	
	public boolean getPaymentStatus() {
		
	}
	
	// Methods from ForSale Interface
	@Override
	public void sell(Customer customer, double price) {
		// TODO Auto-generated method stub
		this.transferOwnership(customer);
		this.getLocation().setRevenue(location.getRevenue() + price);
		
	}

	@Override
	public double appraiseValue() {
		double value = this.getTotalValue();
		if(this.isGoodCondition()) {
			value *= 1.075;
		} else {
			value *= .97;
		}
		return value;
	}

	@Override
	public boolean transferOwnership(Customer customer) {
		if(this.getOwner() == null) {
			this.setOwner(customer);
			return true;
		} else {
			System.out.println("Failure to transfer ownership. Someone already owns this car.")
			return false;
		}
	}

	@Override
	public boolean removeFromInventory() {
		// TODO Auto-generated method stub
		return false;
	}

	// Methods from Vehicle Superclass
	@Override
	public void reportDamage(int cost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fixDamage(int cost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void compareValue(Vehicle other) {
		// TODO Auto-generated method stub
		
	}	
}


