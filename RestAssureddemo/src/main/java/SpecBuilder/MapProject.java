package SpecBuilder;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.pojo.concept.maps.Location;
import com.pojo.concept.maps.MapsPojo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class MapProject {
	
	@Test
	public void SpecBuilderTest() {
		
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
		
		
		//Spec Builder 
		//RequestSpecification type request builder
		//ResponseSpecification type response builder
		//Need to use build() always by end of Specification builder
		//Create this as utility and use these in farmework
		
		RequestSpecification req_spec = new RequestSpecBuilder()
				.setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON)
				//.addHeader("Content-Type", "application/json")
				.addQueryParam("key", "qaclick123")
				.build();
		
		RequestSpecification res = given().spec(req_spec).body(mp);
		
		ResponseSpecification res_spec = new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
	//	.expectHeader("Content-Type", "application/json")
		.build();
		
		
		//Response through spec builder and optimizing code
		Response response = res.when().post("/maps/api/place/add/json")
		 .then().spec(res_spec).extract().response();
		
		System.out.println("*************************************");
		System.out.println(response);
		
		
		
		System.out.println("############################################");
		
		// Response without spec builder 
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response res1 = given().queryParam("key", "qaclick123")
		.header("Content-Type","application/json")
		 .body(mp)
		 .when().post("/maps/api/place/add/json")
		 .then().assertThat().statusCode(200)
		 .extract().response();
		
		System.out.println(res1);
		
		
		
	}

}
