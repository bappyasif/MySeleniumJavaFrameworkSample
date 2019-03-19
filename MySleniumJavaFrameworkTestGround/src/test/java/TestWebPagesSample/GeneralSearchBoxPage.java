package TestWebPagesSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralSearchBoxPage {
	
	private static WebElement webElement = null;
	
	public static WebElement searchTextBox(WebDriver webDriver) {
		
		webElement = webDriver.findElement(By.name("q"));
		
		return webElement;
		
	}
	
	public static WebElement searchBoxButton (WebDriver webDriver) {
		
		webElement = webDriver.findElement(By.name("btnK"));
		
		return webElement;
		
	}
	
	

}
