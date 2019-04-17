package DemoBankProjectLiveTestCaseDay02;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LiveTestOnVerifyLoginEnhanceementScript {

	static WebDriver webDriver;
	static String baseUrl = "http://www.demo.guru99.com/V4/";

	static final String userID = "mngr190242"; 
	static final String userPassword = "hArEdAq";
	
	
	public static void main(String[] args) {
		
		try {
		
			CommencingTestOnLoginVerificationEnhancement();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void CommencingTestOnLoginVerificationEnhancement() throws IOException, InterruptedException {

		// Getting webDriver ready for this.
		WebDriverManager.chromedriver().setup();
		webDriver = new ChromeDriver();

		// Navigating to test site's landing page
		webDriver.get(baseUrl);
		System.out.println("Landing Successful");

		// Entering userID and Password to login section
		webDriver.findElement(By.xpath("//input[@name='uid']")).clear();
		webDriver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		webDriver.findElement(By.xpath("//input[@name='password']")).clear();
		webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys(userPassword);
		webDriver.findElement(By.xpath("//input[@value='LOGIN']")).click();

		System.out.println("Inside User Home Page");
		Thread.sleep(2000);

		// switching to new window. Even though it doesn't need this window switching but 
		// still expert says its better this way when you're landing on a new URL.
		for (String handle : webDriver.getWindowHandles()) {
			webDriver.switchTo().window(handle);
		}

		// Verifying User login was successful
		try {

			System.out.println("Vefication Of User Home Page");

			String userloginHeadingStatement = webDriver.findElement(By.xpath("//marquee[@class='heading3']")).getText().toString().trim();
			System.out.println("Heading Statement : " +userloginHeadingStatement);

			String userVerificationByID = webDriver.findElement(By.xpath("//td[contains(text(),'Manger Id : mngr190242')]")).getText().toString().trim();
			System.out.println("User Identification : " +userVerificationByID);

			// Test Case wanted to include Page title as a step of verification process.
			String hpTitle = webDriver.getTitle().toString().trim();
			System.out.println("Page Title : " +hpTitle);
			
			// Just playing with Uppercase.locale for home page Title. 
			String hptitleAllCaps = webDriver.getTitle().toUpperCase(Locale.US).toString().trim();
			System.out.println("Page Title In Uppper Case Using Locale: " +hptitleAllCaps);
			
			System.out.println("Enhancement In Verification Is Somewhat Done");

		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println(ex.getMessage());
			System.out.println(ex.getCause());

			ex.getStackTrace();

			System.out.println("Check Console For Clarification");

		} finally {

			System.out.println("Inside Finally Code Block Where We Take A Snapshot Of User Home Page.");

			webDriver.manage().window().maximize();
			TakesScreenshot userloginScreenShot = ((TakesScreenshot)webDriver);
			File ulScrenshot = userloginScreenShot.getScreenshotAs(OutputType.FILE);
			String fileSaved = ("E:\\eclipse\\LiveTestCodeSamples\\Bank Demo\\userLoginVerificationSuccessfulScreenShot" +".png");
			FileUtils.copyFile(ulScrenshot, new File(fileSaved));
			System.out.println("Your Screenshot Is Saved At This Location : " +fileSaved);

			System.out.println("Test Completed...");
		}
		webDriver.quit();	
	}
}

