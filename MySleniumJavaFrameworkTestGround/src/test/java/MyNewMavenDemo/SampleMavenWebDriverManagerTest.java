package MyNewMavenDemo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleMavenWebDriverManagerTest {

	// Java needs it's main function to run.
	public static void main(String[] args) {
		// Calling Function
		generalSearch();

	}

	public static void generalSearch() {

		// Setting up the browser with webDriverManager and then fetching data through findElementBy
		// "ID" , "name" and also through List object with findElementsBy then using keys function to either write/hit enter.
		WebDriverManager.chromedriver().setup();

		WebDriver webDriver = new ChromeDriver();

		webDriver.manage().window().maximize();

		webDriver.get("https://www.guru99.com/");

		webDriver.findElement(By.id("search")).sendKeys("Test Automation");

		List<WebElement> listOfWebElements = webDriver.findElements(By.xpath("/input[@placeholder=\"What're we looking for ?\"]"));

		int count = listOfWebElements.size();

		System.out.println("Number Of Charancter in SearchBox  " + count);

		webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);

		System.out.println("Test Successful");

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		webDriver.quit();

	}

}
