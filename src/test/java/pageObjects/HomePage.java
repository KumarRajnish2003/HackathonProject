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

	@FindBy(xpath = "//*[@id=\"bike-tabs\"]/li[3]")
	WebElement upcomingBikes;

	@FindBy(xpath = "//*[@id=\"zw-cmnSilder\"]/div[2]/a")
	WebElement allUpcomingBikes;

	public void ClickLoginAndMore() {
		LoginAndMore.click();
	}

	public void clickUsedCars(WebDriver driver) {

		action.moveToElement(More).perform();
		usedCar.click();
	}

	public void clickonUpcomingBike() {

		js.executeScript("arguments[0].scrollIntoView(true);", upcomingBikes);
		upcomingBikes.click();
	}

	public void clickonAllUpcomingBike() {
		js.executeScript("arguments[0].scrollIntoView(true);", allUpcomingBikes);
		allUpcomingBikes.click();
	}

}
