package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.HomePage;
import pageObjects.UsedCarPage;
import testBase.BaseClass;

public class TS_UsedCar extends BaseClass {

	HomePage home;
	UsedCarPage ucp;

	@BeforeMethod
	public void navigateToUsedCarPage() {
		logger.info("Navigating to used car page");
		home = new HomePage(driver);
		home.hoverMore();
		home.clickUsedCars();
	}

	@Test
	public void Test_PopularCarModels() {

		logger.info("starting to test popular car models");
		try {
			ucp = new UsedCarPage(driver);
			ucp.selectCity("Chennai");
			ucp.enterCity();
			List<String> carList = ucp.selectPopularCars();
			logger.info("Popular car models : " + carList);
			System.out.println(carList);
			Thread.sleep(4000);
//			String usedcarpagePath = BaseClass.takeScreenShot("usedcarpage");
			String usedcarpagePath = BaseClass.takeSpecificScreenShot("usedcarpage",ucp.getUsedCarImageContainer());
			logger.info(usedcarpagePath);

		} catch (Exception e) {
			logger.error("Test failed : " + e);
			logger.debug("Debug logs : ");
			Assert.fail();
		}

	}

	@AfterMethod
	public void navigateToHomePage() {
		logger.info("closing usedCarPage");
	}

}
