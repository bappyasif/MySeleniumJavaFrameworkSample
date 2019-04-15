package LiveTestProjectDay15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnBlankAndDisabledFields {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/index.php/backendlogin/";
	static String userID = "user01";
	static String userPass = "guru99com";
	
	public static void main(String[] args) throws InterruptedException {
		
		CommencingTestOnVerifyingFieldsDisabledAndBlank();
		
	}

	public static void CommencingTestOnVerifyingFieldsDisabledAndBlank() throws InterruptedException {

		// Getting platform geared up.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		// visiting back-end login page
		webDriver.get(baseUrl);
		System.out.println("Navigation Successfull");

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
		
		// Going to Customers and Manage Customers Menu
		webDriver.findElement(By.xpath("//a[@class='active']//span[contains(text(),'Customers')]")).click();
		webDriver.findElement(By.linkText("Manage Customers")).click();
		System.out.println("Inside Manage Customers Page");
		Thread.sleep(2000);
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		// opening any Customer in this case first one from list after Searching user by Country
		webDriver.findElement(By.xpath("//select[@id='customerGrid_filter_billing_country_id']")).sendKeys("Bangladesh");
		webDriver.findElement(By.xpath("//span[contains(text(),'Search')]")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("//input[@value='29659']")).click();
		webDriver.findElement(By.xpath("//td[contains(text(),'29659')]")).click();
		System.out.println("Inside Customer's Information Page");
		Thread.sleep(2000);
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		// Clicking On Account Information Tab From Customer Information Page.
		webDriver.findElement(By.xpath("//span[contains(text(),'Account Information')]")).click();
		Thread.sleep(2000);
		// Verifying Disabled & Blank Fields
		boolean fieldDisabledBoxOne = webDriver.findElement(By.xpath("//select[@id='_accountwebsite_id']")).isEnabled();
		System.out.println(webDriver.findElement(By.xpath("//label[@for='_accountwebsite_id']")).getText().toString().trim().replaceAll("[\\*]", "")+" Is : Enabled ? : "+fieldDisabledBoxOne);
		boolean fieldDisabledBoxTwo = webDriver.findElement(By.xpath("//input[@id='_accountcreated_in']")).isEnabled();
		System.out.println(webDriver.findElement(By.xpath("//label[@for='_accountcreated_in']")).getText().toString().trim()+" Is : Enabled ? : "+fieldDisabledBoxTwo);
		
		boolean blankField = webDriver.findElement(By.xpath("//input[@id='_accountnew_password']")).getText().isEmpty();
		System.out.println(webDriver.findElement(By.xpath("//label[@for='_accountnew_password']")).getText().toString().trim()+" Is : Empty ? : "+blankField);
		
		
		webDriver.findElement(By.xpath("//a[@class='link-logout']")).click();
		System.out.println("Logged Out");
		Thread.sleep(2000);
		
		System.out.println("Test CCompleted");
		webDriver.quit();

	}

}
