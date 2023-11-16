package basicrestassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import files.Reusable;
import io.restassured.path.json.JsonPath;

public class CourseComplexJsonParse {
	
public static void main(String[] args) {
	
	
		
		JsonPath js =Reusable.reusableMethod(Payload.mockCourseResponse());
		int num_course =js.getInt("courses.size()");
		System.out.println("Number of course --> " + num_course);
		
		//json path parent.child 
		int purchase_amt = js.getInt("dashboard.purchaseAmount");
		System.out.println("purchase amount-->"+purchase_amt);
		
		
		System.out.println(js.get("courses[0].title"));
		
		for(int i = 0; i < num_course ; i++) {
			String title = js.get("courses["+i+"].title");
			int price = js.getInt("courses["+i+"].price");
			System.out.println(title);
			System.out.println(js.getInt("courses["+i+"].price"));
		}
		
		
		for(int i = 0; i < num_course ; i++) {
			 String title = js.get("courses["+i+"].title");
			 
			if (title.equals("RPA")) {
				int copies= js.getInt("courses["+i+"].copies");
				System.out.println(copies);
				break;
			 }
			
			
		}
		int pa=0;
		for(int i = 0; i < num_course ; i++) {
			
			int price = js.getInt("courses["+i+"].price");
			int copies= js.getInt("courses["+i+"].copies");
			pa = pa+price*copies;
		}
		
		System.out.println(pa);
		Assert.assertEquals(pa, purchase_amt);
		
	
		
	}
	
	
	
}
