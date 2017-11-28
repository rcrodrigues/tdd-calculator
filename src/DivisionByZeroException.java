package src;

/**
 * Exception raised when trying to divide with 0 denominator
 */
public class DivisionByZeroException extends Exception {

	public DivisionByZeroException() {
		super("Divison by zero");
	}

}
