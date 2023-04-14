package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.payload.searchproduct.SearchProductPayload;
import com.pojo.product.SearchProduct_Input_Pojo;
import com.pojo.product.SearchProduct_POJO;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see Used for Search Product 
 */
public class TC5_SearchProductStep extends BaseClass {
	Response response;
	SearchProductPayload searchProductPayload=new SearchProductPayload();
	
	/**
	 * @see User to add headers for Search product
	 */
	@Given("User add headers to Search Product")
	public void user_add_headers_to_search_product() 
	{
		List<Header> listheader = new ArrayList<Header>();

		Header h1 = new Header("accept", "application/json");
		Header h3 = new Header("Content-Type", "application/json");

		listheader.add(h1);
		listheader.add(h3);

		Headers header = new Headers(listheader);
		addHeaders(header);
	}

	/**
	 * @see User to add request body to get search product 
	 * @param productname
	 */
	@When("User add request body for get Search Product {string}")
	public void user_add_request_body_for_get_search_product(String productname) 
	{
		SearchProduct_POJO searchproductPayload2 = searchProductPayload.searchproductPayload(productname);
		addBody(searchproductPayload2);
	}

	/**
	 * @see User Send "POST" request for search product endpoint
	 * @param requesttype
	 */
	@When("User Send {string} request for Search Product endpoint")
	public void user_send_request_for_search_product_endpoint(String requesttype) 
	{
		response=requestType(requesttype, Endpoints.SEARCHPRODUCT);
		int actstatuscode = getResponseCode(response);
		TC1_LoginStep.globaldatas.setStatuscode(actstatuscode);
	}

	/**
	 * @see User to verify Search product response message 
	 * @param successmsg
	 */
	@Then("User verify the Search Product response message matched {string}")
	public void user_verify_the_search_product_response_message_matched(String successmsg) 
	{
		SearchProduct_Input_Pojo as = response.as(SearchProduct_Input_Pojo.class);
		String expmessage = as.getMessage();
		Assert.assertEquals("Verify search product success message", successmsg, expmessage);
		
		HooksClass.sc.log("Verify status code Expected Message: " +successmsg +" Actual message : "+expmessage);
	}

}
