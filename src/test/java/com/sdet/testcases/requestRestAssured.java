package com.sdet.testcases;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.sdet.Main.MainTest;
import com.sdet.apirequest.ApiRequests;
import com.sdet.utilities.DataUtil;

public class requestRestAssured extends MainTest{
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp1")
	public void requestValidation(String runMode) {
		
		if (runMode.equals("N")) {
			System.out.println("Skipping requestRestAssured execution as the run mode is NO");
			log.info("Skipping requestRestAssured execution as the run mode is NO");
			throw new SkipException("Skipping requestRestAssured execution as the run mode is NO");
		}
		
		
		System.out.println("Starting requestRestAssured------------------------");
		log.info("Starting requestRestAssured");
		
		ApiRequests request = new ApiRequests();
		request.getRequest();
		
		log.info("Finishing requestRestAssured");
		System.out.println("Finishing requestRestAssured------------------------");
	}

}
