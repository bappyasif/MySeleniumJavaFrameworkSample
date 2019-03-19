package TestsSample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import TestWebPagesSample.PageObjectsForGoogleSearchTestSapmle;
import io.github.bonigarcia.wdm.WebDriverManager;

// This class will load our Page Object Class with Actions to perform.
// That we created earlier in TestSample package.
public class GoogleSearchTestPagePOMSampleTwo {
	
	// Creating this Class variable so that its available to use.
	static WebDriver webDriver = null;
	
	// Java needs it's main function to run from.
	public static void main(String[] args) {
		
		// Calling Function
		googleSearchTest();
	}
	
	public static void googleSearchTest() {
		// Setting up the browser for test environment.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		
		webDriver.manage().window().maximize();
		
		// creating an object of our earlier created Page Object Class which takes webDriver as an parameter
		// for more info see that class Constructor.
		PageObjectsForGoogleSearchTestSapmle searchObject = new PageObjectsForGoogleSearchTestSapmle(webDriver);
		
		webDriver.get("https://google.com");
		
		// Here we are fueling our search text-box with stream of characters.
		searchObject.setTextInSearchBox("Selenium Automation");
		
		// here we are hitting the the metaphorical Enter button / search button.
		searchObject.clickSearchButton();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		webDriver.quit();
		
		System.out.println("Test Successful");

	}

}
