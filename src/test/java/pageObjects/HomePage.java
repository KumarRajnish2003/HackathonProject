package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

	public Actions action;
	private JavascriptExecutor js;

	public HomePage(WebDriver driver) {
		super(driver);
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}

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
		try {
			wait.until(ExpectedConditions.elementToBeClickable(LoginAndMore)).click();
		} catch (Exception e) {
			throw e;
		}
	}

	public void hoverMore() {
		try {
			wait.until(ExpectedConditions.visibilityOf(More));
			action.moveToElement(More).perform();

		} catch (Exception e) {
			throw e;
		}
	}

	public void clickUsedCars() {

		action.moveToElement(More).perform();
		wait.until(ExpectedConditions.elementToBeClickable(usedCar)).click();
//		usedCar.click();
	}

	public void clickonUpcomingBike() {

		js.executeScript("arguments[0].scrollIntoView(true);", upcomingBikes);
		js.executeScript("arguments[0].click()", upcomingBikes);
	}

	public void clickonAllUpcomingBike() {
		js.executeScript("arguments[0].scrollIntoView(true);", allUpcomingBikes);
		js.executeScript("arguments[0].click()", allUpcomingBikes);
	}

}
