package com.pojo.concept.maps;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class MapsRestAssured {
	
	@Test
	public void BasicPojoSerializationTest() {
		
		MapsPojo mp = new MapsPojo();
		
		Location lc = new Location();
		
		mp.setAccuracy(50);
		mp.setAddress("29, side layout, cohen 09");		
		mp.setName("Frontline house");
		mp.setPhone_number("(+91) 983 893 3937");
		mp.setWebsite("http://google.com");
		mp.setLanguage("French-IN");
		
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		
		List<String> tc = new ArrayList<String>();
		tc.add("shoe park");
		tc.add("shop");
		
		mp.setLocation(lc);
		mp.setTypes(tc);
		
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String res = given().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		 .body(mp)
		 .when().post("/maps/api/place/add/json")
		 .then().assertThat().statusCode(200)
		 .extract().asString();
		
		System.out.println(res);
		
		
	}
}
