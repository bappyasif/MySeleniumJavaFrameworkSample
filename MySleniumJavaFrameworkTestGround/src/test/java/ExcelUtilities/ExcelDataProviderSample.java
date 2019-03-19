package ExcelUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelDataProviderSample {
	
	WebDriver webDriver;
	String baseUrl = "https://opensource-demo.orangehrmlive.com/";
	
	
	/**
	 *
	 *@param args
	 
	 // As we are using TestNG functionality it does not require main function to run from.
	 public static void main(String[] args) {
		
		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath+"\\ExcelFiles\\DataProvider.xlsx"; 
		String excelPage = "Sheet1";
		
		testData(excelPath, excelPage);
	}
	 
	 * 
	 */
	
	
	// Lets add our TestNg functionality
	
	// lets add BeforeTest TestNG annotations
	@BeforeTest
	public void launchBrowser() {
		
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		// webDriver.manage().window().maximize();
		// webDriver.get(baseUrl);
		
	}
	
	// Step #1 create a function with TestNG annotation 'DataProvider' 
	@DataProvider(name="TestNGDataProviderTest")
	public Object[][] getData() {
		
		// Step #2 provide necessary path for excelPath and excelPage.
		String projectPath = System.getProperty("user.dir");
		String excelPath = projectPath+"\\ExcelFiles\\DataProvider.xlsx"; 
		String excelPage = "Sheet1";
		
		Object testData[][] = testData(excelPath, excelPage);
		
		// rStep #3 return data.
		return testData; 
	}	
	
	@Test(dataProvider = "TestNGDataProviderTest")
	public void myDataProviderTest(String userName, String password, String country, String city ) throws Exception {
		
		System.out.println(userName + " |" + password + " | " + country +" | " +city);
		
		// Lets write up a Selenium test which will get data from our excel file and use it as input
		webDriver.get(baseUrl);
		webDriver.findElement(By.id("txtUsername")).sendKeys(userName);
		webDriver.findElement(By.id("txtPassword")).sendKeys(password);
		Thread.sleep(2000);
		
	}
	
	@AfterTest
	public void terminateBrowser() {
		
		webDriver.quit();
		
		System.out.println("Test Completed");
	}
		
	// create a function and inside of it create an object for 
	// MyExcelUtilitiesSample Class.
	public static Object[][] testData(String excelPath, String excelPage) {
		
		MyExcelUtilitiesSample myExcel = new MyExcelUtilitiesSample(excelPath, excelPage);
		
		// Get row and column count and store it in variables.
		int rowCount = myExcel.getExcelFileCountRows();
		int columnCount = myExcel.getColumnsCountFromFile();
		
		// Creating Object Class Two dimensional array object. Its safer, because data can be of any types.
		Object cellValue[][] = new Object[rowCount-1][columnCount];
		
		// run a nested loop to get all data from the file to an Two Dimensional Object array.
		for(int i = 1; i<rowCount; i++) {
			for(int j = 0; j<columnCount; j++) {
				
				String cellData = myExcel.getRowCollaumnFromFile(i, j);
				
				System.out.print("CellData:  " +cellData +" | ");
				cellValue[i-1][j] = cellData;
			}
			
			System.out.println("\n");
		}
		return cellValue;
		 
	}
	 
	

}
