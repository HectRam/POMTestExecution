package com.sdet.pages;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.sdet.extentlisteners.ExtentListeners;

public class SoftwareVisualStudioPage extends MainPage {

	private String price;

	public SoftwareVisualStudioPage(WebDriver driver) {
		super(driver);
		loadProperties();
		// TODO Auto-generated constructor stub
	}
	//Not needed, Firefox profile disable incoming pop ups
	public void modalPopUpRemoval() {
		log.info("On modalPopUpRemoval");
		elementToBeClickable("PopUp_C");
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Click("PopUp_C");
		elementToBeClickable("PopUpXBtn_C");
		
	}

	public String getSoftwarePrice() {
			
		price = getTxt("GetPrice1_C");
		System.out.println("Price Stored: " + price);
		log.info("Price Stored: " + price);
		ExtentListeners.test.log(Status.INFO, "Price Stored: " + price);
		
		return price;
		
	}

	public CartPage AddToCartBtnClick() {

		Click("BuyBox_ID");// add it to the cart and moving to CartPage
		return new CartPage(driver);

	}

}
