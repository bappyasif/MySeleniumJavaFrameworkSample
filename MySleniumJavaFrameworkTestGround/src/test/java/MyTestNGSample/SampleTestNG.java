package MyTestNGSample;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTestNG {

	public  WebDriver webDriver ;
	public String baseUrl = "https://google.com";



	@BeforeTest
	public void setUpMyTest() {

		// Here we are setting up the environment for Test.
		// Setting up the browser with webDriverManager

		WebDriverManager.chromedriver().setup();

		webDriver = new ChromeDriver();

		webDriver.manage().window().maximize();

		webDriver.get(baseUrl);

		//webDriver.manage().timeouts().wait(0, 0);

	}


	@Test
	public void generalSearch() {



		// Fetching data through findElementBy
		// "ID" , "name" and also through List object with findElementsBy then using keys function to either write/hit enter.
		
		
		webDriver.findElement(By.name("q")).sendKeys("Selenium Automation");
		webDriver.findElement(By.name("btnK")).submit();
		
		/*
		 * 

		  webDriver.findElement(By.id("gsc-i-id1")).sendKeys("Test Automation");

		List<WebElement> listOfWebElements = webDriver.findElements(By.xpath("//input[@placeholder='Custom Search']"));

		int count = listOfWebElements.size();

		System.out.println("Number Of Charancter in SearchBox  " + count);

		webDriver.findElement(By.name("search")).submit();


		 * 
		 */


	}

	@AfterTest
	public void tearDownMyTest() {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		webDriver.close();

		webDriver.quit();

		System.out.println("Test Successful");

	}

}
