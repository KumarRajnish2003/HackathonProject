package testCases;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.UsedCarPage;
import testBase.BaseClass;

public class TS_UsedCar extends BaseClass {

	HomePage home;
	UsedCarPage ucp;

	@BeforeMethod(groups= {"smoke","master"})
	public void navigateToUsedCarPage() {
		try {

			home = new HomePage(driver);
			logger.info("Attempting to hover over 'More' menu.");
			home.hoverMore();
			logger.info("'More' menu hovered successfully.");
			logger.info("Attempting to click 'Used Cars' link.");
			home.clickUsedCars();
			logger.info("'Used Cars' link clicked successfully. Navigated to Used Car page.");

		} catch (Exception e) {
			logger.error("An unexpected error occurred during navigation to Used Car page: " + e.getMessage(), e);
			Assert.fail("Unexpected error during navigation to Used Car page: " + e.getMessage(), e);
		}
	}

	@Test(groups= {"smoke","master"})
	public void Test_PopularCarModels() {

		logger.info("Starting Test_PopularCarModels test...");
		try {
			ucp = new UsedCarPage(driver);

			logger.info("Attempting to select city: Chennai");
			String city = data.get(3);
			ucp.selectCity(city);
			logger.info("City 'Chennai' selected.");

			logger.info("Attempting to enter city (confirm selection).");
			ucp.enterCity();
			logger.info("City entered/confirmed.");

			logger.info("Retrieving list of popular car models.");

			List<String> carList = ucp.selectPopularCars();

			logger.info("Popular car models retrieved successfully. Count: " + carList.size());
			System.out.println("Popular car Models : ");
			System.out.println(carList);
			logger.info("Popular car Models: ", carList);

			Assert.assertFalse(carList.isEmpty(), "Popular car models list should not be empty.");
			logger.info("Assertion passed: Popular car models list is not empty.");
			Thread.sleep(2000);
			logger.info("Attempting to take screenshot of used car image container.");

			String usedcarpagePath = BaseClass.takeSpecificScreenShot("usedcarpage", ucp.getPopularCarList());
			if (usedcarpagePath != null) {
				logger.info("Screenshot taken and saved at: " + usedcarpagePath);
			} else {
				logger.warn("Failed to take specific screenshot for 'usedcarpage'.");
			}

			logger.info("Test_PopularCarModels completed successfully.");

		} catch (Exception e) {
			logger.error("TEST FAILED - An unexpected error occurred: " + e.getMessage(), e);
			Assert.fail("Test_PopularCarModels failed due to unexpected error: " + e.getMessage());
		}
	}

	@AfterMethod(groups= {"smoke","master"})
	public void navigateToHomePage() {
		logger.info("Execution for current test method finished.");
		driver.navigate().back();
	}

}
