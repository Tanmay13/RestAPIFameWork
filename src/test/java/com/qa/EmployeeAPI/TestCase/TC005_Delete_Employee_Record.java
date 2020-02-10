package com.qa.EmployeeAPI.TestCase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.EmployeeAPI.TestBase.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_Delete_Employee_Record extends TestBase {
	
	RequestSpecification httpRequest;
	Response response;

	@BeforeClass
	void deleteEmployee() throws InterruptedException {
		logger.info("*********Started TC005_Delete_Employee_Record **********");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/employees");

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Capture id
		String empID = jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE, "/delete/" + empID); // Pass
																			// ID
																			// to
																			// delete
																			// record

		Thread.sleep(3000);
	}

	@Test
	void checkResposeBody() {
		String responseBody = response.getBody().asString();
		AssertJUnit.assertEquals(responseBody.contains("successfully! deleted Records"), true);

	}

	@Test
	void checkStatusCode() {
		int statusCode = response.getStatusCode(); // Gettng status code
		AssertJUnit.assertEquals(statusCode, 200);
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
	void checkcontentEncoding() {
		String contentEncoding = response.header("Content-Encoding");
		AssertJUnit.assertEquals(contentEncoding, "gzip");

	}

	@AfterMethod
	@AfterClass
	void tearDown() {
		logger.info("*********  Finished TC005_Delete_Employee_Record **********");
	}

}


