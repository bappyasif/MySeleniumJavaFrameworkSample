package tutorial.edureka.example.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGAssertions {

	static WebDriver web_Driver;
	static String url_Location = "http://www.google.com";

	@Test
	public void EqualityAssertion() {
		Assert.assertEquals(true, IsEqual(10, 10));
		System.out.println("Assertion Successfull");
	}

	public boolean IsEqual(int num1, int num2) {
		if (num1 == num2) {
			return true;
		} else {
			return false;
		}
	}

	@BeforeTest
	public void InvokeBrowser() {

		try {

			WebDriverManager.chromedriver().setup();
			web_Driver = new ChromeDriver();
			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			web_Driver.get(url_Location);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void RetrieveTitle() {
		String found_Title = web_Driver.getTitle();
		Assert.assertEquals(found_Title, "Google");
		System.out.println("Assertion Successful");
	}

	@AfterTest
	public void TerminateBrowser() {
		web_Driver.quit();
		System.out.println("Test Completed");
	}

}
