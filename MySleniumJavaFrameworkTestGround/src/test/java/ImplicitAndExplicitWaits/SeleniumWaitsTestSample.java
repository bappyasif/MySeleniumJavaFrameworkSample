package ImplicitAndExplicitWaits;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWaitsTestSample {
	
	static WebDriver webDriver;
	static String baseUrl = "https://google.com";
	
	public static void main(String[] args) {
		
		seleniumWaits();
		
	}
	
	public static void seleniumWaits() {
		
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		
		// Implicit Wait Example code. Default wait time is 250 ms so 2000+250 ms is total time out.
		// Implicit use can set wait for entire session.
		//webDriver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
		webDriver.get(baseUrl);
		
		webDriver.findElement(By.name("q")).sendKeys("Selenium Implicit and Explicit Waits");
		webDriver.findElement(By.name("btnK")).submit();
		
		// Explicit Wait example code. And then attCach to an element to get effected.
		// It's suggested that we use it when you feel like your webElement needs more time for meet the condition.
		WebDriverWait driverWait = new WebDriverWait(webDriver, 2);
		WebElement webElement = driverWait.until(ExpectedConditions.elementToBeClickable(By.name("some_nameTag"))); 
		
		//Aa deliberate fail test case to verify our Implicit/Explicit wait time.
		// webDriver.findElement(By.name("some_nameTag")).click();
		
		webDriver.quit();
	}

}
