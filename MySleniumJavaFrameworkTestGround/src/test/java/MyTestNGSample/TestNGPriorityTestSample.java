package MyTestNGSample;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TestNGPriorityTestSample {

	
	@Test(priority=-1)
	public void sampleTest() {
		
		System.out.println("Inside First Test Block");
	}
	
	@Test(priority=0)
	public void sampleTestTwo() {
		
		System.out.println("Inside Second Test Block");
	}
	
	@Test(priority=-2)
	public void sampleTestNumberThree() {
		
		System.out.println("Inside Third Test Block");
	}
	
}

/**
 * 
 Notes:
 
 Priorities are decide along with the their set values first.
 
 When all the Tests are having the same priorities then Alphabetical order will be maintained in execution.
 
 Priorities can be negative valued as well if you want them to.
 * 
 */
