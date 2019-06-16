package tutorial.edureka.example.com;

import java.awt.Desktop.Action;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UsingDragAndDropActions {

	static WebDriver web_Driver;
	static String url_Location = "http://www.edureka.co/";
	
	static String dd_URL = "http://www.jqueryui.com/droppable/";
	
	public static void main(String[] args) {
		
		UsingDragAndDropActions created_Object = new UsingDragAndDropActions();
		created_Object.InvokeBrowser();
		//created_Object.DragAndDropActions();
		//created_Object.ClosingBrowser();
	}

	public void InvokeBrowser() {

		try {
			WebDriverManager.chromedriver().setup();
			web_Driver = new ChromeDriver();
			//web_Driver.get(url_Location);
			web_Driver.get(dd_URL);
			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			//SearchActions();
			DragAndDropActions();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void DragAndDropActions() throws InterruptedException {
		
		web_Driver.switchTo().frame(0);
		
		WebElement source_Element = web_Driver.findElement(By.cssSelector("#draggable"));
		WebElement target_Element = web_Driver.findElement(By.cssSelector("#droppable"));
		
		Actions dd_Action = new Actions(web_Driver);
		Thread.sleep(2000);
		
		dd_Action.dragAndDrop(source_Element, target_Element).build().perform();
		Thread.sleep(2000);
		
		ClosingBrowser();
		
	}

	public void SearchActions() throws InterruptedException {
		Actions actions_Builder = new Actions(web_Driver);
		actions_Builder.moveToElement(web_Driver.findElement(By.id("header_topcat"))).build().perform();
		//web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(2000);

		WebElement click_Link = web_Driver.findElement(By.cssSelector("#software-testing-certification-courses"));
		actions_Builder.moveToElement(click_Link).build().perform();
		//web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(2000);

		web_Driver.findElement(By.linkText("Software Testing")).click();
		//web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(2000);

		WebElement search_Element = web_Driver.findElement(By.cssSelector("#search-inp"));
		actions_Builder.moveToElement(search_Element).build().perform();
		//web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(2000);

		WebElement search_Button = web_Driver.findElement(By.xpath("//span[@class='typeahead__button']"));
		actions_Builder.moveToElement(search_Button).build().perform();
		//web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(2000);

		org.openqa.selenium.interactions.Action series_OF_Actions;

		series_OF_Actions = actions_Builder.sendKeys(search_Element, "Selenium")
				.keyDown(search_Button, Keys.SHIFT)  //Helps In Clicking Element
				.keyUp(search_Button, Keys.SHIFT)   //Release User Mouse Keys
				.build();

		series_OF_Actions.perform();
		//web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(2000);
		ClosingBrowser();

	}

	public static void ClosingBrowser() {

		web_Driver.close(); // To Close An Opened Page In Browser.
		web_Driver.quit(); // To Quit Browser Entirely.

		System.out.println("Test Completed");

	}
	
	
}
