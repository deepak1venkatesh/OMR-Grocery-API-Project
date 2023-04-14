package com.stepdefinition;

import org.junit.Assert;

import io.cucumber.java.en.Then;

/**
 * @see Used to call common steps
 * @date 11-04-2023
 * @author Deepak Venkatesh PS
 *
 */
public class CommonStep {
	/**
	 * @see User verify the status code 
	 * @param statuscode
	 */
	@Then("User verify the status code is {int}")
	public void user_verify_the_status_code_is(int expstatuscode) 
	{
		int actstatuscode=TC1_LoginStep.globaldatas.getStatuscode();
		System.out.println(actstatuscode);
		Assert.assertEquals("Verify status code", expstatuscode, actstatuscode);
		
		HooksClass.sc.log("Verify status code Expected Message: " +expstatuscode +" Actual message : "+actstatuscode);
	}
	
}
