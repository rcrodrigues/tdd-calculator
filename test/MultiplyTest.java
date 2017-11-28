package test;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import src.Calculator;
import src.MyCalculator;
import src.NegativeNumberException;

public class MultiplyTest {
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
		BigDecimal result = myCalculator.multiply(s);
		Assert.assertEquals(BigDecimal.valueOf(2.0), result);

	}

	@Test
	public void testmultiplyOneNumberSuccess() throws NegativeNumberException {
		String s = "1";
		BigDecimal result = myCalculator.multiply(s);
		Assert.assertEquals(BigDecimal.valueOf(1.0), result);

	}

	@Test
	public void testmultiplyNoNumberSuccess() throws NegativeNumberException {
		String s = "";
		BigDecimal result = myCalculator.multiply(s);
		Assert.assertEquals(BigDecimal.valueOf(0.0), result);

		result = myCalculator.multiply(null);
		Assert.assertEquals(BigDecimal.valueOf(0.0), result);
	}

	@Test
	public void testAnyNonZeroNumberSuccess() throws NegativeNumberException {
		String s = "1,2,3,4,5,6,7,8,9,1001";
		BigDecimal result = myCalculator.multiply(s);
		Assert.assertEquals(BigDecimal.valueOf(362880.0), result);
	}

	@Test
	public void testNegativeNumberShouldFailException() throws NegativeNumberException {
		String s = "-1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -1 ");

		myCalculator.multiply(s);

	}

	@Test
	public void testNegativeNumber2ShouldFailException() throws NegativeNumberException {
		String s = "3,-1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -1 ");

		myCalculator.multiply(s);

	}

	@Test
	public void testNegativeNumber3ShouldFailException() throws NegativeNumberException {
		String s = " -3,-1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -3 -1 ");

		myCalculator.multiply(s);

	}

	@Test
	public void testNegativeNumber4ShouldFailException() throws NegativeNumberException {
		String s = "-2.5, -1";

		thrown.expect(NegativeNumberException.class);
		thrown.expectMessage("negatives not allowed: -2 -1 ");

		myCalculator.multiply(s);

	}

	@Test
	public void testNumberOverThousandDontCount() throws NegativeNumberException {

		String s = "2,1000,3,1001";
		BigDecimal result = myCalculator.multiply(s);
		Assert.assertEquals(BigDecimal.valueOf(6000.0), result);

	}

	@Test
	public void testFloatingNumberSuccess() throws NegativeNumberException {

		String s = "2.5,2";
		BigDecimal result = myCalculator.multiply(s);
		Assert.assertEquals(BigDecimal.valueOf(5.0), result);

	}
}
