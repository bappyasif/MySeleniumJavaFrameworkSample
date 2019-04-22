package DemoBankProjectLiveTestCase07;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class liveTestOnChangingPasswordAndLogin {

	static WebDriver webDriver;
	static String baseUrl = "http://www.demo.guru99.com/V4/";

	static final String userID = "mngr190242"; 
	static String userPassword = "hBrEd^8";
	static String newPassword = "hCrEd^8";

	static String tempPassword = "hArEdAq";
	
	public static void main(String[] args) throws InterruptedException {
		
		CommencingTestOnChangingPasswordAndLogin();
		
	}
	

	public static void CommencingTestOnChangingPasswordAndLogin() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		int count = 0;
		if (count == 0) {

			System.out.println(" Password Changing ");
			ChangingPassword();
			count++;
		} else {

			System.out.println("Current User login Count :" +count);
			System.out.println("Password Is Changed Already");

		}
		
		CommencingLogin();
		
		System.out.println("Test Completed...");
		webDriver.quit();

	}

	public static void ChangingPassword() throws InterruptedException {

		webDriver.findElement(By.linkText("Change Password")).click();
		System.out.println("Inside Change Password Page");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		webDriver.findElement(By.xpath("//input[@name='oldpassword']")).clear();
		webDriver.findElement(By.xpath("//input[@name='oldpassword']")).sendKeys(userID);
		webDriver.findElement(By.xpath("//input[@name='newpassword']")).clear();
		webDriver.findElement(By.xpath("//input[@name='newpassword']")).sendKeys(newPassword);
		webDriver.findElement(By.xpath("//input[@name='confirmpassword']")).clear();
		webDriver.findElement(By.xpath("//input[@name='confirmpassword']")).sendKeys(newPassword);
		webDriver.findElement(By.xpath("//input[@name='sub']")).click();
		System.out.println("Password Changed");
		Thread.sleep(2000);
		userPassword = newPassword;
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		webDriver.findElement(By.linkText("Log out")).click();
		System.out.println("Loogged Out");
		Alert alert = webDriver.switchTo().alert();

		String alertMessage = alert.getText().toString();
		alert.accept();
		System.out.println("Alert Box Message Is:  " +alertMessage +"\n");
		CommencingLogin();
	}

	public static void CommencingLogin() throws InterruptedException {

		// Navigating to test site's landing page
		webDriver.get(baseUrl);
		System.out.println("Landing Successful");

		// Entering userID and Password to login section
		webDriver.findElement(By.xpath("//input[@name='uid']")).clear();
		webDriver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		webDriver.findElement(By.xpath("//input[@name='password']")).clear();
		webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys(userPassword);
		webDriver.findElement(By.xpath("//input[@value='LOGIN']")).click();

		System.out.println("Inside User Home Page");
		Thread.sleep(2000);

		try {

			System.out.println("Vefication Of User Home Page");

			String userloginHeadingStatement = webDriver.findElement(By.xpath("//marquee[@class='heading3']")).getText().toString().trim();
			System.out.println("Heading Statement : " +userloginHeadingStatement);

			String userVerificationByID = webDriver.findElement(By.xpath("//td[contains(text(),'Manger Id : mngr190242')]")).getText().toString().trim();
			System.out.println("User Identification : " +userVerificationByID);

			// Test Case wanted to include Page title as a step of verification process.
			String hpTitle = webDriver.getTitle().toString().trim();
			System.out.println("Page Title : " +hpTitle);

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Check Console For Clarification");
			ex.getStackTrace();

			Alert alert = webDriver.switchTo().alert();

			String alertMessage = alert.getText().toString();
			alert.accept();
			System.out.println("Alert Box Message Is:  " +alertMessage +"\n");
		}

	}

}
