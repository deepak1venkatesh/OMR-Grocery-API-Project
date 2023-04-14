package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.global.GlobalDatas;
import com.payload.addess.AddressPayload;
import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.AddUserAddress_Output_Pojo;
import com.pojo.address.DeleteAddress_Input_Pojo;
import com.pojo.address.DeleteAddress_Output_Pojo;
import com.pojo.address.GetAddress_Input_Pojo;
import com.pojo.address.UpdateUserAddressOUT;
import com.pojo.address.UpdateUserAddressPojo;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see To get the Address Step
 */
public class TC4_AddressStep extends BaseClass {
	static GlobalDatas globaldatas = new GlobalDatas();
	AddressPayload addressPayload = new AddressPayload();
	Response response;

	/**
	 * @see User to add header and bearer authorization for accessing address
	 *      endpoint
	 */
	@Given("User add header and bearer authorization for accessing address endpoint")
	public void user_add_header_and_bearer_authorization_for_accessing_address_endpoint() {
		List<Header> listheader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogintoken());
		Header h3 = new Header("Content-Type", "application/json");

		listheader.add(h1);
		listheader.add(h2);
		listheader.add(h3);

		Headers headers = new Headers(listheader);
		addHeaders(headers);
	}

	/**
	 * @see User add request body for add new address details
	 * @param firstname
	 * @param lastname
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 */
	@When("User add request body for add new address {string},{string},{string},{string},{string},{string},{int},{string},{string} and {string}")
	public void user_add_request_body_for_add_new_address_and(String firstname, String lastname, String mobile,
			String apartment, String state, String city, Integer country, String zipcode, String address,
			String address_type) {
		AddUserAddress_Input_Pojo addUserAddressPayload = addressPayload.addUserAddressPayload(firstname, lastname,
				mobile, apartment, TC1_LoginStep.globaldatas.getStateidnum(), TC1_LoginStep.globaldatas.getCityId(),
				country, zipcode, address, address_type);
		addBody(addUserAddressPayload);
	}

	/**
	 * @see User send "POST" request for addding address endpoints
	 * @param string
	 */
	@When("User send {string} request for add User Address endpoint")
	public void user_send_request_for_add_user_address_endpoint(String requesttype) {
		response = requestType(requesttype, Endpoints.ADDUSERADDRESS);
		int ActStatusCode = getResponseCode(response);
		TC1_LoginStep.globaldatas.setStatuscode(ActStatusCode);

//		System.out.println("Address Status Code " + ActStatusCode);
		
	}

	/**
	 * @see User Verify tbe User address Response message and get the address id
	 * @param string
	 */
	@Then("User verify the added User Address response message {string} and get the addressId")
	public void user_verify_the_added_user_address_response_message_and_get_the_address_id(String actsuccessmsgs) {
		AddUserAddress_Output_Pojo address_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String expsuccessmsg = address_Output_Pojo.getMessage();
		System.out.println(expsuccessmsg);
		Assert.assertEquals("verify Address code", actsuccessmsgs, expsuccessmsg);
		
		HooksClass.sc.log("Verify status code Expected Message: " +actsuccessmsgs +" Actual message : "+expsuccessmsg);
		
		AddUserAddress_Output_Pojo output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		int address_id2 = output_Pojo.getAddress_id();
		
		String address_id = String.valueOf(address_id2);
		System.out.println("Saving address id " + address_id);
		
		TC1_LoginStep.globaldatas.setAddress_id(address_id);
		// TC1_LoginStep.globaldatas.setAddress_id(address_id);
		// System.out.println("getting address id
		// "+TC1_LoginStep.globaldatas.getAddress_id());
	}

	// 2.
	/**
	 * @see User add request body for add new address details
	 * @param address_id
	 * @param firstname
	 * @param lastname
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 */
	@When("User add request body for add new address {string},{string},{string},{string},{string},{string},{string},{int},{string},{string} and {string}")
	public void user_add_request_body_for_add_new_address_and(String address_id, String firstname, String lastname,
			String mobile, String apartment, String state, String city, Integer country, String zipcode, String address,
			String address_type) {
		UpdateUserAddressPojo updateUserAddressPayload = addressPayload.UpdateUserAddressPayload(TC1_LoginStep.globaldatas.getAddress_id(), firstname, lastname, mobile, apartment, TC1_LoginStep.globaldatas.getStateidnum(), TC1_LoginStep.globaldatas.getCityId(), country, zipcode, address, address_type);
//		System.out.println(updateUserAddressPayload);
//		System.out.println(TC1_LoginStep.globaldatas.getAddress_id());
//		System.out.println(TC1_LoginStep.globaldatas.getCityId());
//		System.out.println(TC1_LoginStep.globaldatas.getStateidnum());
//		System.out.println(TC1_LoginStep.globaldatas.getStateid());
		addBody(updateUserAddressPayload);
	}

	/**
	 * @see user send "PUT" request for update user address endpoint
	 * @param requesttype
	 */
	@When("User send {string} request for update User Address endpoint")
	public void user_send_request_for_update_user_address_endpoint(String requesttype) 
	{
		response = requestType(requesttype, Endpoints.UPDATEUSERADDRESS);
		int actstatuscode = getResponseCode(response);
        TC1_LoginStep.globaldatas.setStatuscode(actstatuscode);
	}

	/**
	 * @see user verify the updated User address response message
	 * @param SuccessMessage
	 */
	@Then("User verify the Updated User Address response message matches {string}")
	public void user_verify_the_updated_user_address_response_message_matches(String SuccessMessage) {
		UpdateUserAddressOUT userAddressOUT = response.as(UpdateUserAddressOUT.class);
		String ActMessage = userAddressOUT.getMessage();
		System.out.println("ActMessage of Update USER ADDRESS "+ActMessage);
		Assert.assertEquals("Verify Status code", SuccessMessage, ActMessage);
		
		HooksClass.sc.log("Verify status code Expected Message: " +SuccessMessage +" Actual message : "+ActMessage);
		
		
	}

	/**
	 * @see User add header and bearer authorization for accessing endpoints
	 */
	@Given("User add header and bearer authorization for accessing address endpoints")
	public void user_add_header_and_bearer_authorization_for_accessing_address_endpoints() {
		List<Header> listheader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogintoken());
		Header h3 = new Header("Content-Type", "application/json");

		listheader.add(h1);
		listheader.add(h2);
		listheader.add(h3);

		Headers headers = new Headers(listheader);
		addHeaders(headers);
	}

	// 3.

	/**
	 * @see user add header and bearer authorization for accessing get address
	 */
	@Given("User add header and bearer authorization for accessing get address")
	public void user_add_header_and_bearer_authorization_for_accessing_get_address() 
	{
		List<Header> listHeader = new ArrayList<Header>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogintoken());

		listHeader.add(h1);
		listHeader.add(h2);

		Headers headers = new Headers(listHeader);
		addHeaders(headers);
	}

	/**
	 * @see User send "GET" request for get user address
	 * @param requesttype
	 */
	@When("User send {string} request for get user address")
	public void user_send_request_for_get_user_address(String requesttype) 
	{
		response=requestType(requesttype, Endpoints.GETUSERADDRESS);
		int actStatusCode = getResponseCode(response);
		TC1_LoginStep.globaldatas.setStatuscode(actStatusCode);
	}

	/**
	 * @see User verify the get user address response message
	 * @param successMSG
	 */
	@Then("User verify the get user address response messages matches {string}")
	public void user_verify_the_get_user_address_response_messages_matches(String successMSG) 
	{
		GetAddress_Input_Pojo getAddress_Input_Pojo = response.as(GetAddress_Input_Pojo.class);
		String message=getAddress_Input_Pojo.getMessage();
		System.out.println("Update user address message "+message);
		Assert.assertEquals("Verify Update User Address message", successMSG, message);
		
		HooksClass.sc.log("Verify status code Expected Message: " +successMSG +" Actual message : "+message);
	
	}
	// 4.
	/**
	 * @see User adad request body for delete address
	 * @param address_id
	 */
	@When("User add request body for delete address {string}")
	public void user_add_request_body_for_delete_address(String address_id) 
	{
		DeleteAddress_Input_Pojo deleteAddressPayload = addressPayload.DeleteAddressPayload(TC1_LoginStep.globaldatas.getAddress_id());
		addBody(deleteAddressPayload);
	}

	/**
	 * @see User send "DELETE" request for delete user address endpoint
	 * @param requesttype
	 */
	@When("User send {string} request for delete User Address endpoint")
	public void user_send_request_for_delete_user_address_endpoint(String requesttype) 
	{
		response=requestType(requesttype, Endpoints.DELETEUSERADDRESS);
		int actStatusCode = getResponseCode(response);
		TC1_LoginStep.globaldatas.setStatuscode(actStatusCode);
	}

	/**
	 * @see User verify the delete user address response message
	 * @param successmsg
	 */
	@Then("User verify the delete User Address response message matches {string}")
	public void user_verify_the_delete_user_address_response_message_matches(String expsuccessmsg) 
	{
		 DeleteAddress_Output_Pojo deleteAddress_Output_Pojo = response.as(DeleteAddress_Output_Pojo.class);
		String actsuccessmsg = deleteAddress_Output_Pojo.getMessage();
		System.out.println("Actual Success msg of Delete user address"+actsuccessmsg);
		Assert.assertEquals("Verify delete user address message", expsuccessmsg, actsuccessmsg);
		
		HooksClass.sc.log("Verify status code Expected Message: " +expsuccessmsg +" Actual message : "+actsuccessmsg);
	}

	/**
	 * @see User add header and bearer authorization for accessing address endpoints
	 */
	@Given("User add header and bearer authorization for accessing address endpointss")
	public void user_add_header_and_bearer_authorization_for_accessing_address_endpointss() 
	{
		List<Header> listheader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + TC1_LoginStep.globaldatas.getLogintoken());
		Header h3 = new Header("Content-Type", "application/json");

		listheader.add(h1);
		listheader.add(h2);
		listheader.add(h3);

		Headers headers = new Headers(listheader);
		addHeaders(headers);
	}
}
