package LiveTestProjectDay14;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnSearchFunctionality {

	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/index.php";
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		CommencingTestOnSearchFunctionality();
		
	}
	

	public static void CommencingTestOnSearchFunctionality() throws InterruptedException, IOException {

		// Getting Platform Geared Up
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		// Landing on Tests site Home Index Page
		webDriver.get(baseUrl);
		System.out.println("Landing Successful");

		// Clicking on Advance Search
		webDriver.findElement(By.xpath("//a[@title='Advanced Search']")).click();
		System.out.println("Inside Advanced Search Page");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// In price range putting 0-150 and searching
		webDriver.findElement(By.xpath("//input[@id='price']")).clear();
		webDriver.findElement(By.xpath("//input[@id='price']")).sendKeys("0");
		webDriver.findElement(By.xpath("//input[@id='price_to']")).clear();
		webDriver.findElement(By.xpath("//input[@id='price_to']")).sendKeys("150");
		webDriver.findElement(By.xpath("//div[@class='buttons-set']//button[@title='Search']")).click();
		System.out.println("Search Initiated");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		
		Dimension windowSize = webDriver.manage().window().getSize();
		webDriver.manage().window().maximize();
		// Taking a snapshot of found searched results
		TakesScreenshot firstSearchSanpShot = ((TakesScreenshot)webDriver);
		File firstSearchedScrenShot = firstSearchSanpShot.getScreenshotAs(OutputType.FILE);
		String fsrScrFile = ("E:\\eclipse\\LiveTestCodeSamples\\FirstSearchedResultScreenShot" +".png");
		FileUtils.copyFile(firstSearchedScrenShot, new File(fsrScrFile));
		System.out.println("Your Screenshot Is Saved At this Location : " +fsrScrFile);
		webDriver.manage().window().setSize(windowSize);
		
		// Noting down Found elements
		String elementsFound = webDriver.findElement(By.xpath("//p[@class='advanced-search-amount']")).getText().toString().trim();
		System.out.println("Search Result :" +elementsFound);
		String priceRange = webDriver.findElement(By.xpath("//li[contains(text(),'0 - 150')]")).getText().toString().trim();
		System.out.println("Search Result :" +priceRange);

		// Element One
		webDriver.findElement(By.xpath("//img[@id='product-collection-image-1']")).click();
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		String itemNameFoundOne = webDriver.findElement(By.xpath("//span[@class='h1']")).getText().toString().trim();
		String itemPriceFoundOne = webDriver.findElement(By.xpath("//span[@class='price']")).getText().toString().trim();
		System.out.println("First Found Item Name and Price : "+itemNameFoundOne +" "+itemPriceFoundOne +" "+"Respectively");

		// Navigating back to previous page of searched elements found.
		webDriver.navigate().back(); // thats easy way out.
		// Same thing using JavaScript
		//JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
		//jsExecutor.executeScript("window.history.go(-1)");

		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Element Two
		webDriver.findElement(By.xpath("//img[@id='product-collection-image-3']")).click();
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}
		String itemNameFoundTwo = webDriver.findElement(By.xpath("//span[@class='h1']")).getText().toString().trim();
		String itemPriceFoundTwo = webDriver.findElement(By.xpath("//span[@id='product-price-3']")).getText().toString().trim();
		System.out.println("Second Found Item Name and Price : "+itemNameFoundTwo +" "+itemPriceFoundTwo +" "+"Respectively" +"\n");

		// Navigating back using JavaScript
		JavascriptExecutor jsExecutor = (JavascriptExecutor)webDriver;
		jsExecutor.executeScript("window.history.go(-2)"); //going back two pages to Advance Search Page
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Now In price field change range from 151 to 1000 and click search.
		webDriver.findElement(By.xpath("//input[@id='price']")).clear();
		webDriver.findElement(By.xpath("//input[@id='price']")).sendKeys("151");
		webDriver.findElement(By.xpath("//input[@id='price_to']")).clear();
		webDriver.findElement(By.xpath("//input[@id='price_to']")).sendKeys("1000");
		webDriver.findElement(By.xpath("//div[@class='buttons-set']//button[@title='Search']")).click();
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Noting down Found elements
		String itemsFound = webDriver.findElement(By.xpath("//p[@class='advanced-search-amount']")).getText().toString().trim();
		System.out.println("Search Result :" +itemsFound);
		String rangePrice = webDriver.findElement(By.xpath("//li[contains(text(),'151 - 1000')]")).getText().toString().trim();
		System.out.println("Search Result :" +rangePrice);
		
		Dimension neWindowSize = webDriver.manage().window().getSize();
		webDriver.manage().window().maximize();
		// Taking a snapshot of found searched results
		TakesScreenshot secondSearchSanpShot = ((TakesScreenshot)webDriver);
		File SecondSearchedScrenShot = secondSearchSanpShot.getScreenshotAs(OutputType.FILE);
		String ssrScrFile = ("E:\\eclipse\\LiveTestCodeSamples\\SecondSearchedResultScreenShot" +".png");
		FileUtils.copyFile(SecondSearchedScrenShot, new File(ssrScrFile));
		System.out.println("Your Screenshot Is Saved At this Location : " +ssrScrFile);
		webDriver.manage().window().setSize(windowSize);
		
		// First Item
		webDriver.findElement(By.xpath("//img[@id='product-collection-image-2']")).click();
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		String elementsFoundNameOne = webDriver.findElement(By.xpath("//span[@class='h1']")).getText().toString().trim();
		String elementsFoundPriceOne = webDriver.findElement(By.xpath("//span[@class='price']")).getText().toString().trim();
		System.out.println("First Found Item Name and Price : "+elementsFoundNameOne +" "+elementsFoundPriceOne +" "+"Respectively");

		// navigating back to Advanced Searched Result Page
		jsExecutor.executeScript("window.history.go(-1)");
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Second Item
		webDriver.findElement(By.xpath("//img[@id='product-collection-image-4']")).click();
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		String elementsFoundNameTwo = webDriver.findElement(By.xpath("//span[@class='h1']")).getText().toString().trim();
		String elementsFoundPriceTwo = webDriver.findElement(By.xpath("//span[@id='product-price-4']")).getText().toString().trim();
		System.out.println("Second Found Item Name and Price : "+elementsFoundNameTwo +" "+elementsFoundPriceTwo +" "+"Respectively");

		// navigating back to Advanced Searched Result Page
		jsExecutor.executeScript("window.history.go(-1)");
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Third Item
		webDriver.findElement(By.xpath("//img[@id='product-collection-image-5']")).click();
		Thread.sleep(2000);
		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		String elementsFoundNameThree = webDriver.findElement(By.xpath("//span[@class='h1']")).getText().toString().trim();
		String elementsFoundPriceThree = webDriver.findElement(By.xpath("//span[@class='price']")).getText().toString().trim();
		System.out.println("Third Found Item Name and Price : "+elementsFoundNameThree +" "+elementsFoundPriceThree +" "+"Respectively" +"\n");

		System.out.println("Test Completed...");
		webDriver.quit();
	}	

}
