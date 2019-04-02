package LiveTestProjectDay06;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestPurchaseProducts {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	static String loginEmail = "asifuzzamanbappy+03@gmail.com";
	static String loginPassword = "testtest";
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		CommencingTestProductsPurchase();
		
	}

	public static void CommencingTestProductsPurchase() throws InterruptedException, IOException {

		// preparing webDriver for this task.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		// Navigating to Live Test Site
		webDriver.get(baseUrl);
		System.out.println("Landed on LiveTest Page");

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

		// Clicking on My Wishlist
		webDriver.findElement(By.linkText("MY WISHLIST")).click();

		// Clicking on Add to cart button from that page.
		webDriver.findElement(By.xpath("//button[@title='Add to Cart']")).click();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		webDriver.findElement(By.xpath("//select[@id='country']")).sendKeys("Bangladesh");
		webDriver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("1207");
		webDriver.findElement(By.xpath("//span[contains(text(),'Estimate')]")).click();
		webDriver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).click();
		webDriver.findElement(By.xpath("//span[contains(text(),'Update Total')]")).click();
		
		webDriver.findElement(By.xpath("//td[contains(text(),'Shipping & Handling (Flat Rate - Fixed)')]")).isSelected();

		// Clicking on Proceed To Checkout
		webDriver.findElement(By.xpath("//ul[@class='checkout-types top']//button[@title='Proceed to Checkout']")).click();
		Thread.sleep(2000);
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		// Entering Shipping Information details
		webDriver.findElement(By.xpath("//input[@id='billing:street1']")).clear();
		webDriver.findElement(By.xpath("//input[@id='billing:street1']")).sendKeys("House#Shipping11, Road#02, Shyamoli, Adabar");
		
		webDriver.findElement(By.xpath("//input[@id='billing:city']")).clear();
		webDriver.findElement(By.xpath("//input[@id='billing:city']")).sendKeys("Dhaka");
		
		//webDriver.findElement(By.cssSelector("#billing:region_id")).sendKeys("Virgin Islands");
		
		webDriver.findElement(By.xpath("//input[@id='billing:postcode']")).clear();
		webDriver.findElement(By.xpath("//input[@id='billing:postcode']")).sendKeys("11015");
		
		//webDriver.findElement(By.xpath("//select[@id='billing:region_id']")).sendKeys(null);
		webDriver.findElement(By.xpath("//select[@id='billing:region_id']")).sendKeys("Bangladesh");
		
		webDriver.findElement(By.xpath("//input[@id='billing:telephone']")).clear();
		webDriver.findElement(By.xpath("//input[@id='billing:telephone']")).sendKeys("+8801915645093");
		
		webDriver.findElement(By.xpath("//label[@for='billing:use_for_shipping_yes']")).click();
		
		webDriver.findElement(By.xpath("//div[@id='billing-buttons-container']//button[@title='Continue']")).click();
		//webDriver.manage().window().maximize();
		Thread.sleep(4000);
		
		//webDriver.findElement(By.xpath("//div[@id='shipping-method-buttons-container']//button[@type='button']//span//span[contains(text(),'Continue')]")).click();
		//webDriver.findElement(By.xpath("//label[@for='billing:use_for_shipping_yes']")).click();
		// Something is not right here!! it keep saying its not finding element!! Not intractable.. Check tomorrow.
		webDriver.findElement(By.xpath("//label[@for='p_method_checkmo']")).click();
		
		webDriver.findElement(By.xpath("//div[@id='payment-buttons-container']//button[@type='button']")).click();
		
		//webDriver.findElement(By.xpath("//button[@title='Place Order']")).click();
		
		// Taking a snapshot of this page as verification step.
		TakesScreenshot checkoutScreenshot = ((TakesScreenshot)webDriver);
		File screenshotFile = checkoutScreenshot.getScreenshotAs(OutputType.FILE);
		String chkscrFile = ("E:\\eclipse\\LiveTestCodeSamples\\CheckoutScreenShot" +".png");
		FileUtils.copyFile(screenshotFile, new File(chkscrFile));
		System.out.println("YOur Captured Scrteenshot Now Been Save to : " +chkscrFile + ".png");
		
		webDriver.findElement(By.xpath("//button[@title='Place Order']")).click();
		Thread.sleep(2000);
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		// Taking another snapshot for this page as well
		TakesScreenshot scsschkoutScreenshot = ((TakesScreenshot)webDriver);
		File anotherScreenshot = scsschkoutScreenshot.getScreenshotAs(OutputType.FILE);
		String succchkScrFile = ("E:\\eclipse\\LiveTestCodeSamples\\SuccessCheckoutScreenShot" +".png");
		FileUtils.copyFile(anotherScreenshot, new File(succchkScrFile));
		System.out.println("YOur Captured Scrteenshot Now Been Save to : " +succchkScrFile + ".png");
		
		// Noting Order number form the Success Checkout page.
		String orderNumber = webDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/p[1]/a[1]")).getText();
		System.out.println("Order number : " +orderNumber);
		
		Thread.sleep(2000);
		webDriver.quit();
	}

}
