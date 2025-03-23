package project;

public class InvalidInputException extends Exception {
	public InvalidInputException (String s) {
		super("The input\"" + s + "\" does not match the expected values. Please try again.");
	}
}
