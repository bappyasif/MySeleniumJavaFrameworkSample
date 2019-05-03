package DemoBankProjectLiveTestCaseDay18;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnLogOutVerification {

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";

	static final String userID = "mngr190242";
	static final String userPassword = "hCrEd^8";


	public static void main(String[] args) {

		try {

			CommenccingTestOnLogOutVerification();

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}finally {
			System.out.println("Test Completed...");
			webDriver.quit();
		}

	}


	public static void CommenccingTestOnLogOutVerification() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		System.out.println("Successful Login And verified \n");

		LogOut();
		System.out.println("You Logged Out Successfully \n");

	}


	public static void LogOut() {

		webDriver.findElement(By.linkText("Log out")).click();

		HandlingAlert();

		String pageTitle = webDriver.getTitle();
		System.out.println("Page Title : " +pageTitle);

		System.out.println("Log Out Has Verified");

	}



	public static void HandlingAlert() {

		Alert alert = webDriver.switchTo().alert();
		String alertMessage = alert.getText().toString();
		System.out.println("Alert Message : " +alertMessage);
		alert.accept();

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

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Inside Manager Home Page");
		Thread.sleep(2000);

		try {

			System.out.println("Vefication Of Manager Home Page");

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
