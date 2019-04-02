package LiveTestProjectDay05;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnAccountCreation {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	static String firstName = "Asifuzzaman";
	static String middleName = "Sarkar";
	static String lastName = "Bappy";

	public static void main(String[] args) throws InterruptedException, IOException {

		CommencingAccountCreationTest();

	}

	public static void CommencingAccountCreationTest() throws InterruptedException, IOException {

		// Preparing webDriver for this task.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		// Navigating to Test page
		webDriver.get(baseUrl);
		System.out.println("Navigation Complete");

		// Clicking on My Account from Account Drop-down Menu.
		// driver.findElement(By.linkText("MY ACCOUNT")).click(); // in that case you don't need that implicit call.
		webDriver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")).click();
		webDriver.findElement(By.linkText("My Account")).click();

		// Noted : This Account creation script can only be run unless you provide a new email address each time.
		// Clicking on Create Account and filling up the account creation form
		// driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
		webDriver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		webDriver.findElement(By.id("firstname")).clear();
		webDriver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		webDriver.findElement(By.id("middlename")).clear();
		webDriver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(middleName);
		webDriver.findElement(By.id("lastname")).clear();
		webDriver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		webDriver.findElement(By.id("email_address")).clear();
		webDriver.findElement(By.cssSelector("#email_address")).sendKeys("asifuzzamanbappy+03@gmail.com");
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.cssSelector("#password")).sendKeys("testtest");
		webDriver.findElement(By.id("confirmation")).clear();
		webDriver.findElement(By.cssSelector("#confirmation")).sendKeys("testtest");
		webDriver.findElement(By.cssSelector("#is_subscribed")).isSelected();
		
		webDriver.findElement(By.xpath("//button[@title='Register']")).click();

		Thread.sleep(2000);
		
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Verify Process : Taking a screenshot of dash-board to verify registration is complete.
		TakesScreenshot dashboardScreenshot = ((TakesScreenshot)webDriver);
		File screenshotFile = dashboardScreenshot.getScreenshotAs(OutputType.FILE);
		String dscrFile = ("E:\\eclipse\\LiveTestCodeSamples\\DashoboardScreenShotNew" +".png");
		FileUtils.copyFile(screenshotFile, new File(dscrFile));
		System.out.println("YOur Captured Scrteenshot Now Been Save to : " +dscrFile + ".png");

		// Verify Process: Verify Registration is done. Expected account registration done.
	    String vWelcome = ("WELCOME, " + firstName + " " + lastName + "!");
	    String tWelcome = webDriver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[1]/div/p")).getText();
	    //System.out.println("tWelcome = "+tWelcome);
		
		Thread.sleep(1000);

		// Clicking on TV Menu
		webDriver.findElement(By.linkText("TV")).click();
		System.out.println("INside TV Menu Page");

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Adding Item to wish list by clicking on Add to WishlIst
		webDriver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[1]//a[1]")).click();
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Clicking on Share your wish list button and then filling up form to share.
		webDriver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();
		webDriver.findElement(By.id("email_address")).clear();
		webDriver.findElement(By.xpath("//textarea[@id='email_address']")).sendKeys("asifuzzamanbappy@gmail.com");
		webDriver.findElement(By.id("message")).clear();
		webDriver.findElement(By.xpath("//textarea[@id='message']")).sendKeys("Message for the wishlist");
		webDriver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();

		Thread.sleep(2000);

		// Checking up on shared wishlist success message
		String successMessage = webDriver.findElement(By.xpath("//li[@class='success-msg']//ul//li")).getText();
		System.out.println("Success Message : " +successMessage);

		System.out.println("Test Completed...");
		webDriver.quit();
	}

}
