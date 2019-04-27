package DemoBankProjectLiveTestCaseDay12;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnFundTransferAndCustomizedStatement {
	// customer ID : 41433  AccountID: 59440

	static WebDriver webDriver;	
	static String baseUrl = "http://www.demo.guru99.com/V4/";

	// static String accountID = "59372"; // CustID : 32144 // this account Currently Exist
	//static String accountID = "59371"; // CustID : 5779 // This account Currently does not exist
	//static String customerID = "5779"; // check check
	//static String accountID = "59371";
	static final String payeesAccount = "59440";
	static final String payersAccount = "41433";
	static final String transferAmount = "8000";
	static final String transferDescription = "Friendly Chugg";

	static final String userID = "mngr190242";
	static final String userPassword = "hCrEd^8";

	public static void main(String[] args) {

		try {

			CommencingLiveTestOnFundTransferAndCustomizedStatement();

		}catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		} finally {

			System.out.println("Test Completed");
			webDriver.quit();
		}
	}


	public static void CommencingLiveTestOnFundTransferAndCustomizedStatement() throws InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		CommencingLogin();
		System.out.println("Successful Login And verified \n");

		FucndTransfer();
		System.out.println("Fund Transfer Chekpoint Verified \n");

		CustommizedStatements();
		System.out.println("Transfer Customized Statements Verified \n");

		MiniStatements();
		System.out.println(" Mini Statements Inquiry Verified \n");

	}

	public static void MiniStatements() throws InterruptedException {

		webDriver.findElement(By.linkText("Mini Statement")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Inside Mini Statement Page");
		Thread.sleep(2000);

		webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(payeesAccount);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		try {	

			// When Successful
			String miniStatement = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Mini Statement : " +miniStatement);

			String transactionType = webDriver.findElement(By.xpath("//body//td[3]")).getText().toString();
			System.out.println("Transaction Type : " +transactionType);

			webDriver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

		try {

			// When Unsuccessful
			Alert alertBox = webDriver.switchTo().alert();
			String alertMessage = alertBox.getText().toString();
			alertBox.accept();
			System.out.println("Alert Message : " +alertMessage);

			webDriver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
			System.out.println("Mini Statements Checkpoint Checked");

		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
	}


	public static void CustommizedStatements() throws InterruptedException {

		String todayDate = "04/28/2019";

		// Verifying Customized Statement Reflects On Fund Transaction.
		webDriver.findElement(By.linkText("Customised Statement")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Inside Customized Statement Page");
		Thread.sleep(2000);

		webDriver.findElement(By.xpath("//input[@name='accountno']")).clear();
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(payeesAccount);
		webDriver.findElement(By.xpath("//input[@name='fdate']")).sendKeys(todayDate);
		webDriver.findElement(By.xpath("//input[@name='tdate']")).sendKeys(todayDate);
		webDriver.findElement(By.xpath("//input[@name='amountlowerlimit']")).clear();
		webDriver.findElement(By.xpath("//input[@name='amountlowerlimit']")).sendKeys("2000");
		webDriver.findElement(By.xpath("//input[@name='numtransaction']")).clear();
		webDriver.findElement(By.xpath("//input[@name='numtransaction']")).sendKeys("10");
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		// When Successful
		try {

			String cutomizedStatements = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Customized Statements : " +cutomizedStatements);

			String transactionType = webDriver.findElement(By.xpath("//p[@class='heading3']")).getText().toString();
			System.out.println("Transaction Type : " +transactionType);

			webDriver.findElement(By.xpath("//a[contains(text(),'Continue')]")).click(); 
			System.out.println("Fund Transfer Was Found");

			webDriver.findElement(By.linkText("Home")).click();
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}
			Thread.sleep(2000);
			//SuccessfulStatement();

		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

		// When Not successful
		Alert alert = webDriver.switchTo().alert();
		String alertMessage = alert.getText().toString();
		System.out.println("Alert Message : " +alertMessage);
		alert.accept();
		System.out.println("Fund Transfer Not Found");

		webDriver.findElement(By.linkText("Home")).click();
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		Thread.sleep(2000);	
		//WhenUnsuccessful();
	}


	public static void FucndTransfer() throws InterruptedException {

		webDriver.findElement(By.linkText("Fund Transfer")).click();
		//Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		System.out.println("Inside Fund Transfer Page");


		webDriver.findElement(By.xpath("//input[@name='payersaccount']")).clear();
		webDriver.findElement(By.xpath("//input[@name='payersaccount']")).sendKeys(payersAccount);

		webDriver.findElement(By.xpath("//input[@name='payeeaccount']")).clear();
		webDriver.findElement(By.xpath("//input[@name='payeeaccount']")).sendKeys(payeesAccount);

		webDriver.findElement(By.xpath("//input[@name='ammount']")).clear();
		webDriver.findElement(By.xpath("//input[@name='ammount']")).sendKeys(transferAmount);

		webDriver.findElement(By.xpath("//input[@name='desc']")).clear();
		webDriver.findElement(By.xpath("//input[@name='desc']")).sendKeys(transferDescription);
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

		try {
			
			// When Successful transaction Occurs
			String transferMessage = webDriver.findElement(By.xpath("//body/table[@class='layout']/tbody/tr[1]/td[1]")).getText().toString();
			System.out.println("Transfer Verified: " +transferMessage);
			// May be a Screenshot??
			Thread.sleep(2000);
			
			// Clicking On Refresh button to Check whether it landed on Same page or not.
			//webDriver.navigate().refresh();
			String refreshedPage = webDriver.findElement(By.xpath("//p[contains(text(),'Fund Transfer Details')]")).getText().toString();
			System.out.println("After Refresh Page Title : " +refreshedPage);
			assertEquals(transferMessage, refreshedPage);
			//webDriver.navigate().refresh(); // This would take it to Fund Transfer Page
			//Thread.sleep(2000);
			System.out.println("Assertion Successful");
			//Thread.sleep(2000);

			System.out.println("Fund Transfer Was Successful");
			webDriver.findElement(By.linkText("Continue")).click();
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}
			Thread.sleep(2000);
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
		
		try {
			
			// When Fund Transfer Not Successful
			Alert alert = webDriver.switchTo().alert();
			String alertMessage = alert.getText().toString();
			System.out.println("Alert Message : " +alertMessage);
			alert.accept();
			System.out.println("Fund Transfer Was Unsuccessful");

			webDriver.findElement(By.linkText("Home")).click();
			// switching to new window. Even though it doesn't need this window switching but 
			// still expert says its better this way when you're landing on a new URL.
			for (String handle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handle);
			}
			Thread.sleep(2000);
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}
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