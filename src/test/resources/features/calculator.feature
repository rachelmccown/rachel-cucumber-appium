Feature: Perform Calculations on the built-in android app

@calculator
Scenario Outline: Subtraction on the Calculator App
When I open the Calculator app
And I subtract the numbers <int1> and <int2>
Then the result is <result>
Examples: Add Some Numbers
|int1   |int2     |result     |
|3      |3        |0          |
|20     |21       |−1         |
|857    |746      |111        |
|90539  |6038217  |−5,947,678 |
|10     |-10      |20         |
|-10    |1        |−11        |

@calculator
Scenario Outline: Addition on the Calculator App
When I open the Calculator app
And I add the numbers <int1> and <int2>
Then the result is <result>

Examples: Add Some Numbers
|int1 |int2   |result |
|3    |3      |6      |
|20   |21     |41     |
|857  |746    |1,603   |
|90539|6038217|6,128,756|
|10   |-10    |0      |
|-1   |-1     |−2    |