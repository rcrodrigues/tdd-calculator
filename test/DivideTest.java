package test;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import src.Calculator;
import src.DivisionByZeroException;
import src.MyCalculator;
import src.NegativeNumberException;

public class DivideTest {
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
	public void testTwoNumbersShouldSuccess() throws NegativeNumberException, DivisionByZeroException {

		String s = "2,1";
		BigDecimal result = myCalculator.divide(s);
		Assert.assertEquals(BigDecimal.valueOf(2.0), result);

	}

	@Test
	public void testdivideOneNumberSuccess() throws NegativeNumberException, DivisionByZeroException {
		String s = "1";
		BigDecimal result = myCalculator.divide(s);
		Assert.assertEquals(BigDecimal.valueOf(1.0), result);

	}

	@Test
	public void testdivideNoNumberSuccess() throws NegativeNumberException, DivisionByZeroException {
		String s = "";
		BigDecimal result = myCalculator.divide(s);
		Assert.assertEquals(BigDecimal.valueOf(0.0), result);

		result = myCalculator.divide(null);
		Assert.assertEquals(BigDecimal.valueOf(0.0), result);
	}

	@Test
	public void testAnyNonZeroNumberSuccess() throws NegativeNumberException, DivisionByZeroException {
		String s = "1,2";
		BigDecimal result = myCalculator.divide(s);
		Assert.assertEquals(BigDecimal.valueOf(0.5), result);
	}

	@Test
	public void testNegativeNumberShouldFailException() throws NegativeNumberException, DivisionByZeroException {
		String s = "-1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -1 ");

		myCalculator.divide(s);

	}

	@Test
	public void testNegativeNumber2ShouldFailException() throws NegativeNumberException, DivisionByZeroException {
		String s = "3,-1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -1 ");

		myCalculator.divide(s);

	}

	@Test
	public void testNegativeNumber3ShouldFailException() throws NegativeNumberException, DivisionByZeroException {
		String s = " -3,-1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -3 -1 ");

		myCalculator.divide(s);

	}

	@Test
	public void testNegativeNumber4ShouldFailException() throws NegativeNumberException, DivisionByZeroException {
		String s = "-2, -1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -2 -1 ");

		myCalculator.divide(s);

	}

	@Test
	public void testNumberOverThousandDontCount() throws NegativeNumberException, DivisionByZeroException {

		String s = "2,4,1001";
		BigDecimal result = myCalculator.divide(s);
		Assert.assertEquals(BigDecimal.valueOf(0.5), result);

	}

	@Test
	public void testZeroDenominatorShouldFail() throws NegativeNumberException, DivisionByZeroException {

		String s = "2,1000,3,0";

		thrown.expect(DivisionByZeroException.class);

		myCalculator.divide(s);

	}

	@Test
	public void testZeroNumeratorShouldSuccess() throws NegativeNumberException, DivisionByZeroException {

		String s = "0,1000,3,1";
		BigDecimal result = myCalculator.divide(s);
		Assert.assertEquals(BigDecimal.valueOf(0.0), result);

	}

	@Test
	public void testFloatingNumberSuccess() throws NegativeNumberException, DivisionByZeroException {

		String s = "5,0.5";
		BigDecimal result = myCalculator.divide(s);
		Assert.assertEquals(BigDecimal.valueOf(10.0), result);

	}
}
