package TestWebPagesSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// you are supposedly to create as much of these Page Object Classes for each 
// website's pages that you are Testing.
public class PageObjectsForGoogleSearchTestSapmle {
	
	WebDriver webDriver = null;
	
	// Objects
	// Locating or identifying all the objects that you need to complete this test
	By searchTextBox = By.name("q");
	
	By searchBoxButton = By.name("btnK");
	
	
	// Lets call this Class's Constructor
	public PageObjectsForGoogleSearchTestSapmle(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.webDriver = driver;
	}
	
	
	// Actions #01
	// To activating search box writing sequence
	public void setTextInSearchBox(String text) {
		
		webDriver.findElement(searchTextBox).sendKeys(text);
		
	}
	
	// Actions #2
	// Hit the Search button after writing in Search text-box.
	public void clickSearchButton() {
		
		webDriver.findElement(searchBoxButton).submit();
		
	}

}
