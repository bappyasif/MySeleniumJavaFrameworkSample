package tutorial.edureka.example.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWebDriverEventListenerTest {

	static WebDriver web_Driver;
	static String url_Location = "http://www.edureka.co/";

	public void InvokeBrowser() {

		try {

			WebDriverManager.chromedriver().setup();
			web_Driver = new ChromeDriver();
			
			//web_Driver.get(url_Location);

			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			EventListener();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void EventListener() throws InterruptedException {
		
		JavascriptExecutor script_Executor = (JavascriptExecutor)web_Driver;
		EventFiringWebDriver event_Handler = new EventFiringWebDriver(web_Driver);
		EventCapture implemented_Listener = new EventCapture();
		
		// Registering With Our created EventFiringWebDriver Object, This Will Accommodate WebDriverEventListener To Respond.
		event_Handler.register(implemented_Listener);
		
		// Navigating To Test Site
		event_Handler.navigate().to(url_Location +"blog");
		script_Executor.executeScript("window.scrollBy(0,500)");
		//Thread.sleep(2000);
		//event_Handler.findElement(By.xpath("//div[@class='wzrk-overlay']")).click();
		
		event_Handler.findElement(By.linkText("Software Testing")).click();
		event_Handler.navigate().to(url_Location +"all-courses");
		
		event_Handler.navigate().back();
		event_Handler.quit();
		
		// Unregistering After Test Execution
		event_Handler.unregister(implemented_Listener);
		System.out.println("End Of EventListener Interface");
		
	}

	public void ClosingBrowser() {

		// web_Driver.close(); // To Close An Opened Page In Browser.
		web_Driver.quit(); // To Quit Browser Entirely.

		System.out.println("Test Completed");

	}
	
	public static void main(String[] args) {
		
		SeleniumWebDriverEventListenerTest created_Object = new SeleniumWebDriverEventListenerTest();
		created_Object.InvokeBrowser();
		created_Object.ClosingBrowser();
		
	}

}
