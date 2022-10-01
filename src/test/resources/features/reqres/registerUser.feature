Feature: Register User Reqres.in
  @post @tugas
  Scenario Outline: Register user with valid parameter and body request
    Given Post register user
    When Send request register
    Then Should return <status> OK
    And Response body should contain token "<token>"
    And Post register user json schema
    Examples:
      |token            |status|
      |QpwL5tke4Pnpja7X4|200  |

  @post @tugas
  Scenario Outline: Register user with empty password
    Given Post register user without password
    When Send request register
    Then Should return <status> Bad Request
    And Response body should contain message "<message>"
    And Post register user unsuccessful json schema
    Examples:
      |message         |status|
      |Missing password|400   |