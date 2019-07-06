package tutorial.edureka.example.com;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWaitsTestExample {

	static WebDriver web_Driver;
	static String url_Location = "http://www.edureka.co/";

	static String new_URL = "http://www.facebook.com/";
	static String another_URL = "http://the-internet.herokuapp.com/dynamic_loading/1";

	public void InvokeBrowser() {

		WebDriverManager.chromedriver().setup();
		web_Driver = new ChromeDriver();

		web_Driver.get(url_Location);

		web_Driver.manage().deleteAllCookies();
		web_Driver.manage().window().maximize();

		ImplicitWaits();
		ExplicitWaits();
		//FluentWaits();

	}

	/**
	 * Implicit Waits Applied For All The Elements Within The Test Application. It's
	 * Efficient For Compact Projects.
	 */
	public void ImplicitWaits() {

		try {

			// When Load Time Is Below Limits Then Program Moves On TO Normal Flow, Both Of
			// These Statement Example Of Implicitly Wait Timeouts.
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			web_Driver.findElement(By.cssSelector("#search-inp")).sendKeys("Test Automation");
			Thread.sleep(2000);
			web_Driver.findElement(By.xpath("//span[@class='typeahead__button']")).click();

		} catch (InterruptedException ex) {

			// TODO Auto-generated catch block
			ex.printStackTrace();
			System.out.println("Wait Time Has Exceeded");

		}
	}

	/**
	 * As Explicit Time Outs Does Not Have Any Built-In Functions, We Have To
	 * Explicitly Code Our Way.
	 */
	public void ExplicitWaits() {

		// As We Are Redirecting To New URL, Lets Get Handle On Our Window First.
		String current_Handle = web_Driver.getWindowHandle();

		// Here Switching To Our Current Handle.
		web_Driver.switchTo().window(current_Handle);

		System.out.println("Navigating To New Site");

		web_Driver.get(new_URL);

		// WebElement user_Email =
		// web_Driver.findElement(By.xpath("//input[@id='email']"));
		// WebElement user_Pass =
		// web_Driver.findElement(By.xpath("//input[@id='pass']"));

		WebElement first_Name = web_Driver.findElement(By.xpath("//input[@id='u_0_l']"));
		WebElement last_Name = web_Driver.findElement(By.xpath("//input[@id='u_0_n']"));

		// PerformingTest(web_Driver, first_Name, 20, "Who's There??");
		// PerformingTest(web_Driver, last_Name, 30, "You First!!");

		WebElement forgot_Account = web_Driver.findElement(By.linkText("Forgotten account?"));
		PerformingClickOnTest(web_Driver, forgot_Account, 20);

	}

	/**
	 * 
	 * @param driverParameter
	 * @param elementParameter
	 * @param timeOuts
	 * @param keyValues
	 * 
	 *                         This Where We Inherently Denote Our Expected
	 *                         Conditions, That To Be Met Before Throwing An
	 *                         Exception.
	 * 
	 */
	public void PerformingTest(WebDriver driverParameter, WebElement elementParameter, int timeOuts, String keyValues) {

		new WebDriverWait(driverParameter, timeOuts).until(ExpectedConditions.invisibilityOf(elementParameter));

		elementParameter.sendKeys(keyValues);

	}

	/**
	 * 
	 * @param webDriver
	 * @param webElement
	 * @param timeOuts
	 * 
	 *                   This Explicit Wait Function Would Expected TO Be It's
	 *                   WebElement Click-able First, Before Performing Intended
	 *                   Clicking Operation On It.
	 * 
	 */
	public void PerformingClickOnTest(WebDriver webDriver, WebElement webElement, int timeOuts) {

		new WebDriverWait(webDriver, timeOuts).until(ExpectedConditions.elementToBeClickable(webElement));

		webElement.click();

	}

	public void ClosingBrowser() {

		// web_Driver.close(); // To Close An Opened Page In Browser.
		web_Driver.quit(); // To Quit Browser Entirely.

		System.out.println("Test Completed");

	}

	/**
	 * 
	 * @param args
	 * 
	 *             It's Not Recommended To Use Both Of The Waits Function Together,
	 *             As It Might As Well Affect In Page Wait Constraint For Longer
	 *             Interval.
	 * 
	 *             Implicit And Explicit Wait Will Override EachOther When
	 *             Encountered Sequentially, So It's Better To Use Them Separately
	 *             When Needed.
	 * 
	 */
	public static void main(String[] args) {

		SeleniumWaitsTestExample created_Object = new SeleniumWaitsTestExample();

		created_Object.InvokeBrowser();
		created_Object.ClosingBrowser();

	}

	/**
	 * It's Not Highly Recommended TO Use As An Alternative To Other Waits. It Uses
	 * A Frequency Along With Expected Conditions.
	 */
	public void FluentWaits() {

		// As We Are Redirecting To New URL, Lets Get Handle On Our Window First.
		String current_Handle = web_Driver.getWindowHandle();

		// Here Switching To Our Current Handle.
		web_Driver.switchTo().window(current_Handle);

		System.out.println("Navigating To Another Site");

		web_Driver.get(another_URL);

		web_Driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		Wait<WebDriver> fluenWait = new FluentWait<WebDriver>(web_Driver)
				.withTimeout(30, TimeUnit.MILLISECONDS)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
		WebElement contentFound = fluenWait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver webDriver) {
				// TODO Auto-generated method stub
				return webDriver.findElement(By.xpath("//h4[text()='Hello World']"));
			}
		});
		
		System.out.println("Text Found : "+contentFound.getText());

	}

}
