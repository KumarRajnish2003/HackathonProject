package testBase;

import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public static Properties props;
	
	@Parameters({"browser","os"})
	@BeforeClass
	public void setup(@Optional("Chrome") String browser, String os) {

		logger = LogManager.getLogger(this.getClass());
		try {
			props = new Properties();
			FileInputStream file = new FileInputStream("src/test/resources/config.properties");
			props.load(file);
		}catch(Exception e) {
			logger.error("Unable to load config file");
//			Assert.assertTrue(false);
		}
		
		switch(browser.toLowerCase()) {
		case "chrome": driver = new ChromeDriver(); break;
		case "edge": driver = new EdgeDriver(); break;
		default : logger.error("Unable to get browser");return;
		}
		
		String HomePageUrl = props.getProperty("HomePageUrl");
		driver.get(HomePageUrl);
//		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public static void SwitchWindow() {
		String mainWindowHandle = driver.getWindowHandle();
		Set<String> allWindowHandles = driver.getWindowHandles();

		for (String handle : allWindowHandles) {
			if (!handle.equals(mainWindowHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}

	}

	public static String takeScreenShot(String tname) {
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	String targetFilepath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File target = new File(targetFilepath);
		src.renameTo(target);
		return targetFilepath;
	}

}
