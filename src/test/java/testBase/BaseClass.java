package testBase;

import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.time.Duration;

public class BaseClass {

	public WebDriver driver;

	public static void SwitchWindow(WebDriver driver) {
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String handle : allWindowHandles) {
			if (!handle.equals(mainWindowHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}

	}

	public static void takeScreenShot(WebDriver driver, String dest) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File target = new File(dest);
		src.renameTo(target);
	}
	
	

	@BeforeClass
	public void setup() {

		driver = new ChromeDriver();
		driver.get("https://www.zigwheels.com/");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
