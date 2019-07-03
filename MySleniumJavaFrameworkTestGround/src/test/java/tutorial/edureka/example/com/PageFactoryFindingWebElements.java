package tutorial.edureka.example.com;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class PageFactoryFindingWebElements extends PageFactoryTestCasesBase {

	static String search_Keys = "Selenium Certification";

	@Test
	public void PerformTest() throws InterruptedException {

		PageFacoryTestCaseScenerio find_Elements = PageFactory.initElements(web_Driver,
				PageFacoryTestCaseScenerio.class);

		
		
		find_Elements.set_SearchKey(search_Keys);
		find_Elements.hit_SearchButton();
		
		
		PageFactoryAnotherTestCase found_Elements = PageFactory.initElements(web_Driver,
				PageFactoryAnotherTestCase.class);
		
		found_Elements.hit_Scrolling();
		Thread.sleep(2000);
		//found_Elements.hit_Quit();

		//web_Driver.findElement(By.cssSelector("#search-inp")).sendKeys(search_Keys);
		// web_Driver.findElement(By.className("typeahead_button")).click();
		//web_Driver.findElement(By.xpath("//span[@class='typeahead__button']")).click();
		//script_Executor.executeScript("window.scrollBy(0,500)");
	}

}
