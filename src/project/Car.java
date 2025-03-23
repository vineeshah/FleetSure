package project;

public class Car extends Vehicle implements ForSale{
	private double currentPrice;
	private double totalValue;
	private boolean isGoodCondition;
	private Customer owner;
	 static int currentObjects = 0;
	
	public Car(String VIN, String brand, String model, int year, double mileage, Store location) throws ObjectOverLimitException {
		
		super(VIN, brand, model, year, mileage, location);
		this.currentPrice = 0;
		this.totalValue = 0;
		this.isGoodCondition = true;
		this.owner = null;
		currentObjects++;
		
		if(currentObjects > 100) {
			throw new ObjectOverLimitException("car");
		}
	}
	
	// Setters and Getters
	public double getCurrentPrice() {
		if(currentPrice == 0) {
			currentPrice = this.calculateValue();
		}
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
	
	//Methods Unique to Car Class
	public boolean negociatePrice(double offer) {
		this.setCurrentPrice(this.appraiseValue());
		double currentPrice = this.getCurrentPrice();
		if(Math.random() > .5 && Math.floor(currentPrice - offer) < currentPrice*.05) {
			System.out.println("Congratulations! We've Accepted your offer.");
			return true;
		} else {
			System.out.println("My apologies, unfortunately we will have to deny your offer. Please try again.");
			return false;
		}
	}
	
	private void markAsSold() {
		Store location = this.getLocation();
		Inventory inventory = location.getInventory();
		inventory.getAllVehicles().remove(this);
	}
	
	// Methods from ForSale Interface
	@Override
	public void sell(Customer customer, double price) {
		this.transferOwnership(customer);
		this.getLocation().setRevenue(this.getLocation().getRevenue() + price);
		customer.getRentedVehicles().add(this);
		this.markAsSold();
	}

	@Override
	public double appraiseValue() {
		double value = this.calculateValue();
		if(this.isGoodCondition()) {
			value *= 1.075;
		} else {
			value *= .97;
		}
		return value;
	}

	@Override
	public void transferOwnership(Customer customer) {
		if(this.getOwner() == null) {
			this.setOwner(customer);
			customer.getRentedVehicles().add(this);
		} else {
			System.out.println("Failure to transfer ownership. Someone already owns this car.");
		}
	}

	@Override
	public void removeFromInventory() {
		Store store = this.getLocation();
		Inventory inventory = store.getInventory();
		inventory.removeFromInventory(this);
	}


	@Override
	public void fixDamage(int cost) {
		Store store = this.getLocation();
		store.setProfit(store.getProfit() - cost);
	}

	@Override
	public void compareValue(Vehicle other) {
		if(other instanceof Car) {
			double currentValue = this.getTotalValue();
			double otherValue = ((Car)other).getTotalValue();
			
			if(currentValue > otherValue) {
				System.out.println(this.toString() + "is of better value.");
			} else {
				System.out.println(other.toString() + "is of better value.");
			}
		} else {
			System.out.println("You can't compare a non-rental car to a rental car.");
		}
	}	
	//To String
		@Override
		public String toString() {
			return this.getYear() + " " + this.getBrand() + " " + this.getModel() + ", Miles: " + this.getMileage();
		}
}


