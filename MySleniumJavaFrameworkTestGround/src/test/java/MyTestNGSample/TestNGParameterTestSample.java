package MyTestNGSample;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGParameterTestSample {
	
	@Test
	@Parameters ({"insertName"})
	public void paramTest(String nameCheck) {
		
		System.out.println("Name Found: " +nameCheck );
		
	}
	
	// If our xml file does not include any value for this parameter then it simply ignore this parameter and execute code.
	@Test
	@Parameters({"insertName"})
	public void paramSampleTestTwo(@Optional String nameCheck) {
		
		System.out.println("Name Found: " +nameCheck);
		
	}

	// Even if don't provide any value for our parameter from XML file it still will run with provided optional name tag to be used. 
	@Test
	@Parameters({"insertName"})
	public void paramSampleTestThree(@Optional("Test Automation") String nameCheck) {
		
		System.out.println("Namee Found: " +nameCheck);
		
	}

}
