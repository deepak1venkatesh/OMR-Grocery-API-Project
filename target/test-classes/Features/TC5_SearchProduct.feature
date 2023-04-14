@SearchProduct
Feature: Search product module API automation

  Scenario Outline: Verify User Search Product through API
    Given User add headers to Search Product
    When User add request body for get Search Product "<text>"
    And User Send "POST" request for Search Product endpoint
    Then User verify the status code is 200
    Then User verify the Search Product response message matched "OK"

    Examples: 
      | text |
      | nuts |
