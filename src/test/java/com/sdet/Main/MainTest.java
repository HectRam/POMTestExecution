package com.sdet.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import com.sdet.utilities.ExcelReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MainTest {

	// protected WebDriver driver;
	public ExcelReader excel = new ExcelReader(".//src//test//resources//excel//testdata.xlsx");
	public Properties config = new Properties();
	public Properties OR = new Properties();
	public FileInputStream fis;
	public WebDriverWait wait;
	public static Logger log = LogManager.getLogger();

	private static ThreadLocal<WebDriver> driverTL = new ThreadLocal<>();
	private static ThreadLocal<WebDriverWait> waitTL = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driverTL.get();
	}

	public static WebDriverWait getWait() {
		return waitTL.get();
	}

	public void SetUp(String Browser) {

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			config.load(fis);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (Browser.equals("firefox")) {

			WebDriverManager.firefoxdriver().avoidBrowserDetection().setup();
			FirefoxProfile customProfile = new FirefoxProfile();
			customProfile.setPreference("dom.disable_beforeunload", true);
			// driver = new FirefoxDriver());
			driverTL.set(new FirefoxDriver());
			System.out.println("Firefox launched ");
			log.info("Firefox launched ");
		}
		if (Browser.equals("chrome")) {

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);

			// driver = new ChromeDriver();
			driverTL.set(new ChromeDriver(options));
			System.out.println("Chrome launched ");
			log.info("Chrome launched ");
		}
		getDriver().get(config.getProperty("testsiteurl"));
		System.out.println("Navigated to: " + config.getProperty("testsiteurl"));
		log.info("Navigated to: " + config.getProperty("testsiteurl"));
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
		// wait = new
		// WebDriverWait(getDriver(),Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait"))));
		waitTL.set(new WebDriverWait(getDriver(),
				Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit.wait")))));
	}

	@AfterMethod
	public void tearDown() {
		if (getDriver() != null) {
			getDriver().quit();
			log.info("Test Execution Completed");
		}

		
		driverTL.remove();
		waitTL.remove();
	}
}
