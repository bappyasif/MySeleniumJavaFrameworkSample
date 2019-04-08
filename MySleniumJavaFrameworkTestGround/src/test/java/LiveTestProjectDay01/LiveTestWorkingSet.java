package LiveTestProjectDay01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
import okhttp3.internal.connection.RouteSelector.Selection;

public class LiveTestWorkingSet {
	
	static WebDriver webDriver;
	static String baseUrl = "http://live.guru99.com/";
	static String titleString; 
	static String menuString;
	static boolean selectionEnabled;
	static String sortbySelection;
	
	public static void main(String[] args) {
		
		CommencingTest();
		
	}
	
	public static void CommencingTest() {
		
		WebDriverManager.chromedriver().setup();
		
		webDriver = new ChromeDriver();
		
		webDriver.get(baseUrl);
		
		titleString = webDriver.getTitle();
		System.out.println("Title of this page : " +titleString);
		
		webDriver.findElement(By.linkText("MOBILE")).click();
		menuString = webDriver.getTitle();
		System.out.println("Title of This page :" +menuString);
		
		
		//webDriver.findElement(By.linkText("Sort By")).findElement(By.name("Name")).click();
		//selectionEnabled = webDriver.findElement(By.linkText("Sort BY")).findElement(By.name("Name")).isEnabled();
		/**
		 
		  Select selection = new Select(webDriver.findElement(By.xpath("//body[contains(@class,'"
				+ "catalog-category-view categorypath-mobile-html category-mobile')]"
				+ "/div[@class='wrapper']/div[@class='page']/div[@class='main-container col3-layout']"
				+ "/div[@class='main']/div[@class='col-wrapper']/div[@class='col-main']"
				+ "/div[@class='category-products']/div[@class='toolbar']"
				+ "/div[@class='sorter']/div[@class='sort-by']/select[1]")));
			  
		
		  sortbySelection = drpdwnSelection.getFirstSelectedOption().toString();
		
		if(sortbySelection.equalsIgnoreCase("Name")) {
			
			System.out.println("Sorted By:  " +"Name");
			
		}
		
		WebElement drpdwnElement = webDriver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]"
				+ "/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/select[1]"));
		
		sortbySelection = drpdwnElement.getTagName().toString();

		  
		 */
		
		Select drpdwnSelection = new Select(webDriver.findElement(By.xpath("//body[contains(@class,'"
				+ "catalog-category-view categorypath-mobile-html category-mobile')]"
				+ "/div[@class='wrapper']/div[@class='page']/div[@class='main-container col3-layout']"
				+ "/div[@class='main']/div[@class='col-wrapper']/div[@class='col-main']"
				+ "/div[@class='category-products']/div[@class='toolbar']"
				+ "/div[@class='sorter']/div[@class='sort-by']/select[1]")));

		drpdwnSelection.selectByVisibleText("Name");
		
		if(drpdwnSelection.getFirstSelectedOption().isSelected()) {
			System.out.println("Name");
		}
		
		/**
		 
		WebElement drpdwnOption = webDriver.findElement(By.xpath("//body[contains(@class,'"
				+ "catalog-category-view categorypath-mobile-html category-mobile')]"
				+ "/div[@class='wrapper']/div[@class='page']/div[@class='main-container col3-layout']"
				+ "/div[@class='main']/div[@class='col-wrapper']/div[@class='col-main']"
				+ "/div[@class='category-products']/div[@class='toolbar']"
				+ "/div[@class='sorter']/div[@class='sort-by']/select[1]"));
		drpdwnOption.getAttribute("name").toString();
		
		if(drpdwnOption.isEnabled()) {
			
			System.out.println("Sort By option is : " +drpdwnOption);
			
		}
 		 
		 */
				
		//sortbySelection = drpdwnOption.getText();
		//selectionEnabled = webDriver.findElement(By.id("Name")).isEnabled();
		
		//WebElement dropdownOptions = (WebElement) selection.getAllSelectedOptions();
		
		//Select drpdwnVerification = (Select) drpdwnSelection.getFirstSelectedOption();
		//sortbySelection = ((WebElement) drpdwnVerification).getText();
		
		//selectionEnabled = sortbySelection.matches("Name");
		
		
		//selectionEnabled  = sortbySelection.contains("Name");
		
		System.out.println("Test Completed...");
		webDriver.quit();
		
		
	}

}
