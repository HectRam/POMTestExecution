package com.sdet.pages;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.sdet.extentlisteners.ExtentListeners;


public class HomePage extends MainPage {

	private static int n = 0;
	private static List<String> names = HomeMenuItemsValidation();

	public HomePage(WebDriver driver) {
		super(driver);
		loadProperties();
		// TODO Auto-generated constructor stub
	}

	//private By SearchOnAllItems = By.xpath(OR.getProperty("SearchOnAllItems_X"));

	public WindowsPage goToWindows() {
		
		Click("Windows_X");
		return new WindowsPage(driver);
	}

	public void validateHomePageMenuItems() {
		
		System.out.println("SearchOnAll: " + OR.getProperty("SearchOnAllItems_X"));
		
		WebElement liElements = FindElement("SelectMenuItems_X");

		List<WebElement> ListItems = liElements.findElements(By.xpath(OR.getProperty("SearchOnAllItems_X")));

		System.out.println(ListItems.size());
		log.info(ListItems.size());
		if (ListItems.size() <= 9) {

			for (WebElement e : ListItems) {
				
				if (e.getText().equals(names.get(n))) {
					System.out.println(e.getText() + " is Present");
					ExtentListeners.test.log(Status.PASS, e.getText() + " is Present");
					log.info(e.getText() + " is Present");
				} else {
					ExtentListeners.test.log(Status.FAIL, e.getText() + " is Absent");
					log.error(e.getText() + " is Absent");
					Assert.fail(names.get(n) + " is Absent");
				}
				n++;
			}
		} else {
			ExtentListeners.test.log(Status.FAIL,
					"There are more Menu Items than expected. Actual: " + ListItems.size());
			log.error("There are more Menu Items than expected. Actual: " + ListItems.size());
			Assert.fail("There are more Menu Items than expected. Actual: " + ListItems.size());
			
		}
			n=0;
	}

	private static List<String> HomeMenuItemsValidation() {

		List<String> names = new ArrayList<>();
		Collections.addAll(names, "Microsoft 365", "Azure", "Copilot", "Windows", "Surface", "XBOX", "Deals",
				"Small Business", "Support");
		return names;
	}
	public void clickOnSearch() {
		Click("SearchBoxHomePage_X");
	}
	
	public SoftwareVisualStudioPage searchFor(String Value) {
		
		Write("SearchInputHomePage_C", Value);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		presenceOfElementLocated("ListArticlesHomePage_C");
		List<WebElement> LIST = FindElements("ListArticlesHomePage_C");
		
		for (int i = 0; i < LIST.size(); i++) {
			// System.out.println(LIST.get(i).getText());
			if (LIST.get(i).getText().contains(Value)) {
				LIST.get(i).click();
				System.out.println(Value+" Found and Clicked");
				log.info(Value+" Found and Clicked");
				ExtentListeners.test.log(Status.PASS, Value+" Found and Clicked");
			} else {
				ExtentListeners.test.log(Status.FAIL, Value+" was not Found");
				log.error( Value+" was not Found");
				Assert.fail(Value+" was not Found");
			}
		}
		return new SoftwareVisualStudioPage(driver);
	}

}
