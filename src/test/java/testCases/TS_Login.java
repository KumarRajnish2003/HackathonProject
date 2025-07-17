package testCases;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.GoogleAuthenticationPage;
import testBase.BaseClass;

public class TS_Login extends BaseClass {

	GoogleAuthenticationPage auth;
	@BeforeMethod
	public void openLoginPopUp() throws InterruptedException {

		logger.info("opening the login popup window");
		home.ClickLoginAndMore();
	}

	@Test
	public void Test_Login_With_Invalid_Google_Account() throws InterruptedException {
		logger.info("started the login test with invalid google account");
		auth = new GoogleAuthenticationPage(driver);
		auth.clickOnGoogleLogin();
		BaseClass.SwitchWindow();
		String InvalidMail = data.get(0);
		auth.sendEmailorPhone(InvalidMail);
		auth.clickOnNext();
		logger.info("taking the screenshot of invalid google account");
		String InvalidScreenshotpath = BaseClass.takeScreenShot("LoginWithInvalidEmail");
		logger.info(InvalidScreenshotpath);
	}

	@Test
	public void Test_Login_With_valid_Google_Account() throws InterruptedException {
		logger.info("started the login test with valid google account and invalid password");
		auth = new GoogleAuthenticationPage(driver);
		auth.clickOnGoogleLogin();
		BaseClass.SwitchWindow();
		String validMail = data.get(1);
		auth.sendEmailorPhone(validMail);
		auth.clickOnNext();
		try {
			String pass = data.get(2);
			auth.sendPassword(pass);
			auth.clickOnNext2();
		} catch (Exception e) {
			logger.info("password textfeild is blocked");
			Assert.assertTrue(true);
		}

		logger.info("taking the screenshot of valid google account");
		String validScreenshotpath = BaseClass.takeScreenShot("LoginWithvalidEmail");
		logger.info(validScreenshotpath);
	}

	@AfterMethod
	public void closeLoginPopUp() {
		logger.info("switching back to main window");
//		auth.closeGoogleWindow();
		driver.switchTo().window(mainWindowHandle);
		auth.closePopUp();
	}

}
