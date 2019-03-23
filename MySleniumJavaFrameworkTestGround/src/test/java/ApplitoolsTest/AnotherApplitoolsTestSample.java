package ApplitoolsTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AnotherApplitoolsTestSample {

	static WebDriver driver;

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		//Use Chrome browser
		driver = new ChromeDriver();

		// Initialize the eyes SDK and set your private API key.
		Eyes eyes = new Eyes();

		// Set the API key from the env variable. Please read the "Important Note"
		// section above.
		eyes.setApiKey("aUSoC102Hm1uJ10854AJ98y10Zxwg8tJU110xO9899BTe2H105a5F4110");

		try {
			// Call getTestInfoForPart to get the appropriate test information.
			//HashMap<String, String> testInfo = AnotherApplitoolsTestSample.main(null);

			// Start the test by setting AUT's name, window or the page name that's being tested, viewport width and height
			eyes.open(driver, "Login Form","Another Selenium Java Program To Check Visual Validation Test", new RectangleSize(600, 800));

			// Navigate the browser to the "ACME" demo app
			driver.get("https://demo.applitools.com");

			driver.findElement(By.tagName("Sing in")).click();
			
			// Visual checkpoint #1.
			eyes.checkWindow("Login window");

			//🌟 Note: You can have multiple "checkWindow" to create multiple test steps within a test.🌟  
			//For example, you may want to test errors in the login window after clicking a login button,
			//In that case, you may add the following before you call eyes.close(). 
			//This will create a test with two test steps.
			//driver.click("login");
			// Checkpoint #02
			driver.findElement(By.tagName("Sing in")).click();
			eyes.checkWindow("Login Window Error");
			
			Thread.sleep(3000);
			
			// End the test.
			eyes.close();

		} catch (Exception e) {

			System.out.println(e);

		} finally {

			// Close the browser.
			driver.quit();

			// If the test was aborted before eyes.close was called, ends the test as
			// aborted.
			//eyes.abortIfNotClosed();

			// End main test
			System.exit(0);
		}
	}
}


/**
 *
 
 Notes
Everything between eyes.open and eyes.close is called a Test.
The eyes.checkWindow is the code that takes the picture and uploads the image to our AI eyes server.
You can have multiple checkWindow if you are navigating multiple pages in a single test.
Also note that because this example has only one test, the code you include to 
add Applitools may look like a lot. But once you set things up, the only extra code 
you will add will be "eyes.checkWindow()", "eyes.checkRegion()" etc even if there are hundreds of tests.
 
 
 * 
 */
