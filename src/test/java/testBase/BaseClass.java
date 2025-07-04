package testBase;

import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;

public class BaseClass {

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
}
