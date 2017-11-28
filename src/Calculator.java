package src;

import java.math.BigDecimal;

/**
 * Calculator interface created as input for TDD exercises
 */
public interface Calculator {

	/**
	 * @param string
	 *            with numbers
	 * @return result of addition
	 * @throws NegativeNumberException
	 */
	public int add(String s) throws NegativeNumberException;

	/**
	 * @param string
	 *            with numbers
	 * @return result of subtraction
	 * @throws NegativeNumberException
	 */
	public int subtract(String s) throws NegativeNumberException;

	/**
	 * @param string
	 *            with numbers
	 * @return result of multiplication
	 * @throws NegativeNumberException
	 */
	public BigDecimal multiply(String s) throws NegativeNumberException;

	/**
	 * @param string
	 *            with numbers
	 * @return result of division
	 * @throws NegativeNumberException
	 * @throws DivisionByZeroException
	 */
	public BigDecimal divide(String s) throws NegativeNumberException, DivisionByZeroException;

}
