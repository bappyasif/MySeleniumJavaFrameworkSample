package ExcelUtilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MyExcelUtilitiesSample {

	// Creating class variable for easement.
	public static String projectPath;
	public static XSSFWorkbook myWorkbook;
	public static XSSFSheet myWorksheet;

	// Lets create our Class Constructor
	public MyExcelUtilitiesSample(String workbookPath, String sheetName) {

		try {
			projectPath = System.getProperty("user.dir"); // We will include this from the other Class of this package.
			myWorkbook = new XSSFWorkbook(workbookPath);
			myWorksheet = myWorkbook.getSheet(sheetName);

		} catch(Exception ex) {
			ex.getStackTrace();
		}

	}

	// java needs its main function to run.
	public static void main(String[] args) {
		// Calling functions
		getExcelFileCountRows();
		getColumnsCountFromFile();
		getCellDataFromFile();
		getNumericDataFromFile(1,3);
		getRowCollaumnFromFile(1, 0);
	}


	// Getting row numbers from our excel file.
	public static int getExcelFileCountRows() {

		int rowCount = 0;

		try {

			// Creating a more mobile and reliable project path.
			// projectPath = System.getProperty("user.dir"); // Now it's in the constructor.

			// Step # 01 create some excel files along with some data.

			// Step #2 create an object of XSSFWorkbook- if you're using .xlsx
			// other-wise use HSSFWorkbook Class object.

			// myWorkbook = new XSSFWorkbook(projectPath +"/ExcelFiles/DataSheet.xlsx"); // now it's in the constructor.

			// Step #3 create an workable reference for our created work sheet.
			// myWorksheet = myWorkbook.getSheet("Sheet1"); // now it's in the constructor.

			// Step #4 Call row count function 'There are so many things that can be done'
			// after getting the number of rows from file then store it into a variable.

			rowCount = myWorksheet.getPhysicalNumberOfRows();
			// int lastrowCount = myWorksheet.getLastRowNum();
			System.out.println("Number of Rows are: " +rowCount);
			//System.out.println("Number of last Rows: " +lastrowCount);

		} catch(Exception ex){ 	

			System.out.println("Exception Message: "+ex.getMessage());
			System.out.println("Cause Message: "+ex.getCause());
			ex.getStackTrace(); // show the entire stack trace.

		}

		return rowCount;
	}

	// Getting column counts from our excel file.
	public static int getColumnsCountFromFile() {

		int colCount = 0;

		try {

			// Creating a more mobile and reliable project path.
			//projectPath = System.getProperty("user.dir"); // Now it's in the constructor.
			// Step # 01 create some excel files along with some data.
			// Step #2 create an object of XSSFWorkbook- if you're using .xlsx
			// other-wise use HSSFWorkbook Class object.
			// myWorkbook = new XSSFWorkbook(projectPath +"/ExcelFiles/DataSheet.xlsx"); // now it's in the constructor.
			// Step #3 create an workable reference for our created work sheet.
			// myWorksheet = myWorkbook.getSheet("Sheet1"); // now it's in the constructor.
			// Step #4 Call row count function 'There are so many things that can be done'
			// after getting the number of rows from file then store it into a variable.

			colCount = myWorksheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("Number of Columns are: " +colCount);

		} catch(Exception ex){ 	

			System.out.println("Exception Message: "+ex.getMessage());
			System.out.println("Cause Message: "+ex.getCause());
			ex.getStackTrace(); // show the entire stack trace.

		}

		return colCount;
	}



	// Getting only String/'Alpha-numerical' valued data. 
	public static void getCellDataFromFile() {
		try {
			// Creating a more mobile and reliable project path.
			// projectPath = System.getProperty("user.dir");

			// Step # 01 create some excel files along with some data.

			// Step #2 create an object of XSSFWorkbook- if you're using .xlsx
			// other-wise use HSSFWorkbook Class object.

			// myWorkbook = new XSSFWorkbook(projectPath +"/ExcelFiles/DataSheet.xlsx");

			// Step #3 create an workable reference for our created work sheet.
			// myWorksheet = myWorkbook.getSheet("Sheet1");

			// Step #4 get data and store it in a variable.
			String cellData = myWorksheet.getRow(1).getCell(1).getStringCellValue();

			System.out.println("Cell Data:  " +cellData);

		}
		catch (Exception ex) {
			System.out.println("Exception Message: "+ex.getMessage());
			System.out.println("Cause Message: "+ex.getCause());
			ex.getStackTrace();

		}
	}

	// Getting only Numerical valued data 
	public static void getNumericDataFromFile(int rowNumber, int collumnNumber) {

		try {
			// Creating a more mobile and reliable project path.
			// projectPath = System.getProperty("user.dir");

			// Step # 01 create some excel files along with some data.

			// Step #2 create an object of XSSFWorkbook- if you're using .xlsx
			// other-wise use HSSFWorkbook Class object.

			// myWorkbook = new XSSFWorkbook(projectPath +"/ExcelFiles/DataSheet.xlsx");

			// Step #3 create an workable reference for our created work sheet.
			// myWorksheet = myWorkbook.getSheet("Sheet1");

			// Step #4 get data and store it in a variable.
			double cellData = myWorksheet.getRow(rowNumber).getCell(collumnNumber).getNumericCellValue();

			System.out.println("Cell Data:  " +cellData);

		}
		catch (Exception ex) {
			System.out.println("Numerical Value Exception Message: "+ex.getMessage());
			System.out.println("Numerical Value Cause Message: "+ex.getCause());
			ex.getStackTrace();

		}

	}

	public static String getRowCollaumnFromFile(int rowNumber, int collumnNumber) {

		String cellData = null;
		
		String cellData2 =null;
		
		try {
			// Creating a more mobile and reliable project path.
			// projectPath = System.getProperty("user.dir");

			// Step # 01 create some excel files along with some data.

			// Step #2 create an object of XSSFWorkbook- if you're using .xlsx
			// other-wise use HSSFWorkbook Class object.

			// myWorkbook = new XSSFWorkbook(projectPath +"/ExcelFiles/DataSheet.xlsx");

			// Step #3 create an workable reference for our created work sheet.
			// myWorksheet = myWorkbook.getSheet("Sheet1");

			// Step #4 get data and store it in a variable.
			cellData = myWorksheet.getRow(rowNumber).getCell(collumnNumber).getStringCellValue();
			cellData2 = myWorksheet.getRow(rowNumber).getCell(collumnNumber).getStringCellValue();
			// System.out.println("Cell Data:  " +cellData);
			System.out.println("Cell Dtata:  " +cellData2);

		}
		catch (Exception ex) {
			System.out.println("Exception Message: "+ex.getMessage());
			System.out.println("Cause Message: "+ex.getCause());
			ex.getStackTrace();

		}

		return cellData;

	}

}
