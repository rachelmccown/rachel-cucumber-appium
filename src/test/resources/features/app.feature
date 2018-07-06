Feature: Navigate an App

Background:
  When I open the Out of Milk app
  And I go to the Main Page

  Scenario: Check Out of Milk App Opens
    Then the page displays Shopping List Instructions

  Scenario: Add an item to the list
    When I add Cucumber
    Then there is 1 item on the list

  Scenario: Add an item to the list
    When I add Cucumber, Relish, Water, Lemons, Chocolate, Lettuce, Chicken
    Then there is 7 items on the list

  @runMe
  Scenario Outline: Add some items to the list
    When I add <item>
    Then there is <num> items on the list
    Examples:
    |item|num|
    |Cucumber|1|
    |Relish,Cucumber|2|
    |Chocolate,Peanut Butter,Bread,Bananas,Apples,Quinoa|6|

  Scenario:
    When I go to the Options
    And in categories I add Sauces
    Then Sauces is a category

#    # Complicated but gets to the Pasta search
#  Scenario: Create a Recipe
#    When I go to the Nav Bar
#    And I go to the Recipe Book
#    And I find a recipe for Pasta