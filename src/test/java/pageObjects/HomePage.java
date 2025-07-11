package pageObjects;
 
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
 
public class HomePage extends BasePage {
 
	public HomePage(WebDriver driver) {
		super(driver);
	}
 
	JavascriptExecutor js = (JavascriptExecutor) driver;
	Actions action = new Actions(driver);
 
	@FindBy(id = "forum_login_title_lg")
	WebElement LoginAndMore;
 
	@FindBy(xpath = "//*[@id=\"headerNewVNavWrap\"]/nav/ul/li[5]/span")
	WebElement More;
 
	@FindBy(linkText = "Used Cars")
	WebElement usedCar;
 
	@FindBy(xpath = "//*[@class='upcoming-bike-tab']")
	WebElement upcomingBikes;
 
	@FindBy(linkText = "Upcoming Bikes")
	WebElement allUpcomingBikes;
 
	public void ClickLoginAndMore() {
		LoginAndMore.click();
	}
 
	public void clickUsedCars(WebDriver driver) {
 
		action.moveToElement(More).perform();
		usedCar.click();
	}
 
	public void clickonUpcomingBike() {
 
		js.executeScript("arguments[0].scrollIntoView(true)", upcomingBikes);
		js.executeScript("arguments[0].click()",upcomingBikes);
	}
 
	public void clickonAllUpcomingBike() {
		js.executeScript("arguments[0].scrollIntoView(true)", allUpcomingBikes);
		js.executeScript("arguments[0].click()",allUpcomingBikes);
	}
 
}