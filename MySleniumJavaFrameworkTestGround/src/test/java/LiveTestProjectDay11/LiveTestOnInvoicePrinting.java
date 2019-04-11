package LiveTestProjectDay11;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnInvoicePrinting {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/index.php/backendlogin/";
	static String userID = "user01";
	static String userPass = "guru99com";


	public static void main(String[] args) {
		
		try {
			try {
				CommencingTestOnInvoicePriniting();
			} catch (InvalidPasswordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void CommencingTestOnInvoicePriniting() throws InterruptedException, InvalidPasswordException, IOException {

		// Getting platform geared up.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		// visiting back-end login page
		webDriver.get(baseUrl);
		System.out.println("Navigation Successfull");

		// login in with provided credentials.
		webDriver.findElement(By.xpath("//input[@id='username']")).clear();
		webDriver.findElement(By.xpath("//input[@id='username']")).sendKeys(userID);
		webDriver.findElement(By.xpath("//input[@id='login']")).clear();
		webDriver.findElement(By.xpath("//input[@id='login']")).sendKeys(userPass);

		webDriver.findElement(By.xpath("//input[@title='Login']")).click();
		System.out.println("Back-end Login Successfull");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Handling popup window alert message
		webDriver.findElement(By.xpath("//span[contains(text(),'close')]")).click();

		// Going to click on Sales --> orders menu
		webDriver.findElement(By.xpath("//span[contains(text(),'Sales')]")).click();
		webDriver.findElement(By.linkText("Orders")).click();
		System.out.println("Inside Sales Orders Page");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// In status field clicking on Cancelled and hitting Search button.
		new Select(webDriver.findElement(By.xpath("//select[@id='sales_order_grid_massaction-select']"))).selectByVisibleText("Cancel");
		webDriver.findElement(By.xpath("//span[contains(text(),'Search')]")).click();
		System.out.println("Filter applied in Search");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Selecting first order from generated list and then selecting Print invoices from Actions
		String asertStatemeemnt = "There are no printable documents related to selected orders.";
		webDriver.findElement(By.xpath("//input[@value='9216']")).click();
		new Select(webDriver.findElement(By.xpath("//select[@id='sales_order_grid_massaction-select']"))).selectByVisibleText("Print Invoices");
		webDriver.findElement(By.xpath("//span[contains(text(),'Submit')]")).click();
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		String errorMessage = webDriver.findElement(By.xpath("//li[@class='error-msg']//ul//li")).getText();
		assertEquals(errorMessage, asertStatemeemnt);
		System.out.println("Error Message Is Verified : " +errorMessage);

		// Clicking on Complete from Status drop-down and hitting Search
		new Select(webDriver.findElement(By.xpath("//select[@id='sales_order_grid_filter_status']"))).selectByVisibleText("Complete");
		webDriver.findElement(By.xpath("//span[contains(text(),'Search')]")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Generated Search For Complete");
		Thread.sleep(2000);

		// Selecting First one form list and selecting Print Invoices from Action
		webDriver.findElement(By.xpath("//input[@value='5']")).click();
		new Select(webDriver.findElement(By.xpath("//select[@id='sales_order_grid_massaction-select']"))).selectByVisibleText("Print Invoices");		
		webDriver.findElement(By.xpath("//span[contains(text(),'Submit')]")).click();
		//String inputURL = webDriver.getPageSource();
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Downloads file into file system
		//URL ipURL = new URL(inputURL);
		File opFile = new File(System.getProperty("user.home") +"\\savedFile"+".pdf");
		//FileUtils.copyURLToFile(ipURL, opFile);
		//String filePath = System.getProperty("user.home") +"\\savedFile"+".pdf";
		System.out.println("File Saved : " +opFile);
		
		// Verifying Download was successful
		PDDocument document = PDDocument.load(new File("C:\\Users\\BappY\\Downloads\\savedFile.pdf"));
		if(!document.isEncrypted()) {
			PDFTextStripper stripper = new PDFTextStripper();
			String text = stripper.getText(document);
			System.out.println("Here is PDF Text : \n" +text);
		}
		
		System.out.println("Test COmpleted");
		webDriver.quit();


	}

}
