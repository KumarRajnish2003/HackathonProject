package testBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public static Properties props;
	public static String mainWindowHandle = null;
	public List<String> data;
	ExcelUtility uty;

	@Parameters({ "browser", "os" })
	@BeforeClass(groups= {"smoke","master","sanity"})
	public void setup(@Optional("Chrome") String browser, String os) throws IOException {

		logger = LogManager.getLogger(this.getClass());
		try {
			props = new Properties();
			FileInputStream file = new FileInputStream("src/test/resources/config.properties");
			props.load(file);
		} catch (Exception e) {
			logger.error("Unable to load config file");
			Assert.assertTrue(false, "Failed to load config.properties");
		}

		if (props.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (browser.toLowerCase()) {

			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				Map<String, Object> chromePrefs = new HashMap<>();
				chromePrefs.put("profile.default_content_setting_values.notifications", 1); // Set the preference to
																							// allow
																							// notifications (1 = allow,
																							// 2 =
																							// block, 0 = ask)
				chromeOptions.setExperimentalOption("prefs", chromePrefs);
				driver = new ChromeDriver(chromeOptions);
				logger.info("Chrome browser initialized with notifications allowed.");
				break;

			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions();
				Map<String, Object> edgePrefs = new HashMap<>();
				edgePrefs.put("profile.default_content_setting_values.notifications", 1);
				edgeOptions.setExperimentalOption("prefs", edgePrefs);
				edgeOptions.addArguments("--disable-gpu");
				edgeOptions.addArguments("--disable-animations");
				edgeOptions.addArguments("--disable-extensions");
				driver = new EdgeDriver(edgeOptions);
				logger.info("Edge browser initialized with notifications allowed.");
				break;

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				firefoxProfile.setPreference("dom.webnotifications.enabled", true);
				firefoxOptions.setProfile(firefoxProfile);
				driver = new FirefoxDriver(firefoxOptions);
				logger.info("Firefox browser initialized with notifications allowed.");
				break;

			default:
				logger.fatal("Unable to get browser");
				return;
			}

		} else if (props.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();

			if (os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			} else if (os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching os");
				return;
			}

			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			default:
				System.out.println("No matching browser");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}

		String HomePageUrl = props.getProperty("HomePageUrl");
		driver.get(HomePageUrl);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		try {
			getData();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterClass(groups= {"smoke","master","sanity"})
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
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File target = new File(targetFilepath);
		src.renameTo(target);
		return targetFilepath;
	}

	public static String takeSpecificScreenShot(String tname, WebElement ele) {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		File src = ele.getScreenshotAs(OutputType.FILE);
		String targetFilepath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File target = new File(targetFilepath);
		src.renameTo(target);
		return targetFilepath;
	}

	public void getData() throws IOException {
		uty = new ExcelUtility();
		String filepath = props.getProperty("filepath");
		data = new ArrayList<>();
		for (int i = 0; i < uty.getCellCount(filepath, "Sheet1", 1); i++) {
			data.add(uty.getCellData(filepath, "Sheet1", 1, i));
		}

	}

}
