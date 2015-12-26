import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	public static void main(String[] args) {
		// do1();
		do2();
	}

	public static void do1() {
		Result result = JUnitCore.runClasses(MathApplicationTester.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}

	public static void do2() {
		System.out.println("1");
		Result result = JUnitCore.runClasses(MultiMathApplicationTester.class);
		System.out.println("2");
		for (Failure failure : result.getFailures()) {
			System.out.println("failure");
			System.out.println(failure.toString());
		}
		System.out.println("success");
		System.out.println(result.wasSuccessful());
	}
}