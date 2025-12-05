# E3 – Monitor Station Network
@customer @stations
Feature: Monitor station network
  As a customer, I want to monitor the station network,
  so that I can find available chargers and check their current status before arriving.

  Background:
    Given A location "Karlsplatz charging" exists with the adress "Karlsplatz 1, 1010 Wien "
    And  a location "Donauinsel charging" exists with the adress "An der Neuen Donau, 1210 Wien"


  @US3.1
  Scenario: View operational status by location
    Given the following chargers exist at location "Karlsplatz charging":
     | chargerId | type | status            |
     | 1         | AC   | IN_OPERATION_FREE |
     | 2         | AC   | OCCUPIED          |
     | 3         | DC   | OUT_OF_ORDER      |
    And the following chargers exist at location "Donauinsel charging":
      | chargerId | type | status             |
      | 1         | DC   | IN_OPERATION_FREE  |
      | 2         | DC   | OCCUPIED           |
    When I attempt to access the locations information
    Then I see a list of every locations and their chargers:
    """
    --- Karlsplatz charging ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER

    --- Donauinsel charging ---
    1 - DC - IN_OPERATION_FREE
    2 - dc - OCCUPIED
    """
