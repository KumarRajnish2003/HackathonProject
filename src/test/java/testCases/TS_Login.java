package testCases;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import pageObjects.GoogleAuthenticationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TS_Login {

	WebDriver driver;
	WebDriverWait wait;
	GoogleAuthenticationPage auth;
	HomePage home;

	@BeforeClass
	public void setup() {

		driver = new ChromeDriver();
		driver.get("https://www.zigwheels.com/");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}

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
		BaseClass.takeScreenShot(driver,System.getProperty("user.dir")+ "//screenshots//screenshot.png");
	}

	@AfterMethod
	public void closeLoginPopUp() {
		BaseClass.SwitchWindow(driver);
		auth.closePopUp();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
