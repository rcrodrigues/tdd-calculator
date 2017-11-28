package src;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementing Calculator as TDD exercises
 */
public class MyCalculator implements Calculator {

	private List<Integer> getIntegersFromString(String s) {

		String[] values = Optional.ofNullable(s).orElse("").split(",");

		return Arrays.stream(values).map(String::trim).filter(numString -> numString.length() > 0).map(Integer::parseInt).filter(num -> num < 1001).collect(Collectors.toList());

	}

	private List<BigDecimal> getBigDecimalsFromString(String s) {

		String[] values = Optional.ofNullable(s).orElse("").split(",");

		return Arrays.stream(values).map(String::trim).filter(numString -> numString.length() > 0).map(num -> BigDecimal.valueOf(Float.valueOf(num))).filter(num -> num.longValue() < 1001L).collect(Collectors.toList());

	}

	private void verifyZeroDenominator(List<BigDecimal> numbers) throws DivisionByZeroException {

		boolean hasZeros = !numbers.stream().skip(1).noneMatch(num -> num.doubleValue() == 0.0);

		if (hasZeros) {
			throw new DivisionByZeroException();
		}

	}

	private static void verifyIntegerNegatives(List<Integer> numbers) throws NegativeNumberException {

		StringBuilder builder = new StringBuilder();

		numbers.stream().filter(num -> num < 0).forEach(num -> builder.append(num + " "));

		if (builder.length() > 0) {
			throw new NegativeNumberException("negatives not allowed: " + builder.toString());
		}

	}

	private static void verifyBigDecimalNegatives(List<BigDecimal> numbers) throws NegativeNumberException {

		StringBuilder builder = new StringBuilder();

		numbers.stream().filter(num -> num.intValue() < 0).forEach(num -> builder.append(num.intValue() + " "));

		if (builder.length() > 0) {
			throw new NegativeNumberException("negatives not allowed: " + builder.toString());
		}

	}

	@Override
	public int add(String s) throws NegativeNumberException {

		List<Integer> numbers = getIntegersFromString(s);

		verifyIntegerNegatives(numbers);

		return numbers.stream().reduce((a, b) -> a + b).orElse(0);

	}

	@Override
	public int subtract(String s) throws NegativeNumberException {

		List<Integer> numbers = getIntegersFromString(s);

		verifyIntegerNegatives(numbers);

		return numbers.stream().reduce((a, b) -> a - b).orElse(0);

	}

	@Override
	public BigDecimal multiply(String s) throws NegativeNumberException {

		List<BigDecimal> numbers = getBigDecimalsFromString(s);

		verifyBigDecimalNegatives(numbers);

		return numbers.stream().reduce((a, b) -> a.multiply(b, MathContext.UNLIMITED)).map(number -> number.setScale(1)).orElse(BigDecimal.valueOf(0.0));

	}

	@Override
	public BigDecimal divide(String s) throws NegativeNumberException, DivisionByZeroException {

		List<BigDecimal> numbers = getBigDecimalsFromString(s);

		verifyBigDecimalNegatives(numbers);

		verifyZeroDenominator(numbers);

		return numbers.stream().reduce((a, b) -> a.divide(b, MathContext.UNLIMITED)).map(number -> number.setScale(1)).orElse(BigDecimal.valueOf(0.0));

	}

}
