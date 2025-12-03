# E3 – Monitor Station Network
@customer @stations
Feature: Monitor station network
  As a customer, I want to monitor the station network,
  so that I can find available chargers and check their current status before arriving.

  Background:
    Given A map shows all locations with their chargers and live statuses


  @US3.1
  Scenario: View operational status by location
    Given a location "Karlsplatz charging" exists
    And the following chargers exist at location "Karlsplatz charging":
     | chargerId | type | status       |
     | 1         | AC   | FREE         |
     | 2         | AC   | OCCUPIED     |
     | 3         | DC   | OUT_OF_ORDER |
    Then I see a list of the chargers at location "Karlsplatz charging" with the following output:
    """
    1 - AC - FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    """
