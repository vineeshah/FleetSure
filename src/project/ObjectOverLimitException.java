package project;

public class ObjectOverLimitException extends Exception {
	public ObjectOverLimitException() {
		super("The system can't support greater than 100 instances of each object.");
	}
}
