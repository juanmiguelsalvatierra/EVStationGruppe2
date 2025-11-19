Feature: An example

  Scenario: The example
    Given an example scenario
    When all step definitions are implemented
    Then the scenario passes


    #Example for Scenario Outline
  Scenario Outline: Create a new location
    Given no location exists
    When I create a location "<name>" with address "<address>"
    Then I see a location with name "<name>" with address "<address>"
    And it initially has 0 chargers

    Examples:
      | name               | address                     |
      | Brigittenau, Wien  | Höchstädtplatz 6, 1200 Wien |
