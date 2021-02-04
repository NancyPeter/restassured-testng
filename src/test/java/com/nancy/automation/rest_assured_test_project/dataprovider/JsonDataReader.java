package com.nancy.automation.rest_assured_test_project.dataprovider;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.*;

public class JsonDataReader {
	
	public static Object jsonReaderFunc(String fileName, String className) throws JsonParseException, JsonMappingException, ClassNotFoundException, IOException {
		URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		Object requestObj = objectMapper.readValue(url, Class.forName(className));
		
		return requestObj;	
		
	}

}
