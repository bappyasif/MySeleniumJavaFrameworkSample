package tutorial.edureka.example.com;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ManagingMultipleWindowHandles {
	
	static WebDriver web_Driver;
	static String url_Location = "https://www.edureka.co/";
	
	//static String url_Location = "https://www.toolsqa.com/automation-practice-switch-windows/";
	//static String url_Location = "https://www.edureka.co/community/";
	//static String url_Location = "https://bdjobs.com/";
	
	JavascriptExecutor script_Executor;
	static String new_URL = "http://www.google.com/";
	static String another_URL = "http://www.toolsqa.com/";
	static String search_String = "Selenium Certification Training";
	
	public static void main(String[] args) {
		
		ManagingMultipleWindowHandles created_Object = new ManagingMultipleWindowHandles();
		created_Object.InvokeBrowser();
		created_Object.ClosingBrowser();
		
	}

	public void InvokeBrowser() {

		try {
			WebDriverManager.chromedriver().setup();
			web_Driver = new ChromeDriver();
			
			web_Driver.get(url_Location);
			
			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			//WindowsHandler();
			//AnotherMultipleWindowHandler();
			//MultipleWindows();
			OneMoreWindowHandlerExample();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void OneMoreWindowHandlerExample() throws InterruptedException {
		
		String page_Title = web_Driver.getTitle();
		System.out.println("Page Title : " +page_Title);
		
		// Initializing JSExecutor For Use
		script_Executor = (JavascriptExecutor)web_Driver;
		
		web_Driver.findElement(By.cssSelector("#search-inp")).sendKeys(search_String);
		Thread.sleep(2000);
		script_Executor.executeScript("window.scrollBy(0, 40)");
		Thread.sleep(2000);
		web_Driver.findElement(By.xpath("//span[@class='typeahead__button']")).click();
		web_Driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		//WebElement link_Courses = web_Driver.findElement(By.linkText("Coursres"));
		WebElement link_Courses = web_Driver.findElement(By.xpath("//li[@class='ga-allcourses']//a[@class='giTrackElementHeader'][contains(text(),'Courses')]"));
		Actions new_Action = new Actions(web_Driver);
		new_Action.keyDown(Keys.SHIFT).click(link_Courses).keyUp(Keys.SHIFT).build().perform();
		Thread.sleep(2000);
		script_Executor.executeScript("window.scrollBy(0, 40)");
		
		Set<String> window_Handdlers = web_Driver.getWindowHandles();
		System.out.println(window_Handdlers);
		System.out.println("A1");  // Reference Print
		for (String window : window_Handdlers) {
			web_Driver.switchTo().window(window);
			
			// Asserting Title
			if (web_Driver.getTitle().contains("Best Training & Certification Courses For Professional | Edureka")) {
				System.out.println("A2");
				script_Executor.executeScript("window.scrollBy(0, 1000)");
				Thread.sleep(2000);
				System.out.println("B1");
				web_Driver.findElement(By.xpath("//*[@id=\\\"allc_catlist\\\"]/li[3]/a\"")).click();
				web_Driver.manage().window().setPosition(new Point(-2000, 0));
			}
			
			Thread.sleep(2000);
			Set<String> more_Windows = web_Driver.getWindowHandles();
			System.out.println(more_Windows);
			System.out.println("A3");
			
			for (String window_New : more_Windows) {
				web_Driver.switchTo().window(window_New);
				System.out.println("A4");
				script_Executor.executeScript("window.scrollBy(0, 400)");
			}
		}
	}
	
	/**
	 * 
	 * @throws InterruptedException
	 * This Program Is Supposed To Open Few Window On Click Event But Web-site Seems TO Be Not Behaving As Expected.
	 * Underlying Concept Is Sound And Working Just Fine. We Should Get As Many New Windows(Opened) Navigation to 
	 * New URL As Well.    
	 */
	public void WindowsHandler() throws InterruptedException {
		
		try {
			String parentWindowHandle = web_Driver.getWindowHandle();
			System.out.println("Parent Window Handle Is : " +parentWindowHandle);
			
			WebElement click_Element = web_Driver.findElement(By.id("button1"));
			
			for (int i = 0; i < 3; i++) {
				click_Element.click();
				Thread.sleep(2000);
			}
			
			// All Handles
			Set <String> allWindowHandles = web_Driver.getWindowHandles();
			for (String current_Handle : allWindowHandles) {
				System.out.println("Current Window Handle Is : " +current_Handle);
				
				System.out.println("Now Navigating To New Window");
				web_Driver.switchTo().window(current_Handle);
				web_Driver.get(new_URL);
				System.out.println("Current Window Title Is : " +web_Driver.getTitle());
				
				String lastOpenedWindowHande = current_Handle;
				// Switching To Parent Window
				web_Driver.switchTo().window(parentWindowHandle);
				// Closing Window
				//web_Driver.close();
				// Now That We Have Empty Window Handles, So Switching Back to Last Opened Window
				web_Driver.switchTo().window(lastOpenedWindowHande);
				web_Driver.get(another_URL);
				Thread.sleep(2000);
				System.out.println("Current Window Title Is : " +web_Driver.getTitle());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void AnotherMultipleWindowHandler() throws InterruptedException {
		
		script_Executor = (JavascriptExecutor)web_Driver;
		String startingWindow = web_Driver.getWindowHandle();
		
		// Find Element
		web_Driver.findElement(By.xpath("//a[@class='qa-logo-link edureka']")).sendKeys(Keys.SHIFT, Keys.ENTER);
		
		script_Executor.executeScript("window.scrollBy(0,400)");
		Thread.sleep(2000);
		
		// All Windows
		Set<String> windowSets = web_Driver.getWindowHandles();
		System.out.println("Windows Opened : " +windowSets);
		
		Iterator<String> windowsIterator = windowSets.iterator();
		while (windowsIterator.hasNext()) {
			script_Executor.executeScript("window.scrollBy(0,400)");
			String child_Window = windowsIterator.next();
			
			if (!startingWindow.equals(child_Window)) {
				web_Driver.switchTo().window(child_Window);
				System.out.println("Current Window Title Is : " +web_Driver.getTitle());
			}
			
		}
		
		// Switching BAck To Starting Window
		web_Driver.switchTo().window(startingWindow);
		System.out.println("Starting Window : " +web_Driver.getTitle());
	}
	
	public void MultipleWindows() {
		
		try {
			// Returns Window Handle As String
			String parentWindow = web_Driver.getWindowHandle();
			
			// Returns All Windows Handler That Are Already Opened By WebDriver.
			Set<String> allWindowsHandlerSet = web_Driver.getWindowHandles();
			
			// Iterator Through Windows Handler Set
			Iterator<String> windowsIterator = allWindowsHandlerSet.iterator();
			
			while (windowsIterator.hasNext()) {
				
				String child_Window = windowsIterator.next();
				
				// Compare Windows That Are Equal Or Not To Parent Window
				if (parentWindow.equals(child_Window)) {
					
					web_Driver.switchTo().window(child_Window);
					System.out.println(web_Driver.getTitle());
					
					web_Driver.close();
					
				}
			}
			
			// This piece Of Code Would Have Worked If Had Multiple Windows Opened Already!!
			//web_Driver.switchTo().window(parentWindow);
			//System.out.println(web_Driver.getTitle());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ClosingBrowser() {

		//web_Driver.close(); // To Close An Opened Page In Browser.
		web_Driver.quit(); // To Quit Browser Entirely.

		System.out.println("Test Completed");

	}

}
