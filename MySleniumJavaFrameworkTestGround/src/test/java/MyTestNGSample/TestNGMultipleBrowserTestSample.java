package MyTestNGSample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGMultipleBrowserTestSample {
	
	WebDriver webDriver;
	String baseUrl = "https://google.com";
	//String projectPath = System.getProperty("user.dir");
	
	// Using this annotation cause we will send this parameter through XML file.
	@Parameters("browserName")
	
	@BeforeTest
	public void launchBrowser(String browserName) {
		
		System.out.println("Browser platform:  " +browserName);
		
		System.out.println("Thread Number :  " +Thread.currentThread().getId());
		
		if(browserName.equalsIgnoreCase("Chrome")){
			
			WebDriverManager.chromedriver().setup();
			webDriver = new ChromeDriver();
		}
		
		else if(browserName.equalsIgnoreCase("Firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			webDriver = new FirefoxDriver();
		}
		
		else if(browserName.equalsIgnoreCase("IE")) {
			
			WebDriverManager.iedriver().setup();
			webDriver = new InternetExplorerDriver();
		}
		
	}
	
	@Test
	public void sampleTest() throws InterruptedException {
		
		webDriver.get(baseUrl);
		
		System.out.println("Navigated To Google Home Page");
		
		Thread.sleep(4000);
	}
	
	@AfterTest
	public void terminateBrowser() {
		
		System.out.println("Test Completed");
		
		webDriver.quit();
		
	}

}
