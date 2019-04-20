package DemoBankProjectLiveTestCaseDay04;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnDataParameterizationUsingTestNG {

	static WebDriver webDriver;
	static String baseUrl = "http://www.demo.guru99.com/V4/";

	static XSSFWorkbook bankWorkbook;
	static XSSFSheet workbookSheet;
	static String projectPath;

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


	// Just a heads up that data are available to retrieve whence called upon.
	@BeforeClass
	public static void CommencingTestOnDataParameterization() throws IOException, InterruptedException {

		// We will use a excel file that we created saved in BankDemoExcelFiles Folder, 
		// to retrieve user name and password for Login Section of this script.
		projectPath = System.getProperty("user.dir");
		String workbookPath = projectPath+"\\BankDemoExcelFiles\\BankDemoTestData.xlsx";

		bankWorkbook = new XSSFWorkbook(workbookPath);
		workbookSheet = bankWorkbook.getSheet("Sheet1");

		String ID = workbookSheet.getRow(1).getCell(0).getStringCellValue();
		String PD = workbookSheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println("ID  " +ID);
		System.out.println("Pass  " +PD +"\n");
		//CommencingLogin(userID, userPassword);  // We will be using more convenient way of calling Data from Excel File.
	}

	@Test
	public static void ComencingIterationThroughRows() throws InterruptedException {

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < i; j++) {

				String userID = workbookSheet.getRow(i).getCell(j).getStringCellValue();
				String userPassword = workbookSheet.getRow(j+i).getCell(j+1).getStringCellValue();

				System.out.println(userID);
				System.out.println(userPassword+"\n");

				try {

					CommencingLogin(userID, userPassword);

				} catch (Exception ex) {
					// TODO: handle exception
					System.out.println("Cause Is: "+ex.getMessage());
					ex.getStackTrace();
				} 
				Thread.sleep(2000);
				break;
			}
		}
	}


	// This method is invoking only when its called. In other words not Injecting it unless required.
	public static void CommencingLogin(String UserID, String UserPD) throws InterruptedException {
		// TODO Auto-generated method stub

		// Entering userID and Password to login section
		webDriver.findElement(By.xpath("//input[@name='uid']")).clear();
		webDriver.findElement(By.xpath("//input[@name='uid']")).sendKeys(UserID);
		webDriver.findElement(By.xpath("//input[@name='password']")).clear();
		webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys(UserPD);
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
			// TODO: handle exception
			System.out.println(ex.getMessage());
			System.out.println(ex.getCause());

			System.out.println("Check Console For Clarification");
			ex.getStackTrace();
			
			// Alert Box Message Handling code, which was missing from earlier solution.
			// It seems much more convenient and easy to execute. 
			Alert alert = webDriver.switchTo().alert();
			String alertMessage = alert.getText().toString();
			alert.accept();
			System.out.println("Message Shown In Alert Box :   " +alertMessage);

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


