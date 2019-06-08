package tutorial.edureka.example.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class NavigationCommands {

	static WebDriver web_Driver;
	static String url_Location = "http://www.seleniumhq.org/";

	static JavascriptExecutor script_Runner;

	public static void main(String[] args) {

		NavigationCommands my_Obj = new NavigationCommands();
		my_Obj.InvokeBrowser();

		my_Obj.ClosingBrowser();

	}

	public void InvokeBrowser() {

		try {

			WebDriverManager.chromedriver().setup();
			web_Driver = new ChromeDriver();
			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			web_Driver.get(url_Location);
			FindingElements();
			InvokeNavigation();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void InvokeNavigation() {

		try {

			web_Driver.findElement(By.linkText("Download")).click();
			System.out.println("Current URL : " + web_Driver.getCurrentUrl());
			web_Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(2000);
			// ScrollingMethod();

			web_Driver.findElement(By.partialLinkText("Source Co")).click();
			System.out.println("Current URL : " + web_Driver.getCurrentUrl());
			// web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			Thread.sleep(2000);
			ScrollingMethod();

			web_Driver.navigate().back();
			System.out.println("Current URL : " + web_Driver.getCurrentUrl());
			// web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			// ScrollingMethod();
			Thread.sleep(2000);

			web_Driver.findElement(By.linkText("Documentation")).click();
			System.out.println("Current URL : " + web_Driver.getCurrentUrl());
			// web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			ScrollingMethod();
			Thread.sleep(2000);

			web_Driver.navigate().back();
			System.out.println("Current URL : " + web_Driver.getCurrentUrl());
			// web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			ScrollingMethod();
			Thread.sleep(2000);

			web_Driver.navigate().forward();
			System.out.println("Current URL : " + web_Driver.getCurrentUrl());
			// web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			ScrollingMethod();
			Thread.sleep(2000);

			web_Driver.navigate().refresh();
			System.out.println("Current URL : " + web_Driver.getCurrentUrl());
			// web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			ScrollingMethod();
			Thread.sleep(2000);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void ScrollingMethod() {

		script_Runner = (JavascriptExecutor) web_Driver;
		script_Runner.executeScript("scroll(10, 970)");

	}

	public static void ClosingBrowser() {

		web_Driver.close(); // To Close An Opened Page In Browser.
		web_Driver.quit(); // To Quit Browser Entirely.

		System.out.println("Test Completed");

	}

	public static void FindingElements() {

		web_Driver.findElement(By.id("q")).click();
		// web_Driver.findElement(By.className("")).click();
		
		// web_Driver.findElement(By.tagName("")).click();
		web_Driver.findElement(By.linkText("Projects")).click();
		
		web_Driver.findElement(By.partialLinkText("Abou")).click();
		//web_Driver.findElement(By.xpath("//h3//a[contains(text(),'Selenium IDE')]")).click();
		
		web_Driver.findElement(By.name("q")).sendKeys("Web Driver");
		web_Driver.findElement(By.cssSelector("#submit")).click();
		ScrollingMethod();
		
		web_Driver.navigate().back();

	}

}
