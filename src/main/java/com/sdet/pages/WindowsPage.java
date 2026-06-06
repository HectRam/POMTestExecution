package com.sdet.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.sdet.extentlisteners.ExtentListeners;

public class WindowsPage extends MainPage {

	public WindowsPage(WebDriver driver) {
		super(driver);
		loadProperties();
		// TODO Auto-generated constructor stub
	}

	public void aboutWindowsMenuClickExpand() {
		// WebElement AboutWindows = FindElement("AboutWindows_C");
		// new Actions(driver).moveToElement(AboutWindows).perform();
		Click("AboutWindows_C");
	}

	public void aboutWindowsMenulValidateAllItems() {

		WebElement liElements = FindElement("listElements_C");

		List<WebElement> ListItems = liElements.findElements(By.cssSelector(".c-w0 > li"));

		System.out.println(ListItems.size());
		log.info(ListItems.size());
		if (ListItems.size() > 0 && ListItems.size() <= 12) {
			for (WebElement e : ListItems) {
				System.out.println("List Item Text : " + e.getText());
				log.info("List Item Text : " + e.getText());
				ExtentListeners.test.log(Status.INFO, "List Item Text : : " + e.getText());
			}
			ExtentListeners.test.log(Status.PASS, "About Windows Items were listed successfuly");
			log.info("About Windows Items were listed successfuly");
		} else {
			ExtentListeners.test.log(Status.FAIL,
					"There are 0 or more tha 12 Items listed. Actual: " + ListItems.size());
			log.error("There are 0 or more tha 12 Items listed. Actual: " + ListItems.size());
			Assert.fail("There are 0 or more tha 12 Items listed. Actual: " + ListItems.size());
		}
	}

	public void getwindowsMenu() {

	}

	public void clickOnSearch() {

	}

	public void search() {

	}

}
