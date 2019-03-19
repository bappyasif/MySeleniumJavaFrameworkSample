package AnotherAttemptInTestNGSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyTestNGSamepleTwoSubOne {
	
	public String baseUrl = "https://google.com";
	public WebDriver webDriver;
	
	
	@BeforeTest
	public void setBaseUrlEnvironment() {
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		webDriver.get(baseUrl);
	}
	
	@Test
	public void genralSearchDemoSample() {
		
		webDriver.findElement(By.name("q")).sendKeys("Test Automation");
		webDriver.findElement(By.name("btnK")).submit();
		
	}
	
	@Test
	public void genralSearchDemoSampleTwo() {
		
		webDriver.findElement(By.name("q")).sendKeys("Test Automation");
		webDriver.findElement(By.name("btnK")).submit();
		
	}
	
	@AfterTest
	public void terminateBrowser() {
		
		webDriver.quit();
		
	}

}
