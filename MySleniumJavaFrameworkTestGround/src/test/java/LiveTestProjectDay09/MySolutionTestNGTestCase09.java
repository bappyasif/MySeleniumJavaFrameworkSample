package LiveTestProjectDay09;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MySolutionTestNGTestCase09 {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";

	@BeforeTest
	public void launchingBrowser() {
		// Setting up environment here.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	public void CommencingTest09Day09() throws IOException, InterruptedException {

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
		System.out.println("Before Discount Code Applied : " +beforeDiscountGrandTotal.trim());

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
		System.out.println("Discount Code Takes Effect In Checkout Section: " +dicountCharge.trim());


		//  Now all trimmed valued figure. And also what should site actually reflect in its calculation nevertheless.
		System.out.println("Here Are Some New ways to extract data and manipulate when required..");
		// this is the Generated discount amount displayed (e.g. $475.00)
		String vDiscountedAmtDisplayed = webDriver.findElement(By.xpath(".//*[@id='shopping-cart-totals-table']/tfoot/tr/td[2]/strong/span")).getText().trim();
		// It would have showed 475 iff system had it implemented it. NOw this will show total of 500.
		// System.out.println(vDiscountedAmtDisplayed); // thats why omitting this. 
		String trimmedGrandTotalValue = beforeDiscountGrandTotal.replaceAll("\\$", "").trim();
		String trimmedDiscountAmountValue = dicountCharge.replaceAll("\\$", "").trim();
		String trimmedExpectedGrandTotal = vDiscountedAmtDisplayed.replaceAll("\\$", "");

		// Remove the - negative sign from discounted amount.
		String removedNegativeDiscountedAmount = trimmedDiscountAmountValue.replaceAll("\\-", "");

		// Parsing them into double data structured value.
		double bdgTotalValue = Double.parseDouble(trimmedGrandTotalValue);
		double discountedAmount = Double.parseDouble(removedNegativeDiscountedAmount);
		double discountedTotal = Double.parseDouble(trimmedExpectedGrandTotal);

		// multiply the dSubTot by the GURU50 discount rate (GURU50 = 5% = 0.05) 
		double discountedAmt = (bdgTotalValue * .05);     // discountedAmt is the calculated discounted amount (e.g. $25.00)	
		double dDiscPrice = (bdgTotalValue - discountedAmt); // e.g. Discounted Price (e.g. $475) = Sub Total ($500.00) less the 5% discount amount ($25.00)
		System.out.println("Discounted Amount :" +discountedAmount);
		System.out.println("Discounted Price : " +dDiscPrice);

		webDriver.manage().window().fullscreen();
		Thread.sleep(2000);

		// Now taking a snapshot
		TakesScreenshot discountedScreenshot = ((TakesScreenshot)webDriver);
		File discountCharged = discountedScreenshot.getScreenshotAs(OutputType.FILE);
		String couopnScrFile = ("E:\\eclipse\\LiveTestCodeSamples\\DiscountedCouponScreenShot" +".png");
		//FileUtils.copyFileToDirectory(discountCharged, new File(couopnScrFile)); This would create a folder and then copy that file
		FileUtils.copyFile(discountCharged, new File(couopnScrFile));
		System.out.println("Our Captured Scrteenshot Now Been Save to : " +couopnScrFile);
	}

	@AfterTest
	public void terminatingBrowser() {
		System.out.println("Test Completed...");
		webDriver.quit();
	}

}
