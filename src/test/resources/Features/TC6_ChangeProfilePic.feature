@ChangeProfilePicture
Feature: Change Profile Picture API automation

  Scenario: Verify User Change Profile through API
    Given Given User add Header and bearer authorization for accessing changeProfilePic endpoint
    When User add form data to provide file path
    And User send "POST" request for changeProfilePic endpoint
    Then User verify the status code is 200
    And User should verify the change profile pic response message matches "Profile updated Successfully"
