Feature: Register User Reqres.in
  @post @tugas
  Scenario: Register user with valid parameter and body request
    Given Post register user
    When Send request register
    Then Should return 200 OK
    And Response body should contain token "QpwL5tke4Pnpja7X4"
    And Post register user json schema

  @post @tugas
  Scenario: Register user with empty password
    Given Post register user without password
    When Send request register
    Then Should return 400 Bad Request
    And Response body should contain message "Missing password"
    And Post register user unsuccessful json schema