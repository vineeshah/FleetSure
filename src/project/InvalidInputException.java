package project;

public class InvalidInputException extends Exception {
	public InvalidInputException () {
		super("The input enter does not match the expected values. Please try again.");
	}
}
