package MyFluentWaitTest;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FluntWaitTestSample {

	static WebDriver webDriver ; 
	static String baseUrl = "https://google.com";

	public static void main(String[] args) {

		waitTest();
	}

	public static void waitTest() {

		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		webDriver.get(baseUrl);

		webDriver.findElement(By.name("q")).sendKeys("FluentWait Documentation");
		webDriver.findElement(By.name("btnK")).submit();

		// NOw tthis statement is used in fluent wait block.
		//webDriver.findElement(By.linkText("[webdriver] How to use Fluent Wait Predicate and Function - Grokbase")).click();

		// Implicit wait
		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// Fluent Wait
		// Waiting 3 seconds for an element to be present on the page, checking
		// for its presence once every 5 seconds.
	
		Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
	            .withTimeout(Duration.ofSeconds(3))
	            .pollingEvery(Duration.ofSeconds(5))
	            .ignoring(WebDriverException.class);

		WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver newDriver) {
				WebElement linkElement =  newDriver.findElement(By.partialLinkText("[webdriver] How to use Fluent Wait Predicate and Function - Grokbase"));

				if(linkElement.isEnabled()) {
					System.out.println("Element Found");
				}

				return linkElement;

			}
		});

		webElement.click();


		webDriver.quit();
	}

}
