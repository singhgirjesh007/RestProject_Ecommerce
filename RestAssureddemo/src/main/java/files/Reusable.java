package files;

import io.restassured.path.json.JsonPath;

public class Reusable {
	
	public static JsonPath reusableMethod(String response) {
		
		JsonPath js = new JsonPath(response);
		return js;
	}

}
