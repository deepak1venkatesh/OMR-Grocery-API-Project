package com.pojo.address;

import java.util.ArrayList;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see For accessing State Input Data 
 */
public class StateIPpojo 
{
	public int status;
    public String message;
    public ArrayList<StateOPpojo> data;
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
	public ArrayList<StateOPpojo> getData() {
		return data;
	}
	public void setData(ArrayList<StateOPpojo> data) {
		this.data = data;
	}
    
    
}
