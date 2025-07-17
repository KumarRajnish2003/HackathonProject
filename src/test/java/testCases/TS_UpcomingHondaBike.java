package testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.HondaUpcomingBikePage;
import pageObjects.UpcomingBikePage;
import testBase.BaseClass;

public class TS_UpcomingHondaBike extends BaseClass {
	HomePage home;
	UpcomingBikePage Ubike;
	HondaUpcomingBikePage HUbike;
	WebDriverWait wait;

	@BeforeMethod
	public void goToUpcomingHondaBikes() {
		logger.info("Clicking on Upcoming Bikes");
		home = new HomePage(driver);
		home.clickonUpcomingBike();
		logger.info("Clicking on all upcoming bikes");
		home.clickonAllUpcomingBike();
		logger.info("Clicking on Honda in upcoming bikes page");
		Ubike = new UpcomingBikePage(driver);
		Ubike.clickOnHonda();
	}

	@Test
	public void get_Honda_Upcoming_Bikes_Under_4lac() throws InterruptedException {
		logger.info("Starting to test Honda upcoming bikes under 4lac");
		HUbike = new HondaUpcomingBikePage(driver);
		List<String> bike = HUbike.getTitle();
		List<String> price = HUbike.getPrice();
		List<String> expLaunch = HUbike.getExpLaunch();
		
		logger.info("Upcomming Honda Bikes under 4lac are");
		System.out.println("Upcomming Honda Bikes under 4lac are:");
		
		for (int i = 0; i < bike.size(); i++) {
			logger.info("[ " + bike.get(i) + " , " + price.get(i) + " , " + expLaunch.get(i) + " ]");
			System.out.println("[ " + bike.get(i) + " , " + price.get(i) + " , " + expLaunch.get(i) + " ]");
		}
		WebElement upcoming=HUbike.getUpcomingHondaBikeSS();
		Thread.sleep(1000);
		String hondaupcomingbikes = BaseClass.takeSpecificScreenShot("Honda_upcoming",upcoming);
		logger.info(hondaupcomingbikes);

	}

	@AfterMethod
	public void navigateToHomePage() {
		logger.info("Navigating back to homepage");
		String HomePageUrl = props.getProperty("HomePageUrl");
		driver.navigate().to(HomePageUrl);
		
	}

}