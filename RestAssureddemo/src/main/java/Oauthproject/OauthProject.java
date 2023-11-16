package Oauthproject;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OauthProject {
	
	public static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		/*
		 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
		 * driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php"
		 * ); driver.findElement(By.cssSelector("input[type='email']")).sendKeys(
		 * "babbugirjesh5@gmail.com");
		 * driver.findElement(By.cssSelector(DEFAULT_BODY_ROOT_PATH)).click();
		 * driver.findElement(By.cssSelector("input[type='password']")).sendKeys(
		 * "Cimple@007"); String url= driver.getCurrentUrl();
		 */
		
		//Code will come  will come from driver.get() url hit on browser and login in with
		//username and password
		 
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AfJohXnFYREBeA5SLqK8ntOgcplu7j-WYlnop9CSwVof2wSycMj3bUf1uDnTD-ppXwwPOA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
	String partialcode =url.split("code=")[1];
	String code = partialcode.split("&")[0];
	
	//urlEncodingEnabled method , code will return some %or special character
	// restassured will use this as integer so use urlEncodingEnabled
	String accessTokenResponse =given().
			urlEncodingEnabled(false)
	.queryParam("code",code ).
	queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
	.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
	.queryParam("redirect_url", "https://rahulshettyacademy.com/getCourse.php")
	.queryParam("grant_type", "authorization_code")
	.when().log().all()
	.post("https://rahulshettyacademy.com/getCourse.php")
	.then().extract().response().asString();
	
	JsonPath js = new JsonPath(accessTokenResponse);
	String access_token = js.getString("access_token");
	
	
	
	
	String rs =given().queryParam("access_token", access_token).
	when().log().all()
	.get("https://rahulshettyacademy.com/getCourse.php").asString();
	
	System.out.println(rs);

	}

}
