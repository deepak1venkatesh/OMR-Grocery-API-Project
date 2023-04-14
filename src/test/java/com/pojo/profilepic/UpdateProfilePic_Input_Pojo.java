package com.pojo.profilepic;

/**
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see UpdateProfilePicture Input pojo
 */
public class UpdateProfilePic_Input_Pojo 
{
	public int status;
    public String message;
    public UpdateProfilePic_Output_Pojo data;
    public int cart_count;
    
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
	public UpdateProfilePic_Output_Pojo getData() {
		return data;
	}
	public void setData(UpdateProfilePic_Output_Pojo data) {
		this.data = data;
	}
	public int getCart_count() {
		return cart_count;
	}
	public void setCart_count(int cart_count) {
		this.cart_count = cart_count;
	}
    
    
}
