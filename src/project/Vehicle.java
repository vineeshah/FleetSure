package project;

public abstract class Vehicle {
	// defining attributes that will be common for every vehicle type
	private String VIN;
	private String brand;
	private String model;
	private int year;
	private double mileage;
	private Store location;

	//cConstructors with all attributes
	public Vehicle(String VIN, String brand, String model, int year, double mileage, Store location) {
		this.VIN = VIN;
		this.model = model;
		this.brand = brand;
		this.year = year;
		this.mileage = mileage;
		this.location = location;
	}

	// default constructor to avoid any inheritance risks
	public Vehicle() {
		this.VIN = "N/A";
		this.model = "N/A";
		this.brand = "N/A";
		this.year = 0;
		this.mileage = 0;
	}

	// setters and getters
	public String getVIN() {
		return this.VIN;
	}
	
	public void setVIN(String newVIN) {
		this.VIN = newVIN;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public void setModel(String newModel) {
		this.model = newModel;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public void setBrand(String newBrand) {
		this.brand = newBrand;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public void setYear(int newYear) {
		this.year = newYear;
	}
	
	public double getMileage() {
		return this.mileage;
	}
	
	public void setMileage(double newMileage) {
		this.mileage = newMileage;
	}
	
	public Store getLocation( ) {
		return this.location;
	}
	
	public void setLocation(Store newLocation) {
		this.location = newLocation;
	}
	
	// Methods to be overridden in subclasses
	public double calculateValue() {
		double value = 10000;
		if(year < 2000) {
			value -= 2000;
		} else if (year < 2010) {
			value -= 1000;
		} else {
			value += 1500;
		}	
		double depreciation = mileage/75000;
		
		value /= Math.max(1, depreciation);
		
		return value;
	}
	
	public abstract void reportDamage(int cost);
	
	public abstract void fixDamage(int cost);
	
	public abstract void compareValue(Vehicle other);
	
}
