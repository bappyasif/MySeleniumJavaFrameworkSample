package MyTestNGListenersSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtilities.MyExcelUtilitiesSample;
import io.github.bonigarcia.wdm.WebDriverManager;

//@Listeners(MyTestNGListenersSample.TestNGListeners.class) // we can commit this annotatioon because we have 
// added this dependency in our testngListenersTestSample.xml 
public class TestNGLListenersTestSampleTwo {
	
	@Test
	public void testSampleCopyOne() {
		
		System.out.println("Inside First Test Sample CopyOne");
		
	}
	
	@Test
	public void testSampleTwoCopyTwo() {
		
		System.out.println("Inside Seccond Test Sample CopyTwo");
		// Assert.assertTrue(false);
		
		String projectPath = System.getProperty("user.dir");
		WebDriverManager.chromedriver().setup();
		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get("https://google.com");
		webDriver.findElement(By.name("q")).sendKeys("TestNG Listeners");
		webDriver.findElement(By.name("q2")).submit();  // deliberate mistake for causing an error.
		
		webDriver.quit();

		
		
	}
	
	@Test
	public void testThreeCopyThree() {
		
		System.out.println("Inside third Test Sample CopyThree");
		// throw new SkipException("This test is skipped");
	}

}
