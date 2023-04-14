package com.pojo.product;

import java.util.ArrayList;

/**
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see For Search Product Input pojo
 */
public class SearchProduct_Input_Pojo {
	public int status;
	public String message;
	public ArrayList<SearchProduct_Output_Pojo> data;
	public String currency;

	public int getStatus() 
	{
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

	public ArrayList<SearchProduct_Output_Pojo> getData() {
		return data;
	}

	public void setData(ArrayList<SearchProduct_Output_Pojo> data) {
		this.data = data;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}
