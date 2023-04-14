package com.stepdefinition;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.global.GlobalDatas;
import com.payload.addess.AddressPayload;
import com.pojo.address.CityID_Input_Pojo;
import com.pojo.address.CityID_output_pojo;
import com.pojo.address.CityList;
import com.pojo.address.StateIPpojo;
import com.pojo.address.StateOPpojo;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/**
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see To Get the City ID 
 */
public class TC3_GetCityIdStep extends BaseClass
{
	static GlobalDatas globaldatas=new GlobalDatas();
	AddressPayload addressPayload = new AddressPayload();
	Response response;
	/**
	 * @See To Add Header for City List
	 */
	@Given("User add header for the CityList")
	public void user_add_header_for_the_city_list() 
	{
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h3 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h3);
		Headers header = new Headers(listHeader);
		addHeaders(header);
	}
	
	/**
	 * @see To Send The String stateid to get the Citylist
	 * @param stateid
	 */
	@When("User add req body  {string} for get citylist")
	public void user_add_req_body_for_get_citylist(String stateId) 
	{
		System.out.println(TC1_LoginStep.globaldatas.getStateid());
		System.out.println(TC1_LoginStep.globaldatas.getStateidnum());
		 CityID_Input_Pojo cityID_Input_Pojo = addressPayload.CityIDPayload(TC1_LoginStep.globaldatas.getStateid());
				 addBody(cityID_Input_Pojo);
				 
	}
	
	/**
	 * @see User send POST request type to get Citylist endpoint
	 * @param requesttype
	 */
	@When("User send {string} request for CityList endpoint")
	public void user_send_request_for_city_list_endpoint(String requesttype) 
	{
		response = requestType(requesttype, Endpoints.CITYLIST);
		int actstatuscode=getResponseCode(response);
		TC1_LoginStep.globaldatas.setStatuscode(actstatuscode);
	}
	
	/**
	 * @see To verify the Citylist response message
	 * @param cityname
	 */
	@Then("User verify the CityList response message matches {string}")
	public void user_verify_the_city_list_response_message_matches(String expcityname) 
	{
		CityID_output_pojo cityID_output_pojo = response.as(CityID_output_pojo.class);
		ArrayList<CityList> city = cityID_output_pojo.getData();
	
		for (CityList cityList : city) 
		{
		String actcityname=cityList.getName();
		if(actcityname.equals("Yercaud"))
		{
			int actcityidnum=cityList.getId();
			System.out.println("actcityname "+actcityname);
			
			System.out.println("actcityidnum "+actcityidnum);
			TC1_LoginStep.globaldatas.setCityId(actcityidnum);
			
			Assert.assertEquals("Verify City Name", expcityname, actcityname);
			
			HooksClass.sc.log("Verify status code Expected City Name: " +expcityname +" Actual City Name : "+actcityname);
		}
		}
	}



}
