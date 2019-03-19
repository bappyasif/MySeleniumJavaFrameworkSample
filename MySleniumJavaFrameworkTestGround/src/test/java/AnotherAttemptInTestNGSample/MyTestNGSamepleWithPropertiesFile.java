package AnotherAttemptInTestNGSample;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Log4jSample.SampleLog4jTest;
import SampleConfigPropertiesTest.ConfigPropertiesFileTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MyTestNGSamepleWithPropertiesFile {
	
	public String baseUrl = "https://google.com";
	public WebDriver webDriver;
	public static String browserPlatform;
	
	private static Logger logger = LogManager.getLogger(SampleLog4jTest.class);
	
	@BeforeTest
	public void setBaseUrlEnvironment() {
	
		// Retrieve the properties entry for browser rather from the Class variable.
		ConfigPropertiesFileTest.getPropertiesFromFile();
		
		if(browserPlatform.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			webDriver = new ChromeDriver();
			
		}
		
		else if(browserPlatform.equalsIgnoreCase("InternetExplorer")) {
			
			WebDriverManager.iedriver().setup();
			webDriver = new InternetExplorerDriver();
			
		}
		
		logger.info("Browser Started");
	}
	
	@Test
	public void genralSearch() {
		
		webDriver.get(baseUrl);
		logger.info("Navigated To google home page.");
		
		webDriver.findElement(By.name("q")).sendKeys("Test Automation");
		webDriver.findElement(By.name("btnK")).submit();
		logger.info("Google Search was impleemented properly.");
	}
	
	@AfterTest
	public void terminateBrowser() {
		
		webDriver.quit();
		logger.info("Test Successful");
		
		System.out.println("Test Completed!!");
		ConfigPropertiesFileTest.setPropertiesIntoFile();
	}

}
