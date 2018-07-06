Feature: Navigate an App

Background:
  When I open the Out of Milk app

  Scenario: Check Out of Milk App Opens
    When I go to the Main Page
    Then the page displays Shopping List Instructions

  Scenario: Add an item to the list
    When I go to the Main Page
    And I add Cucumber
    Then there is 1 item on the list

  @runMe
  Scenario: Add an item to the list
    When I go to the Main Page
    And I add Cucumber, Relish, Water, Lemons, Chocolate, Lettuce, Chicken
    Then there is 7 items on the list