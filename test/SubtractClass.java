package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import src.Calculator;
import src.MyCalculator;
import src.NegativeNumberException;

public class SubtractClass {
	Calculator myCalculator;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myCalculator = new MyCalculator();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		myCalculator = null;
	}

	@Test
	public void testTwoNumbersShouldSuccess() throws NegativeNumberException {

		String s = "2,1";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(1, result);

	}

	@Test
	public void testSubtractOneNumberSuccess() throws NegativeNumberException {
		String s = "1";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(1, result);

	}

	@Test
	public void testsubtractNoNumberSuccess() throws NegativeNumberException {
		String s = "";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(0, result);

		result = myCalculator.subtract(null);
		Assert.assertEquals(0, result);
	}

	@Test
	public void testAnyNumberSuccess() throws NegativeNumberException {
		String s = "1,2,3,4,5,6,7,8,9,0";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(-43, result);
	}

	@Test
	public void testNegativeNumberShouldFailException() throws NegativeNumberException {
		String s = "-1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -1 ");

		myCalculator.subtract(s);
	}

	@Test
	public void testNegativeNumber2ShouldFailException() throws NegativeNumberException {
		String s = "3,-1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -1 ");

		myCalculator.subtract(s);
	}

	@Test
	public void testNegativeNumber3ShouldFailException() throws NegativeNumberException {
		String s = " -3,-1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -3 -1 ");

		myCalculator.subtract(s);
	}

	@Test
	public void testNegativeNumber4ShouldFailException() throws NegativeNumberException {
		String s = "-2, -1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -2 -1 ");

		myCalculator.subtract(s);
	}

	@Test
	public void testNumberOverThousandDontCount() throws NegativeNumberException {

		String s = "2,1000,3,1001";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(-1001, result);

	}
}
