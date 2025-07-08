package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.UsedCarPage;
import testBase.BaseClass;

public class TS_UsedCar extends BaseClass {

	@BeforeMethod
	public void navigateToUsedCarPage() {
		logger.info("Navigating to used car page");
		driver.navigate().to("https://www.zigwheels.com/used-car");

	}

	@Test
	public void Test_PopularCarModels() {

		logger.info("starting to test popular car models");
		try {
			UsedCarPage ucp = new UsedCarPage(driver);
			ucp.selectCity("Chennai");
			ucp.enterCity();
			List<String> carList = ucp.selectPopularCars();
			logger.info("popular car models : " + carList);
			Thread.sleep(4000);
			BaseClass.takeScreenShot(driver, System.getProperty("user.dir") + "//screenshots//usedcarpage.png");
		} catch (Exception e) {
			logger.error("Test failed : " + e);
			logger.debug("Debug logs : ");
			Assert.fail();
		}

	}

	@AfterMethod
	public void navigateToHomePage() {
		logger.info("Navigating back to home page");
		driver.navigate().back();
	}

}
