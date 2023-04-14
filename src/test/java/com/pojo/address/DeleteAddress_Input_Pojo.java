package com.pojo.address;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see For accessing Delete Address input data
 */
public class DeleteAddress_Input_Pojo {
	 private String address_id;

	public String getAddress_id() {
		return address_id;
	}

	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}

	public DeleteAddress_Input_Pojo(String address_id) {
		super();
		this.address_id = address_id;
	} 
	 
	
	
}
