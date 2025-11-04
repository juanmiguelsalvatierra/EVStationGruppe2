# E3 – Monitor Station Network
@customer @stations
Feature: Monitor station network
  As a customer I want to find available chargers and their status.

  Background:
    Given the map shows all locations and chargers with live status

  @US3.1
  Scenario Outline: View operational status by location
    When I open location "<location>"
    Then I see all chargers listed with type "<type>" and status among ["Available","Occupied","Out of Service"]

    Examples:
      | location        | type |
      | Vienna Westpark | AC   |
      | Linz Center     | DC   |
