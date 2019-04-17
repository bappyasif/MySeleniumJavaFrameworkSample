package DemoBankProjectLiveTestCaseDay01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SolutionFromGuru99ForLoginVerificationTestCase {

	/**
	 * 
	 * @param args
	 * @throws Exception
	  		  Test Script 01
	 *        ************** 
	 *        Test Steps
	 *        1)  Go to http://www.demo.guru99.com/V4/
        	  2) Enter valid UserId
              3) Enter valid Password
              4) Click Login
	 * 
	 */


	public static void main(String[] args) throws Exception {	  

		//Setup Firefox driver    	
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://www.demo.guru99.com/V4/";

		// launch Firefox and direct it to the Base URL
		driver.get(baseUrl);


		// Enter username
		driver.findElement(By.name("uid")).sendKeys("mngr1336");

		// Enter Password
		driver.findElement(By.name("password")).sendKeys("dAnavUq");

		// Click Login
		driver.findElement(By.name("btnLogin")).click();


	}

}
