package LiveTestProjectDay09;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CouponDiscountLiveTest {
	
	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	
	public static void main(String[] args) {
		
		try {
			CommencingCouponDiscountTest();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getCause());
		}
		
	}
	
	
	public static void CommencingCouponDiscountTest() throws InterruptedException, IOException {
		
		// Making webDriver ready for task
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		
		// Navigating to Test Site landing page.
		webDriver.get(baseUrl);
		System.out.println("Navigation Successfull");
		
		// Clicking on MOBILE menu and adding iPhone to cart.
		webDriver.findElement(By.linkText("MOBILE")).click();
		webDriver.findElement(By.xpath("//a[@title='IPhone'][contains(text(),'IPhone')]")).click();
		System.out.println("IPHONE Slected");
		webDriver.findElement(By.xpath("//span[contains(text(),'Add to Cart')]")).click();
		System.out.println("IPHONE Added To Cart");
		
		// noting grand total before discounted price gets successful. 
		// Discounted price does not get any effect on grand total now...
		String beforeDiscountGrandTotal = webDriver.findElement(By.xpath("//tfoot//td[2]")).getText();
		System.out.println("Before Discount Code Applied : " +beforeDiscountGrandTotal);
		
		// Entering Coupon Code "GURU50" to Discount codes and then apply
		webDriver.findElement(By.xpath("//input[@id='coupon_code']")).clear();
		webDriver.findElement(By.xpath("//input[@id='coupon_code']")).sendKeys("GURU50");
		webDriver.findElement(By.xpath("//span[contains(text(),'Apply')]")).click();
		System.out.println("Discount Code Is Applied");
		
		// Verifying that Coupon got applied in shopping cart. Checking Grand total section for confirmation.
		String couponMessage = webDriver.findElement(By.xpath("//li[@class='success-msg']//ul//li")).getText();
		assertEquals("Coupon code \"GURU50\" was applied.", couponMessage);
		System.out.println("Coupon Code Apllied Successfully. \n" + "Here is verfied message from this website: " +couponMessage);
		
		// Now checking in Grand total section for confirmation of discount got applied.
		String dicountCharge = webDriver.findElement(By.xpath("//tr[2]//td[2]")).getText();
		assertEquals("-$25.00", dicountCharge);
		System.out.println("Discount Code Takes Effect In Checkout Section: " +dicountCharge);
		
		webDriver.manage().window().fullscreen();
		Thread.sleep(2000);
		
		// Now taking a snapshot
		TakesScreenshot discountedScreenshot = ((TakesScreenshot)webDriver);
		File discountCharged = discountedScreenshot.getScreenshotAs(OutputType.FILE);
		String couopnScrFile = ("E:\\eclipse\\LiveTestCodeSamples\\DiscountedCouponScreenShot" +".png");
		//FileUtils.copyFileToDirectory(discountCharged, new File(couopnScrFile)); This would create a folder and then copy that file
		FileUtils.copyFile(discountCharged, new File(couopnScrFile));
		System.out.println("Our Captured Scrteenshot Now Been Save to : " +couopnScrFile);
		
		System.out.println("Test Completed...");
		webDriver.quit();
		
	}

}
