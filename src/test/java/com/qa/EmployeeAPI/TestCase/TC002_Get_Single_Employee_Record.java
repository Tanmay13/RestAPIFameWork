package com.qa.EmployeeAPI.TestCase;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.EmployeeAPI.TestBase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_Get_Single_Employee_Record  extends TestBase{

	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	void getEmployeeData() throws InterruptedException {
		logger.info("*********Started TC002_Get_Single_Employee_Record **********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/" + empID);

		Thread.sleep(7000);
	}

	@Test
	void checkResposeBody() {
		String responseBody = response.getBody().asString();
		CharSequence empID = null;
		AssertJUnit.assertEquals(responseBody.contains(empID), true);
	}

	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode(); // Gettng status code
		AssertJUnit.assertEquals(statusCode, 200);
	}

	@Test
	void checkResponseTime() {
		long responseTime = response.getTime(); // Getting status Line
		AssertJUnit.assertTrue(responseTime < 6000);

	}

	@Test
	void checkstatusLine() {
		String statusLine = response.getStatusLine(); // Gettng status Line
		AssertJUnit.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	void checkContentType() {
		String contentType = response.header("Content-Type");
		AssertJUnit.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkserverType() {
		String serverType = response.header("Server");
		AssertJUnit.assertEquals(serverType, "nginx/1.14.1");
	}

	@Test
	void checkContentLenght() {
		String contentLength = response.header("Content-Length");
		AssertJUnit.assertTrue(Integer.parseInt(contentLength) < 1500);
	}

	@AfterMethod
	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC002_Get_Single_Employee_Record **********");
	}

}
