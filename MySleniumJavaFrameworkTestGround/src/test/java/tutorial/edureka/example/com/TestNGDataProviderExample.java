package tutorial.edureka.example.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author BappY This Class Demonstrates Two Different Ways For DataProvider
 *         Annotations And Execution. It Also Includes Other TestNG Annotations
 *         Such As Test, AfterTest, Priority, etc.
 * 
 */
public class TestNGDataProviderExample {

	static WebDriver web_Driver;
	static String url_Location = "http://www.facebook.com/";

	// Example #01
	@Test(dataProvider = "GetData")
	public void SetData(String uName, String uPassword) {
		System.out.println("User Name : " + uName);
		System.out.println("User Password : " + uPassword);
	}

	@DataProvider
	public Object[][] GetData() {
		Object[][] dataStream = new Object[3][2];

		// 1st Row
		dataStream[0][0] = "user01";
		dataStream[0][1] = "01pass";

		// 2nd Row
		dataStream[1][0] = "user02";
		dataStream[1][1] = "02pass";

		// 3rd Row
		dataStream[2][0] = "user03";
		dataStream[2][1] = "03pass";

		return dataStream;
	}

	// Example #02. We Could Have Used BeforeTest But Didn't Due To 
	// Putting Them Together In One Class for Demonstration purpose.
	@Test(priority = 0)
	public void InvokeBrowser() {

		try {

			WebDriverManager.chromedriver().setup();

			web_Driver = new ChromeDriver();
			web_Driver.get(url_Location);

			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(dataProvider = "testData", priority = 1)
	public void DataDrivenTestFramework(String uName, String uPassword) {

		web_Driver.findElement(By.cssSelector("#email")).clear();
		web_Driver.findElement(By.cssSelector("#email")).sendKeys(uName);

		web_Driver.findElement(By.cssSelector("#pass")).clear();
		web_Driver.findElement(By.cssSelector("#pass")).sendKeys(uPassword);
	}

	@DataProvider(name = "testData")
	public Object[][] DataFeed() {

		Object[][] loginCredentials = new Object[2][2];

		// 1st Row
		loginCredentials[0][0] = "someuser@domain.com";
		loginCredentials[0][1] = "01someuser";

		// 1st Row
		loginCredentials[1][0] = "anotheruser@domain.com";
		loginCredentials[1][1] = "02anotheruser";

		return loginCredentials;

	}

	@AfterTest
	public void ClosingBrowser() {

		// web_Driver.close(); // To Close An Opened Page In Browser.
		web_Driver.quit(); // To Quit Browser Entirely.

		System.out.println("Test Completed");

	}

}
