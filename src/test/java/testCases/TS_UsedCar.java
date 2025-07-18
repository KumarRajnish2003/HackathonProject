package testCases;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.TimeoutException;
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
	
	@Test(priority = 2)
	public void Test_VerifyUsedCarPageTitleAndURL() {
		logger.info("Starting Test_VerifyUsedCarPageTitleAndURL test...");
		try {
			
			String expectedTitle = "Used Cars for Sale, Buy Second Hand Cars in India @ Zigwheels";
			String actualTitle = driver.getTitle();
			logger.info("Expected Page Title: '" + expectedTitle + "', Actual Page Title: '" + actualTitle + "'");
			Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match for Used Cars page.");

			String expectedURLContains = "/used-car";
			String actualURL = driver.getCurrentUrl();
			logger.info("Expected URL to contain: '" + expectedURLContains + "', Actual URL: '" + actualURL + "'");
			Assert.assertTrue(actualURL.contains(expectedURLContains), "Page URL does not contain expected string for Used Cars page.");

			logger.info("Test_VerifyUsedCarPageTitleAndURL completed successfully.");
		} catch (AssertionError e) {
			logger.error("TEST FAILED - Assertion failed for page title/URL: " + e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("TEST FAILED - An unexpected error occurred during title/URL verification: " + e.getMessage(), e);
			Assert.fail("Test_VerifyUsedCarPageTitleAndURL failed due to unexpected error: " + e.getMessage());
		}
	}
	
	@Test(priority = 3)
	public void Test_InvalidCitySelection() {
		logger.info("Starting Test_InvalidCitySelection test...");
		String invalidCity = "NonExistentCity123";
		try {
			logger.info("Attempting to select an invalid city: " + invalidCity);
			ucp.selectCity(invalidCity); 
			List<String> carList = ucp.selectPopularCars();
			logger.info("Popular car models retrieved after invalid city attempt. Count: " + carList.size());
			Assert.assertTrue(carList.isEmpty() || carList.get(0).equals("No cars found") , "Popular car models list should be empty or show specific message after invalid city selection.");
			logger.info("Assertion passed: Popular car models list is empty or shows expected message for invalid city.");

			logger.info("Test_InvalidCitySelection completed successfully.");
		} catch (NoSuchElementException | TimeoutException | ElementNotInteractableException e) {
			logger.info("Expected error for invalid city selection (e.g., element not found/interactable): " + e.getMessage());
			Assert.assertTrue(true, "Test passed as expected: Invalid city selection prevented successful interaction or returned no results.");
		} catch (AssertionError e) {
			logger.error("TEST FAILED - Assertion failed for invalid city: " + e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			logger.error("TEST FAILED - An unexpected error occurred during invalid city selection: " + e.getMessage(), e);
			Assert.fail("Test_InvalidCitySelection failed due to unexpected error: " + e.getMessage());
		}
	}
	
	@AfterMethod
	public void navigateToHomePage() {
	    logger.info("--- Starting @AfterMethod: Navigating back to Home Page ---");
	    try {
	        String HomePageUrl = props.getProperty("HomePageUrl"); 
	        if (HomePageUrl != null && !HomePageUrl.isEmpty()) {
	            driver.navigate().to(HomePageUrl);
	            logger.info("Navigated back to Home Page: " + HomePageUrl);
	        } else {
	            logger.error("HomePageUrl property is not configured correctly. Cannot navigate to home page.");
	            Assert.fail("Configuration error: HomePageUrl not found.");
	        }

	 
	    } catch (Exception e) {
	        logger.error("Error navigating back to Home Page in @AfterMethod: " + e.getMessage(), e);
	    }
	    logger.info("--- Finished @AfterMethod ---");
	}

}
