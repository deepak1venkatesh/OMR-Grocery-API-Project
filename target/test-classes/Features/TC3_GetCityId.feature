@City
Feature: City module API Automation

  Scenario: Verify User Get CityList through api
    Given User add header for the CityList
    When User add req body  "stateId" for get citylist
    And User send "POST" request for CityList endpoint
    Then User verify the status code is 200
    Then User verify the CityList response message matches "Yercaud"
