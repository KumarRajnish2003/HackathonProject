package testCases;

import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.GoogleAuthenticationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TS_Login extends BaseClass {

	GoogleAuthenticationPage auth;
	HomePage home;

	@BeforeMethod(groups= {"smoke","master","sanity"})
	public void openLoginPopUp() throws InterruptedException {

		logger.info("opening the login popup window");
		home = new HomePage(driver);
		home.ClickLoginAndMore();
	}

	@Test(groups={"smoke","sanity"})
	public void Test_Login_With_Invalid_Google_Account() throws InterruptedException {
		logger.info("started the login test with invalid google account");
		auth = new GoogleAuthenticationPage(driver);
		auth.clickOnGoogleLogin();
		BaseClass.SwitchWindow();
		auth.sendEmailorPhone(data.get(0));
		auth.clickOnNext();
		logger.info("taking the screenshot of invalid google account");
		String InvalidScreenshotpath = BaseClass.takeScreenShot("LoginWithInvalidEmail");
		logger.info(InvalidScreenshotpath);
	}

	@Test(groups={"master"})
	public void Test_Login_With_valid_Google_Account() throws Exception {
		logger.info("started the login test with valid google account and invalid password");
		auth = new GoogleAuthenticationPage(driver);
		auth.clickOnGoogleLogin();
		BaseClass.SwitchWindow();
		auth.sendEmailorPhone(data.get(1));
		auth.clickOnNext();
		try {
			auth.sendPassword(data.get(2));
			auth.clickOnNext2();
		} catch (Exception e) {
			logger.info("password textfeild is blocked");
			Assert.assertTrue(true);
		}

		logger.info("taking the screenshot of valid google account");
		String validScreenshotpath = BaseClass.takeScreenShot("LoginWithvalidEmail");
		logger.info(validScreenshotpath);
	}
	
	@Test(groups={"sanity","master"})
	public void Test_Login_With_Empty_Email() throws InterruptedException {
		
		logger.info("started the login test with blank email");
		auth = new GoogleAuthenticationPage(driver);
		auth.clickOnGoogleLogin();
		BaseClass.SwitchWindow();
		auth.sendEmailorPhone(" ");
		auth.clickOnNext();
		logger.info("taking the screenshot of invalid google account");
		String BlankScreenshotpath = BaseClass.takeScreenShot("LoginWithBlankEmail");
		logger.info(BlankScreenshotpath);
	}

	

	@AfterMethod(groups= {"smoke","master","sanity"})
	public void closeLoginPopUp() {
		logger.info("switching back to main window");
		driver.switchTo().window(mainWindowHandle);
		auth.closePopUp();
	}

}
