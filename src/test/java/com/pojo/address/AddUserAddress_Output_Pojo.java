package com.pojo.address;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see For accessing AddUserAddress output Data
 */
public class AddUserAddress_Output_Pojo {
	public int status;
	public String message;
	public int address_id;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

}
