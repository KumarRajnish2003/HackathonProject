package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
	@FindBy(xpath="//div[@class='gsc_thin_scroll']/ul/li/label")
	List<WebElement> popularCars;
	
	public void selectCity(String cityinfo) {
		wait.until(ExpectedConditions.elementToBeClickable(cityinput));
		cityinput.sendKeys(cityinfo);
	}

	public void enterCity() {
		wait.until(ExpectedConditions.visibilityOf(city));
		city.click();
	}
	
	public WebElement getPopularCarList() {
		wait.until(ExpectedConditions.visibilityOf(popularCarList));
		return popularCarList;
	}

	public List<String> selectPopularCars() {
		List<String> popularCarList = new ArrayList<>();
		wait.until(ExpectedConditions.visibilityOfAllElements(popularCars));
		
		if (popularCars.isEmpty()) {
            System.out.println("No label elements found within the specified div.");
        } else {
            for (WebElement car : popularCars) {
                String text = car.getText().trim();
                if (!text.isEmpty()) {
                    popularCarList.add(text);
                }
            }
        }
		return popularCarList;

	}

}
