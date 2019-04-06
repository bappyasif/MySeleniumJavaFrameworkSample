package LiveTestProjectDay10;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestOnBackendLoginAndCSVEmailAttachment {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/index.php/backendlogin/";
	static String userID = "user01";
	static String userPass = "guru99com";


	public static void main(String[] args) {

		try {
			ComencingTestWithCSVFileAsEmailAttachment();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void ComencingTestWithCSVFileAsEmailAttachment() throws InterruptedException {

		// preparing webDriver for this task.
		WebDriverManager.chromedriver().setup();
		webDriver =  new ChromeDriver();

		// navigating to back-end Login page
		webDriver.get(baseUrl);
		System.out.println("Landed in Login Page");

		// login in with provided credentials.
		webDriver.findElement(By.xpath("//input[@id='username']")).clear();
		webDriver.findElement(By.xpath("//input[@id='username']")).sendKeys(userID);
		webDriver.findElement(By.xpath("//input[@id='login']")).clear();
		webDriver.findElement(By.xpath("//input[@id='login']")).sendKeys(userPass);

		webDriver.findElement(By.xpath("//input[@title='Login']")).click();
		System.out.println("Back-end Login Successfull");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Handling popup window alert message
		webDriver.findElement(By.xpath("//span[contains(text(),'close')]")).click();

		// Going to click on Sales --> orders menu
		webDriver.findElement(By.xpath("//span[contains(text(),'Sales')]")).click();
		webDriver.findElement(By.linkText("Orders")).click();
		System.out.println("Inside Sales Orders Page");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Now clicking on Export Drop-down and selecting CSV from options.
		webDriver.findElement(By.xpath("//select[@id='sales_order_grid_export']")).click();
		new Select(webDriver.findElement(By.xpath("//select[@id='sales_order_grid_export']"))).selectByIndex(0);
		webDriver.findElement(By.xpath("//span[contains(text(),'Export')]")).click();
		System.out.println("Sales Orders File Been Exported As CSV Format");

		// Here comes download able window handing part which can b done manually changing download settings 
		// from Chrome settings to never to ask before downloading file enabled. Which is not recommended but using
		// AutoIT will be redundant and excessive brute force.  Rather use wiget which is a easier way to handle 
		// download able windows pop-ups easily.Fior that we need to install it and have a test run cmd  
		// before using it in Eclipse.

		// For the time being lets try sending a email which is doable not a big dieal. but then again 
		// when you will need to have an attachment along with this email body then it gets surfaced with
		// same problem of handling system window which I have not learned how to do it, as of yet!!

		System.out.println("Test Completed...");
		webDriver.quit();

	}

}
