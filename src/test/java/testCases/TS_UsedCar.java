package testCases;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.UsedCarPage;
import testBase.BaseClass;

public class TS_UsedCar extends BaseClass {

	UsedCarPage ucp;

	@BeforeMethod
	public void navigateToUsedCarPage() {
		try {
			logger.info("Attempting to hover over 'More' menu.");
			home.hoverMore();
			logger.info("'More' menu hovered successfully.");
			logger.info("Attempting to click 'Used Cars' link.");
			home.clickUsedCars();
			logger.info("'Used Cars' link clicked successfully. Navigated to Used Car page.");
			ucp = new UsedCarPage(driver); 
		} catch (Exception e) {
			logger.error("An unexpected error occurred during navigation to Used Car page: " + e.getMessage(), e);
			Assert.fail("Unexpected error during navigation to Used Car page: " + e.getMessage(), e);
		}
	}

	@Test(priority = 1)
	public void Test_PopularCarModels_Chennai() {
		logger.info("Starting Test_PopularCarModels_Chennai test...");
		try {
			String city = data.get(3);
			logger.info("Attempting to select city: "+city);
			Thread.sleep(2000);
			ucp.selectCity(city);
			logger.info("City '" + city + "' selected.");

			logger.info("Attempting to enter city (confirm selection).");
			ucp.enterCity();
			logger.info("City entered/confirmed.");
			logger.info("Retrieving list of popular car models.");
			List<String> carList = ucp.selectPopularCars();
			logger.info("Popular car models retrieved successfully. Count: " + carList.size());
			System.out.println("\n--- Popular car Models in " + city + " ---");
			System.out.println(carList);
			Assert.assertFalse(carList.isEmpty(), "Popular car models list should not be empty for " + city + ".");
			logger.info("Assertion passed: Popular car models list for " + city + " is not empty.");

			logger.info("Attempting to take screenshot of popular car models section.");
			Thread.sleep(2000); // Wait for rendering
			String usedcarpagePath = BaseClass.takeSpecificScreenShot("PopularUsedCarModels_" + city, ucp.getPopularCarList());
			if (usedcarpagePath != null) {
				logger.info("Screenshot taken and saved at: " + usedcarpagePath);
			} else {
				logger.warn("Failed to take specific screenshot for 'PopularUsedCarModels_" + city + "'.");
			}

			logger.info("Test_PopularCarModels_Chennai completed successfully.");
		} catch (Exception e) {
			logger.error("TEST FAILED - An unexpected error occurred: " + e.getMessage(), e);
			Assert.fail("Test_PopularCarModels_Chennai failed due to unexpected error: " + e.getMessage());
		}
	}

	@AfterMethod
	public void navigateToHomePage() {
		logger.info("Execution for current test method finished.");
	}

}
