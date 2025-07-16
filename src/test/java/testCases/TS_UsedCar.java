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

	@BeforeMethod
	public void navigateToUsedCarPage() {
		try {
			// Initialize HomePage using the thread-local driver
			home = new HomePage(driver);
			logger.info("Attempting to hover over 'More' menu.");
			home.hoverMore();
			logger.info("'More' menu hovered successfully.");
			logger.info("Attempting to click 'Used Cars' link.");
			home.clickUsedCars();
			logger.info("'Used Cars' link clicked successfully. Navigated to Used Car page.");
		} catch (NoSuchElementException e) {
			logger.error("Element not found during navigation to Used Car page: " + e.getMessage());
			// Use Assert.fail() to mark the @BeforeMethod as failed, preventing @Test from running
			Assert.fail("Failed to navigate to Used Car page: Element not found. " + e.getMessage(), e);
		} catch (TimeoutException e) {
			logger.error("Timeout during navigation to Used Car page (element not visible/clickable in time): " + e.getMessage());
			Assert.fail("Failed to navigate to Used Car page: Timeout waiting for element. " + e.getMessage(), e);
		} catch (ElementNotInteractableException e) {
			logger.error("Element not interactable during navigation to Used Car page: " + e.getMessage());
			Assert.fail("Failed to navigate to Used Car page: Element not interactable. " + e.getMessage(), e);
		} catch (WebDriverException e) { // Catches other WebDriver-related issues (e.g., browser disconnected)
			logger.error("WebDriver error during navigation to Used Car page: " + e.getMessage());
			Assert.fail("Failed to navigate to Used Car page: WebDriver error. " + e.getMessage(), e);
		} catch (Exception e) { // Catch any other unexpected exceptions
			logger.error("An unexpected error occurred during navigation to Used Car page: " + e.getMessage(), e);
			Assert.fail("Unexpected error during navigation to Used Car page: " + e.getMessage(), e);
		}
	}

	@Test
	public void Test_PopularCarModels() {

		logger.info("Starting Test_PopularCarModels test...");
		try {
			ucp = new UsedCarPage(driver);

			logger.info("Attempting to select city: Chennai");
			String city = props.getProperty("City");
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
			Assert.assertFalse(carList.isEmpty(), "Popular car models list should not be empty.");
			logger.info("Assertion passed: Popular car models list is not empty.");

			// Take screenshot of the specific element
			logger.info("Attempting to take screenshot of used car image container.");
			Thread.sleep(2000);
			String usedcarpagePath = BaseClass.takeSpecificScreenShot("usedcarpage", ucp.getPopularCarList());
			if (usedcarpagePath != null) {
				logger.info("Screenshot taken and saved at: " + usedcarpagePath);
			} else {
				logger.warn("Failed to take specific screenshot for 'usedcarpage'.");
			}

			logger.info("Test_PopularCarModels completed successfully.");

		} catch (NoSuchElementException e) {
			logger.error("TEST FAILED - Element not found: " + e.getMessage(), e);
			Assert.fail("Test_PopularCarModels failed: Element not found. " + e.getMessage());
		} catch (TimeoutException e) {
			logger.error("TEST FAILED - Timeout waiting for element: " + e.getMessage(), e);
			Assert.fail("Test_PopularCarModels failed: Timeout waiting for element. " + e.getMessage());
		} catch (ElementNotInteractableException e) {
			logger.error("TEST FAILED - Element not interactable: " + e.getMessage(), e);
			Assert.fail("Test_PopularCarModels failed: Element not interactable. " + e.getMessage());
		} catch (WebDriverException e) {
			logger.error("TEST FAILED - WebDriver issue: " + e.getMessage(), e);
			Assert.fail("Test_PopularCarModels failed: WebDriver error. " + e.getMessage());
		} catch (AssertionError e) { // Catch explicit TestNG assertions
			logger.error("TEST FAILED - Assertion failed: " + e.getMessage(), e);
			throw e; // Re-throw the assertion error so TestNG marks it as failed
		} catch (Exception e) { // Catch any other unexpected exceptions
			logger.error("TEST FAILED - An unexpected error occurred: " + e.getMessage(), e);
			Assert.fail("Test_PopularCarModels failed due to unexpected error: " + e.getMessage());
		}
	}

	@AfterMethod
	public void navigateToHomePage() {
		logger.info("Execution for current test method finished.");
	}

}
