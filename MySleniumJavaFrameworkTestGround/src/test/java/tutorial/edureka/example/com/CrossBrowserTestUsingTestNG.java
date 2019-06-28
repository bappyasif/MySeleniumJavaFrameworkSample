package tutorial.edureka.example.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowserTestUsingTestNG {

	static WebDriver web_Driver;
	static String url_Location = "http://www.edureka.co/";

	static String user_ID = "abc@some.com";
	static String user_Pass = "some1234";
	static String search_Quotation = "Selenium";

	@BeforeTest
	@Parameters("Browser")
	public void ChoosingBrowser(String browser) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();

			web_Driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("IE")) {

			// WebDriverManager.iedriver().clearPreferences();

			WebDriverManager.iedriver().setup();

			web_Driver = new InternetExplorerDriver();

		}else if (browser.equalsIgnoreCase("EDGE")) {

				// WebDriverManager.iedriver().clearPreferences();

				WebDriverManager.edgedriver().setup();

				web_Driver = new EdgeDriver();
			
		} else {

			System.out.println("Unmatched Browser");

			throw new Exception("No Matched Browser Found");
		}

		web_Driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);

	}

	@Test(priority = 0)
	public void InvokeBrowser() {

		try {

			web_Driver.get(url_Location);

			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			CrossBrowsing();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test(priority = 1)
	public void CrossBrowsing() throws InterruptedException {

		WebElement login_Link = web_Driver.findElement(By.linkText("Log In"));
		login_Link.click();
		Thread.sleep(2000);

		WebElement user_Name = web_Driver.findElement(By.id("si_popup_email"));
		user_Name.sendKeys(user_ID);
		Thread.sleep(2000);

		WebElement user_Password = web_Driver.findElement(By.id("si_popup_passwd"));
		user_Password.sendKeys(user_Pass);
		Thread.sleep(2000);

		WebElement login_Button = web_Driver.findElement(By.xpath("//button[@class='clik_btn_log btn-block']"));
		login_Button.click();
		Thread.sleep(2000);

		web_Driver.findElement(By.xpath("//div[@class='modal-header']//button[@class='close'][contains(text(),'×')]"))
				.click();

		WebElement search_Box = web_Driver.findElement(By.cssSelector("#search-inp"));
		search_Box.sendKeys(search_Quotation);
		Thread.sleep(2000);

		WebElement search_Button = web_Driver.findElement(By.xpath("//span[@class='typeahead__button']"));
		search_Button.click();
		Thread.sleep(2000);
	}

	@AfterTest
	public void ClosingBrowser() {

		// web_Driver.close(); // To Close An Opened Page In Browser.
		web_Driver.quit(); // To Quit Browser Entirely.

		System.out.println("Test Completed");

	}

}
