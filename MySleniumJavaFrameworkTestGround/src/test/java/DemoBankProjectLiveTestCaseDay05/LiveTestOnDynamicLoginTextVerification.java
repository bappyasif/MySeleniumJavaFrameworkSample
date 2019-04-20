package DemoBankProjectLiveTestCaseDay05;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnDynamicLoginTextVerification {

	static WebDriver webDriver;
	static String baseUrl = "http://www.demo.guru99.com/V4/";


	@BeforeTest
	public static void launchingBrowser() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		// Navigating to test site's landing page
		webDriver.get(baseUrl);
		System.out.println("Landing Successful");
		Thread.sleep(2000);
	}


	@Test(dataProvider="getData")
	public static void setData(String userID, String userPassword) throws InterruptedException {

		System.out.println("ID Used: " +userID);
		System.out.println("Password Used: " +userPassword);
		System.out.println("Commencing Login :");
		CommencingLogin(userID,userPassword);
	}


	@DataProvider
	public Object[][] getData(){

		//Rows - Number of times your test has to be repeated. "4"
		//Columns - Number of parameters in test data. "2"
		Object[][] data = new Object[4][2];

		// 1st Row : Valid ID and Password
		data[0][0] = "mngr190242";
		data[0][1] = "hArEdAq";

		// 2nd Row : InValid ID and Valid Password
		data[1][0] = "mngr1136";
		data[1][1] = "hArEdAq";

		// 3rd Row : Valid ID and InValid Password
		data[2][0] = "mngr190242";
		data[2][1] = "hBr0dAq";

		// 4th Row : InValid ID and InValid Password
		data[3][0] = "mngr1137";
		data[3][1] = "hBr0d0q";

		return data;

	}

	
	public static void CommencingLogin(String ID, String PD) throws InterruptedException {

		// Entering userID and Password to login section
		webDriver.findElement(By.xpath("//input[@name='uid']")).clear();
		webDriver.findElement(By.xpath("//input[@name='uid']")).sendKeys(ID);
		webDriver.findElement(By.xpath("//input[@name='password']")).clear();
		webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys(PD);
		webDriver.findElement(By.xpath("//input[@value='LOGIN']")).click();

		System.out.println("Inside User Home Page");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Verifying User login was successful
		try {

			System.out.println("Vefication Of User Home Page");

			String userloginHeadingStatement = webDriver.findElement(By.xpath("//marquee[@class='heading3']")).getText().toString().trim();
			System.out.println("Heading Statement : " +userloginHeadingStatement);

			String userVerificationByID = webDriver.findElement(By.xpath("//td[contains(text(),'Manger Id : mngr190242')]")).getText().toString().trim();
			System.out.println("User Identification : " +userVerificationByID);

			System.out.println("Verification Done \n");

		} catch (Exception ex) {
			// TODO: handle exception along with Alert Message.
			System.out.println("Check Console For Clarification");
			ex.getStackTrace();

			Alert alert = webDriver.switchTo().alert();
			
			String alertMessage = alert.getText().toString();
			alert.accept();
			System.out.println("Alert Box Message Is:  " +alertMessage +"\n");

		} finally {

			webDriver.get(baseUrl);
		}

	}
	
	@AfterTest
	public static void terminatingBrowser() {
		
		System.out.println("Test Completed...");
		webDriver.quit();
		
	}

}
