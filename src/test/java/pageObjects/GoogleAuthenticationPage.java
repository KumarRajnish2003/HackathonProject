package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleAuthenticationPage extends BasePage {

	public GoogleAuthenticationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"myModal3-modal-content\"]/div[1]/div/div[3]/div[6]/div/span[2]")
	WebElement googleLogin;

	@FindBy(id = "identifierId")
	WebElement EmailorPhone;

	@FindBy(xpath = "//*[@id=\"identifierNext\"]/div/button/span") ////*[@id="passwordNext"]/div/button/span
	WebElement next;
	
	@FindBy(xpath = "//*[@id=\"report_submit_close_login\"]")
	WebElement close;

	@FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
	WebElement password;
	
	@FindBy(xpath = "//*[@id=\"passwordNext\"]/div/button/span") 
	WebElement next2;

	public void clickOnGoogleLogin() {
		googleLogin.click();

	}

	public void sendEmailorPhone(String data) {
		try {
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
		next.click();
	}
	
	public void clickOnNext2() {
		next2.click();
	}

	public void closePopUp() {
		close.click();
	}

}
