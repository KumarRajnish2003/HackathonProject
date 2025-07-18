package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UsedCarPage extends BasePage {

	public UsedCarPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"gs_input5\"]")
	WebElement cityinput;

	@FindBy(xpath = "//*[@id=\"ui-id-5\"]/li/a")
	WebElement city;

	@FindBy(xpath = "//div[@class='gsc_thin_scroll']/ul")
	WebElement popularCarList;

	@FindBy(xpath = "//div[@class='gsc_thin_scroll']/ul/li/label")
	List<WebElement> popularCars;

	@FindBy(id = "headerSearch")
	WebElement searchInput;
	
	@FindAll({
		@FindBy(xpath="//*[@id=\"ui-id-5\"]/li/a") ,
		@FindBy(className="ui-autocomplete-input")
		}) List<WebElement> suggestions;

	public void selectCity(String cityinfo) {
		cityinput.sendKeys(cityinfo);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
//				By.xpath("//ul[starts-with(@id, 'ui-id')]/li/a[normalize-space()='" + cityinfo + "']")
				By.xpath("//ul[starts-with(@id, 'ui-id')]/li/a")
				));
	}

	public void enterCity() {
//		city.click();
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(city));
		button.click();
//		cityinput.sendKeys(Keys.ARROW_DOWN);
//		cityinput.sendKeys(Keys.ENTER);
//		WebElement specificCitySuggestion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//				"//ul[starts-with(@id, 'ui-id')]/li/a[normalize-space()='" + cityinput.getAttribute("value") + "']")));
//		specificCitySuggestion.click();
	}

	public WebElement getPopularCarList() {
		return popularCarList;
	}

	public List<String> selectPopularCars() {
		List<String> popularCarList = new ArrayList<>();
		if (popularCars.isEmpty()) {
			System.out.println("No label elements found within the specified div.");
		} else {
			for (WebElement car : popularCars) {
				String text = car.getText().trim(); // .trim() to remove leading/trailing whitespace
				if (!text.isEmpty()) { // Only add non-empty text
					popularCarList.add(text);
				}
			}
		}
		return popularCarList;

	}

}
