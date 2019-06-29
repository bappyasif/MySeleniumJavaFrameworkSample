package tutorial.edureka.example.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(EventListenersUsingTestNG.class)  // Linking EventListener  Class Here

public class EventListnersTestCases {
	
	static WebDriver web_Driver;
	static String url_Location = "http://www.edureka.co/";
	
	static String search_Keys = "Test Automation Engineer Masters Program";
	
  @BeforeTest
  public void InvokeBrowser() {

		try {

			WebDriverManager.chromedriver().setup();
			web_Driver = new ChromeDriver();
			
			web_Driver.get(url_Location);

			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			//EventListener();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
  
  @Test
  public void PerformTest() throws InterruptedException {
	  
	  JavascriptExecutor script_Executor = (JavascriptExecutor)web_Driver;
	  
	  web_Driver.findElement(By.cssSelector("#search-inp")).sendKeys(search_Keys);
	  //web_Driver.findElement(By.className("typeahead_button")).click();
	  web_Driver.findElement(By.xpath("//span[@class='typeahead__button']")).click();
	  script_Executor.executeScript("window.scrollBy(0,500)");
	  
	  Thread.sleep(2000);
	  script_Executor.executeScript("window.scrollBy(0,700)");
	  
	  Thread.sleep(2000);
	  script_Executor.executeScript("window.scrollBy(0,1100)");

  }
  
  @Test
  public void FabricatedFunction() {
	  System.out.println("This Is To Check 'Failure' Test Assertion Works Or Not..");
	  Assert.assertTrue(false);
  }
  
  @AfterTest
  public void ClosingBrowser() {

		// web_Driver.close(); // To Close An Opened Page In Browser.
		web_Driver.quit(); // To Quit Browser Entirely.

		System.out.println("Test Completed");

	}

}
