package com.qa.EmployeeAPI.TestCase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.EmployeeAPI.TestBase.TestBase;
import com.qa.EmployeeAPI.Utility.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_Record extends TestBase {
	


RequestSpecification httpRequest;
Response response;
String empName=RestUtils.empName();
String empSalary=RestUtils.empSal();
String empAge=RestUtils.empAge();


@BeforeClass
void updateEmployee() throws InterruptedException
{
	logger.info("*********Started TC004_Put_Employee_Record **********");
	
	RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	httpRequest = RestAssured.given();

	// JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method
	//{"name":"John123X","salary":"123","age":"23"}
	JSONObject requestParams = new JSONObject();
	requestParams.put("name", empName); // Cast
	requestParams.put("salary", empSalary);
	requestParams.put("age", empAge);
	
	// Add a header stating the Request body is a JSON
	httpRequest.header("Content-Type", "application/json");

	// Add the Json to the body of the request
	httpRequest.body(requestParams.toJSONString());

	response = httpRequest.request(Method.PUT, "/update/"+empID);
	
	Thread.sleep(5000);

}

@Test
void checkResposeBody()
{
	String responseBody = response.getBody().asString();
			
	AssertJUnit.assertEquals(responseBody.contains(empName), true);
	AssertJUnit.assertEquals(responseBody.contains(empSalary), true);
	AssertJUnit.assertEquals(responseBody.contains(empAge), true);
}
	
@Test
void checkStatusCode()
{
	int statusCode = response.getStatusCode(); // Gettng status code
	AssertJUnit.assertEquals(statusCode, 200);
}
	
@Test
void checkstatusLine()
{
	String statusLine = response.getStatusLine(); // Gettng status Line
	AssertJUnit.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
}

@Test
void checkContentType()
{
	String contentType = response.header("Content-Type");
	AssertJUnit.assertEquals(contentType, "text/html; charset=UTF-8");
}

@Test
void checkserverType()
{
	String serverType = response.header("Server");
	AssertJUnit.assertEquals(serverType, "nginx/1.14.1");
}

@Test
void checkcontentEncoding()
{
	String contentEncoding = response.header("Content-Encoding");
	AssertJUnit.assertEquals(contentEncoding, "gzip");

}

@AfterMethod
@AfterClass
void tearDown()
{
	logger.info("*********  Finished TC004_Put_Employee_Record **********");
}
}
