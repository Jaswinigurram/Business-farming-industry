public class APITesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		package com.apiTest;

		import java.util.ArrayList;
		import org.testng.annotations.BeforeTest;
		import org.testng.annotations.Test;
		import io.restassured.RestAssured;
		import io.restassured.path.json.JsonPath;
		import io.restassured.response.Response;
		import io.restassured.specification.RequestSpecification;

		public class APITest {
		private JsonPath jsonPathEvaluator;
		@BeforeTest
		public void con()
		{
		RestAssured.baseURI = “https://api.tmsandbox.co.in/v1/Categories/6327/Details.json?  catalogue=false”;
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get();
		jsonPathEvaluator = response.jsonPath();
		}

		@Test(priority=0)
		public void nameTest()
		{
		String name = jsonPathEvaluator.get(“Name”);
		if(name.equals(“Carbon credits”))
		{
		System.out.println(“Expected value retrieved for \”Name\” attribute. The retrieved value for Name is \””+name+”\””);
		}
		else
		{
		System.out.println(“Wrong value retrieved for Name attribute”);
		}

		}

		@Test(priority=1)
		public void booleanTest()
		{
		Boolean canRelist  = jsonPathEvaluator.get(“CanRelist”);
		if(canRelist==true)
		{
		System.out.println(“Expected value retrieved for \”CanRelist\” attribute. The retrieved value for Name is \””+canRelist+”\””);
		}
		else
		{
		System.out.println(“Wrong value retrieved for \”CanRelist\” attribute”);
		}

		}

		@Test(priority=2)
		public void proTest()
		{
		Boolean b=false;
		ArrayList<String> promotionslist = jsonPathEvaluator.get(“Promotions”);
		Object[] obj=promotionslist.toArray();
		for(int i=0;i<obj.length;i++)
		{
		if(obj[i].toString().contains(“Name=Gallery”)&&obj[i].toString().contains(“2x larger image”)==true)
		{
		b=true;
		}
		}

		if(b==true)
		{
		System.out.println(“Expected value retrieved. The Promotions element with Name = \”Gallery\” has a Description that contains the text \”2x larger image\”\n”);
		}
		else
		{
		System.out.println(“Wrong value retrieved. The Promotions element with Name = \”Gallery\” doesn’t have any Description”);

		}

		}

		}

	}

}
