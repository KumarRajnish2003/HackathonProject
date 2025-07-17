package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleAuthenticationPage extends BasePage {

	public GoogleAuthenticationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id=\"myModal3-modal-content\"]/div[1]/div/div[3]/div[6]/div")
	WebElement googleLogin;

	@FindBy(xpath="//*[@id=\"identifierId\"]")
	WebElement EmailorPhone;

	@FindBy(xpath = "//*[@id=\"identifierNext\"]/div/button/span")
	WebElement next;
	
	@FindBy(xpath = "//*[@id=\"report_submit_close_login\"]")
	WebElement close;

	@FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
	WebElement password;
	
	@FindBy(xpath = "//*[@id=\"passwordNext\"]/div/button/span") 
	WebElement next2;

	public void clickOnGoogleLogin() throws InterruptedException{
		Thread.sleep(2000);
		googleLogin.click();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); // Wait up to 10 seconds

        // Using ExpectedConditions.elementToBeClickable() with the WebElement itself
//        wait.until(ExpectedConditions.elementToBeClickable(googleLogin));
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();", googleLogin);
//        googleLogin.click();
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
			Thread.sleep(2000);
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
	
	public void closeGoogleWindow() {
		driver.close();
	}

}
