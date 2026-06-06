package com.sdet.extentlisteners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sdet.pages.MainPage;



public class ExtentManager extends MainPage {

	public ExtentManager(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private static ExtentReports extent;
	public static String fileName;

	public static ExtentReports createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle(fileName);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName(fileName);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Tester", "Hector Murguia");
		extent.setSystemInfo("Organization", "Test Example");
		extent.setSystemInfo("Build no:", "Test-0903");

		return extent;
	}

	public static void captureScreenshot() throws IOException {

		Date d = new Date();
		fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		File screeshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		log.info("Capturing Screenshot: " + fileName);
		FileUtils.copyFile(screeshot,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\" + fileName));
	}

	public static void captureElementScreenshot(WebElement element) throws IOException {

		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		
		File screeshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
		log.info("Capturing Element Screenshot Screenshot: " + fileName);
		FileUtils.copyFile(screeshot,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\" + fileName));
	}

}
