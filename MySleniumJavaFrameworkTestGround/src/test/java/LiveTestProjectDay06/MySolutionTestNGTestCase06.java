package LiveTestProjectDay06;

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

public class MySolutionTestNGTestCase06 {

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
	public void CommencingTest06Day06() throws IOException, InterruptedException {
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
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Clicking on Add to cart button from that page.
		webDriver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Enter general shipping country, state/province and zip for the shipping cost estimate
		//webDriver.findElement(By.xpath("//select[@id='country']")).sendKeys("Bangladesh");
		new Select(webDriver.findElement(By.xpath("//select[@id='country']"))).selectByIndex(19);
		webDriver.findElement(By.xpath("//input[@id='postcode']")).sendKeys("1207");
		webDriver.findElement(By.id("region")).sendKeys("New South Wales");

		// Click Estimate
		webDriver.findElement(By.xpath("//span[contains(text(),'Estimate')]")).click();
		// Verify Shipping cost generated
		String sFlatRate = "Flat Rate";
		String flatRate = webDriver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dt")).getText();
		try {
			assertEquals(sFlatRate, flatRate);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

		String sFlatRatePrice = "Fixed - $5.00";
		String flatRatePrice = webDriver.findElement(By.xpath(".//*[@id='co-shipping-method-form']/dl/dd/ul/li/label")).getText();
		try {
			assertEquals(sFlatRatePrice, flatRatePrice);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

		// Select Shipping Cost (already selected as default), Update Total
		webDriver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).click();
		System.out.println("Flat Rate is : "+flatRatePrice);
		webDriver.findElement(By.xpath("//span[contains(text(),'Update Total')]")).click();

		// Verify shipping cost is added to total
		String vFlatRatePrice = "$5.00";
		String shippingCostIncluded = webDriver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tbody/tr[2]/td[2]/span")).getText();
		try {
			assertEquals(vFlatRatePrice, shippingCostIncluded);
			System.out.println("Veryfied Shipping Cost Included :" +shippingCostIncluded);
		} catch (Exception ex) {
			// TODO: handle exception
			ex.getStackTrace();
		}

		// doubly checking on radio button is also there and selected.
		webDriver.findElement(By.xpath("//td[contains(text(),'Shipping & Handling (Flat Rate - Fixed)')]")).isSelected();

		// Clicking on Proceed To Checkout
		webDriver.findElement(By.xpath("//ul[@class='checkout-types top']//button[@title='Proceed to Checkout']")).click();
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Check if Select element is present. If not present, it is the first time a customer has logged back in, 
		// to process what is in his/her wishlist.
		try {
			Select billingAddress = new Select(webDriver.findElement(By.name("billing_address_id")));
			int baSize = billingAddress.getOptions().size();
			billingAddress.selectByIndex(baSize-1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No dropdown element present");
		}

		// Entering Shipping Information details
		webDriver.findElement(By.id("billing:firstname")).clear();
		webDriver.findElement(By.id("billing:firstname")).sendKeys("Asifuzzaman"); 
		webDriver.findElement(By.id("billing:lastname")).clear();
		webDriver.findElement(By.id("billing:lastname")).sendKeys("Bappy"); 
		webDriver.findElement(By.id("billing:company")).clear(); 

		webDriver.findElement(By.xpath("//input[@id='billing:city']")).clear();
		webDriver.findElement(By.xpath("//input[@id='billing:city']")).sendKeys("Dhaka");

		//webDriver.findElement(By.cssSelector("#billing:region_id")).sendKeys("Virgin Islands");

		webDriver.findElement(By.xpath("//input[@id='billing:postcode']")).clear();
		webDriver.findElement(By.xpath("//input[@id='billing:postcode']")).sendKeys("11015");

		//webDriver.findElement(By.xpath("//select[@id='billing:region_id']")).sendKeys(null);
		//webDriver.findElement(By.xpath("//select[@id='billing:region_id']")).sendKeys("Bangladesh");
		new Select(webDriver.findElement(By.xpath("//select[@id='billing:country_id']"))).selectByIndex(19);
		Thread.sleep(2000);

		webDriver.findElement(By.xpath("//input[@id='billing:street1']")).clear();
		webDriver.findElement(By.xpath("//input[@id='billing:street1']")).sendKeys("House#Shipping11, Road#02, Shyamoli, Adabar");

		webDriver.findElement(By.id("billing:region")).clear();
		webDriver.findElement(By.id("billing:region")).sendKeys("Shyamoli");

		webDriver.findElement(By.xpath("//input[@id='billing:telephone']")).clear();
		webDriver.findElement(By.xpath("//input[@id='billing:telephone']")).sendKeys("+8801915645093");

		// check radio button to "Ship to different address" 
		webDriver.findElement(By.xpath("//label[@for='billing:use_for_shipping_yes']")).click();

		// click the CONTINUE button 
		webDriver.findElement(By.xpath("//div[@id='billing-buttons-container']//button[@title='Continue']")).click();
		//webDriver.manage().window().maximize();

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		Thread.sleep(2000);

		// Check if Select element is present or not.  If not present, it is first time user 
		// has logged back in to process his/her order
		try {
			Select billingAddress = new Select(webDriver.findElement(By.name("billing_address_id")));
			int baSize = billingAddress.getOptions().size();
			billingAddress.selectByIndex(baSize-1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("No dropdown element present");
		}
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		Thread.sleep(2000);
		// In Shipping Method, Click Continue 
		webDriver.findElement(By.xpath(".//*[@id='shipping-method-buttons-container']/button")).click();
		Thread.sleep(2000);

		// In Payment Information select 'Check/Money Order' radio button. Click Continue
		//webDriver.findElement(By.xpath("//label[@for='p_method_checkmo']")).click();
		webDriver.findElement(By.xpath("//input[@title='Check / Money order']")).click();
		Thread.sleep(2000);

		webDriver.findElement(By.xpath("//div[@id='payment-buttons-container']//button[@type='button']")).click();

		//webDriver.findElement(By.xpath("//button[@title='Place Order']")).click();
		webDriver.manage().window().maximize();
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

	}

	@AfterTest
	public void terminatingBrowser() {
		System.out.println("Test Completed...");
		webDriver.quit();
	}

}
