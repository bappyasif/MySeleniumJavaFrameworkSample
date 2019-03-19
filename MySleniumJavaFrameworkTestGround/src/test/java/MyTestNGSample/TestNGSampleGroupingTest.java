package MyTestNGSample;

import org.testng.annotations.Test;

// We can also use class level annotation for Grouping which will include every Test annotated functions in the Class.

@Test(groups = {"AllTests"})
public class TestNGSampleGroupingTest {

	@Test(groups= {"sanity"})
	public void sampleGroupTestOne() {

		System.out.println("It's in Sample Test 01");

	}

	@Test(groups= {"sanity", "smoke"})
	public void sampleGroupTestTwo() {

		System.out.println("It's in Sample Test 02");

	}

	@Test(groups= {"smoke", "regression"})
	public void sampleGroupTestThree() {

		System.out.println("It's in Sample Test 03");

	}

	@Test(groups= {"smoke"})
	public void sampleGroupTestFour() {

		System.out.println("It's in Sample Test 04");

	}


	@Test(groups= {"windows.regression"})
	public void sampleGroupTestFive() {

		System.out.println("It's in Sample Test 05");

	}

	@Test(groups= {"linux.regression"})
	public void sampleGroupTestSix() {

		System.out.println("It's in Sample Test 06");

	}
	
	@Test(groups= {"regression"})
	public void sampleGroupTestSeven() {

		System.out.println("It's in Sample Test 07");

	}
	
	// As this class is not grouped individually but due to Class level annotation it will be included now in the 'AllTests'
	@Test
	public void sampleGroupTestEight() {

		System.out.println("It's in Sample Test 08");

	}

}
