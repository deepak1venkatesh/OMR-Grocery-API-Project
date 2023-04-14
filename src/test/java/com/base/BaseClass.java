package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * 
 * @author Deepak Venkatesh PS 
 * @date 11-04-2023
 * @see To access BaseClass
 */
public class BaseClass 
{
	RequestSpecification reqSpec;
	public static Response response;

	/**
	 * @see Used to add Header using key and value
	 * @param key
	 * @param value
	 */
	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);
	}
	
	/**
	 * @see Used to add Headers
	 * @param headers
	 */
	public void addHeaders(Headers headers)
	{
		reqSpec = RestAssured.given().headers(headers);	
	}
	
	/**
	 * @see Used to add Path Parameter
	 * @param key
	 * @param value
	 */
	public void addPathParameter(String key, String value) {
		reqSpec = reqSpec.pathParam(key, value);

	}

	/**
	 * @see Used to add query parameter
	 * @param key
	 * @param value
	 */
	public void addQueryParameter(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);
	}

	/**
	 * @see Used to add Body 
	 * @param body
	 */
	public void addBody(String body) {
		reqSpec = reqSpec.body(body);
	}

	/**
	 * @see Used to add Request Type
	 * @param type
	 * @param endpoint
	 * @return
	 */
	public Response requestType(String type, String endpoint) 
	{
		switch(type)
		{
		case "GET":
			response = reqSpec.log().all().get(endpoint);
			break;
		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;
		case "PUT":
			response = reqSpec.log().all().put(endpoint);
			break;
		case "PATCH":
			response = reqSpec.log().all().patch(endpoint);
			break;
		case "DELETE":
			response = reqSpec.log().all().delete(endpoint);
			break;

		default:
			break;
		}
		return response;
	}

	/**
	 * @see Used to get Response Code
	 * @param response
	 * @return
	 */
	public int getResponseCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;

	}

	/**
	 * @see Used to Get response as String
	 * @param response
	 * @return
	 */
	public String getResBodyAsString(Response response) {
		String asString = response.asString();
		return asString;
	}

	/**
	 * @see Used to Get response as pretty String
	 * @param response
	 * @return
	 */
	public String getResAsPrettyString(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;

	}
	
	/**
	 * @see Used to add Basic Authentication
	 * @param username
	 * @param password
	 */
	public void addBasicAuth(String username,String password)
	{
	reqSpec=reqSpec.auth().preemptive().basic(username, password);	
	}
	
	/**
	 * @see Used to add Body 
	 * @param body
	 */
	public void addBody(Object body)
	{
		reqSpec=reqSpec.body(body);
	}
	
	/**
	 * @see Used to get Project Path
	 * @return
	 */
	public static String getprojectpath()
	{
		String path=System.getProperty("user.dir");
		return path;
	}

	/**
	 * @see Used to get the property file value
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
public static String getPropertyFileValue(String key) throws FileNotFoundException, IOException
	{	
		Properties properties=new Properties();
		properties.load(new FileInputStream("C:\\Users\\Admin\\Desktop\\eclipse\\OmrBranchGroceryAPIAutomation\\config\\config.properties"));
		Object object=properties.get(key);
		String value=(String) object;
		return value;
	}

/**
 * @see Used to add Form Data
 * @param key
 * @param string
 */
public void addFormData(String key,File value)
{
	reqSpec=reqSpec.multiPart(key,value);
}

}
