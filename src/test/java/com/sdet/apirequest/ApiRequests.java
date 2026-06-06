package com.sdet.apirequest;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.sdet.Main.MainTest;
import com.sdet.extentlisteners.ExtentListeners;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ApiRequests extends MainTest{
	
	@Test
	public void getRequest() {

		System.out.println("Starting requestRestAssured------------------------");
		log.info("Starting requestRestAssured");

		try {
			RestAssured.baseURI = "https://pokeapi.co/";

			String Response = given().log().all().when().get("api/v2/pokemon?limit=300&name").then().assertThat().log()
					.all().statusCode(200).extract().response().asString();

			JsonPath js = new JsonPath(Response);
			// String name = js.getString("name");

			int count = js.getInt("results.size()");
			System.out.println("Pokemon Count Size: " + count);
			log.info("Pokemon Count Size: " + count);
			ExtentListeners.test.log(Status.INFO, "Pokemon Count Size: " + count);
			for (int i = 0; i < count; i++) {
				String s = js.get("results[" + i + "].name");
				char r = ' ';
				r = s.charAt(0);

				if (r == 's') {
					log.info("Pokemon ID:" + (i + 1) + " Name: " + s);
					System.out.println("Pokemon ID:" + (i + 1) + " Name: " + s);
					ExtentListeners.test.log(Status.INFO, "Pokemon ID:" + (i + 1) + " Name: " + s);
				}

			}
			log.info("Request Passed");
			ExtentListeners.test.log(Status.PASS, "Request Passed");
		} catch (Throwable t) {
			log.error("There was an error on the request: " + t.getMessage());
			ExtentListeners.test.log(Status.FAIL, "There was an error on the request: " + t.getMessage());
			Assert.fail(t.getMessage());
		}
		log.info("Finishing requestRestAssured");
		System.out.println("Finishing requestRestAssured------------------------");
	}
}
