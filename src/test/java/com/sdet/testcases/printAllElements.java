package com.sdet.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sdet.Main.MainTest;
import com.sdet.pages.HomePage;
import com.sdet.pages.WindowsPage;
import com.sdet.utilities.DataUtil;

public class printAllElements extends MainTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	public void printAll(String runMode, String browser) throws InterruptedException {

		if (runMode.equals("N")) {
			System.out.println("Skipping " + browser + " printAllElements execution as the run mode is NO");
			log.info("Skipping " + browser + " printAllElements execution as the run mode is NO");
			throw new SkipException("Skipping " + browser + " printAllElements execution as the run mode is NO");
		}

		System.out.println("Starting printAllElements------------------------");
		log.info("Starting printAllElements");

		SetUp(browser);
		HomePage mainP = new HomePage(driver);
		WindowsPage windowsP = mainP.goToWindows();
		Thread.sleep(1000);
		windowsP.aboutWindowsMenuClickExpand();
		Thread.sleep(1000);
		windowsP.aboutWindowsMenulValidateAllItems();

		log.info("Finishing printAllElements");
		System.out.println("Finishing printAllElements------------------------");
	}

}
