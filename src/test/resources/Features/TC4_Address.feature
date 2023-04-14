@Address
Feature: Address module API Automation

  Scenario Outline: Verify add User address to the application through api
    Given User add header and bearer authorization for accessing address endpoint
    When User add request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>",101,"<zipcode>","<address>" and "<address_type>"
    And User send "POST" request for add User Address endpoint
    Then User verify the status code is 200
    Then User verify the added User Address response message "Address added successfully" and get the addressId

    Examples: 
      | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address | address_type |
      | Deepak     | Venkatesh | 9876543210 | L&T       |    35 | 4171 |     101 |  600092 | chennai | home         |
	
  Scenario Outline: Verify update user address to the application through api
    Given User add header and bearer authorization for accessing address endpoints
    When User add request body for add new address "<address_id>","<first_name>","<last_name>","<mobile>","<apartment>","<state>","<city>",101,"<zipcode>","<address>" and "<address_type>"
    And User send "PUT" request for update User Address endpoint
    Then User verify the status code is 200
    And User verify the Updated User Address response message matches "Address updated successfully"

    Examples: 
      | address_id | first_name | last_name | mobile     | apartment | state | city | country | zipcode | address | address_type |
      |       5666 | Deepak     | Venkatesh | 9876543210 | L&T       |    35 | 4171 |     101 |  600092 | chennai | Home         |

  Scenario: Verify get user address to the application through api
    Given User add header and bearer authorization for accessing get address
    When User send "GET" request for get user address
    Then User verify the status code is 200
    And User verify the get user address response messages matches "OK"

  Scenario Outline: Verify user delete address through api
    Given User add header and bearer authorization for accessing address endpointss
    When User add request body for delete address "<address_id>"
    And User send "DELETE" request for delete User Address endpoint
    Then User verify the status code is 200
    And User verify the delete User Address response message matches "Address deleted successfully"

    Examples: 
      | address_id |
      |       5666 |
