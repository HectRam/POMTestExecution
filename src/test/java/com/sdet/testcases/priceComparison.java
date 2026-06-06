package com.sdet.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sdet.Main.MainTest;
import com.sdet.pages.CartPage;
import com.sdet.pages.HomePage;
import com.sdet.pages.SoftwareVisualStudioPage;
import com.sdet.utilities.DataUtil;

public class priceComparison extends MainTest {

	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	public void priceCompare(String runMode, String browser,String searchItem) {
		
		
		if (runMode.equals("N")) {
			System.out.println("Skipping " + browser + " priceComparison execution as the run mode is NO");
			log.info("Skipping " + browser + " priceComparison execution as the run mode is NO");
			throw new SkipException("Skipping " + browser + " priceComparison execution as the run mode is NO");
		}
		
		System.out.println("Starting priceComparison------------------------");
		log.info("Starting priceComparison");
		

		SetUp(browser);
		HomePage homeP = new HomePage(driver);
		homeP.clickOnSearch();
		SoftwareVisualStudioPage sVSP = homeP.searchFor(searchItem);
		//sVSP.modalPopUpRemoval();
		String sPrice = sVSP.getSoftwarePrice();
		CartPage cartP = sVSP.AddToCartBtnClick();
		String cPrice=cartP.getCartPrice();
		cartP.ComparePrices(sPrice,cPrice);
		
		
		log.info("Finishing priceComparison");
		System.out.println("Finishing priceComparison------------------------");
	}

}
