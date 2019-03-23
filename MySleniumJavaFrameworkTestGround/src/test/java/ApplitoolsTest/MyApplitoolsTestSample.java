package ApplitoolsTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyApplitoolsTestSample {

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

			// Start test and set the browser to these specifics
			eyes.open(driver,"Hello World!", "My First Selenium Java Program To Check Visual Validation Test", new RectangleSize(800,600));

			// navvigate to Hello World website.
			//driver.get("http://applitools.com/helloworld");
			
			// Navigating another version of this page
			// driver.get("https://applitools.com/helloworld?diff1");
			
			// Navigating to the other one version of this page
			   driver.get("https://applitools.com/helloworld?diff2");

			// Visual Checkpoint #01
			eyes.checkWindow("Hello!");
			
			// Click the button says 'Click Me!'
			driver.findElement(By.tagName("button")).click();

			// Visual Checkpoint #02
			eyes.checkWindow("Click!");

			eyes.close();
	
		} catch (Exception e) {
			
			System.out.println(e);
		
		} finally {

			// Close the browser.
			driver.quit();

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

