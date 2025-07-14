package testBase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public static Properties props;
	public static String mainWindowHandle = null;
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
			Assert.assertTrue(false, "Failed to load config.properties");
		}
		
		switch(browser.toLowerCase()) {
		case "chrome": //driver = new ChromeDriver(); break;
				WebDriverManager.chromedriver().setup(); // Setup ChromeDriver
	            ChromeOptions chromeOptions = new ChromeOptions();
	            // Create a HashMap to store preferences for Chrome
	            Map<String, Object> chromePrefs = new HashMap<>();
	            // Set the preference to allow notifications (1 = allow, 2 = block, 0 = ask)
	            chromePrefs.put("profile.default_content_setting_values.notifications", 1);
	            chromeOptions.setExperimentalOption("prefs", chromePrefs);
	            driver = new ChromeDriver(chromeOptions);
	            logger.info("Chrome browser initialized with notifications allowed.");
	            break;
		 case "edge":
             WebDriverManager.edgedriver().setup(); // Setup EdgeDriver
             EdgeOptions edgeOptions = new EdgeOptions();
             // Create a HashMap to store preferences for Edge
             Map<String, Object> edgePrefs = new HashMap<>();
             // Set the preference to allow notifications (1 = allow, 2 = block, 0 = ask)
             edgePrefs.put("profile.default_content_setting_values.notifications", 1);
             edgeOptions.setExperimentalOption("prefs", edgePrefs);
             driver = new EdgeDriver(edgeOptions);
             logger.info("Edge browser initialized with notifications allowed.");
             break;

         case "firefox":
             WebDriverManager.firefoxdriver().setup(); // Setup FirefoxDriver
             FirefoxOptions firefoxOptions = new FirefoxOptions();
             FirefoxProfile firefoxProfile = new FirefoxProfile();
             // Set the preference to allow web notifications for Firefox
             firefoxProfile.setPreference("dom.webnotifications.enabled", true);
             firefoxOptions.setProfile(firefoxProfile);
             driver = new FirefoxDriver(firefoxOptions);
             logger.info("Firefox browser initialized with notifications allowed.");
             break;
		default : logger.error("Unable to get browser");return;
		}
		
		String HomePageUrl = props.getProperty("HomePageUrl");
		driver.get(HomePageUrl);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public static void SwitchWindow() {
		mainWindowHandle = driver.getWindowHandle();
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
//    	String targetFilepath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
    	String targetFilepath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+".png";
    	File target = new File(targetFilepath);
		src.renameTo(target);
		return targetFilepath;
	}

}
