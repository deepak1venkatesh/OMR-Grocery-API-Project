package com.stepdefinition;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.global.GlobalDatas;
import com.pojo.profilepic.UpdateProfilePic_Input_Pojo;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see Used for Changing Profile Picture
 */
public class TC6_ChangeProfilePicStep extends BaseClass
{
	Response response;
	static GlobalDatas globaldatas = new GlobalDatas();
	/**
	 * @see User to add Header and bearer authorization for accessing Change profile picture endpoint
	 */
	@Given("Given User add Header and bearer authorization for accessing changeProfilePic endpoint")
	public void given_user_add_header_and_bearer_authorization_for_accessing_change_profile_pic_endpoint() 
	{
		List<Header> listheader = new ArrayList<Header>();

		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogintoken());
		Header h3 = new Header("Content-Type", "multipart/form-data");

		listheader.add(h1);
		listheader.add(h2);
		listheader.add(h3);

		Headers header = new Headers(listheader);
		addHeaders(header);
	}
	
	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @see to add form data 
	 */
	@When("User add form data to provide file path")
	public void user_add_form_data_to_provide_file_path() throws FileNotFoundException, IOException 
	{
		addFormData("profile_picture", new File(getprojectpath()+getPropertyFileValue("profilePicPath")));
	}
	
	/**
	 * @see User send POST request for the changeProfilePic endpoint
	 * @param requesttype
	 */
	@When("User send {string} request for changeProfilePic endpoint")
	public void user_send_request_for_change_profile_pic_endpoint(String requesttype) 
	{
		response = requestType(requesttype, Endpoints.CHANGEPROFILEPIC);
		int actStatuscode = getResponseCode(response);
		System.out.println("Real status code  "+actStatuscode);
		TC1_LoginStep.globaldatas.setStatuscode(actStatuscode);
		
	}
	
	/**
	 * @see User should verify the response message that matches "Profile updated Successfully"
	 * @param Sucessmsg
	 */
	@Then("User should verify the change profile pic response message matches {string}")
	public void user_should_verify_the_change_profile_pic_response_message_matches(String ExpSucessmsg) 
	{
		UpdateProfilePic_Input_Pojo profilePic_Input_Pojo = response.as(UpdateProfilePic_Input_Pojo.class);
		String actmessage = profilePic_Input_Pojo.getMessage();
		Assert.assertEquals("Verify update profile picture", ExpSucessmsg, actmessage);
		
		HooksClass.sc.log("Verify status code Expected Message: " +ExpSucessmsg +" Actual message : "+actmessage);
	}



}
