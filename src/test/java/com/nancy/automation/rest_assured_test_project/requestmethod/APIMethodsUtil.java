package com.nancy.automation.rest_assured_test_project.requestmethod;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.Map;

public class APIMethodsUtil {
	
	public static String username = "";
	public static String password = "";
	public static String baseURL = "";
	
public static Response getReq(String endPointUrl) {
	Response response = given().urlEncodingEnabled(false)
			.auth().preemptive().basic(username, password)
			.when().log().all()
			.headers("Content-Type", "application/json")
			.get(baseURL+endPointUrl);
	return response;		
	}


public static Response postReq(Object reqbody, String endPointUrl) {
	Response response = given().urlEncodingEnabled(false)
			.auth().preemptive().basic(username, password)
			.when().log().all()
			.headers("Content-Type", "application/json")
			.body(reqbody)
			.post(baseURL+endPointUrl);
	return response;		
	}



}
