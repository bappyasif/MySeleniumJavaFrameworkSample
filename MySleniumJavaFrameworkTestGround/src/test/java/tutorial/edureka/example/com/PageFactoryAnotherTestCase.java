package tutorial.edureka.example.com;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageFactoryAnotherTestCase {

	WebDriver web_Driver;

	public PageFactoryAnotherTestCase(WebDriver driver) {
		this.web_Driver = driver;
	}
	
	// script_Executor.executeScript("window.scrollBy(0,500)");
	//@FindBy(how = How.XPATH, using = "//span[@class='typeahead__button']") WebElement search_ButtonIcon;
	
	public void hit_Scrolling() {
		
		JavascriptExecutor script_Executor = (JavascriptExecutor) web_Driver;
		
		script_Executor.executeScript("window.scrollBy(0,500)");
	}

}
