package LiveTestProjectDay02;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestWorkingSet {
	
	static WebDriver webDriver;
	static String baseUrl  = "http://live.guru99.com/";
	static String mobilePrice;
	static String sonyPrice;
	
	public static void main(String[] args) {
		
		CommencingTest();
	}

	
	public static void CommencingTest() {
		
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();
		
		// Navigating to the Test Site
		webDriver.get(baseUrl);
		System.out.println("Navigateed to Landing Page");
		
		// Clicking on Mobile Menu
		webDriver.findElement(By.linkText("MOBILE")).click();
		System.out.println("Mobile Page Is Clicked");
		
		// Reading the Cost of Sony Experia mobile. Also save it for comparison purpose.
		//mobilePrice = webDriver.findElement(By.cssSelector("body.catalog-category-view.categorypath-mobile-html.category-mobile:nth-child(4) div.wrapper:nth-child(1) div.page:nth-child(2) div.main-container.col3-layout div.main div.col-wrapper div.col-main div.category-products ul.products-grid.products-grid--max-4-col.first.last.odd:nth-child(2) li.item.last:nth-child(1) div.product-info div.price-box span.regular-price > span.price")).getText();
		// In the list of all mobile , read the cost of Sony Xperia mobile (which is $100) 	    	      
	    //String XPeriaPrice = driver.findElement(By.cssSelector("#product-price-1 > span.price")).getText();
		mobilePrice = webDriver.findElement(By.className("price")).getText();
		System.out.println("Price On Product Page  : " +mobilePrice);
		
		// Clicking on Sony Experia mobile for details.
		// webDriver.findElement(By.linkText("Sony Xperia")).click();
		// Click on Sony Xperia mobile 	   
	    // driver.findElement(By.id("product-collection-image-1")).click();
		webDriver.findElement(By.cssSelector("#product-collection-image-1")).click();
		//sonyPrice = webDriver.findElement(By.cssSelector("body.catalog-product-view.catalog-product-view.product-sony-xperia.categorypath-mobile-html.category-mobile:nth-child(4) div.wrapper:nth-child(1) div.page:nth-child(2) div.main-container.col1-layout div.main div.col-main div.product-view:nth-child(3) div.product-essential div.product-shop:nth-child(4) div.price-info div.price-box span.regular-price > span.price")).getText();
		// Read the XPeria mobile price from details page
	    // String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();
		sonyPrice = webDriver.findElement(By.className("price")).getText();
		System.out.println("Price On Details Page : " +sonyPrice);
		
		/**
		 * 
		 // Compare two prices for equality.
		if (mobilePrice.equalsIgnoreCase(sonyPrice)) {
			System.out.println("Equal Price");
		} else {
			System.out.println("Price Didnt Match");
		}
		 */
		
	//  Product price in list and details page should be equal ($100)
	    try {
	        assertEquals(mobilePrice, sonyPrice); 
	      } catch (Exception e) {
	    	  e.printStackTrace();
	      }
				
		System.out.println("Test Completed...");
		webDriver.quit();
	}
}
