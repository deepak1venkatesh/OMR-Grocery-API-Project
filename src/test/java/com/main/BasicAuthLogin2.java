package com.main;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.endpoints.Endpoints;
import com.payload.addess.AddressPayload;
import com.payload.searchproduct.SearchProductPayload;
import com.pojo.Login.BasicAuthPojo;
import com.pojo.Login.Data;
import com.pojo.Login.Pivot;
import com.pojo.Login.UserRole;
import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.AddUserAddress_Output_Pojo;
import com.pojo.address.CityID_Input_Pojo;
import com.pojo.address.CityID_output_pojo;
import com.pojo.address.CityList;
import com.pojo.address.DeleteAddress_Input_Pojo;
import com.pojo.address.DeleteAddress_Output_Pojo;
import com.pojo.address.StateIPpojo;
import com.pojo.address.StateOPpojo;
import com.pojo.address.UpdateUserAddressOUT;
import com.pojo.address.UpdateUserAddressPojo;
import com.pojo.product.SearchProduct_Input_Pojo;
import com.pojo.product.SearchProduct_POJO;
import com.pojo.profilepic.UpdateProfilePic_Input_Pojo;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see For Accessing All methods
 */
public class BasicAuthLogin2 extends BaseClass {
	String logintoken;
	int id;
	String stateid;
	String name;

	String address_id;
	String first_name;
	String last_name;
	String mobile;
	String apartment;
	int state;
	int city;
	int country;
	String zipcode;
	String address;
	String address_type;

	AddressPayload addressPayload = new AddressPayload();
	SearchProductPayload profilePicPayload = new SearchProductPayload();

	/**
	 * @see For accessing Login Data
	 */
	@Test(priority = 0)
	public void login() {
		addHeader("accept", "application/json");
		addBasicAuth("deepak1venkatesh@gmail.com", "Password@123");
		Response response = requestType("POST", Endpoints.POSTMANBASICAUTH);
		int ActStatusCode = getResponseCode(response);
		System.out.println(ActStatusCode);
		Assert.assertEquals(ActStatusCode, 200, "Verify Status code");
		
		BasicAuthPojo as = response.as(BasicAuthPojo.class);
		String firstname=as.getData().getFirst_name();
		System.out.println(firstname);
		Assert.assertEquals(firstname, "Deepak", "Verify First name ");
		
		BasicAuthPojo bp = response.as(BasicAuthPojo.class);
		logintoken = bp.getData().getLogtoken();
	}

	/**
	 * @see for accessing  AddUserAddress Data
	 */
	@Test(priority = 1)
	public void addUserAddress() {
		// 1.Header
		List<Header> listheader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logintoken);
		Header h3 = new Header("Content-Type", "application/json");

		listheader.add(h1);
		listheader.add(h2);
		listheader.add(h3);

		Headers headers = new Headers(listheader);
		addHeaders(headers);

		// 2.payload
		AddUserAddress_Input_Pojo addUserAddressPayload = addressPayload.addUserAddressPayload("Deepak", "Venkatesh",
				"9876543210", "Madras M2 Apartments", 35, 4440, 101, "600092", "Virugambakkam", "home");
		addBody(addUserAddressPayload);

		// 3.Method Type
		Response response = requestType("POST", Endpoints.ADDUSERADDRESS);
		int ActStatusCode = getResponseCode(response);
		System.out.println("Address Status Code " + ActStatusCode);
		Assert.assertEquals(ActStatusCode, 200, "Verify Status Code");

