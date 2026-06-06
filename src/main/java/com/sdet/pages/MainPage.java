package com.sdet.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.sdet.extentlisteners.ExtentListeners;

public class MainPage {

	public static WebDriver driver;
	public MainPage mainpage;
	public Properties config = new Properties();
	public Properties OR = new Properties();
	public FileInputStream fis;
	public static Logger log = LogManager.getLogger();
	public WebDriverWait wait;
	
	public MainPage(WebDriver driver) {

		MainPage.driver = driver;

	}

	protected void loadProperties() {

		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected WebElement FindElement(String locator) {

		WebElement res = null;

		if (locator.endsWith("_X")) {
			res = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_C")) {
			res = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else {

			ExtentListeners.test.log(Status.INFO, "Element not found : " + locator);
			log.error("Element not found : " + locator);
			Assert.fail("Unable to find Element on FindElement Method");
		}
		ExtentListeners.test.log(Status.INFO, "Finding Element : " + locator);
		log.info("Finding Element : " + locator);
		return res;
	}
	
	protected List<WebElement> FindElements(String locator) {

		List<WebElement> res = null;

		if (locator.endsWith("_X")) {
			res = driver.findElements(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_C")) {
			res = driver.findElements(By.cssSelector(OR.getProperty(locator)));
		} else {

			ExtentListeners.test.log(Status.INFO, "Elements not found : " + locator);
			log.error("Elements not found : " + locator);
			Assert.fail("Unable to find Elements on FindElements Method");
		}
		ExtentListeners.test.log(Status.INFO, "Finding Elements : " + locator);
		log.info("Finding Elements : " + locator);
		return res ;
	}
	
	protected String getTxt(String element) {

		try {
			if (element.endsWith("_C")) {
				element = driver.findElement(By.cssSelector(OR.getProperty(element))).getText();
			} else if (element.endsWith("_X")) {
				element = driver.findElement(By.xpath(OR.getProperty(element))).getText();

			}

		} catch (Throwable t) {
			ExtentListeners.test.log(Status.FAIL,
					"Unable to find Element on getTxt Method : " + element + " error message : " + t.getMessage());
			log.error("Unable to find Element on getTxt Method : " + element + " error message : " + t.getMessage());
			Assert.fail("Unable to find Element on getTxt Method : " + t.getMessage());
		}
		ExtentListeners.test.log(Status.INFO, "Getting text on Element : " + element);
		log.info("Getting text on Element : " + element);
		return element;
	}

	protected void Click(String element) {

		try {
			if (element.endsWith("_X")) {
				driver.findElement(By.xpath(OR.getProperty(element))).click();
			} else if (element.endsWith("_C")) {
				driver.findElement(By.cssSelector(OR.getProperty(element))).click();
			} else if (element.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(element))).click();
			}
			
			
		} catch (Throwable t) {
			log.error("Error Clicking on an Element : " + element + " error message : " + t.getMessage());
			ExtentListeners.test.log(Status.FAIL,
					"Error Clicking on an Element : " + element + " error message : " + t.getMessage());
			Assert.fail("Error Clicking on an Element : " + element + " error message : " + t.getMessage());
		}
		log.info("Clicking on an Element : " + element);
		ExtentListeners.test.log(Status.INFO, "Clicking on an Element : " + element);
	}

	protected void Write(String element, String value) {
		try {
			if (element.endsWith("_X")) {
				driver.findElement(By.xpath(OR.getProperty(element))).sendKeys(value);
			} else if (element.endsWith("_C")) {
				driver.findElement(By.cssSelector(OR.getProperty(element))).sendKeys(value);
			} else if (element.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(element))).sendKeys(value);
			}
			log.info("typing in an Element : " + element + " entered the value as : " + value);
			ExtentListeners.test.log(Status.INFO,
					"typing in an Element : " + element + " entered the value as : " + value);
		} catch (Throwable t) {

			log.error("Error while typing in Element : " + element + " error message : " + t.getMessage());
			ExtentListeners.test.log(Status.FAIL,
					"Error while typing in Element : " + element + " error message : " + t.getMessage());
			Assert.fail(t.getMessage());

		}
	}

	public void presenceOfElementLocated(String element) {
		if (element.endsWith("_C")) {
			new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(10000))
            .ignoring(ElementNotInteractableException.class)
					.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty(element))));
		} else if (element.endsWith("_X")) {
			new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(2))
            .pollingEvery(Duration.ofMillis(300))
            .ignoring(ElementNotInteractableException.class)
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty(element))));
		}
	}

	public void visibilityOfElementLocated(String element) {
		if (element.endsWith("_C")) {
			new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(10000))
            .ignoring(ElementNotInteractableException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty(element))));
		} else if (element.endsWith("_X")) {
			new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(10000))
            .ignoring(ElementNotInteractableException.class)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(element))));
		}
	}

	public void elementToBeClickable(String element) {
		if (element.endsWith("_C")) {
			new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(10000))
            .ignoring(ElementNotInteractableException.class)
					.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.getProperty(element))));
		} else if (element.endsWith("_X")) {
			new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(10000))
            .ignoring(ElementNotInteractableException.class)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty(element))));
		}
	}
}
