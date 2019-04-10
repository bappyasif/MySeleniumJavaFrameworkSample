package LiveTestProjctDay04;

import static org.testng.Assert.assertEquals;

import java.io.File;
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

public class MySolutionTestNGLiveTestCase04 {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	static String compareMobileFirstEntry;
	static String compareMobileSecondEntry;
	static String pwFile;

	@BeforeTest
	public void launchingBrowser() {
		// Setting up environment here.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	@Test
	public void CommencingTest04Day04() throws InterruptedException {

		// Navigating to Live Test Site
		webDriver.get(baseUrl);
		System.out.println("Navigated to Live Test Site.");

		// Clicking on Mobile Menu
		webDriver.findElement(By.linkText("MOBILE")).click();
		System.out.println("Navigated to Mobile Menu Page.");

		// Clicking on Add to Compare button for Iphone and Samsung and the clicking on Compare button from page. 
		// First adding Iphone in to the compare list.
		webDriver.findElement(By.xpath("//li[2]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
		compareMobileFirstEntry = webDriver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();  // text captured - upperCase "IPHONE"
		System.out.println( compareMobileFirstEntry + "Is Added To The Compare List.");

		// Now adding Samsung to the compare list
		webDriver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
		compareMobileSecondEntry = webDriver.findElement(By.xpath("//a[contains(text(),'Samsung Galaxy')]")).getText();  // text captured - upperCase "SAMSUNG GALAXY"
		System.out.println( compareMobileSecondEntry + "Is Added To The Compare List");

		Thread.sleep(1000);

		// Displaying them on console.
		System.out.println("Two Compared Mobile Phones Are : " + "1." +compareMobileFirstEntry + "  2." +compareMobileSecondEntry  );

		// Attempting click on Compare Button from page
		try {

			webDriver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]")).click();
			//webDriver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
			//Thread.sleep(7000);

			//Switching to Pop Up Window that just surfaced up after clicking Compare Button.
			for(String handlePopup : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(handlePopup);
				webDriver.manage().window().maximize();
				Thread.sleep(7000);
			}

			//webDriver.manage().window().maximize();
			// Trying to take screenshot of poopup window!! Now It does work!! thanks to Window Handler
			TakesScreenshot popupScreenshot = ((TakesScreenshot)webDriver);
			File sctreenshotFile = popupScreenshot.getScreenshotAs(OutputType.FILE);
			pwFile = ("E:\\eclipse\\LiveTestCodeSamples\\PopUpWindowScreenShotNew" +".png");
			FileUtils.copyFile(sctreenshotFile, new File(pwFile));
			System.out.println("YOur Captured Scrteenshot Now Been Save to : " +pwFile + ".png");

			// Verify the pop-up window and check that the products are reflected in it
			// Heading "COMPARE PRODUCTS" with selected products in it.
			String popupHeadings = ("COMPARE PRODUCTS");
			String compareHeadings = webDriver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div[1]/h1")).getText();
			assertEquals(popupHeadings, compareHeadings);
			System.out.println("Pop Up Window Heading Matches");   // Which means we are in the right popUp window.

			String popupMobileFirstEntry = webDriver.findElement(By.xpath("//h2/a[@title='IPhone']")).getText();  // text captured is "IPHONE" in uppercase
			String popupMobileSecondEntry = webDriver.findElement(By.linkText("Samsung Galaxy")).getText();  // text captured is "SAMSUNG GALAXY" in uppercase
			//String popupMobile2 = driver.findElement(By.xpath("//h2/a[@title='Sony Xperia']")).getText();  // text captured "SONY XPERIA" in uppercase
			System.out.println("First PopUp Mobile Entry" +popupMobileFirstEntry);
			System.out.println("Second PopUp Mobile Entry" +compareMobileSecondEntry);

			// Verifying compare list elements with pop Up window List Elements
			assertEquals(compareMobileFirstEntry, popupMobileFirstEntry);
			System.out.println("First Mobile Entry Matches In Both Windows");
			assertEquals(compareMobileSecondEntry, popupMobileSecondEntry);
			System.out.println("Second Mobile Entry Matches In Both Windows");
			// Closing the Popup Windows
			webDriver.findElement(By.xpath("//span[contains(text(),'Close Window')]")).click();

			// Switching to New Window
			for(String windowHandle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(windowHandle);
			}

			Thread.sleep(2000);

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Error Error : " + "No Such Element Been Found On Page!!");
			ex.getStackTrace();
		}
	}

	@AfterTest
	public void terminatingBrowser() {
		System.out.println("Test Completed...");
		webDriver.quit();
	}

}
