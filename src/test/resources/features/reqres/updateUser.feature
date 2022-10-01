Feature: Update User Reqres.in
  @update @exercise
  Scenario Outline: Put update user with valid json
    Given Put update user with id <id>
    When Send request with put update user
    Then Should return <status> Created
    And Should return name "<name>" and job "<job>"
    And Put update user json schema validator
    Examples:
      |id |status |name         |job             |
      |1  |200    |Rahdian Edit |QA Engineer Edit|

  @update @tugas @negative
  Scenario Outline: Put update user with unregistered id
    Given Put update user with id <id>
    When Send request with put update user
    Then Should return <status> Not Found
    And Put update user with unregistered id json schema
    Examples:
      |id |status|
      |500  |404  |

  @update @tugas @negative
  Scenario Outline: Put update user with empty body req
    Given Put update user with id <id> and empty json
    When Send request with put update user
    Then Should return <status> Bad Request
    And Put update user with empty body json schema
    Examples:
      |id   |status |
      |500  |400    |

  @update @tugas @negative
  Scenario Outline: Put update user with invalid parameter id
    Given Put update user with id "<id>" and valid body request
    When Send request with put update user
    Then Should return <status> Bad Request
    Examples:
      |id    |status |
      |##### |400    |

  @update @tugas @negative
  Scenario Outline: Put update user with invalid body request
    Given Put update user with id <id> and invalid body request
    When Send request with put update user
    Then Should return <status> Bad Request
    And Put update user with invalid body request json schema
    Examples:
      |id|status |
      |1 |400    |