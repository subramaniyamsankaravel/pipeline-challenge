package calculator;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class CalcTest {
	private Calculator objCalcUnderTest;

	@Before
	public void setUp() {
		// Arrange
		objCalcUnderTest = new Calculator();
	}

	@Test
	public void testAdd() {
		int a = 15;
		int b = 20;
		int expectedResult = 35;
		// Act
		long result = objCalcUnderTest.add(a, b);
		// Assert
		Assert.assertEquals(35, result);
	}

}