package testCases;

import java.util.List;

import org.testng.annotations.*;

import pageObjects.UsedCarPage;
import testBase.BaseClass;

public class TS_UsedCar extends BaseClass {

	@BeforeMethod
	public void navigateToUsedCarPage() {
		driver.navigate().to("https://www.zigwheels.com/used-car");
	}
	
	@Test
	public void Test_PopularCarModels() {
		
		UsedCarPage ucp=new UsedCarPage(driver);
		ucp.selectCity("Chennai");
		ucp.enterCity();
		List<String> carList=ucp.selectPopularCars();
		for(String car:carList) {
			System.out.println(car);
		}
		
	}
	
	@AfterMethod
	public void navigateToHomePage() {
		driver.navigate().back();
	}
	
	
}
