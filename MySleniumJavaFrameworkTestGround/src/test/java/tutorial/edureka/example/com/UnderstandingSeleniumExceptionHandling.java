package tutorial.edureka.example.com;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UnderstandingSeleniumExceptionHandling {
	
	static WebDriver web_Driver;
	static String url_Location = "http://www.seleniumhq.org/";
	
	public void InvokeBrowser() {

		try {
			
			WebDriverManager.chromedriver().setup();
			
			// #1 >> If We Want To Instantiate Driver For Any Other Browser Than Chrome Complier Will Throw WebDriverException.
			web_Driver = new ChromeDriver(); 
			web_Driver.get(url_Location);
			
			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			
			// Exception Handling Methods Explained.
			HandlingExceptions();
			NoAlertExceptionHandling();
			NoSuchFrameException();
			NoSuchWindowExcption();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void NoSuchFrameException() {
		
		try {
			// Trying Switch To A Frame That Does Not Exist.
			web_Driver.switchTo().window("Undiscloused");
			
		} catch (org.openqa.selenium.NoSuchFrameException nsFE) {
			// TODO: handle exception
			nsFE.getStackTrace();			
		}
		
	}
	
	public void NoSuchWindowExcption() {
		try {
			// Trying Switch To A Window That Does Not Exist.
			web_Driver.switchTo().window("Undiscloused");
			
		} catch (NoSuchWindowException nsWE) {
			// TODO: handle exception
			nsWE.getMessage();
			
		}
	}
	
	public void NoAlertExceptionHandling() {
		try {
			
			// Trying To Handle Exception When There Is none
			web_Driver.switchTo().alert().accept();
			// Or
			web_Driver.switchTo().alert().dismiss();
			
		} catch (NoAlertPresentException naPE) {
			// Even Though Exception Being Handled It Will Still Throw An Exception & Show It In Console.
			//naPE.printStackTrace();
			naPE.getCause();
		}
	}
	
	public void HandlingExceptions() {
		
		try {
			// #2 >> NoSuchElementFound Exception Handling Example 
			web_Driver.findElement(By.name("Undiscloused")).click();
			//NoAlertExceptionHandling();
			
		} catch (NoSuchElementException nsEL) {
			// TODO: handle exception
			System.out.println("No Such Elelment Found");
			System.out.println("Exception Handled Without An Error");
			
			throw (nsEL);  // Despite Exception Being Handled Or Not It Will Throw This Exception In Console.
		}
		
		System.out.println("Normal Program Excution Flow");
	}
	
	public void ClosingBrowser() {

		//web_Driver.close(); // To Close An Opened Page In Browser.
		web_Driver.quit(); // To Quit Browser Entirely.

		System.out.println("Test Completed");

	}
	
	public static void main(String[] args) {
		
		UnderstandingSeleniumExceptionHandling created_Objeect = new UnderstandingSeleniumExceptionHandling();
		created_Objeect.InvokeBrowser();
		created_Objeect.ClosingBrowser();
		
	}

}
