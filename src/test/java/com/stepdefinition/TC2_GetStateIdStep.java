package com.stepdefinition;

import java.util.ArrayList;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.global.GlobalDatas;
import com.pojo.address.StateIPpojo;
import com.pojo.address.StateOPpojo;

import io.cucumber.java.en.*;
import io.restassured.response.Response;

/**
 * 
 * @author Deepak Venkatesh
 * @date 11-04-2023
 * @see To get the StateID
 *
 */
public class TC2_GetStateIdStep extends BaseClass
{
	static GlobalDatas globaldatas=new GlobalDatas();
	Response response;
	/**
	 * @see User add header for the statelist
	 */
	@Given("User add header for the StateList")
	public void user_add_header_for_the_state_list() 
	{
		addHeader("accept", "application/json");
	}
	
	/**
	 * @see User send "GET" request for statelist endpoint
	 * @param requesttype
	 */
	@When("User send {string} request for StateList endpoint")
	public void user_send_request_for_state_list_endpoint(String requesttype) 
	{
		response = requestType(requesttype, Endpoints.STATELIST);
		int actstatuscode=getResponseCode(response);
		TC1_LoginStep.globaldatas.setStatuscode(actstatuscode);
	}
	
	/**
	 * @see User verify the statelist response messge 
	 * @param statename
	 */
	@Then("User verify the StateList response message matches {string} and save the Stateid")
	public void user_verify_the_state_list_response_message_matches_and_save_the_stateid(String expstatename) 
	{
		StateIPpojo stateIPpojo = response.as(StateIPpojo.class);
		ArrayList<StateOPpojo> statelist = stateIPpojo.getData();
	
		for (StateOPpojo eachstatelist : statelist) 
		{
		String actstatename=eachstatelist.getName();
		
		
		if(actstatename.equals("Tamil Nadu"))
		{
			System.out.println("Act State name -->"+actstatename);
			int actstateidnum = eachstatelist.getId();
			TC1_LoginStep.globaldatas.setStateidnum(actstateidnum);
			String stateId=String.valueOf(actstateidnum);
			System.out.println("Act state id num -->"+actstateidnum);
			TC1_LoginStep.globaldatas.setStateid(stateId);
			System.out.println("String State id "+stateId);
			
			Assert.assertEquals("Verify state name", expstatename, actstatename);
	
			HooksClass.sc.log("Verify status code Expected State Name: " +expstatename +" Actual State Name : "+actstatename);

		}
		}
	}



}
