package MySeleniumWebDriverManagerSample;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.runner.Version;
import net.bytebuddy.asm.Advice.Return;

public class MyMavenWebDriverManagerDemo {
	
	public static void main(String[] args) throws InterruptedException {
		
		//This would run on specific version of Browser set by the user. instead of Best Supported version of that browser.
		// WebDriverManager.chromedriver().version("any particular version of Chrome you want").setup();
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver webDriver = new ChromeDriver();
		
		webDriver.manage().window().maximize();
		
		webDriver.get("http://google.com");
		webDriver.findElement(By.name("q")).sendKeys("Test Automation");
		
		List<WebElement> listOfWebElements = webDriver.findElements(By.xpath("//input"));
		int count = listOfWebElements.size();
		
		System.out.println("Count of Input Elelemnts "  +count);
		
		webDriver.findElement(By.name("btnK")).submit();
		
		//WebElement webElement = webDriver.findElement(By.name("btnK"));
		//webElement.sendKeys(Keys.RETURN);
		//webElement.sendKeys(Keys.ENTER);
		//webDriver.findElement(By.name("btnK")).sendKeys(Keys.ENTER);
		
		System.out.println("Test was Successfull ");

				
		Thread.sleep(3000);
		
		webDriver.quit();
		
		
		/**
		 * 
	    webDriver.get("http://goldgrizzly.com/");
		WebElement webElement = webDriver.findElement(By.xpath("top-bar__search"));
		webElement.sendKeys("Belinger.com");
		*
		* 
		This followed statement also does the same thing as above two statements. This is efficient.
		webDriver.findElement(By.className("top-bar_search")).sendKeys("Belinger.com");
		*
		*
		//webDriver.findElements(By.xpath("//input")).clear();
		//WebElement webElement = webDriver.findElement(By.name("q"));
		//webElement.sendKeys("SeleniumHQ");
		//webElement.click();
		//webDriver.findElement(By.id("lst-ib")).sendKeys("Selenium");
		//webDriver.findElement(By.className("input.gLFyf.gsfi")).sendKeys("Selenium");
		//webDriver.findElement(By.xpath("//input[@title='Search']")).clear();
		//webDriver.findElement(By.className("gLFyf gsfi")).clear();
		//WebElement webElement = webDriver.findElement(By.ByTagName.className("gLFyf gsfi"));
		//webElement.clear();
		//webElement.sendKeys("Test Automation Step By Step");
		//webDriver.findElement(By.xpath("//input[@title='Search']")).sendKeys("Test Automation Step By Step ");
		//webDriver.findElement(By.xpath("//input[@Id='lst-id']")).sendKeys("Test Automation");
		*
		*
		**/

	}

}

/**
 * 
WebDriverManager resolves the driver binaries for the browsers Chrome, Firefox, Opera, PhantomJS, Microsoft Edge, and Internet Explorer.
For that, it provides several drivers managers for these browsers. These drivers managers can be used as follows:

WebDriverManager.chromedriver().setup();
WebDriverManager.firefoxdriver().setup();
WebDriverManager.operadriver().setup();
WebDriverManager.phantomjs().setup();
WebDriverManager.edgedriver().setup();
WebDriverManager.iedriver().setup();
 * 
 * 
 You would also need appropriate WebDriver for each Browser configuration respectively.
 *
 */
