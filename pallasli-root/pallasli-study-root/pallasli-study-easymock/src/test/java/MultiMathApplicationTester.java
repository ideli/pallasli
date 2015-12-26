import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(EasyMockRunner.class)
public class MultiMathApplicationTester extends EasyMockSupport {

	private MathApplication mathApplication1;
	private MathApplication mathApplication2;

	@Mock
	private CalculatorService calcService1;
	@Mock
	private CalculatorService calcService2;

	@Before
	public void setUp() {
		mathApplication1 = new MathApplication();
		mathApplication2 = new MathApplication();
		calcService1 = createNiceMock(CalculatorService.class);
		calcService2 = createNiceMock(CalculatorService.class);
		mathApplication1.setCalculatorService(calcService1);
		mathApplication2.setCalculatorService(calcService2);
	}

	@Test
	public void testCalcService() {
		System.out.println(mathApplication1.add(20.0, 10.0));
		// activate all mocks
		replayAll();
		// test the add functionality
		Assert.assertEquals(mathApplication1.add(20.0, 10.0), 0.0, 0);
		// test the substract functionality
		Assert.assertEquals(mathApplication1.subtract(20.0, 10.0), 0.0, 0);
		// test the multiply functionality
		Assert.assertEquals(mathApplication1.divide(20.0, 10.0), 0.0, 0);
		// test the divide functionality
		Assert.assertEquals(mathApplication1.multiply(20.0, 10.0), 0.0, 0);

		// test the add functionality
		Assert.assertEquals(mathApplication2.add(20.0, 10.0), 0.0, 0);
		// test the substract functionality
		Assert.assertEquals(mathApplication2.subtract(20.0, 10.0), 0.0, 0);
		// test the multiply functionality
		Assert.assertEquals(mathApplication2.divide(20.0, 10.0), 0.0, 0);
		// test the divide functionality
		Assert.assertEquals(mathApplication2.multiply(20.0, 10.0), 0.0, 0);

		// verify all the mocks
		verifyAll();
	}
}
