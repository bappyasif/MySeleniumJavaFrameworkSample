package tutorial.edureka.example.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PageFacoryTestCaseScenerio {
	
	WebDriver web_Driver;
	
	public PageFacoryTestCaseScenerio(WebDriver driver) {
		this.web_Driver = driver;
	}
	
	//Using Support Class Methods
	@FindBy(how = How.CSS, using = "#search-inp") WebElement search_TextBox;
	
	@FindBy(how = How.XPATH, using = "//span[@class='typeahead__button']") WebElement search_ButtonIcon;
	
	// Setting Up Search Parameters
	public void set_SearchKey(String stringSearch) {
		search_TextBox.sendKeys(stringSearch);
	}
	
	// Hitting Search Button icon
	public void hit_SearchButton() {
		search_ButtonIcon.click();
	}

}
