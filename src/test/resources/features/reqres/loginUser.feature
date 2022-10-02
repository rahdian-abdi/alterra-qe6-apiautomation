Feature: Login User Reqres.in
  @post @tugas
  Scenario: Login user with valid body
    Given Login user with valid body request
    When Send request login
    Then Should return 200 OK
    And Response body should contain token "QpwL5tke4Pnpja7X4"
    And Login user successful json schema

  @post @tugas
  Scenario: Login user with invalid body
    Given Login user with invalid body request
    When Send request login
    Then Should return 400 Bad Request
    And Response body should contain message "Missing password"
    And Login user unsuccessful json schema