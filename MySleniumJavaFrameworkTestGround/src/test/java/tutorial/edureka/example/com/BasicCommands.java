package tutorial.edureka.example.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicCommands {

	static WebDriver web_Driver;
	static String url_Location = "http://www.seleniumhq.org/";

	static JavascriptExecutor script_Runner;

	public void InvokeBrowser() {

		try {
			WebDriverManager.chromedriver().setup();
			web_Driver = new ChromeDriver();
			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			GetCommands();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void GetCommands() {

		try {

			web_Driver.get(url_Location);
			System.out.println("Landed Successfully");
			web_Driver.findElement(By.linkText("Download")).click();
			System.out.println("Current URL : " + web_Driver.getCurrentUrl());
			web_Driver.getTitle();
			System.out.println("Page Title : " + web_Driver.getTitle());
			ScrollMethod();
			web_Driver.findElement(By.xpath("//a[contains(text(),'Selenium WebDriver')]")).click();
			System.out.println("Page Title : " + web_Driver.getTitle());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void ScrollMethod() {

		script_Runner = (JavascriptExecutor) web_Driver;
		script_Runner.executeScript("scroll(0,1000)");

	}

	public static void main(String[] args) {

		try {
			// InvokeBrowser();
			BasicCommands my_Obj = new BasicCommands();
			my_Obj.InvokeBrowser();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			web_Driver.quit();
			System.out.println("Test Completed");

		}

	}

}

/**
 * try {
 * 
 * BasicCommands my_Obj = new BasicCommands(); my_Obj.GetCommands();
 * 
 * } catch (Exception ex) { // TODO: handle exception
 * 
 * ex.getStackTrace(); System.out.println(ex.getMessage()); } finally {
 * 
 * web_Driver.quit(); System.out.println("Test Completed");
 * 
 * }
 **/