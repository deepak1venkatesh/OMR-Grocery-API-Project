package com.pojo.address;

import java.util.ArrayList;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see For accessing Get Address Input Data
 */
public class GetAddress_Input_Pojo 
{
	public int status;
    public String message;
    public ArrayList<GetAddress_Output_Pojo> data;
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
	public ArrayList<GetAddress_Output_Pojo> getData() {
		return data;
	}
	public void setData(ArrayList<GetAddress_Output_Pojo> data) {
		this.data = data;
	}
    
    
}
