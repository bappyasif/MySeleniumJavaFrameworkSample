package DemoBankProjectLiveTestCaseDay09;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.asprise.ocr.Ocr;

import io.github.bonigarcia.wdm.WebDriverManager;

// Before running this test case firstly, run testCase08 from this series with a different email test account.
// get cutomerID from there and use it  in here as a requirement.
public class LiveTestOnVerifyNewAccountCreated {

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";
	static final String userID = "mngr190242";
	static String userPassword = "hCrEd^8";
	static String filePath = "E:\\eclipse\\LiveTestCodeSamples\\Bank Demo\\NewCustomerAccountCreationScreenShot.png";
	static String customerID = "32144";
	static String extractID;
	static String initialDeposit = "99999992";
	//static String accountID = "59371";  // just in case if you need this data to manipulate in future. CustID : 5779.
	// Test Data 2: cutomerID : 32144  ; accountID : 59372
	public static void main(String[] args) {
		
		try {
			CommencingTestOnAccountCreationVerification();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		} finally {
			
			System.out.println("Test Completed...");
			webDriver.quit();
		}
		
	}
	
	
	public static void CommencingTestOnAccountCreationVerification() throws InterruptedException, IOException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		CreatingNewBankingAccount();
	}

	public static void CreatingNewBankingAccount() throws InterruptedException, IOException {
		// getting new customer ID from earlier Test Case
		//DemoBankProjectLiveTestCase08.LiveTestOnCreaingNewCustomerAccount.CommencingTestOnNewCustomerAccountCreation();
		//ExtractingNewlyCratedCustomerID();

		webDriver.findElement(By.linkText("New Account")).click();
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Inside New Account Page");
		Thread.sleep(2000);

		try {

			webDriver.findElement(By.xpath("//input[@name='cusid']")).clear();
			webDriver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
			new Select(webDriver.findElement(By.xpath("//select[@name='selaccount']"))).selectByIndex(1);
			webDriver.findElement(By.xpath("//input[@name='inideposit']")).clear();
			webDriver.findElement(By.xpath("//input[@name='inideposit']")).sendKeys(initialDeposit);
			webDriver.findElement(By.xpath("//input[@name='button2']")).click();

			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}

			System.out.println("Account Created??");
			Thread.sleep(2000);

			String accountMessage = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Message Shown After Completeion : " +accountMessage);

			String accountID = webDriver.findElement(By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]"
					+ "/td[1]/table[1]/tbody[1]/tr[4]/td[2]")).getText().toString();
			System.out.println("Account ID : " +accountID);
			
			TakingSnapshot();
			ReeadingDataFromImage(); // It's just an approach to get something specific rather than whole.
			System.out.println("Verification Completed");
			
			webDriver.findElement(By.linkText("Continue")).click();
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}
			Thread.sleep(2000);
			System.out.println("New Bank Account Creation Completed.");


		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

	}

	// Even though it doesn't work as per my expectation but, just keeping it for future reference. 
	public static void ReeadingDataFromImage() throws IOException {
		Ocr.setUp();
		Ocr ocrReady = new Ocr();
		//ocrReady.setUp();
		ocrReady.startEngine("eng", Ocr.SPEED_FAST); // English OCR
		extractID = ocrReady.recognize((RenderedImage) new File(filePath), ocrReady.RECOGNIZE_TYPE_ALL, ocrReady.OUTPUT_FORMAT_PLAINTEXT);
		System.out.println(extractID);
		ocrReady.stopEngine();

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

	public static void TakingSnapshot() throws InterruptedException, IOException {

		org.openqa.selenium.Dimension windowSize = webDriver.manage().window().getSize(); 
		webDriver.navigate().refresh();
		Thread.sleep(2000);

		// Scrolling Page Until EOF
		((JavascriptExecutor) webDriver)
		.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		webDriver.manage().window().maximize();
		System.out.println("Taking ScreenShot");

		TakesScreenshot newBankAccountScreenShot = ((TakesScreenshot)webDriver);
		File ncacScreenShot = newBankAccountScreenShot.getScreenshotAs(OutputType.FILE);
		String fileSaved = ("E:\\eclipse\\LiveTestCodeSamples\\Bank Demo\\NewBankAccountCreationScreenShot" +".png");
		FileUtils.copyFile(ncacScreenShot, new File(fileSaved));

		System.out.println("Your Screenshot Is Saved At This Location : " +fileSaved);

		webDriver.manage().window().setSize(windowSize);
		System.out.println("Done TakingSnapshot");

	}


}