		AddUserAddress_Output_Pojo address_Output_Pojo = response.as(AddUserAddress_Output_Pojo.class);
		String actmsg = address_Output_Pojo.getMessage();
		System.out.println(actmsg);
		int address_id2 = address_Output_Pojo.address_id;
		address_id = String.valueOf(address_id2);
		Assert.assertEquals(actmsg, "Address added successfully", "Verify Address added Successfully");
	}

	/**
	 * @see For accessing StateID
	 */
	@Test(priority = 2)
	public void FindStateIdByStateName() {
		addHeader("accept", "application/json");
		addBasicAuth("deepak1venkatesh@gmail.com", "Password@123");
		Response response = requestType("GET", Endpoints.STATELIST);
		int ActStatusCode = getResponseCode(response);
		System.out.println("State Status Code " + ActStatusCode);

		// StateId_Input_pojo stateId_Input_pojo =
		// response.as(StateId_Input_pojo.class);
		// ArrayList<StateId_Output_Pojo> StateList = stateId_Input_pojo.getData();
		//
		StateIPpojo iPpojo = response.as(StateIPpojo.class);
		ArrayList<StateOPpojo> StateList = iPpojo.getData();

		for (StateOPpojo eachstatelist : StateList) {
			String eachstatename = eachstatelist.getName();
			if (eachstatename.equals("Tamil Nadu")) {
				System.out.println("State Name : " + eachstatename);
				int actstateidnum = eachstatelist.getId();
				stateid = String.valueOf(actstateidnum);
				System.out.println("State ID : " + actstateidnum);
				System.out.println(actstateidnum);
				Assert.assertEquals(eachstatename, "Tamil Nadu", "Verify the state name is present TN");
			}
		}
	}

	/**
	 * @see For accessing CityID
	 */
	@Test(priority = 3)
	public void FindCityIdByCityName() {
		List<Header> listHeader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h3 = new Header("Content-Type", "application/json");
		listHeader.add(h1);
		listHeader.add(h3);
		Headers header = new Headers(listHeader);
		addHeaders(header);

		// CityID_Input_Pojo cityID_Input_Pojo = new CityID_Input_Pojo(stateid);
		// addBody(cityID_Input_Pojo);

		CityID_Input_Pojo cityidpayload = addressPayload.CityIDPayload(stateid);
		addBody(cityidpayload);

		Response res = requestType("POST", Endpoints.CITYLIST);
		CityID_output_pojo as = res.as(CityID_output_pojo.class);
		ArrayList<CityList> citylist = as.getData();
		for (CityList eachCityList : citylist) {
			String eachCityName = eachCityList.getName();
			if (eachCityName.equals("Madurai")) {
				String name2 = eachCityList.getName();
				Assert.assertEquals(name2, "Madurai");
				int actCityId = eachCityList.getId();
			}
		}

	}

	/**
	 * @see For Accessing UpdateUserAddress
	 */
	@Test(priority = 5)
	public void UpdateUserAddress() {
		// 1.Header
		List<Header> listheader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logintoken);
		Header h3 = new Header("Content-Type", "application/json");

		listheader.add(h1);
		listheader.add(h2);
		listheader.add(h3);

		Headers headers = new Headers(listheader);
		addHeaders(headers);

		// 2.payload

		// UpdateUserAddressPojo
		// UpdateuseraddressPayload=addressPayload.UpdateUserAddressPayload(address_id,
		// "Deepak", "Venkatesh", "9887654231","DLF cybercity", 35, 4440, 101, "600092",
		// "chennai", "Home");

		UpdateUserAddressPojo UpdateuseraddressPayload = addressPayload.UpdateUserAddressPayload(address_id, "Deepak",
				"Venkatesh", "9887654231", "DLF cybercity", 35, 4440, 101, "600092", "chennai", "Home");
		addBody(UpdateuseraddressPayload);
		// UpdateUserAddressPojo userAddressPojo = new UpdateUserAddressPojo(
		// address_id, "Deepak", "Venkatesh", "9887654231",
		// "DLF cybercity", 35, 4440, 101, "600092", "chennai", "Home");

		// 3.Type
		Response response = requestType("PUT", Endpoints.UPDATEUSERADDRESS);
		int actstatuscode = getResponseCode(response);

		System.out.println("Update User address Status code " + actstatuscode);
		Assert.assertEquals(actstatuscode, 200, "verify status code for UpdateUserAddress");

		UpdateUserAddressOUT userAddressOUT = response.as(UpdateUserAddressOUT.class);
		String ActMessage = userAddressOUT.getMessage();
		System.out.println("UpdateUserAddress message " + ActMessage);

		Assert.assertEquals(ActMessage, "Address updated successfully", "Verify UserAddress Updation Successfully");
	}

	/**
	 * @see For accessing DeleteAddresss
	 */
	@Test(priority = 6)
	public void deleteAddress() {
		// 1.header
		List<Header> listheader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logintoken);
		Header h3 = new Header("Content-Type", "application/json");

		listheader.add(h1);
		listheader.add(h2);
		listheader.add(h3);

		Headers headers = new Headers(listheader);
		addHeaders(headers);

		// 2.payload
		// DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new
		// DeleteAddress_Input_Pojo(address_id);
		// addBody(deleteAddress_Input_Pojo);

		DeleteAddress_Input_Pojo deleteaddresspayload = addressPayload.DeleteAddressPayload(address_id);
		addBody(deleteaddresspayload);

		Response response = requestType("DELETE", Endpoints.DELETEUSERADDRESS);

		int responsecode = getResponseCode(response);
		System.out.println(responsecode);
		Assert.assertEquals(responsecode, 200, "Verify the Delete message");

		DeleteAddress_Output_Pojo deleteAddress_Output_Pojo = response.as(DeleteAddress_Output_Pojo.class);
		String actMessage = deleteAddress_Output_Pojo.getMessage();
		System.out.println(actMessage);
		Assert.assertEquals(actMessage, "Address deleted successfully");

	}

	/**
	 * @see For accessing Searchproduct
	 */
	@Test(priority = 7)
	private void searchproduct() {
		List<Header> listheader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h3 = new Header("Content-Type", "application/json");

		listheader.add(h1);
		listheader.add(h3);

		Headers headers = new Headers(listheader);
		addHeaders(headers);

		// SearchProduct_POJO searchpojo = new SearchProduct_POJO("nuts");
		// addBody(searchpojo);

		SearchProduct_POJO searchpayload = profilePicPayload.searchproductPayload("nuts");
		addBody(searchpayload);

		Response response = requestType("POST", Endpoints.SEARCHPRODUCT);

		int actstatuscode = getResponseCode(response);
		System.out.println(actstatuscode);
		Assert.assertEquals(actstatuscode, 200, "Verify Get product status code");

		SearchProduct_Input_Pojo product_Input_Pojo = response.as(SearchProduct_Input_Pojo.class);
		String actmessage = product_Input_Pojo.getMessage();

		System.out.println("Product message --> " + actmessage);

		Assert.assertEquals(actmessage, "OK", "Verify search product Message");
	}

	@Test(priority = 8)
	private void UpdateProfilePicture() {
		// 1.header

		List<Header> listheader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logintoken);
		Header h3 = new Header("Content-Type", "multipart/form-data");

		listheader.add(h1);
		listheader.add(h2);
		listheader.add(h3);

		Headers headers = new Headers(listheader);
		addHeaders(headers);

		// 2.

		addFormData("profile_picture", new File("C:\\Users\\Admin\\Desktop\\d word.jpg"));
		Response response = requestType("POST", Endpoints.CHANGEPROFILEPIC);
		int actstatuscode = getResponseCode(response);
		System.out.println("Profile picture Status Code -->" + actstatuscode);
		assertEquals(actstatuscode, 200, "Verify profile picture status code");

		UpdateProfilePic_Input_Pojo updateProfilePic_Input_Pojo = response.as(UpdateProfilePic_Input_Pojo.class);
		String updatestatus = updateProfilePic_Input_Pojo.getMessage();
		System.out.println("Update profile picture status message --> " + updatestatus);
		Assert.assertEquals(updatestatus, "Profile updated Successfully", "Verify the Profile picture Success message");
	}
}
