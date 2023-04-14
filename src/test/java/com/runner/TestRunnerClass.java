package com.runner;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.base.BaseClass;
import com.reports.Reporting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * 
 * @author Deepak Venkatesh PS
 * @date 11-04-2023
 * @see Used to run Test Runner Class
 */
@RunWith(Cucumber.class)
@CucumberOptions(dryRun = false, tags = (""), features = "src\\test\\resources\\Features", glue = "com.stepdefinition", plugin = "json:target/index.json")
public class TestRunnerClass extends BaseClass 
{
	@AfterClass
	public static void afterClass() throws FileNotFoundException, IOException {
		Reporting.generateJVMreport(getprojectpath() + getPropertyFileValue("jsonpath"));
	}

}
