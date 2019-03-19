package MyTestNGSample;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class TestNGRetryFailedTestSample {

	
	@Test
	public void sampleTest() {
		
		System.out.println("Inside sample test block");
		
	}

	@Test
	public void anotherSampleTest() {
		
		System.out.println("Inside another sample  test");
		// another attempt to deliberately make it it fail test case.
		// int i  = 1/0; // in real life these would bee some kind of environmental or runtime issues that  could be affevting this.
	}
	
	@Test(retryAnalyzer = MyTestNGListenersSample.TestNGRetryAnalyzerTestSample.class)
	public void someotherSampleTest() {
		
		System.out.println("Inside someother sample test");
		Assert.assertTrue(false); // or any condition that will suffice the parameter value here.
		
	}
	
}
