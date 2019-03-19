package BrowsersHeadless;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HeadlessChromeBrowserTestSample {
	
	public static void main(String[] args) {
		
		sampleTest();
		
	}

	public static void sampleTest() {
		
		WebDriverManager.chromedriver().setup();

		// Here goes headless Chrome Browser code sample.
		ChromeOptions chromeOptions = new ChromeOptions();
		//chromeOptions.setHeadless(true);
		chromeOptions.addArguments("--headless");
		//chromeOptions.addArguments("window-size=1920,1080");
		
		WebDriver webDriver = new ChromeDriver(chromeOptions);
		
		webDriver.get("https://google.com");
		
		System.out.println("Title is  : " +webDriver.getTitle());
		
		webDriver.findElement(By.name("q")).sendKeys("Headless Browsers");
		webDriver.findElement(By.name("btnK")).submit();
		
		webDriver.quit();
		System.out.println("Test Completed");
	}
	
}
