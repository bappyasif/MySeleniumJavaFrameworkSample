package TestAutoITSample;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleTestAutoIT {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		testiingAutoItScript();
		
	}
	
	public static void testiingAutoItScript() throws IOException, InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver webDriver = new ChromeDriver();
		
		webDriver.get("http://tinyupload.com/");
		webDriver.findElement(By.name("uploaded_file")).click();
		
		Thread.sleep(3000);
		
		// now we will use our operating script that we created in SOTE
		Runtime.getRuntime().exec("E:\\AutoIt\\FileUploadScriptTest.exe");
		

		Thread.sleep(3000);
		
		webDriver.quit();
	}

}
