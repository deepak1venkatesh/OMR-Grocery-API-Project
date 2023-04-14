@Login
Feature: Login Module API Automation
Scenario: Get User logtoken from login endpoint

Given User Add Header
When User Add Basic Authentication for Login
And User sent "POST" request for login endpoint
Then User verify the status code is 200
Then User verify the login response body first name present as "Deepak" and get the login token saved