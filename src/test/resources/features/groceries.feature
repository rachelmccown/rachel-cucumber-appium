@groceries
Feature: Navigate an app for tracking groceries

Background:
  When I open the Out of Milk app
  And I go to the Main Page

  Scenario: Check Out of Milk App Opens
    When I open the Out of Milk app
    And I go to the Main Page
    Then the page displays Shopping List Instructions

  Scenario: Add an item to the list
    When I open the Out of Milk app
    And I go to the Main Page
    And I add Cucumber to the list
    Then there is 1 item on the list

  Scenario: Add many items to the list
    When I open the Out of Milk app
    And I go to the Main Page
    And I add Cucumber, Relish, Water, Lemons, Chocolate, Lettuce, Chicken to the list
    Then there is 7 items on the list

  Scenario Outline: Add some items to the list
    When I open the Out of Milk app
    And I go to the Main Page
    And I add <item> to the list
    Then there is <num> items on the list
    Examples:
    |item|num|
    |Cucumber|1|
    |Relish,Cucumber|2|
    |Chocolate,Peanut Butter,Bread,Bananas,Apples,Quinoa|6|

  Scenario: Add Category to the app
    When I open the Out of Milk app
    And I go to the Main Page
    And I go to the Options
    And in categories I add Sauces
    Then Sauces is a category

#    # Complicated but gets to the Pasta search
#  Scenario: Create a Recipe
#    When I go to the Nav Bar
#    And I go to the Recipe Book
#    And I find a recipe for Pasta