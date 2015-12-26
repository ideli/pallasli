import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

//@RunWith attaches a runner with the test class to initialize the test data
@RunWith(EasyMockRunner.class)
public class MathApplicationTester {

	// @TestSubject annotation is used to identify class which is going to use
	// the
	// mock object
	@TestSubject
	MathApplication mathApplication = new MathApplication();

	// @Mock annotation is used to create the mock object to be injected
	@Mock
	CalculatorServiceImpl calcService;

	@Before
	public void setUp() {
		mathApplication = new MathApplication();
		calcService = EasyMock.createMock(CalculatorServiceImpl.class);
		// calcService = EasyMock.createStrictMock(CalculatorService.class);
		// calcService = EasyMock.createNiceMock(CalculatorService.class);
		mathApplication.setCalculatorService(calcService);
	}

	@Test(expected = RuntimeException.class)
	public void testAdd() {
		// EasyMock.expect(calcService.add(10.0, 20.0)).andReturn(30.00);
		// add the behavior to throw exception
		// activate the mock
		EasyMock.replay(calcService);
		EasyMock.expect(calcService.add(10.0, 20.0)).andReturn(30.00);
		// EasyMock.expect(calcService.add(10.0, 20.0)).andThrow(
		// new RuntimeException("Add operation not implemented"));
		// test the add functionality
		// Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
		// verify call to calcService is made or not
		EasyMock.verify(calcService);

		// calcService.subtract(20.0, 5.0);
		// // limit the method call to 1, no less and no more calls are allowed
		// EasyMock.expectLastCall().times(2);
	}
}