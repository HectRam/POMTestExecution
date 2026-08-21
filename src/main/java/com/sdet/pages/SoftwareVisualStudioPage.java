package com.sdet.pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.sdet.extentlisteners.ExtentListeners;

public class SoftwareVisualStudioPage extends MainPage {

	private String price;

	public SoftwareVisualStudioPage(WebDriver driver) {
		super(driver);
		
		// TODO Auto-generated constructor stub
	}
	//Not needed, Firefox profile disable incoming pop ups
	public void modalPopUpRemoval() {
		log.info("On modalPopUpRemoval");
		
		
		Click("PopUp_C");
		
		
	}

	public String getSoftwarePrice() {
			
		price = getTxt("GetPrice1_C");
		System.out.println("Price Stored: " + price);
		log.info("Price Stored: " + price);
		ExtentListeners.getExtent().log(Status.INFO, "Price Stored: " + price);
		
		return price;
		
	}

	public CartPage AddToCartBtnClick() {

		Click("BuyBox_ID");// add it to the cart and moving to CartPage
		return new CartPage(getDriver());

	}

}
