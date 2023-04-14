package com.pojo.address;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see For accessing UpdateUserAddress Input Data
 */
public class UpdateUserAddressPojo {
	public String address_id;
	public String first_name;
	public String last_name;
	public String mobile;
	public String apartment;
	public int state;
	public int city;
	public int country;
	public String zipcode;
	public String address;
	public String address_type;

	public UpdateUserAddressPojo(String address_id, String first_name, String last_name, String mobile,
			String apartment, int state, int city, int country, String zipcode, String address, String address_type) {
		super();
		this.address_id = address_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile = mobile;
		this.apartment = apartment;
		this.state = state;
		this.city = city;
		this.country = country;
		this.zipcode = zipcode;
		this.address = address;
		this.address_type = address_type;
	}

}
