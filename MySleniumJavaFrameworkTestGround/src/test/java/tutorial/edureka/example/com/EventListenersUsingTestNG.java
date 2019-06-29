	package tutorial.edureka.example.com;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class EventListenersUsingTestNG implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("TestCase Exceution Was Successfull : " +result.getName());

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("TestCase Exceution Was Un-successfull : " +result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println("TestCase Exceution Was Skipped : " +result.getName());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Test Started");

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		System.out.println("Test Finished");

	}

}
