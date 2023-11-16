package basicrestassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import files.Reusable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MapAPITest {
	
	//public static void main(String[] args) {
	@Test
	public void basicTest() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		System.out.println("test");
	/*	given().log().all().queryParam("key","qaclick123")
		.headers("Content-Type", "application/json")
		.body(Payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200); */
		
		String response =given().log().all().queryParam("key","qaclick123")
		.headers("Content-Type", "application/json")
		.body(Payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println();
		System.out.println(response);
		
		//AddPlace --> Update Place with new address --> get place to validate if new address in present
		
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		System.out.println(placeId);
		
		String address = "70 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+address+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		System.out.println("Get call started");
		
		String res =given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).header("Content-Type","application/json")
		.when().get("/maps/api/place/get/json")
		.then().assertThat().statusCode(200).body("address",equalTo(address)).extract().response().asString();
		
		System.out.println();
		System.out.println(res);
		
		//JsonPath js1 = new JsonPath(res);
		JsonPath js1 = Reusable.reusableMethod(res);
		
		String u_addr = js1.getString("address");
		System.out.println(u_addr);
		
		Assert.assertEquals(address, u_addr);
		
	}
}
