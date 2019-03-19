package TestsSample;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import TestWebPagesSample.GeneralSearchBoxPage;

public class GoogleSearchTestPOM {
	
	private static WebDriver webDriver = null;
	
	public static void main(String[] args) {
		
		generalGoogleSearch();
		
	}

	public static void generalGoogleSearch() {
		
		WebDriverManager.chromedriver().setup();
		
		webDriver  = new ChromeDriver();
		
		webDriver.manage().window().maximize();
		
		webDriver.get("https://google.com");
		
		// As we are using outr POM class for dealing with search sendkeys.
		//webDriver.findElement(By.name("q")).sendKeys("Selenium Test Automation Step By Step Tutorials");
		
		GeneralSearchBoxPage.searchTextBox(webDriver).sendKeys("Selenium Test Automation Step By Step Tutorials");
		
		GeneralSearchBoxPage.searchBoxButton(webDriver).submit();
		
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
