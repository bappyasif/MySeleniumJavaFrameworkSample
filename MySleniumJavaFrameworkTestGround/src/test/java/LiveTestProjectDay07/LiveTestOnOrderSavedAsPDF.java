package LiveTestProjectDay07;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnOrderSavedAsPDF {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	static String loginEmail = "asifuzzamanbappy+02@gmail.com";
	static String loginPassword = "testtest";
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		commencingTestOnOrderSavedAsPDF();
		
	}
	

	public static void commencingTestOnOrderSavedAsPDF() throws InterruptedException, IOException {

		// preparing webDriver for this task
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		// Going to live Test page
		webDriver.get(baseUrl);
		System.out.println("Successful Landing");

		// Clicking on My Account
		//webDriver.findElement(By.linkText("My Account")).click();
		webDriver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")).click();
		webDriver.findElement(By.linkText("My Account")).click();
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Login in with previously created credentials from earlier.

		webDriver.findElement(By.cssSelector("#email")).clear();
		webDriver.findElement(By.cssSelector("#email")).sendKeys(loginEmail);
		webDriver.findElement(By.cssSelector("#pass")).clear();
		webDriver.findElement(By.cssSelector("#pass")).sendKeys(loginPassword);

		webDriver.findElement(By.cssSelector("#send2")).click();
		Thread.sleep(1000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Clicking on View Order
		webDriver.findElement(By.xpath("//a[contains(text(),'View Order')]")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		
		// Verify the previously created order is displayed in 'RECENT ORDERS' table and status is Pending
	    // note: RECENT ORDERS page is displayed immediately after customer/user has logged in
		/**
		 * 
		 * This block of code would have worked if that test web-site 
		 * had those options available in those expected areas. 
		 * 
		try {
	        assertEquals("RECENT ORDERS", webDriver.findElement(By.cssSelector("h2")).getText());
	        System.out.println("*** RECENT ORDERS is confirmed as the correct page to be in. ***");
	      } catch (Error e) {
	    	  System.out.println("*** RECENT ORDERS failed to get displayed on the page. ***");
	    	  e.printStackTrace();	
	      }
	    
	    try {
	    	assertEquals("Pending", webDriver.findElement(By.cssSelector("em")).getText());
	    	System.out.println("*** Status of Pending is correctly displayed for this recent order. ***");
	      } catch (Error e) {
	    	  System.out.println("*** Status of Pending failed to be confirmed for this recent order. ***");
	    	  e.printStackTrace();	
	      }		 
		 
		 */
		
		String orpgMessage = webDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/h1[1]")).getText();
		System.out.println("Order Message Current Situation : " +orpgMessage);

		// Clicking on Print Order
		webDriver.findElement(By.linkText("Print Order")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		Thread.sleep(2000);
		
		webDriver.manage().window().fullscreen();
		
		// Taking a snapshot as verification. We will do PDF saving thing tomorrow if not today.
		TakesScreenshot orderScreenshot = ((TakesScreenshot)webDriver);
		File pageScreenshot = orderScreenshot.getScreenshotAs(OutputType.FILE);
		String prorScrFile = ("E:\\eclipse\\LiveTestCodeSamples\\PrintOrderScreenShot" +".png"); 
		FileUtils.copyFile(pageScreenshot, new File(prorScrFile));
		System.out.println("YOur Captured Scrteenshot Now Been Save to : " +prorScrFile + ".png");
		
		System.out.println("Test Completed...");
		webDriver.quit();
		
	    // Step 8. Click 'Change...' link and a popup will be opened as 'Select a destination' , select 'Save as PDF' link.  
	    // note:  There is no "Change...." link 


	    // Step 9. Click on 'Save' button and save the file in some location.
	    // note: unable to find this Save button
  
	    // Step 10.Verify Order is saved as PDF
	    // unable to perform any verification because there is no option to save as PDF
		
	}
}
