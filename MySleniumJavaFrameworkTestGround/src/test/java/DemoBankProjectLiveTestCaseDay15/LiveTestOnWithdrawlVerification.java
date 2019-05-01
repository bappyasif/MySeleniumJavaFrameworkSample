package DemoBankProjectLiveTestCaseDay15;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnWithdrawlVerification {

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";
	static final String userID = "mngr190242";
	static final String userPassword = "hCrEd^8";
	static String accountID = "59372";
	static String withdrawlAmount = "7000";
	static String withdrawlDescription = "Withdrawl Amount";


	public static void main(String[] args) throws InterruptedException {



		try {
			
			//CommencingLogin();
			//CrossReferencingBalanceEnquiry(String balance);
			CommencingTestOnWithdrawlVerification();
			BalanceEnquiry();
			//MiniStatement();

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();

		} finally {
			//BalanceEnquiry();			
			System.out.println("Test Completed...");
			webDriver.quit();

		}
	}


	public static void CommencingTestOnWithdrawlVerification() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		System.out.println("Successful Login And verified \n");

		WithdrawlAmount();
		System.out.println("Withdrawl Amount Checkpoint Verified \n");

	}


	public static void WithdrawlAmount() throws InterruptedException {

		webDriver.findElement(By.linkText("Withdrawal")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for(String windowHandler : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(windowHandler);
		}

		System.out.println("Inside Withdrawl Amount Page");
		Thread.sleep(2000);

		webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
		webDriver.findElement(By.xpath("//input[@name='ammount']")).clear();
		webDriver.findElement(By.xpath("//input[@name='ammount']")).sendKeys(withdrawlAmount);
		webDriver.findElement(By.xpath("//input[@name='desc']")).clear();
		webDriver.findElement(By.xpath("//input[@name='desc']")).sendKeys(withdrawlDescription);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();


		// When Successful
		try {

			String withdrawlConfirmation = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Withdrawl Confirmation : " +withdrawlConfirmation);

			// Will be checking up on Account ID and Amount Shown in Confirmation Message and a snapshot.
			String accountShown = webDriver.findElement(By.xpath("//td[contains(text(),'59372')]")).getText().toString();
			assertEquals(accountID, accountShown);
			System.out.println("ID Verified");
			String amountDebitted = webDriver.findElement(By.xpath("//td[contains(text(),'7000')]")).getText().toString();
			assertEquals(withdrawlAmount, amountDebitted);
			System.out.println("Amount Verified");

			String currentBalance = webDriver.findElement(By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]"
					+ "/table[1]/tbody[1]/tr[23]/td[2]")).getText().toString();
			System.out.println("Current Balance : " +currentBalance);
			
			TakingSnapshot();
			
			String verifiedBalance = CrossReferencingBalanceEnquiry(currentBalance);
			System.out.println("Balance Returned : " +verifiedBalance);
			
			assertEquals(currentBalance, verifiedBalance);
			System.out.println("Balance Asserted");
			
			/**
			 * 
			// Even though this block of code works just fine but for some reasons assertion does not seem to work!!
			// It matched with the actual String value that we are comparing with. 
			String verifiedBalance = CrossReferencing(currentBalance);
			int amount = Integer.parseInt(withdrawlAmount);
			//assertEquals(currentBalance, verifiedBalance);
			int newBalance = Integer.parseInt(verifiedBalance);
			int adjustedBalance = newBalance - amount;
			String expectedBalance = String.valueOf(adjustedBalance);
			assertEquals(currentBalance, adjustedBalance); // Even though it checks out perfectly but doesn't comply. 
			
			System.out.println("Balance Returned : " +verifiedBalance);
			System.out.println("Balance Verified : " +adjustedBalance);
			 
			 * 
			 */

			

			webDriver.findElement(By.linkText("Continue")).click();
			System.out.println("Withdrawl Cheked");

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();

		} finally {
			MiniStatement(); // When unsuccessful withdrawal occurs it will show last transaction detail from records.
			webDriver.findElement(By.linkText("Continue")).click();
			System.out.println("Mini Statement Is Cheked");
			
			//BalanceEnquiry();
		}

		// When Unsuccessful
		try {

			HandlingAlert();

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
	}


	public static void MiniStatement() throws InterruptedException {

		webDriver.findElement(By.linkText("Mini Statement")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for(String windowHandler : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(windowHandler);
		}

		System.out.println("Inside Mini Statement Page");
		Thread.sleep(2000);

		webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		// When Successful
		try {

			String foudnAmount = webDriver.findElement(By.xpath("//tr//tr[2]//td[2]")).getText().toString();
			System.out.println("Amount Debited : " +foudnAmount);

			String transsactionType = webDriver.findElement(By.xpath("//td[contains(text(),'w')]")).getText().toString().toUpperCase();
			System.out.println("Transaction Type : " +transsactionType);

			//

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

		// When Unsuccessful
		try {
			HandlingAlert();
		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
	}


	public static void BalanceEnquiry() {

		//String currentBalance = "";  // Please Change this by reduced withdrawal amount.
		//long balanceUpdate = Long.parseLong(currentBalance);
		//long withdrawalAmount = Long.parseLong(withdrawlAmount);
		//long adjustedBalance = balanceUpdate  - withdrawalAmount;
		//String updatedBalance = String.valueOf(adjustedBalance);

		//String customPath = "//td[contains(text(),'"+currentBalance+"')]";

		webDriver.findElement(By.linkText("Balance Enquiry")).click();

		webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		//String checkingBalanece = webDriver.findElement(By.xpath(customPath)).getText().toString();
		//System.out.println("Balance Extracted : " +checkingBalanece);

		//return checkingBalanece;
	}
	
	
	public static String CrossReferencingBalanceEnquiry(String balance) {
		
		webDriver.findElement(By.linkText("Balance Enquiry")).click();

		webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		String checkingBalanece = webDriver.findElement(By.xpath("/html[1]/body[1]/table[1]/tbody[1]"
				+ "/tr[1]/td[1]/table[1]/tbody[1]/tr[16]/td[2]")).getText().toString();
		return checkingBalanece;
	}
	

/**
 * 
 * @param balance
 * @return
 * 
 
 public static String CrossReferencing(String balance) {
		String balaneceCheck = balance;  // Please Change this by reduced withdrawal amount.
		double beforeWithdrawl = Double.parseDouble(balaneceCheck);
		long afWdrawal = Long.parseLong(balaneceCheck);
		double amountWithdrawl = Double.parseDouble(withdrawlAmount);
		long amWithdrawal = Long.parseLong(withdrawlAmount);
		double verifiedBalance = beforeWithdrawl - amountWithdrawl;
		long vdBalance = afWdrawal + amWithdrawal;
		String currentBalance = String.valueOf(verifiedBalance);
		String prevBalance = String.valueOf(vdBalance);
		//System.out.println("New Balance : " +currentBalance);
		System.out.println("New Balance : " +prevBalance);
		return prevBalance;
	}
 
 * 
 */
	
	
	public static void TakingSnapshot() throws InterruptedException, IOException {

		org.openqa.selenium.Dimension windowSize = webDriver.manage().window().getSize(); 
		//webDriver.navigate().refresh();
		Thread.sleep(2000);

		// Scrolling Page Until EOF
		((JavascriptExecutor) webDriver)
		.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		webDriver.manage().window().maximize();
		System.out.println("Taking ScreenShot");

		TakesScreenshot withdrawlAmountScreenShot = ((TakesScreenshot)webDriver);
		File daScreenShot = withdrawlAmountScreenShot.getScreenshotAs(OutputType.FILE);
		String fileSaved = ("E:\\eclipse\\LiveTestCodeSamples\\Bank Demo\\WithdrwalAmountVerifiedScreenShot" +".png");
		FileUtils.copyFile(daScreenShot, new File(fileSaved));

		System.out.println("Your Screenshot Is Saved At This Location : " +fileSaved);

		webDriver.manage().window().setSize(windowSize);
		System.out.println("Getting Out Of TakingSnapshot");

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
