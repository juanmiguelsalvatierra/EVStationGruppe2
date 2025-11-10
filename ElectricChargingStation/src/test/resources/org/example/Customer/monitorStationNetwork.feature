# E3 – Monitor Station Network
@customer @stations
Feature: Monitor station network
  As a customer, I want to monitor the station network,
  so that I can find available chargers and check their current status before arriving.

  Background:
    Given the map shows all locations and chargers with live status

  @US3.1
  Scenario Outline: View operational status by location
    When I open location "<location>"
    Then I see all "<chargerId>" at the location
    And I can see the type "<type>" and "<status>"

    Examples:
      | chargerId | type | status     |
      | 1         | AC   | Available  |
      | 2         | DC   | Occupied   |
