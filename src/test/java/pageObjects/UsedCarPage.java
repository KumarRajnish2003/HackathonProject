package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

	@FindBy(xpath = "//div[@class='gsc_thin_scroll']/ul/li/label")
	List <WebElement> popularmod;


	@FindBy(xpath = "//div[@class='gsc_thin_scroll']/ul")
	WebElement UsedCarImages;
	
	public void selectCity(String cityinfo) {
		cityinput.sendKeys(cityinfo);
	}

	public void enterCity() {
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(city));
		button.click();
		
	}
	
	public WebElement getUsedCarImageContainer() {
		
		return UsedCarImages;
	}

	public List<String> selectPopularCars() {
		
		
		List<String> PopularcarList = new ArrayList<String>();
		for(WebElement web:popularmod) {
			PopularcarList.add(web.getText());
		}

		return PopularcarList;

	}

}
