package tutorial.edureka.example.com;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumRobotClassUsageExample {

	static WebDriver web_Driver;
	static String url_Location = "http://www.edureka.co/";

	String browser_Name = "ChRoMe";

	public void InvokeBrowser() {

		try {

			ChoosingBrowser(browser_Name);

			web_Driver.get(url_Location);

			web_Driver.manage().deleteAllCookies();
			web_Driver.manage().window().maximize();
			web_Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			web_Driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

			RobotClassTestExample();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ChoosingBrowser(String browser) throws Exception {

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();

			web_Driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("IE")) {

			// WebDriverManager.iedriver().clearPreferences();

			WebDriverManager.iedriver().setup();

			web_Driver = new InternetExplorerDriver();

		} else {

			System.out.println("Unmatched Browser");

			throw new Exception("No Matched Browser Found");
		}

	}

	public void RobotClassTestExample() throws AWTException, InterruptedException {

		web_Driver.findElement(By.linkText("Courses")).click();

		Robot created_Robot = new Robot();

		Thread.sleep(2000);

		created_Robot.keyPress(KeyEvent.VK_DOWN); // Key Pressed Is Specified.

		Thread.sleep(2000);

		// created_Robot.keyPress(KeyEvent.VK_TAB); // Tab Key Is Pressed

		// Thread.sleep(2000);

		// System.out.println("Test Console");

		created_Robot.keyPress(KeyEvent.VK_TAB); // Tab Key Is Pressed

		Thread.sleep(2000);

		System.out.println("Testt Console");

		// created_Robot.keyPress(KeyEvent.VK_TAB); // Tab Key Is Pressed

		// Thread.sleep(2000);

		// System.out.println("Testtt Console");

		created_Robot.keyPress(KeyEvent.VK_TAB); // Tab Key Is Pressed

		Thread.sleep(2000);

		System.out.println("Testttt Console");

		created_Robot.mouseMove(20, 160); // Depends On Screen Resolution

	}

	public void ClosingBrowser() {

		// web_Driver.close(); // To Close An Opened Page In Browser.
		web_Driver.quit(); // To Quit Browser Entirely.

		System.out.println("Test Completed");

	}

	/**
	 * 
	 * @param args In Robot Class Mouse Key Events Will Only Work In Current
	 *             Instances Of Opened Window. There are Difficulties In Switching
	 *             Between Frames Or Windows. Operating On VM Also Throws Unexpected
	 *             Error. Robot Class Handles All The Keyboard And Mouse Handles.
	 *             Methods Includes But Not Limited To KeyPress(), KeyRelease(),
	 *             MousePress(), MouseRelease(), MouseMove(). Handles PopUps And
	 *             Click Options.
	 */
	public static void main(String[] args) {

		SeleniumRobotClassUsageExample created_Object = new SeleniumRobotClassUsageExample();

		created_Object.InvokeBrowser();

		created_Object.ClosingBrowser();

	}

}
