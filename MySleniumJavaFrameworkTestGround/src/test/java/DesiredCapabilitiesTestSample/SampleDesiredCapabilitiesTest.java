package DesiredCapabilitiesTestSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SampleDesiredCapabilitiesTest {
	
	public static WebDriver webDriver;
	public static String baseUrl = "https://google.com";
	static DesiredCapabilities dCap;
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		dCap = new DesiredCapabilities();
		dCap.setCapability("ignoreProtectedModeSettings", true);
		
		WebDriverManager.iedriver().setup();
		
		webDriver = new InternetExplorerDriver(dCap);
		
		webDriver.manage().window().maximize();
		
		webDriver.get(baseUrl);
		
		webDriver.findElement(By.name("q")).sendKeys("Desired Capabbilities");
		webDriver.findElement(By.name("btnK")).submit();
		
		webDriver.quit();
		
	}

}
