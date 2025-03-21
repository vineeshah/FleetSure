package project;

public class RentalAvailibilityException extends Exception {
	public RentalAvailibilityException() {
		super("This rental is already being rented by someone else. Please select another vehicle.");
	}
}
