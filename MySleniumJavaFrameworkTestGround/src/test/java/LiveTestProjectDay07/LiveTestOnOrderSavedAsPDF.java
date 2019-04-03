package LiveTestProjectDay07;

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

	}
}
