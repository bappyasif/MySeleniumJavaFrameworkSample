package LiveTestProjectDay12;

import static org.testng.Assert.assertEquals;

import java.awt.Window;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnProductReview {

	static WebDriver webDriver;

	static String productReviewSubmitUrl = "http://live.guru99.com/index.php/review/product/list/id/1";
	static String baseUrlFrontEnd = "http://live.guru99.com/"; 
	static String baseUrlBackEnd = "http://live.guru99.com/index.php/backendlogin/";

	static String userID = "user01";
	static String userPass = "guru99com";


	public static void main(String[] args) {

		try {
			CommencingTestOnProductReview();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public static void CommencingTestOnProductReview() throws InterruptedException, IOException {

		// getting environment ready for task.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		//  Navigating to Test site
		webDriver.get(baseUrlFrontEnd);
		System.out.println("Navigation Successful");

		// going to product review link and leave a review and submit it there.
		webDriver.get(productReviewSubmitUrl);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		Thread.sleep(2000);

		System.out.println("Landed on Product Review Submission Page for Sony Xperia");

		webDriver.findElement(By.xpath("//textarea[@id='review_field']")).sendKeys("Not The Worst One in Market For Sure.");
		webDriver.findElement(By.xpath("//input[@id='summary_field']")).sendKeys("You can definately give it a try");
		webDriver.findElement(By.xpath("//input[@id='nickname_field']")).sendKeys("Bappy");
		webDriver.findElement(By.xpath("//button[@title='Submit Review']")).click();

		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		System.out.println("Review Submitted");

		// Going to login page for back-end and login there
		// login in with provided credentials.
		webDriver.get(baseUrlBackEnd);
		System.out.println("Login to Back-end Successful");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

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

		// Going to catalogs --> Review & Ratings --> Customer Reviews --> Pending Reviews Menu.
		// Then sorting by ID and selecting comment and clicking on Edit.
		webDriver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
		webDriver.findElement(By.linkText("Reviews and Ratings")).click();
		webDriver.findElement(By.linkText("Customer Reviews")).click();
		webDriver.findElement(By.linkText("Pending Reviews")).click();
		System.out.println("In Pending Reviews Mennu Page");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		webDriver.findElement(By.xpath("//span[contains(text(),'ID')]")).click();
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("//span[contains(text(),'ID')]")).click();
		String userID = webDriver.findElement(By.xpath("//input[@value='1444']")).getText();
		webDriver.findElement(By.xpath("//input[@name='review_id']")).sendKeys(userID);
		webDriver.findElement(By.xpath("//a[contains(text(),'Edit')]")).click();
		//webDriver.findElement(By.xpath("//a[@href='http://live.guru99.com/index.php/backendlogin/catalog_product_review/"
		//		+ "edit/ret/pending/id/1444/key/2107e7b6d7999e7c5330dc6cefb93717/']")).click();
		System.out.println("Inside Procduct Review Edit Page");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Changing status to Approved and then clicking on Save review
		new Select(webDriver.findElement(By.xpath("//select[@name='status_id']"))).selectByVisibleText("Approved");
		webDriver.findElement(By.xpath("//div[@id='page:main-container']//button[@id='save_button']")).click();
		System.out.println("Review Saved");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// verifying that review was saved
		String expectedStatement = "The review has been saved.";
		String actualStatement = webDriver.findElement(By.xpath("//span[contains(text(),'The review has been saved.')]")).getText();
		assertEquals(actualStatement, expectedStatement);
		System.out.println("Review Saved Vefication Asserted");

		// Login out from back end to check review presence on product List.
		webDriver.findElement(By.xpath("//a[@class='link-logout']")).click();
		System.out.println("Logged Out From Backend");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Now loading test site landing page again and navigating to products review page
		webDriver.get(baseUrlFrontEnd);
		
		webDriver.findElement(By.linkText("MOBILE")).click();
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		webDriver.findElement(By.xpath("//a[@title='Sony Xperia']")).click();
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		webDriver.findElement(By.xpath("//li[@class='last']//span[contains(text(),'Reviews')]")).click();
		String reviewedMessage = webDriver.findElement(By.xpath("//dd[@class='tab-container last current']//dt[1]")).getText();
		System.out.println("Bappy's Review Of Sony Xperia : " +reviewedMessage);

		// New Code fir scrolling
		WebElement scrolUntil = webDriver.findElement(By.xpath("//dd[@class='tab-container last current']//dd[1]"));
		int windowSize = scrolUntil.getLocation().getY();
		JavascriptExecutor windowScroller = (JavascriptExecutor)webDriver;
		windowScroller.executeScript("window.scrollTo(0,"+windowSize+")");
		Thread.sleep(2000);
		
		
		// Just taking a snapshot of product reviews
		webDriver.manage().window().maximize();
		TakesScreenshot productReviewPageScreenshot = ((TakesScreenshot)webDriver);
		File reviewScreenshot = productReviewPageScreenshot.getScreenshotAs(OutputType.FILE);
		String prdctrevScrFile = ("E:\\eclipse\\LiveTestCodeSamples\\SuccessfulProductReviewScreenShot" +".png");
		FileUtils.copyFile(reviewScreenshot, new File(prdctrevScrFile));
		System.out.println("Your Screenshot Is Saved At this Location : " +prdctrevScrFile);

		System.out.println("Test Completed...");
		webDriver.quit();

	}

}
