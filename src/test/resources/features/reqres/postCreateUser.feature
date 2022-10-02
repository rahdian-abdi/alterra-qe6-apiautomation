Feature: Post Create User Reqres.in
  @post @exercise
  Scenario: Create new user with valid parameter
    Given Post create new user
    When Send request post new user
    Then Should return 201 Created
    And Response body should contain name "Rahdian" and job "QA Engineer"
    And Post create new user json schema validator

  @post @tugas @negative
  Scenario: Create new user with empty body
    Given Post create new user with empty body
    When Send request post new user
    Then Should return 400 Created
    And Post user with empty body json schema

  @post @tugas @negative
  Scenario: Create new user with invalid parameter
    Given Post create new user
    When Send request post new user with invalid path
    Then Should return 400 Bad Request
    And Create new user with invalid parameter json schema

  @post @tugas @negative
  Scenario: Create new user without name body
    Given Post create new user without name body
    When Send request post new user
    Then Should return 400 Bad Request
    And Create new user without name body json schema

  @post @tugas @negative
  Scenario: Create new user without job body
    Given Post create new user without job body
    When Send request post new user
    Then Should return 400 Bad Request
    And Create new user without name job json schema