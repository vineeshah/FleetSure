package project;

public abstract class Vehicle {
	// defining attributes that will be common for every vehicle type
	private String model;
	private String brand;
	private boolean isAvailable;
	private double pricePerSession;

	// making constructors for common
	public Vehicle(String model, String brand, boolean isAvailable, double pricePerSession) {
		this.model = model;
		this.brand = brand;
		this.isAvailable = isAvailable;
		this.pricePerSession = pricePerSession;
	}

	// default cstr to avoid any inheritance risks
	public Vehicle() {
		this.brand = "TBD";
		this.model = "TBD";
		this.pricePerSession = 0.0;
		this.isAvailable = true;
	}

	// setters and getters
	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailability(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPricePerSession() {
		return pricePerSession;
	}

	public void setPricePerSession(double price) {
		this.pricePerSession = price;
	}

	public double getTotalPrice(int days) {
		return this.pricePerSession * days;
	}

	@Override
	public String toString() {
		return "Vehicle[Brand=" + brand + ", Model=" + model + ", Price per session=" + pricePerSession + ", Available="
				+ isAvailable + "]";
	}

	// abstract methods that each vehicle must implement on their own
	public abstract boolean envFriendly(Vehicle vehicle);

}
