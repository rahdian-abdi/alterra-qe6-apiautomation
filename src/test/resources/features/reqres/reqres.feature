Feature: Test Reqres.in
  Scenario: Get list user with valid page
    Given Get list user with parameter page 2
    When Send request get list user
    Then Should return 200 OK
    And Response body page should be 2

    Scenario: Create new user with valid parameter
      Given Post create new user
      When Send request post new user
      Then Should return 201 Created
      And Response body should contain name "Rahdian" and job "QE Engineer"