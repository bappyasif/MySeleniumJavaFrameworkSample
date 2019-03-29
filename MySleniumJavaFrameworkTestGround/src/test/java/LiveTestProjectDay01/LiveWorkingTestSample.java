package LiveTestProjectDay01;

import java.util.concurrent.TimeUnit;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveWorkingTestSample {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	static String titleString; 
	static String menuString;
	static int scCounter;
	static String pngFile;
	
	public static void main(String[] args) throws IOException {
		
		CommencingTest();
	}
	
	public static void CommencingTest() throws IOException {
	
		WebDriverManager.chromedriver().setup();

		webDriver = new ChromeDriver();

		webDriver.get(baseUrl);

		// both of these below statements Verify Title of the page
		//titleString = webDriver.getTitle();
		titleString = webDriver.findElement(By.cssSelector("h2")).getText();
		System.out.println("Title of this page : " +titleString);

		webDriver.findElement(By.linkText("MOBILE")).click();
		// Below statement Verify Title of the page
		menuString = webDriver.getTitle();
		System.out.println("Title of This page :" +menuString);
		
		//new Select(webDriver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Price");
		//webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//new Select(webDriver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).deselectByVisibleText("Price");
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		new Select(webDriver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByIndex(1);
		webDriver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		
		// Verify all products are sorted by name
		// this will take a screen shot of the manager's page after a successful login
		scCounter =scCounter+1;
		File scrshotFile = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
		pngFile = ("E:\\eclipse\\LiveTestCodeSamples\\ProductsSorted" +scCounter + ".png");
		FileUtils.copyFile(scrshotFile, new File(pngFile));
		System.out.println("File's ben saved into :  " +pngFile);
		
		System.out.println("Test Comnpleted...");
		
		webDriver.quit();
	}
}
