package com.sdet.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.sdet.extentlisteners.ExtentListeners;

public class CartPage extends MainPage{
			
	private String price2;
	
	public CartPage(WebDriver driver) {
		super(driver);
		loadProperties();
		// TODO Auto-generated constructor stub
	}
	
	public void cartBtnClick() {
		
		Click("CartBtn_X");// click into cart button next to search and
		// is sent into checkout page
	}
	
	public void switchFrame(String element) {
		driver.switchTo().frame(element);
		log.info("switching to frame with: "+element);
		System.out.println("switching to frame: "+element);
	}
	
	public String getCartPrice(){
		log.info("On getCartPrice");
		switchFrame("purchase-frame");
		price2 = getTxt("GetPrice2_X");
		System.out.println("Cart price Stored: " + price2);
		log.info("Cart price Stored: " + price2);
		ExtentListeners.test.log(Status.INFO, "Cart price Stored: " + price2);
		return price2;
	}
	
	public void ComparePrices(String price, String price2) {
		log.info("On ComparePrices");
		if (price.substring(0, 5).equals(price2.substring(0, 5))) {
			log.info("Same price");
			System.out.println("Same price");
			ExtentListeners.test.log(Status.PASS, "Same price");
		} else {
			// Assert.assertEquals(true, pricecorr.equals(pricecorr2));
			log.info("Different price");
			ExtentListeners.test.log(Status.FAIL, "Different price");
			Assert.fail("Different price");
		}

	}
	
}
