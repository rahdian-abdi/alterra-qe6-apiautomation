Feature: Login User Reqres.in
  @post @tugas
  Scenario Outline: Login user with valid body
    Given Login user with valid body request
    When Send request login
    Then Should return <status> OK
    And Response body should contain token "<token>"
    And Login user successful json schema
    Examples:
      |token            |status|
      |QpwL5tke4Pnpja7X4|200   |

  @post @tugas
  Scenario Outline: Login user with invalid body
    Given Login user with invalid body request
    When Send request login
    Then Should return <status> Bad Request
    And Response body should contain message "<error>"
    And Login user unsuccessful json schema
    Examples:
      |error            |status|
      |Missing password |400   |