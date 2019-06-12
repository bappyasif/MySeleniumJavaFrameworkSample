package tutorial.edureka.example.com;

import org.testng.annotations.Test;

public class TestNGAttributes {

	/**
	 * When Our Method Does Not Carry Any Priority Then Test Cases Will Run
	 * Using Alphabetical Order Or Alpha-Numerical Order From Ascending To Descending Order Of Test Method Names.
	 */
	
	/**
	 * When There Is Priority Exist, Then Lower The Priority Gets Higher The Preference Over Others. [0 - Any Positive Numbers]
	 */
	
	/**
	 * Dependency And Disable : Uses Two Ways Of Choosing.
	 */
	
	@Test(priority = 2)
	public void TestCaseFunctionAA() {
		System.out.println("Inside AA");
	}
	
	@Test (dependsOnMethods= {"TestCaseFunctionBB"})
	public void TestCaseFunctionA1() {
		System.out.println("Inside A1");
	}

	@Test (priority = 0)
	public void TestCaseFunctionBB() {
		System.out.println("Inside BB");
	}
		
	@Test (dependsOnMethods = {"TestCaseFunctionFF"})
	public void TestCaseFunctionB1() {
		System.out.println("Inside B1");
	}

	@Test (priority = 4)
	public void TestCaseFunctionCC() {
		System.out.println("Inside CC");
	}
	
	@Test (enabled = false) 
	public void TestCaseFunctionC1() {
		System.out.println("Inside C1");
	}	

	@Test (priority = 6)
	public void TestCaseFunctionDD() {
		System.out.println("Inside DD");
	}
	
	@Test (enabled = true)
	public void TestCaseFunctionD1() {
		System.out.println("Inside D1");
	}

	@Test (priority = 74)
	public void TestCaseFunctionEE() {
		System.out.println("Inside EE");
	}
	
	@Test (dependsOnMethods = {"TestCaseFunctionD1"})  // This Will Run Fine Because Dependency Is Enabled.
	public void TestCaseFunctionE1() {
		System.out.println("Inside E1");
	}

	@Test (priority = 65)
	public void TestCaseFunctionFF() {
		System.out.println("Inside FF");
	}
	
	@Test (dependsOnMethods = {"TestCaseFunctionD1"}) // TestCaseFunctionD1 will Throw an exception saying not Included.
	public void TestCaseFunctionF1() {
		System.out.println("Inside F1");
	}

}
