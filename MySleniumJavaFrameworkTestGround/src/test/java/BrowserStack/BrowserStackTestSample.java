package BrowserStack;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserStackTestSample {
	
	public static final String USERNAME = "asifuzzamanbappy1";
	  public static final String AUTOMATE_KEY = "AitmyvcyjwLfyBgK2927";
	  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	  public static void main(String[] args) throws Exception {

		  DesiredCapabilities caps = new DesiredCapabilities();
		  caps.setCapability("os", "Windows"); 
		  caps.setCapability("os_version", "10"); 
		  caps.setCapability("browser", "Chrome"); 
		  caps.setCapability("browser_version", "72.0"); 
		  caps.setCapability("resolution", "1920x1080"); 
		  caps.setCapability("project", "brStkTestSample"); 
		  caps.setCapability("build", "brStkBuildSample"); 
		  caps.setCapability("name", "brStkNavSample"); 
		  caps.setCapability("browserstack.local", "false"); 
		  caps.setCapability("browserstack.debug", "true"); 
		  caps.setCapability("browserstack.networkLogs", "true"); 
		  caps.setCapability("browserstack.selenium_version", "3.5.2"); 

	    WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
	    driver.get("http://www.google.com");
	    WebElement element = driver.findElement(By.name("q"));

	    element.sendKeys("BrowserStack");
	    element.submit();

	    System.out.println(driver.getTitle());
	    driver.quit();

	  }


}
