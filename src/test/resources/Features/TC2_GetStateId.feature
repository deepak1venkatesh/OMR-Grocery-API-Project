@State
Feature: State Module API Automation

  Scenario: Verify User get StateList through API
    Given User add header for the StateList
    When User send "GET" request for StateList endpoint
    Then User verify the status code is 200
    Then User verify the StateList response message matches "Tamil Nadu" and save the Stateid
