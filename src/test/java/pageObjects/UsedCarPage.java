package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UsedCarPage extends BasePage {

	public UsedCarPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"gs_input5\"]")
	WebElement cityinput;

	@FindBy(xpath = "//*[@id=\"ui-id-5\"]/li/a")
	WebElement city;

	@FindBy(xpath = "//*[@id=\"mmvLi_22_317\"]/label")
	WebElement Maruti800;

	@FindBy(xpath = "//*[@id=\"mmvLi_22_338\"]/label")
	WebElement MarutiSwift;

	@FindBy(xpath = "//*[@id=\"mmvLi_10_146\"]/label")
	WebElement HyundaiI10;

	@FindBy(xpath = "//*[@id=\"mmvLi_10_156\"]/label")
	WebElement HyundaiSantroXing;

	@FindBy(xpath = "//*[@id=\"mmvLi_8_125\"]/label")
	WebElement HondaCity;

	@FindBy(xpath = "//*[@id=\"mmvLi_21_314\"]/label")
	WebElement MahindraXUV500;

	@FindBy(xpath = "//*[@id=\"mmvLi_13_205\"]/label")
	WebElement ToyotaFortuner;

	@FindBy(xpath = "//*[@id=\"mmvLi_13_207\"]/label")
	WebElement ToyotaInnova;

	public void selectCity(String cityinfo) {
		cityinput.sendKeys(cityinfo);
	}

	public void enterCity() {
		city.click();
	}

	public List<String> selectPopularCars() {

		List<String> PopularcarList = new ArrayList<String>();

		PopularcarList.add(Maruti800.getText());
		PopularcarList.add(MarutiSwift.getText());
		PopularcarList.add(HyundaiI10.getText());
		PopularcarList.add(HyundaiSantroXing.getText());
		PopularcarList.add(HondaCity.getText());
		PopularcarList.add(ToyotaInnova.getText());
		PopularcarList.add(ToyotaFortuner.getText());
		PopularcarList.add(MahindraXUV500.getText());

		return PopularcarList;

	}

}
