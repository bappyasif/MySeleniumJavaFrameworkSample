package LiveTestProjeectDay08;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MySolutionTestNGTestCase08 {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	static String loginEmail = "asifuzzamanbappy+04@gmail.com";
	static String loginPassword = "testtest";

	@BeforeTest
	public void launchingBrowser() {
		// Setting up environment here.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	public void CommencingTest08Day08() throws IOException, InterruptedException {
		// Navigating to Test site Landing Page to begin test with.
		webDriver.get(baseUrl);
		System.out.println("Landing Successfull");

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

		// Clicking on Reorder button and changing quantity then updating order.
		//webDriver.findElement(By.linkText("Reorder")).click();
		webDriver.findElement(By.xpath("//a[@class='link-reorder']")).click();
		Thread.sleep(2000);


		// Getting Grand Total Price before ReOrder takes place and quantity gets updated.
		String vPrice = webDriver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")).getText();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}


		try {
			webDriver.findElement(By.xpath("//input[@title='Qty']")).clear();
			webDriver.findElement(By.xpath("//input[@title='Qty']")).sendKeys("3");
			webDriver.findElement(By.xpath("//button[@title='Update']")).click();
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Unable to find elements");
			ex.getStackTrace();
		}

		// Verifying Grand-total is changed
		try {
			String grandTotal = webDriver.findElement(By.xpath("//tfoot//td[2]")).getText();
			assertEquals("GRAND TOTAL", webDriver.findElement(By.xpath("//div[@class='cart-totals-wrapper']//tfoot//td[1]")).getText());
			assertEquals(grandTotal, webDriver.findElement(By.xpath("//tfoot//td[2]")).getText());
			String quantityNumber = webDriver.findElement(By.xpath("//span[@class='count']")).getText();
			System.out.println("*** QTY  Set ***");
			System.out.println("*** Cart Updated ***");

			if(vPrice == grandTotal) {
				System.out.println("*** Grand Total price has not changed. ***");
			} else {
				System.out.println("Grand Total has Changed: " +grandTotal + " For : " +quantityNumber  +" Products");
			}

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Could Not Find Elememts");
			ex.getStackTrace();
		}

		// Proceeding with Checkout process.
		webDriver.findElement(By.xpath("//ul[@class='checkout-types top']//button[@title='Proceed to Checkout']")).click();
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Check if Select element is present. If not present, it is the first time a customer has logged back in, 
		// to process what is in his/her checkout cart.
		try {
			Select billingAddress = new Select(webDriver.findElement(By.name("billing_address_id")));
			int baSize = billingAddress.getOptions().size();
			billingAddress.selectByIndex(baSize-1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No dropdown element present");
		}

		// Here premise is we are using previous created credentials to Re Order!! 
		// Thats why we are not using Billing Address and Shipping Address information re entered!!! 

		// once found Clicking on radio button which says ship to this address.
		webDriver.findElement(By.xpath("//label[@for='billing:use_for_shipping_yes']")).click();
		System.out.println("Shipping Address Is Sleceted " +webDriver.findElement(By.xpath("//label[@for='billing:use_for_shipping_yes']")).isEnabled());
		boolean shippingCheckTry01 = webDriver.findElement(By.xpath("//label[@for='billing:use_for_shipping_yes']")).isEnabled();
		// Clicking on Billing Information Continue button
		webDriver.findElement(By.xpath("//div[@id='billing-buttons-container']//button[@title='Continue']")).click();
		Thread.sleep(2000);

		// Checking whether Using billing address is selected as shipping Address. Then it will skip over to the next
		// segment of checkout process.
		boolean shippingCheckTry02 = webDriver.findElement(By.xpath("//label[@for='shipping:same_as_billing']")).isEnabled();

		// Check if Select element is present. If not present, it is the first time a customer has logged back in, 
		// to process what is in his/her Checkout cart..
		try {
			Select shippingAddress = new Select(webDriver.findElement(By.name("billing_address_id")));
			int baSize = shippingAddress.getOptions().size();
			shippingAddress.selectByIndex(baSize-1);
			System.out.println("Using Same Billing Address "  +shippingCheckTry01);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No dropdown element present");
			//System.out.println("Using Same Billing Address" +shippingCheck);
		}

		// CLicking on Shipping segment Continue button to proceed on to the next.
		if(shippingCheckTry02 != true) {
			webDriver.findElement(By.xpath("//label[@for='shipping:same_as_billing']")).click();

		}

		// Clicking Shipping Method Continue Button to proceed to payment segment.
		webDriver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click();
		Thread.sleep(2000);

		// Clicking on Check/Money Order option from Payment selection radio buttons
		webDriver.findElement(By.xpath("//label[@for='p_method_checkmo']")).click();
		System.out.println("Payment Meethod Is Slected Money Order/Check " +webDriver.findElement(By.xpath("//label[@for='p_method_checkmo']")).isEnabled());

		// Clicking on Payment Information Continue Button to proceed to Order Review.
		webDriver.findElement(By.xpath("//div[@id='payment-buttons-container']//button[@type='button']")).click();
		Thread.sleep(2000);

		// Clicking on Place Order to complete user Order request placement.
		webDriver.findElement(By.xpath("//button[@title='Place Order']")).click();
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Verifying Order is generated by noting down Order Number and then perhaps a snapshot!!
		try {
			assertEquals("YOUR ORDER HAS BEEN RECEIVED.", webDriver.findElement(By.xpath("//h1[contains(text(),'Your order has been received.')]")).getText());
			assertEquals("THANK YOU FOR YOUR PURCHASE!", webDriver.findElement(By.xpath("//h2[@class='sub-title']")).getText());
			System.out.println("Order Confirmation:  " +webDriver.findElement(By.xpath("//h1[contains(text(),'Your order has been received.')]")).getText());
			//System.out.println(webDriver.findElement(By.xpath("//h2[@class='sub-title']")).getText());

			// Noting Order number form the Success Checkout page.
			String orderNumber = webDriver.findElement(By.xpath("//div[@class='main-container col1-layout']//p[1]")).getText();
			System.out.println("Order Message : " +orderNumber);

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Your Order Details Page Not Found!!");
			ex.getStackTrace();
		}

		// If all goes well then Taking a snapshot confirming order placement.
		TakesScreenshot ordrplcmntSnapshot = ((TakesScreenshot)webDriver);
		File reorderSceenshot = ordrplcmntSnapshot.getScreenshotAs(OutputType.FILE);
		String rdrpScrFile = ("E:\\eclipse\\LiveTestCodeSamples\\Re-OrderScreenShot" +".png");
		FileUtils.copyFile(reorderSceenshot, new File(rdrpScrFile));
		System.out.println("Our Captured Scrteenshot Now Been Save to : " +rdrpScrFile + ".png");

	}

	@AfterTest
	public void terminatingBrowser() {
		System.out.println("Test Completed...");
		webDriver.quit();
	}

}
