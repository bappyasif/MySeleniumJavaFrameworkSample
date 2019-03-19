package MyExtentReportsTestNGSample;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportsTestNGSample {

	ExtentHtmlReporter htmlReporter;
	ExtentReports extentReports;
	WebDriver webDriver;
	public String baseUrl = "https://google.com";

	// BeforeSuite annotations runs only once while you have multiple Test Cases to run.
	// Whereas BeforeTest annotation runs for every test case scenarios.
	@BeforeSuite
	public void setUpTheEnvironment() {

		// start reporters
		htmlReporter = new ExtentHtmlReporter("extentReportsTestNGFile.html");

		// create ExtentReports and attach reporter(s)
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);

	}

	@BeforeTest
	public void launchBrowser() {

		// Setting up the Browser Environment for my TestCase
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		webDriver.manage().window().maximize();

	}

	@Test
	public void myTestNGTestCase() throws IOException {

		// creates a toggle for the given test, adds all log events under it.
		ExtentTest extentTest = extentReports.createTest("AnotherExtentReportsUsingTestNG","Sample Description : his will run and validate a googlle search.");

		// Here COmes my TestCase
		webDriver.get(baseUrl);
		// Log the test details
		extentTest.pass("Navigated to Google home Page");
		// Type into google search TextBox area and hit enter
		webDriver.findElement(By.name("q")).sendKeys("Test Automation");
		extentTest.pass("Writing into Search Box");
		webDriver.findElement(By.name("btnK")).submit();
		extentTest.pass("Pressed Google Search Box");
		
		// Here COmes my TestCase
		webDriver.get(baseUrl);
		// Log the test details
		extentTest.pass("Navigated to Google home Page");		

		// log(Status, details)
		extentTest.log(Status.INFO, "This step shows usage of log(status, details)");

		// info(details)
		extentTest.info("This step shows usage of info(details)");

		// log with snapshot
		extentTest.fail("Details....: ", MediaEntityBuilder.createScreenCaptureFromPath("snapshot.png").build());

		// test with snapshot
		extentTest.addScreenCaptureFromPath("snapshot.png"); 

	}

	@Test
	public void myTestNGTestCaseTwo() throws IOException {

		// Duplicating previous codes for another sample example for fail Test case.

		// creates a toggle for the given test, adds all log events under it.
		ExtentTest extentTest = extentReports.createTest("AnotherExtentReportsUsingTestNG","Sample Description : his will run and validate a googlle search.");
		
		// Here COmes my TestCase
		webDriver.get(baseUrl);
		// Log the test details
		extentTest.pass("Navigated to Google home Page");
		// Type into google search TextBox area and hit enter
		webDriver.findElement(By.name("q")).sendKeys("Test Automation");
		extentTest.pass("Writing into Search Box");
		webDriver.findElement(By.name("btnK")).submit();
		extentTest.pass("Pressed Google Search Box");
		
		// log(Status, details)
		extentTest.log(Status.INFO, "This step shows usage of log(status, details)");

		// info(details)
		extentTest.info("This step shows usage of info(details)");

		// log with snapshot
		extentTest.pass("Details....: ", MediaEntityBuilder.createScreenCaptureFromPath("snapshot.png").build());

		// test with snapshot
		extentTest.addScreenCaptureFromPath("snapshot.png");

	}
	
	
	@AfterTest
	public void terminateBrowser() throws InterruptedException {
		
		Thread.sleep(3000);
		
		webDriver.quit();
		
		System.out.println("Test Succesful");
		
	}


	// AfterSuite annotations runs only once while you have multiple Test Cases to run.
	// Whereas AfterTest annotation runs for every test case scenarios.
	@AfterSuite
	public void logTheReportsFile() {

		// calling flush writes everything to the log file
		extentReports.flush();

	}


}
