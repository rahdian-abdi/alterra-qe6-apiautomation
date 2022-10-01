Feature: Post Create User Reqres.in
  @post @exercise
  Scenario Outline: Create new user with valid parameter
    Given Post create new user
    When Send request post new user
    Then Should return <status> Created
    And Response body should contain name "<name>" and job "<job>"
    And Post create new user json schema validator
    Examples:
      |name   |job        |status|
      |Rahdian|QA Engineer|201   |

  @post @tugas @negative
  Scenario Outline: Create new user with empty body
    Given Post create new user with empty body
    When Send request post new user
    Then Should return <status> Created
    And Post user with empty body json schema
    Examples:
      |status|
      |400   |

  @post @tugas @negative
  Scenario Outline: Create new user with invalid parameter
    Given Post create new user
    When Send request post new user with invalid path
    Then Should return <status> Bad Request
    And Create new user with invalid parameter json schema
    Examples:
      |status|
      |400   |

  @post @tugas @negative
  Scenario Outline: Create new user without name body
    Given Post create new user without name body
    When Send request post new user
    Then Should return <status> Bad Request
    And Create new user without name body json schema
    Examples:
      |status|
      |400   |

  @post @tugas @negative
  Scenario Outline: Create new user without job body
    Given Post create new user without job body
    When Send request post new user
    Then Should return <status> Bad Request
    And Create new user without name job json schema
    Examples:
      |status|
      |400   |