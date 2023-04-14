package com.payload.addess;

import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.CityID_Input_Pojo;
import com.pojo.address.DeleteAddress_Input_Pojo;
import com.pojo.address.UpdateUserAddressPojo;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see Used for AddressPayload
 */
public class AddressPayload 
{
	/**
	 * @see For Address payload
	 * @param first_name
	 * @param last_name
	 * @param mobile
	 * @param apartment
	 * @param state
	 * @param city
	 * @param country
	 * @param zipcode
	 * @param address
	 * @param address_type
	 * @return
	 */
public AddUserAddress_Input_Pojo addUserAddressPayload(String first_name, String last_name, String mobile, String apartment, int state,
		int city, int country, String zipcode, String address, String address_type)
{
	AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo(first_name, last_name, mobile, apartment, state, city, country, zipcode, address, address_type);
	return addUserAddress_Input_Pojo;
}

/**
 * @see For Update user address payload
 * @param address_id
 * @param first_name
 * @param last_name
 * @param mobile
 * @param apartment
 * @param state
 * @param city
 * @param country
 * @param zipcode
 * @param address
 * @param address_type
 * @return
 */
public UpdateUserAddressPojo UpdateUserAddressPayload(String address_id, String first_name, String last_name, String mobile,
			String apartment, int state, int city, int country, String zipcode, String address, String address_type)
{
	UpdateUserAddressPojo updateUserAddressPojo = new UpdateUserAddressPojo(address_id, first_name, last_name, mobile, apartment, state, city, country, zipcode, address, address_type);
	return updateUserAddressPojo;
}

/**
 * @see For Delete address payload
 * @param address_id
 * @return
 */
public DeleteAddress_Input_Pojo DeleteAddressPayload(String address_id)
{
	DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(address_id);
	return deleteAddress_Input_Pojo;
}

/**
 * @see For CityID payload
 * @param state_id
 * @return
 */
public CityID_Input_Pojo CityIDPayload(String stateId)
{
	CityID_Input_Pojo cityID_Input_Pojo = new CityID_Input_Pojo(stateId);
	return cityID_Input_Pojo;
	
}
}
