package com.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.global.GlobalDatas;
import com.pojo.Login.BasicAuthPojo;
import com.pojo.Login.Data;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

/**
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see Used for LoginStep
 */
public class TC1_LoginStep extends BaseClass 
{
	static GlobalDatas globaldatas=new GlobalDatas();

	/**
	 * @see User add header
	 */
	@Given("User Add Header")
	public void user_add_header() {
		addHeader("accept", "application/json");
		System.out.println();
	}

	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @see User add basic authentication for login
	 */
	@When("User Add Basic Authentication for Login")
	public void user_add_basic_authentication_for_login() throws FileNotFoundException, IOException {
		addBasicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));
	}

	/**
	 * User sent "POST" request for login endpoints
	 * 
	 * @param requesttype
	 */
	@When("User sent {string} request for login endpoint")
	public void user_sent_request_for_login_endpoint(String requesttype) 
	{
		response=requestType(requesttype, Endpoints.POSTMANBASICAUTH);
		int actstatuscode=getResponseCode(response);
		globaldatas.setStatuscode(actstatuscode);
	}

	/**
	 * @see User verify the login response body first name and get the login token
	 *      saved
	 * @param firstname
	 */
	@Then("User verify the login response body first name present as {string} and get the login token saved")
	public void user_verify_the_login_response_body_first_name_present_as_and_get_the_login_token_saved(
			String expfirstname) 
	{
		BasicAuthPojo loginpojo = response.as(BasicAuthPojo.class);
		String actfirstname=loginpojo.getData().getFirst_name();
		System.out.println(actfirstname);
		
		Assert.assertEquals("Verify First name", expfirstname, actfirstname);
		
		HooksClass.sc.log("Verify status code Expected First Name: " +expfirstname +" Actual First Name : "+actfirstname);
		
		String logintoken=loginpojo.getData().getLogtoken();
		globaldatas.setLogintoken(logintoken);
	}

}
