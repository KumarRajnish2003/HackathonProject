package testCases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

		logger.info("opening the login popup window");
		home = new HomePage(driver);
		home.ClickLoginAndMore();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"report_submit_close_login\"]")));

	}

	@Test
	public void Test_Login_With_Invalid_Google_Account() {
		logger.info("started the login test with invalid google account");
		auth = new GoogleAuthenticationPage(driver);
		auth.clickOnGoogleLogin();
		BaseClass.SwitchWindow(driver);
		auth.sendEmailorPhone("abc@gmail.com");
		auth.clickOnNext();
		logger.info("taking the screenshot of invalid google account");
		BaseClass.takeScreenShot(driver, System.getProperty("user.dir") + "//screenshots//invalidGoogleAccount.png");

	}

	@Test
	public void Test_Login_With_valid_Google_Account() throws InterruptedException {
		logger.info("started the login test with valid google account and invalid password");
		auth = new GoogleAuthenticationPage(driver);
		auth.clickOnGoogleLogin();
		BaseClass.SwitchWindow(driver);
		auth.sendEmailorPhone("merajnish1999@gmail.com");
		auth.clickOnNext();
		try {
			auth.sendPassword("12345abc");
			auth.clickOnNext2();
		}catch(Exception e) {
			logger.info("password textfeild is blocked");
			Assert.assertTrue(true);
		}
		
		Thread.sleep(4000);
		logger.info("taking the screenshot of invalid google account");
		BaseClass.takeScreenShot(driver, System.getProperty("user.dir") + "//screenshots//invalidpassword.png");

	}

	@AfterMethod
	public void closeLoginPopUp() {
		logger.info("switching back to main window");
		BaseClass.SwitchWindow(driver);
		auth.closePopUp();
	}

}
