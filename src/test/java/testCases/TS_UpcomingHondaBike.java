package testCases;

import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HondaUpcomingBikePage;
import pageObjects.UpcomingBikePage;
import testBase.BaseClass;

public class TS_UpcomingHondaBike extends BaseClass {
	UpcomingBikePage Ubike;
	HondaUpcomingBikePage HUbike;

	@BeforeMethod
	public void goToUpcomingHondaBikes() {
		logger.info("Clicking on Upcoming Bikes");
		home.clickonUpcomingBike();
		logger.info("Clicking on all upcoming bikes");
		home.clickonAllUpcomingBike();
		String Bike = data.get(4);
		logger.info("Clicking on " + Bike + " in upcoming bikes page");
		Ubike = new UpcomingBikePage(driver);
		Ubike.clickOnHonda();
	}

	@Test
	public void get_Honda_Upcoming_Bikes_Under_4lac() {
		logger.info("Starting to test Honda upcoming bikes under 4 lac");
		HUbike = new HondaUpcomingBikePage(driver);
		List<String> bike = HUbike.getTitle();
		List<String> price = HUbike.getPrice();
		List<String> expLaunch = HUbike.getExpLaunch();
		String ExpectedPrice = data.get(5);
		logger.info("Upcomming Honda Bikes under " + ExpectedPrice + " are");
		for (int i = 0; i < bike.size(); i++) {
			logger.info("[ " + bike.get(i) + " , " + price.get(i) + " , " + expLaunch.get(i) + " ]");
			System.out.println("[ " + bike.get(i) + " , " + price.get(i) + " , " + expLaunch.get(i) + " ]");
		}

	}

	@AfterMethod
	public void navigateToHomePage() {
		logger.info("Navigating back to homepage");
		driver.navigate().back();
		driver.navigate().back();
	}

}