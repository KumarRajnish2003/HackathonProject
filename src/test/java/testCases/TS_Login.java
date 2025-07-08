package testCases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import pageObjects.GoogleAuthenticationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TS_Login extends BaseClass {

	WebDriverWait wait;
	GoogleAuthenticationPage auth;
	HomePage home;

	@BeforeMethod()
	public void openLoginPopUp() {

		home = new HomePage(driver);
		home.ClickLoginAndMore();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"report_submit_close_login\"]")));

	}

	@Test
	public void Test_Login() {
		auth = new GoogleAuthenticationPage(driver);
		auth.clickOnGoogleLogin();
		BaseClass.SwitchWindow(driver);
		auth.sendEmailorPhone("abc@gmail.com");
		auth.clickOnNext();
		BaseClass.takeScreenShot(driver, System.getProperty("user.dir") + "//screenshots//screenshot.png");
	}

	@AfterMethod
	public void closeLoginPopUp() {
		BaseClass.SwitchWindow(driver);
		auth.closePopUp();
	}

}
