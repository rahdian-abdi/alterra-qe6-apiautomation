Feature: Test Reqres.in
  @get @exercise @positive
  Scenario Outline: Get list user with valid page
    Given Get list user with parameter page <page>
    When Send request get list user
    Then Should return <status> OK
    And Response body page should be <page>
    And Get list user json schema validator
    Examples:
    |page|status|
    |2   |200   |

  @get @tugas @positive
  Scenario Outline: Get all list user
    When Send request get all user
    Then Should return <status> OK
    And Get list all user with json schema validator
    Examples:
      |status|
      |200   |

  @get @tugas @negative
  Scenario Outline: Get list user without valid page
    When Send request get list user without page query
    Then Should return <status> Bad Request
    And Get list user without page query json schema
    Examples:
      |status|
      |400   |

  @get @tugas @positive
  Scenario Outline: Get list user with non exist page
    Given Get list user with parameter page <page>
    When Send request get list user
    Then Should return <status> OK
    And Response body should contain empty data
    And Get list user with non exist page json schema
    Examples:
      |page  |status|
      |500   |200   |

  @get @tugas @negative
  Scenario Outline: Get list user with invalid page
    Given Get list user with parameter page "<page>"
    When Send request get list user
    Then Should return <status> Bad Request
    And Get list user with invalid page json schema
    Examples:
      |page |status|
      |#####|400   |

  @get @tugas @negative
  Scenario Outline: Get list user with wrong page title
    When Send request with wrong page title
    Then Should return <status> Bad Request
    And Get list user with wrong title page json schema
    Examples:
      |status|
      |400   |

  @get @tugas @negative
  Scenario Outline: Get list user without question mark
    When Send request without question mark
    Then Should return <status> Bad Request
    And Get list user without question mark json schema
    Examples:
      |status|
      |400   |

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
    Then Should return <status> Bad Request
    Examples:
      |id |status|
      |500  |400  |

  @update @tugas @negative
  Scenario Outline: Put update user with empty body req
    Given Put update user with id <id> and empty json
    When Send request with put update user
    Then Should return <status> Bad Request
    Examples:
      |id   |status |
      |500  |400    |

  @update @tugas @negative
  Scenario Outline: Put update user with invalid parameter id
    Given Put update user with id "<id>" and empty json
    When Send request with put update user
    Then Should return <status> Bad Request
    Examples:
      |id    |status |
      |!@#$% |400    |

  @update @tugas @negative
  Scenario Outline: Put update user with invalid body request
    Given Put update user with id <id> and invalid body request
    When Send request with put update user
    Then Should return <status> Bad Request
    Examples:
      |id|status |
      |1 |400    |

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

    #REGISTER LOGIN
  @post @tugas
  Scenario Outline: Register user with valid parameter and body request
    Given Post register user
    When Send request register
    Then Should return <status> OK
    And Response body should contain token "<token>"
    Examples:
      |token            |status|
      |QpwL5tke4Pnpja7X4|200  |

  @post @tugas
  Scenario Outline: Register user with empty password
    Given Post register user without password
    When Send request register
    Then Should return <status> Bad Request
    And Response body should contain message "<message>"
    Examples:
      |message         |status|
      |Missing password|400   |

  @post @tugas
  Scenario Outline: Login user with valid body
    Given Login user with valid body request
    When Send request login
    Then Should return <status> OK
    And Response body should contain token "<token>"
    Examples:
      |token            |status|
      |QpwL5tke4Pnpja7X4|200   |

  @post @tugas
  Scenario Outline: Login user with invalid body
    Given Login user with invalid body request
    When Send request login
    Then Should return <status> Bad Request
    And Response body should contain message "<error>"
    Examples:
      |error            |status|
      |Missing password |400   |