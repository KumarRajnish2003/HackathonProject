package testCases;


import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.GoogleAuthenticationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TS_Login extends BaseClass {

	GoogleAuthenticationPage auth;
	HomePage home;

	@BeforeMethod
	public void openLoginPopUp() {

		logger.info("opening the login popup window");
		home = new HomePage(driver);
		home.ClickLoginAndMore();
	}

	@Test
	public void Test_Login_With_Invalid_Google_Account() throws InterruptedException {
		logger.info("started the login test with invalid google account");
		auth = new GoogleAuthenticationPage(driver);
		auth.clickOnGoogleLogin();
		BaseClass.SwitchWindow();
		auth.sendEmailorPhone("xyz@gmail.com");
		auth.clickOnNext();
		logger.info("taking the screenshot of invalid google account");
		String InvalidScreenshotpath = BaseClass.takeScreenShot("LoginWithInvalidEmail");
		logger.info(InvalidScreenshotpath);
	}

	@Test
	public void Test_Login_With_valid_Google_Account() {
		logger.info("started the login test with valid google account and invalid password");
		auth = new GoogleAuthenticationPage(driver);
		auth.clickOnGoogleLogin();
		BaseClass.SwitchWindow();
		auth.sendEmailorPhone("merajnish1999@gmail.com");
		auth.clickOnNext();
		try {
			auth.sendPassword("12345abc");
			auth.clickOnNext2();
		} catch (Exception e) {
			logger.info("password textfeild is blocked");
			Assert.assertTrue(true);
		}

		logger.info("taking the screenshot of invalid google account");
		String validScreenshotpath = BaseClass.takeScreenShot("LoginWithvalidEmail");
		logger.info(validScreenshotpath);
	}

	@AfterMethod
	public void closeLoginPopUp() {
		logger.info("switching back to main window");
		BaseClass.SwitchWindow();
		auth.closePopUp();
		
	}

}
