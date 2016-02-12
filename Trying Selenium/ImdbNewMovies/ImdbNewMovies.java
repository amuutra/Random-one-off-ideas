package tryingSelenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumThings {

	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.imdb.com");
		Actions action = new Actions(driver);
		
		action.moveToElement(driver.findElement(By.cssSelector("#navTitleMenu")));
		action.perform();
				
		WebElement element = (new WebDriverWait(driver, 10))
				   .until(ExpectedConditions.elementToBeClickable(By.cssSelector("#navMenu1 > div:nth-child(3) > ul:nth-child(2) > li:nth-child(6) > a:nth-child(1)")));
		element.click();
      
		List<WebElement> movieEntries = driver.findElements(By.className("info"));
		
		for(int i = 0; i<movieEntries.size(); i++) {
			System.out.println(movieEntries.get(i).getText().substring(0,  movieEntries.get(i).getText().indexOf(" (")));
		}
		
		driver.quit();
	}
		
}