package LiveTestProjectDay01;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestWorkingSetTry {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	static String titleString; 
	static String menuString;
	static boolean selectionEnabled;
	static String sortbySelection;

	public static void main(String[] args) {

		CommencingTest();

	}

	public static void CommencingTest() {

		WebDriverManager.chromedriver().setup();

		webDriver = new ChromeDriver();

		webDriver.get(baseUrl);

		titleString = webDriver.getTitle();
		System.out.println("Title of this page : " +titleString);

		webDriver.findElement(By.linkText("MOBILE")).click();
		menuString = webDriver.getTitle();
		System.out.println("Title of This page :" +menuString);
		
		Select drpdwnSelection = new Select(webDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]"
				+ "/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/select[1]")));
		
		drpdwnSelection.selectByVisibleText("Name");
		// Code Works up until now. It just need to verify whether its really being selected or not. 
		// btw, which it does just fine.
		
		//HELP HELP HELP HELP
		// Need Help to Verify with a conditional statement whether its selected as expected.
		// Here in this case sorted By "Name"
		// According to my searches i could find that below statement should work!!
		//sortbySelection = drpdwnSelection.getFirstSelectedOption().getAttribute("name").toString();
		
		//Tried some more options!!
		//sortbySelection = drpdwnSelection.getFirstSelectedOption().getAttribute("select").toString();
		//This one shows the entire list rather Selected Item!!
		//sortbySelection = drpdwnElement.findElement(By.tagName("select")).getText();
		// was trying to assert the condition for availability of tag.
		//assertEquals("Name", drpdwnSelection.getOptions().contains("name"));
		
		System.out.println(sortbySelection);
		
		System.out.println("Test Completed");
		
		webDriver.quit();

	}
}
