package project;

public class RentalAvailibilityException extends Exception {
	public RentalAvailibilityException(Vehicle vehicle) {
		super("This " + vehicle.getBrand() + " " + vehicle.getModel() +" is unavailable at this time. Please try again in " + ((Rentable) vehicle).getDaysRented() +" days.");
		
	}
}
