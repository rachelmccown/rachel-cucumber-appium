Feature: Navigate an App

#Background:
#  Given I open up the App

  Scenario Outline: Addition on the Calculator App
    When I open the Calculator app
    And I add <int1> and <int2>
    Then the result is <result>

    Examples: Add Some Numbers
    |int1|int2|result|
    |3   |3   |6     |
    |20  |21  |41    |
    |857 |746 |1603  |
    |90539|6038217|6128756|
    |10   |-10    |0      |
  #  |-1   |-1     |-2     |

  Scenario: Check Out of Milk App
    When I open the Out of Milk app
    Then the page displays Shopping List