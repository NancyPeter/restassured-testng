package com.nancy.automation.rest_assured_test_project;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nancy.automation.rest_assured_test_project.dataprovider.JsonDataReader;
import com.nancy.automation.rest_assured_test_project.model.CreateUserReq;
import com.nancy.automation.rest_assured_test_project.requestmethod.APIMethodsUtil;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class UserTests {
	
	private CreateUserReq createUserReq;
	private Response response;
	private JsonPath jsonPath;
	private String responseBody;
	private Map<String, String> headers;
	private String token;
	private String reqFileName;
	
	@Test(priority = 0)
	  public void valid_UserCreation() throws JsonParseException, JsonMappingException, ClassNotFoundException, IOException, MalformedURLException  {
				  		  	  
		  System.out.println("Successful Card Creation");
		  
		  reqFileName = "./validUsercreation.json";
		  createUserReq = (CreateUserReq)JsonDataReader.jsonReaderFunc(reqFileName, "com.nancy.automation.rest_assured_test_project.model.CreateUserReq");  
  

		  String endPointUrl = "/users";
		  Response response = APIMethodsUtil.postReq(createUserReq, endPointUrl);
		  
		  System.out.println("status code : "+ response.getStatusCode());
		  
		  Assert.assertEquals(response.getStatusCode(), 201);
		  jsonPath = response.jsonPath();
		  
		  token = jsonPath.get("token");
		  System.out.println("Token - "+token);
		  
		  System.out.println("----------------------------------");
	  }
	
	@Test(priority = 1)
	  public void invalid_UserCreation() throws JsonParseException, JsonMappingException, ClassNotFoundException, IOException, MalformedURLException  {
		
		  System.out.println("Unuccessful Card Creation");
		  
		  reqFileName = "./invalidUsercreation.json";
		  createUserReq = (CreateUserReq)JsonDataReader.jsonReaderFunc(reqFileName, "com.nancy.automation.rest_assured_test_project.model.CreateUserReq");  

		  String endPointUrl = "/users";
		  Response response = APIMethodsUtil.postReq(createUserReq, endPointUrl);
		  
		  System.out.println("status code : "+ response.getStatusCode());
		  
		  Assert.assertEquals(response.getStatusCode(), 409);  
		  
		  System.out.println("----------------------------------");
	  }
	
	
	@Test(priority = 2, dependsOnMethods = { "valid_UserCreation" })
  public void retrieve_CARD() throws JsonParseException, JsonMappingException, ClassNotFoundException, IOException {
	  
	  System.out.println("Retrieve CARD");
	  
	  if(token != null) {	
		  
		  String endPointUrl = "/users/"+token;
		  Response response = APIMethodsUtil.getReq(endPointUrl);
		  
		  System.out.println("status code : "+ response.getStatusCode());
		  
		  Assert.assertEquals(response.getStatusCode(), 200);
	  }else {
		  System.out.println("Token is null");
	  }  
		  
	    
	  System.out.println("----------------------------------");
  }
  

}
