package ExcelUtilities;

public class ExccelUtilitiesCallingClassSample {

	public static void main(String[] args) {
		
		// object of our implemented Excel Utilities class has been instantiated here.
		String projectPath = System.getProperty("user.dir");
		MyExcelUtilitiesSample excelWork = new MyExcelUtilitiesSample(projectPath +"/ExcelFiles/DataSheet.xlsx", "Sheet1");
		
		// Now we can simply call our implemented functions from that that class.
		excelWork.getExcelFileCountRows();
		excelWork.getColumnsCountFromFile();
		excelWork.getCellDataFromFile();
		excelWork.getNumericDataFromFile(1,2);
		excelWork.getRowCollaumnFromFile(1, 0);

	}

}
