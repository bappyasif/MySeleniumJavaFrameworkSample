package MyTestNGSample;

import org.testng.annotations.Test;

public class TestNGDependencyTestSample {

	// Will be using dependency annotation
	// lets add priority as well to check which gets executed first. We will find out that dependency gets preference over priority.
	@Test(dependsOnMethods = {"anotherTestSample"} , priority = 1)
	public void testSample() {
		
		System.out.println("Inside Sample Test Block");
	}
	
	@Test(priority = 2)
	public void anotherTestSample() {
		
		System.out.println("Inside Another Test Sample");
	}
	
	// Apparently multiple dependencies gets priority over single dependency!!
	@Test(dependsOnMethods= {"hereComesAnotherOne" , "hereGoesAnotherOne" })
	public void yetAnotherTestSample() {
		
		System.out.println("Inside Yet Another Test");
	}
	
	@Test(groups= {"sanityOne"})
	public void hereComesAnotherOne() {
		
		System.out.println("Inside Here Comes Another");
	}
	
	@Test(dependsOnGroups = {"sanityOne"})
	public void hereGoesAnotherOne() {
		
		System.out.println("Inside Here Goes Another");
	}
	
	@Test(groups= {"sanityTwo"})
	public void thereGoesAnotherTest() {
		
		System.out.println("Inside There goes Another");
	}
	
	// using a simple '*' as a RegEx expression to include anything after 'sanity' 
	@Test(dependsOnGroups = {"sanity.*"})
	public void thenComesAnotherOne() {
		
		System.out.println("Inside Then Comes Another");
	}

}
