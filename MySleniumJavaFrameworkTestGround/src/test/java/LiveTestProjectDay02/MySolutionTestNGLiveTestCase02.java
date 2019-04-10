package LiveTestProjectDay02;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MySolutionTestNGLiveTestCase02 {

	static WebDriver webDriver;
	static String baseUrl  = "http://live.guru99.com/";
	static String mobilePrice;
	static String sonyPrice;

	@BeforeTest
	public void LaunchingBrowser() {
		// Setting up environment here.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	public void CommencingTest02Day02() {
		// Navigating to the Test Site
		webDriver.get(baseUrl);
		System.out.println("Navigateed to Landing Page");

		// Clicking on Mobile Menu
		webDriver.findElement(By.linkText("MOBILE")).click();
		System.out.println("Mobile Page Is Clicked");

		// Reading the Cost of Sony Experia mobile. Also save it for comparison purpose.
		mobilePrice = webDriver.findElement(By.className("price")).getText();
		System.out.println("Price On Product Page  : " +mobilePrice);

		// Clicking on Sony Experia mobile for details.
		webDriver.findElement(By.cssSelector("#product-collection-image-1")).click();

		// Read the XPeria mobile price from details page
		sonyPrice = webDriver.findElement(By.className("price")).getText();
		System.out.println("Price On Details Page : " +sonyPrice);

		//  Product price in list and details page should be equal ($100)
		try {
			assertEquals(mobilePrice, sonyPrice); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void terminatingBrowser() {
		System.out.println("Test Completed...");
		webDriver.quit();
	}

}
