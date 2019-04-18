package DemoBankProjectLiveTestDay03;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnParameterizingVerifyLoginSectionScript {

	static WebDriver webDriver;
	static String baseUrl = "http://www.demo.guru99.com/V4/";

	static XSSFWorkbook bankWorkbook;
	static XSSFSheet workbookSheet;
	static String projectPath;


	public static void main(String[] args) throws IOException, InterruptedException {

		CommencingTestOnParametrizingLoginSectionThroughExcel();

	}


	public static void CommencingTestOnParametrizingLoginSectionThroughExcel() throws IOException, InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		// Navigating to test site's landing page
		webDriver.get(baseUrl);
		System.out.println("Landing Successful");

		// We will use a excel file that we created saved in BankDemoExcelFiles Folder, 
		// to retrieve user name and password for Login Section of this script.
		projectPath = System.getProperty("user.dir");
		String workbookPath = projectPath+"\\BankDemoExcelFiles\\BankDemoTestData.xlsx";

		bankWorkbook = new XSSFWorkbook(workbookPath);
		workbookSheet = bankWorkbook.getSheet("Sheet1");

		String userID = workbookSheet.getRow(1).getCell(0).getStringCellValue();
		String userPassword = workbookSheet.getRow(1).getCell(1).getStringCellValue();
		System.out.println("ID  " +userID);
		System.out.println("Pass  " +userPassword +"\n");
		CommencingLogin(userID, userPassword);

		/**
		 * 
		 * 		 * 
		// This block of code for hard coding cell path to retrieve data from Excel File, which is redundant.
		// we used a nested for loops to achieve that, scroll below.

		String userIDRowTwo = workbookSheet.getRow(2).getCell(0).getStringCellValue();
		String userPasswordRowTwo = workbookSheet.getRow(2).getCell(1).getStringCellValue();
		System.out.println("ID  " +userIDRowTwo);
		System.out.println("Pass  " +userPasswordRowTwo +"\n");
		try {
			CommencingLogin(userIDRowTwo, userPasswordRowTwo);
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Cause Is: "+ex.getMessage());
			ex.getStackTrace();
		}

		String userIDRowThree = workbookSheet.getRow(3).getCell(0).getStringCellValue();
		String userPasswordRowThree = workbookSheet.getRow(3).getCell(1).getStringCellValue();
		System.out.println("ID  " +userIDRowThree);
		System.out.println("Pass  " +userPasswordRowThree +"\n");
		try {
			CommencingLogin(userIDRowThree, userPasswordRowThree);
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Cause Is: "+ex.getMessage());
			ex.getStackTrace();
		}

		String userIDRowFour = workbookSheet.getRow(4).getCell(0).getStringCellValue();
		String userPasswordRowFour = workbookSheet.getRow(4).getCell(1).getStringCellValue();
		System.out.println("ID  " +userIDRowFour);
		System.out.println("Pass  " +userPasswordRowFour +"\n");
		try {
			CommencingLogin(userIDRowFour, userPasswordRowFour);
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Cause Is: "+ex.getMessage());
			ex.getStackTrace();
		}

		 * 
		 */


		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < i; j++) {

				String userIDTestLooop = workbookSheet.getRow(i).getCell(j).getStringCellValue();
				String userPasswordTestLoop = workbookSheet.getRow(j+i).getCell(j+1).getStringCellValue();

				System.out.println(userIDTestLooop+"\n");
				System.out.println(userPasswordTestLoop+"\n");

				try {
					CommencingLogin(userIDTestLooop, userPasswordTestLoop);
				} catch (Exception ex) {
					// TODO: handle exception
					System.out.println("Cause Is: "+ex.getMessage());
					ex.getStackTrace();
				} 
				break;
			}
		}


		System.out.println("Test Completed");
		webDriver.quit();

	}

	public static void CommencingLogin(String UserID, String UserPass) throws InterruptedException {

		// Entering userID and Password to login section
		webDriver.findElement(By.xpath("//input[@name='uid']")).clear();
		webDriver.findElement(By.xpath("//input[@name='uid']")).sendKeys(UserID);
		webDriver.findElement(By.xpath("//input[@name='password']")).clear();
		webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys(UserPass);
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

			System.out.println("Verification Done");

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println(ex.getMessage());
			System.out.println(ex.getCause());

			ex.getStackTrace();

			System.out.println("Check Console For Clarification");

		} finally {

			webDriver.get(baseUrl);
		}

	}

}


/*
 * 

 public Object[][] getBankTestData() {

		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath+"\\BankDemoExcelFiles\\BankDemoTestData.xlsx";
		String excelSheet = "Sheet1";

		Object testData[][] = getBankTestData(excelPath, excelSheet);

		return testData;

	}
	private static void getBankTestData() {
		// TODO Auto-generated method stub

	}

 	public LiveTestOnParameterizingVerifyLoginSectionScript(String excelPath, String sheetName) {

		try {
			projectPath = System.getProperty("user.dir");
			//String workbookPath = projectPath+"\\BankDemoExcelFiles\\BankDemoTestData.xlsx";
			bankWorkbook = new XSSFWorkbook(excelPath);
			workbookSheet = bankWorkbook.getSheet(sheetName);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

	}

	for(int i = 0; i < 6; i++) {
			for(int j = 0; j < i-1; j++) {

				String userIDTestLooop = workbookSheet.getRow(i).getCell(j).getStringCellValue();
				//String userPasswordTestLoop = workbookSheet.getRow(j-i).getCell(j-i).getStringCellValue();

				System.out.println(userIDTestLooop+"\n");
				//System.out.println(userPasswordTestLoop);
			}
		}


		String userIDRowFive = workbookSheet.getRow(5).getCell(0).getStringCellValue();
		String userPasswordRowFive = workbookSheet.getRow(5).getCell(1).getStringCellValue();
		System.out.println("ID  " +userIDRowFive);
		System.out.println("Pass  " +userPasswordRowFive +"\n");




 * 
 * 
 */
