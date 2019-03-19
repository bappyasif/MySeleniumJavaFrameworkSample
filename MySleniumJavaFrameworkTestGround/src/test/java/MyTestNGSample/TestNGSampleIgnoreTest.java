package MyTestNGSample;

import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

// This 'ignore' would ignore all the test cases of this class. we can rather use individual ignore test to single out each test cases. 
// @Ignore
public class TestNGSampleIgnoreTest {

	// using ignore as a singleton would work for that particular function only. Whereas we can also using it in Class level to 
	// ignore all of the tests within that class.
	@Test
	@Ignore
	public void sampleTest() {
		
		System.out.println("Sample Test");
	}
	
	@Test
	public void anotherTest(){
		
		System.out.println("Another Test");
	}
	
}
