package MyExtentReportSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyExtentReportBasicSample {
	
	public static WebDriver webDriver = null;
	
	public static void main(String[] args) throws InterruptedException {
		
		// start reporters
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReports.html");
		
		 // create ExtentReports and attach reporter(s)
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);
		
		 // creates a toggle for the given test, adds all log events under it   
		ExtentTest extentTest = extentReports.createTest("MyFirstExtentReportsTestOnGoogleSearch", "This test will run and validate a google search");
		
		// Here goes the Test
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		
		extentTest.log(Status.INFO, "Starting My Test Case ");
		
		webDriver.get("https://google.com");
		extentTest.pass("Navigated to Google Page ");
		
		webDriver.findElement(By.name("q")).sendKeys("Selenium Automation");
		extentTest.pass("Enetered Search Query in Search TextBox Area");
		
		webDriver.findElement(By.name("btnK")).submit();
		extentTest.pass("Pressed The Enter Key....");
		
		Thread.sleep(3000);
		
		webDriver.quit();
		extentTest.pass("Closed Browser");
		
		extentTest.info("Test Completed");
		
		// calling flush writes everything to the log file
		extentReports.flush();
		
	}

}
