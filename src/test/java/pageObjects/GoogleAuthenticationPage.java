package pageObjects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleAuthenticationPage extends BasePage {

	public GoogleAuthenticationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"myModal3-modal-content\"]/div[1]/div/div[3]/div[6]/div")
	WebElement googleLogin;

	@FindBy(xpath = "//*[@id=\"identifierId\"]")
	WebElement EmailorPhone;

	@FindBy(xpath = "//*[@id=\"identifierNext\"]/div/button/span")
	WebElement next;

	@FindBy(xpath = "//*[@id=\"report_submit_close_login\"]")
	WebElement close;

	@FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
	WebElement password;

	@FindBy(xpath = "//*[@id=\"passwordNext\"]/div/button/span") //// *[@id="identifierNext"]/div/button/span
	WebElement next2;

	public void clickOnGoogleLogin() {

		wait.until(ExpectedConditions.elementToBeClickable(googleLogin));
		googleLogin.click();

	}

	public void sendEmailorPhone(String data) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(EmailorPhone));
			EmailorPhone.sendKeys(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void sendPassword(String pass) {
		try {
			password.sendKeys(pass);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void clickOnNext() {
		wait.until(ExpectedConditions.elementToBeClickable(next));
		next.click();
	}

	public void clickOnNext2() {
		wait.until(ExpectedConditions.elementToBeClickable(next2));
		next2.click();
	}

	public void closePopUp() {
		wait.until(ExpectedConditions.elementToBeClickable(close));
		close.click();
	}

}
