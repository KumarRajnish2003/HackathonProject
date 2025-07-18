package pageObjects;
 


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


 
public class UpcomingBikePage extends BasePage {
	public UpcomingBikePage(WebDriver driver) {
		super(driver);
	}
	
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@FindBy(xpath="//a[text()='Honda']")
	WebElement honda;
	
	public void clickOnHonda() {
		js.executeScript("arguments[0].scrollIntoView(true)", honda);
		js.executeScript("arguments[0].click()",honda);
	}

 
}