Feature: Delete User Reqres.in
  @delete @exercise
  Scenario Outline: Delete user with valid parameter
    Given Delete user with valid <id>
    When Send request delete user
    Then Should return <status> No Content
    Examples:
      |id |status |
      |1  |204    |

  @delete @tugas @negative
  Scenario Outline: Delete user with unregistered user
    Given Delete user with valid <id>
    When Send request delete user
    Then Should return <status> Not Found
    Examples:
      |id   |status |
      |4000 |404    |

  @delete @tugas @negative
  Scenario Outline: Delete user with already deleted user
    Given Delete user with valid <id>
    When Send request delete user
    And Send request delete user
    Then Should return <status> Not Found
    Examples:
      |id   |status |
      |1    |404    |

  @delete @tugas @negative
  Scenario Outline: Delete user with invalid parameter
    Given Delete user with valid <id> and set path "<path>"
    When Send request delete user
    Then Should return <status> Bad Request
    Examples:
      |id   |path     |status |
      |1    |!@#$%    |404    |