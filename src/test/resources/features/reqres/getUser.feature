Feature: Get user Reqres.in
  @get @tugas @positive
  Scenario Outline: Get user with valid id
    Given Get user with id <id>
    When Send request user
    Then Should return <status> OK
    And Should return email "<email>"
    And Get user with valid id json schema
    Examples:
      |id|status|email                 |
      |1 |200   |george.bluth@reqres.in|
      |3 |200   |emma.wong@reqres.in   |
      |6 |200   |tracey.ramos@reqres.in|

  @get @tugas @positive
  Scenario Outline: Get user with non exist id
    Given Get user with id <id>
    When Send request user
    Then Should return <status> Not Found
    And Should return empty data
    Examples:
      |id   |status|
      |200  |404   |

  @get @tugas @positive
  Scenario Outline: Get user with invalid id
    Given Get user with id "<id>"
    When Send request user
    Then Should return <status> Not Found
    And Should return empty data
    Examples:
      |id     |status|
      |!@#$%  |404   |