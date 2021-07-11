Feature: Calculator with add and subtract
  Scenario: Add two integers
    Given a integer 1
    And a second integer 2
    When the numbers are added
    Then the result is 3

  Scenario: Subtract two integers
    Given a integer 2
    And a second integer 1
    When the numbers are subtracted
    Then the result is 1
