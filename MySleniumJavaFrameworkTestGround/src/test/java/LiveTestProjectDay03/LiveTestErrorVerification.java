package LiveTestProjectDay03;

import static org.testng.Assert.assertEquals;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.model.ScreenCapture;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestErrorVerification {
	
	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	static String errorMessage;
	static String cartMessage;
	static String ecPngFile;

	public static void main(String[] args) {
		
		CommencingErrorVerificationTest();
		
	}
	
	public static void CommencingErrorVerificationTest() {
		
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		
		// navigating to live Test Site.
		webDriver.get(baseUrl);
		System.out.println("Navigated to live Test Site");
		
		// Clicking on the Mobile Menu Page from the landing site.
		webDriver.findElement(By.linkText("MOBILE")).click();
		System.out.println("Now inside Mobile Menu Page");
		
		// Clicking on Sony Xperia mobile's Add To Cart Button
		//webDriver.findElement(By.className("button btn-cart")).click();
		webDriver.findElement(By.xpath("//li[1]//div[1]//div[3]//button[1]")).click();
		
		// Changing value to QTY to 1000 and clicking on Update button.
		try {	
			
			// Clearing input space from previous quantity number.
			webDriver.findElement(By.xpath(".//*[@id='shopping-cart-table']/tbody/tr/td[4]/input")).clear();
			//webDriver.findElement(By.className("input-text qty")).sendKeys("1000");
			webDriver.findElement(By.xpath("//input[@title='Qty']")).sendKeys("1000");
			//webDriver.findElement(By.className("button btn-update")).click();
			webDriver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();
			
			// Verifying error message on screen at the site. 
			//errorMessage = webDriver.findElement(By.className("item-msg error")).getText();
			errorMessage = webDriver.findElement(By.xpath("//p[@class='item-msg error']")).getText();
			System.out.println("Message thrown from the Site : " +errorMessage);
			
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("There was an error: 'No Such Element Found");
			ex.getStackTrace();
		}
		
		// Clicking on Empty Cart button. A message "SHOPPING CART IS EMPTY" is shown on the screen. 
		//webDriver.findElement(By.className("button2 btn-empty")).click();
		// webDriver.findElement(By.xpath(".//*[@id='empty_cart_button']")).click();
		webDriver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]")).click();
		System.out.println("Cart Is Now Empty");
		
		// Verifying cart is empty 
		try {
			
			// Verifying by taking a Screenshot
			TakesScreenshot emptycartScreenshot = ((TakesScreenshot)webDriver);
			File screenshotFile = emptycartScreenshot.getScreenshotAs(OutputType.FILE);
			ecPngFile = ("E:\\eclipse\\LiveTestCodeSamples\\EmptyCartScreenShot" +".png");
			FileUtils.copyFile(screenshotFile, new File(ecPngFile));
			System.out.println("File's been moved to :" +ecPngFile);
			
			// verifying by checking in empty Cart message from the Empty Cart button. A message will be shown on screen
			// later on console as well confirming that notion.
			String noItemsStg = ("You have no items in your shopping cart.");
		    String noItemsMsg = webDriver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/div[2]/p[1]")).getText();
		    System.out.println("You have no items msg = " + noItemsMsg);
		    
		    try {	    	
	    	assertEquals(noItemsStg, noItemsMsg);
		    } catch (Exception e) {
		    	e.printStackTrace();	    	
		    }	
			
			//cartMessage = webDriver.findElement(By.cssSelector("")).findElement(By.className("cart-empty")).getText();
			//cartMessage = webDriver.findElement(By.xpath("//span[contains(text(),'Cart')]")).getText();
			//System.out.println("Cart Message On Screen: " +cartMessage);
			
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("There was an error: 'No Such Element Found");
			ex.getStackTrace();
		}
				
		System.out.println("Test Completed...");
		
		webDriver.quit();
	}
	
}
