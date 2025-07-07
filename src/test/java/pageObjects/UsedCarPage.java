package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UsedCarPage extends BasePage{

	public UsedCarPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//*[@id=\"gs_input5\"]")
	WebElement cityinput;
	
	@FindBy(xpath="//*[@id=\"ui-id-5\"]/li/a")
	WebElement city;
	
	
	
	public void selectCity(String cityinfo) {
		cityinput.sendKeys(cityinfo);
	}
	
	public void enterCity() {
		cityinput.click();
	}
	
	
	
	
}
