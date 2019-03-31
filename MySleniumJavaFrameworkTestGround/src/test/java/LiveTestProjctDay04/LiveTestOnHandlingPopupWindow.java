package LiveTestProjctDay04;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.core.util.NanoClock;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnHandlingPopupWindow {
	
	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	static String mobileIphone;
	static String mobileSamsung;
	static String pwFile;
	
	public static void main(String[] args) {
		
		CommencingErrorHandlingPopUpWindowTest();
		
	}
	
	public static void CommencingErrorHandlingPopUpWindowTest() {
		
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		
		// Navigating to Live Test Site
		webDriver.get(baseUrl);
		System.out.println("Navigated to Live Test Site.");
		
		// Clicking on Mobile Menu
		webDriver.findElement(By.linkText("MOBILE")).click();
		System.out.println("Navigated to Mobile Menu Page.");
		
		// Clicking on Add to Compare button for Iphone and Samsung and the clicking on Compare button from page. 
		// First adding Iphone in to the compare list.
		webDriver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
		System.out.println("Iphone Is Added To The Compare List.");
		
		// Now adding Samsung to the compare list
		webDriver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
		System.out.println("Samsung Galaxy Is Added To The Compare List");
		
		// Attempting click on Compare Button from page
		try {
			
			webDriver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]")).click();
			//webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
			Thread.sleep(7000);
			//webDriver.manage().window().maximize();
			// Trying to take screenshot of poopup window!!
			//TakesScreenshot popupScreenshot = ((TakesScreenshot)webDriver.findElement(By.className("page-popup catalog-product-compare-index")));
			TakesScreenshot popupScreenshot = ((TakesScreenshot)webDriver);
			File sctreenshotFile = popupScreenshot.getScreenshotAs(OutputType.FILE);
			pwFile = ("E:\\eclipse\\LiveTestCodeSamples\\PopUpWindowScreenShot" +".png");
			FileUtils.copyFile(sctreenshotFile, new File(pwFile));
			System.out.println("YOur Captured Scrteenshot Now Been Save to : " +pwFile + ".png");
			
			// Verifying compare list elements
			//mobileIphone = webDriver.findElement(By.className("item odd")).getText();
			//mobileIphone = webDriver.findElement(By.className("page-popup catalog-product-compare-index")).findElement(By.xpath("//td[1]//h2[1]")).getText();
			//mobileSamsung =webDriver.findElement(By.className("item last even")).getText();
			//mobileSamsung = webDriver.findElement(By.className("page-popup catalog-product-compare-index")).findElement(By.linkText("Samsung Galaxy")).getText();
			//mobileIphone = webDriver.findElement(By.className("block block-list block-compare")).findElement(By.xpath("//li[@class='item odd']//p[@class='product-name']")).getText();
			//mobileSamsung = webDriver.findElement(By.className("block block-list block-compare")).findElement(By.xpath("//a[@href='http://live.guru99.com/index.php/samsung-galaxy.html']")).getText();
			mobileIphone = webDriver.findElement(By.xpath("//li[@class='item odd']//p[@class='product-name']")).getText();
			mobileSamsung = webDriver.findElement(By.xpath("//a[@href='http://live.guru99.com/index.php/samsung-galaxy.html']")).getText();
			// Displaying them on console.
			System.out.println("Two Compared Mobile Phones Are : " + "1." +mobileIphone + "  2." +mobileSamsung  );
			
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error Error : " + "No Such Element Been Found On Page!!");
		}
		
		System.out.println("Test Completed...");
		webDriver.quit();
	}

}
