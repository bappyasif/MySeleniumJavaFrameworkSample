package tutorial.edureka.example.com;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAnnotations {

	@Test
	public void TestCaseFunctionOne() {
		
		System.out.println("Inside First Test Function");
	}
	
	@Test
	public void TestCaseFunctionTwo() {
		
		System.out.println("Inside Second Test Function");
		
	}
	
	/**
	 * Takes Preference Over BeforeClass Annotation
	 */
	@BeforeTest
	public void BeforeAnyTestCase() {
		
		System.out.println("Runs Before Any Test");
		
	}
	
	@AfterTest
	public void AfterAllTestCases() {
		
		System.out.println("Runs After All Test");
		
	}
	
	@BeforeMethod
	public void BeforeEveryTestCases() {
		
		System.out.println("Runs Before Every Test");
		
	}
	
	@AfterMethod
	public void AfetrEveryTestCases() {
		
		System.out.println("Runs After Every Test");
		
	}
	
	/**
	 * BeforeTest Annotation Takes Precedence Over BeforeClass Annotated Method.
	 */
	@org.testng.annotations.BeforeClass
	public void BeforeClass() {
		
		System.out.println("Runs Before Class Execution");
		
	}
	
	@org.testng.annotations.AfterClass
	public void AfterClass() {
		
		System.out.println("Runs After Class Execution");
		
	}

}
