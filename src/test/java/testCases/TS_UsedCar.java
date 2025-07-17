package testCases;

import java.io.IOException;
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
	public void navigateToUsedCarPage() throws IOException {
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
			
			List<String> data=getData();
			ucp.selectCity(data.get(3));
			ucp.enterCity();
			List<String> carList = ucp.selectPopularCars();
			logger.info("Popular Usedcar models in "+data.get(3)+" are: " + carList);
			System.out.println("The popular used car models in "+data.get(3)+" are:");
			System.out.println(carList);
			Thread.sleep(2000);
			String usedcarpagePath = BaseClass.takeSpecificScreenShot("popularmodels",ucp.getUsedCarImageContainer());
			logger.info(usedcarpagePath);

		} catch (Exception e) {
			logger.error("Test failed : " + e);
			logger.debug("Debug logs : ");
			Assert.fail();
		}

	}

	@AfterMethod
	public void navigateToHomePage() {
		String HomePageUrl = props.getProperty("HomePageUrl");
		driver.navigate().to(HomePageUrl);
		logger.info("closing usedCarPage");
	}

}
