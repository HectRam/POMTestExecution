package com.sdet.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sdet.Main.MainTest;
import com.sdet.pages.HomePage;
import com.sdet.utilities.DataUtil;

public class itemsArePresent extends MainTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	public void itemsPresent(String runMode, String browser) {
		if (runMode.equals("N")) {
			System.out.println("Skipping " + browser + " itemsArePresent execution as the run mode is NO");
			log.info("Skipping " + browser + " itemsArePresent execution as the run mode is NO");
			throw new SkipException("Skipping " + browser + " itemsArePresent execution as the run mode is NO");
		}

		System.out.println("Starting itemsArePresent--------------");
		log.info("Starting itemsArePresent");

		SetUp(browser);

		HomePage mainP = new HomePage(driver);
		mainP.validateHomePageMenuItems();

		log.info("Finishing itemsArePresent");
		System.out.println("Finishing itemsArePresent------------------------");

	}

}
