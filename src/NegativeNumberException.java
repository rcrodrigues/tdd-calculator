package src;

/**
 * Exception raised when negative numbers appears on the calculator operations inputs.
 */
public class NegativeNumberException extends Exception {

	public NegativeNumberException(String string) {
		super(string);
	}

}
