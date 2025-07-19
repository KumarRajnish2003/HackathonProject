package pageObjects;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	JavascriptExecutor js = (JavascriptExecutor) driver;

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
	
	@FindBy(xpath="//*[@id=\"headerSearch\"]")
	WebElement search;       
	
	@FindBy(xpath="//*[@id=\"search-Sml\"]/span[3]")
	WebElement searchData;

	public void ClickLoginAndMore() {

		wait.until(ExpectedConditions.elementToBeClickable(LoginAndMore));
		LoginAndMore.click();
	}

	public void hoverMore() {

		wait.until(ExpectedConditions.elementToBeClickable(More));
		action.moveToElement(More).perform();
	}

	public void clickUsedCars() {

		wait.until(ExpectedConditions.elementToBeClickable(usedCar));
		usedCar.click();
	}

	public void clickonUpcomingBike() {

		js.executeScript("arguments[0].scrollIntoView(true);", upcomingBikes);
		js.executeScript("arguments[0].click()", upcomingBikes);
	}

	public void clickonAllUpcomingBike() {
		js.executeScript("arguments[0].scrollIntoView(true);", allUpcomingBikes);
		js.executeScript("arguments[0].click()", allUpcomingBikes);
	}
	
	public void sendSearchData(String data) {
		
		wait.until(ExpectedConditions.elementToBeClickable(search));
		wait.until(ExpectedConditions.elementToBeClickable(searchData));
		search.sendKeys(data);
		searchData.click();
	}

}
