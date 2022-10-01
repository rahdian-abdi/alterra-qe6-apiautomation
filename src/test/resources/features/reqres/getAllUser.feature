Feature: Get All User Reqres.in
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
      |1   |200   |

  @get @tugas @positive
  Scenario Outline: Get all list user
    Given Get all list user with "users" param
    When Send request get all user
    Then Should return <status> OK
    And Get list all user with json schema validator
    Examples:
      |status|
      |200   |

  @get @tugas @negative
  Scenario Outline: Get list user without valid page
    Given Get all user without "page" query number
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
    Given Get list user with wrong page query title on page <page>
    When Send request with wrong page title
    Then Should return <status> Bad Request
    And Get list user with wrong title page json schema
    Examples:
      |page|status|
      |1   |400   |

  @get @tugas @negative
  Scenario Outline: Get list user without question mark
    Given Get list user without question mark on page <page>
    When Send request without question mark
    Then Should return <status> Bad Request
    And Get list user without question mark json schema
    Examples:
      |page|status|
      |1   |400   |