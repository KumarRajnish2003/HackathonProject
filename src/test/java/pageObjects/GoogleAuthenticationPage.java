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

	@FindBy(id="identifierId")
	WebElement EmailorPhone;

	@FindBy(xpath = "//*[@id=\"identifierNext\"]/div/button/span")
	WebElement next;

	@FindBy(xpath = "//*[@id=\"report_submit_close_login\"]")
	WebElement close;

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

	public void clickOnNext() {
		next.click();
	}

	public void closePopUp() {
		close.click();
	}

}





//*[@id="identifierId"]
//@FindBy(xpath="//*[@id=\"yDmH0d\"]/div[1]/div[1]/div[2]/div/div/div[2]/div/div/div[1]/form/span/section/div/div/div/div/ul/li[2]/div")
//WebElement anotherAccount;

//@FindBy(xpath="//*[@id=\"password\"]/div[1]/div/div[1]/input")
//WebElement password;

//public void clickOnAnotherAccount() {
//	anotherAccount.click();
//}

//public void sendPassword(String pass) {
//password.sendKeys(pass);
//}