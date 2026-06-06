package com.sdet.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sdet.Main.MainTest;
import com.sdet.pages.HomePage;
import com.sdet.utilities.DataUtil;

public class searchBox extends MainTest{
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	public void search(String runMode, String browser,String searchItem) {
		
		if (runMode.equals("N")) {
			System.out.println("Skipping " + browser + " searchBox execution as the run mode is NO");
			log.info("Skipping " + browser + " searchBox execution as the run mode is NO");
			throw new SkipException("Skipping " + browser + " searchBox execution as the run mode is NO");
		}
		
		System.out.println("Starting searchBox------------------------");
		log.info("Starting searchBox");
		
		SetUp(browser);
		HomePage homeP = new HomePage(driver);
		homeP.clickOnSearch();
		homeP.searchFor(searchItem);
		
		log.info("Finishing searchBox");
		System.out.println("Finishing searchBox------------------------");

	}

}
