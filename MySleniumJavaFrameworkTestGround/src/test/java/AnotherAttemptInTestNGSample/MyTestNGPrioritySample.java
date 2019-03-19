package AnotherAttemptInTestNGSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyTestNGPrioritySample {
	
	public String baseUrl = "http://demo.guru99.com/test/newtours/";
	
	public WebDriver webDriver = null;
	
	public String expectedValue = null;
	
	public String actualValue = null;
	
	
	@BeforeTest
	public void launchBrowser() {
		
		System.out.println("Launching Browser");
		
		WebDriverManager.chromedriver().setup();
		
		webDriver = new ChromeDriver();
		
		webDriver.manage().window().maximize();
		
		webDriver.get(baseUrl);
		
	}
	
	@BeforeMethod
	public void verifyMessageTitle() {
		
		String expectedSequence = "Welcome: Mercury Tours";
		
		String actualSequence = webDriver.getTitle();
		
		Assert.assertEquals(actualSequence, expectedSequence);
		
	}
	
	
	@Test(priority=0)
	public void registerButton() throws InterruptedException {
		
		webDriver.findElement(By.linkText("REGISTER")).click();
		
		expectedValue = "Register: Mercury Tours";
		
		actualValue = webDriver.getTitle();
		
		Thread.sleep(3000);
		
		Assert.assertEquals(actualValue, expectedValue);
		
	}
	
	
	@Test(priority=1)
	public void supportButton() throws InterruptedException {
		
		webDriver.findElement(By.linkText("SUPPORT")).click();
		
		expectedValue = "Under Construction: Mercury Tours";
		
		actualValue = webDriver.getTitle();
		
		Thread.sleep(3000);
		
		Assert.assertEquals(actualValue, expectedValue);
		
	}
	
	
	@AfterMethod
	public void goBackToHomePage() {
		
		System.out.println("Entering to the Home Pgae");
		
		webDriver.findElement(By.linkText("Home")).click();
		
	}
	
	@AfterTest
	public void terminateBrowser() {
		
		System.out.println("Closing Browser");
		
		webDriver.quit();
		
		System.out.println("Test Successful");
	}

}

/*
 * 
Summary of TestNG Annotations

@BeforeSuite: The annotated method will be run before all tests in this suite have run. 
@AfterSuite: The annotated method will be run after all tests in this suite have run. 
@BeforeTest: The annotated method will be run before any test method belonging to the classes inside the tag is run. 
@AfterTest: The annotated method will be run after all the test methods belonging to the classes inside the tag have run. 
@BeforeGroups: The list of groups that this configuration method will run before. This method is guaranteed to run shortly before the first test method that belongs to any of these groups is invoked. 
@AfterGroups: The list of groups that this configuration method will run after. This method is guaranteed to run shortly after the last test method that belongs to any of these groups is invoked. 
@BeforeClass: The annotated method will be run before the first test method in the current class is invoked. 
@AfterClass: The annotated method will be run after all the test methods in the current class have been run. 
@BeforeMethod: The annotated method will be run before each test method. 
@AfterMethod: The annotated method will be run after each test method. 
@Test: The annotated method is a part of a test case 
  
 * 
 * */

