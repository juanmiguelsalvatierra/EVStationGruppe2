
# E7 – Manage Chargers
@owner @chargers
Feature: Manage chargers
  As the owner, I want to manage all chargers at each location,
  so that I can operate and monitor my station network effectively

  @US7.1
  Scenario: Create a new charger at a location
    Given a location "Brigittenau, Wien" exists with address "Höchstädtplatz 6, 1200 Wien" to create a charger
    And the location "Brigittenau, Wien" has no chargers
    When I create a charger at location "Brigittenau, Wien" with type "AC" and status "FREE"
    Then I see a charger at location "Brigittenau, Wien" with type "AC" and status "FREE"
    And the location "Brigittenau, Wien" has 1 charger

  @US7.1
  Scenario: Create a list of chargers for a location
    Given a location "Donauinsel" exists with address "Donauinsel 1, 1220 Wien"
    And the location "Donauinsel" has no chargers
    When I create chargers at location "Donauinsel" with following parameters:
      | type | status         |
      | AC   | FREE           |
      | AC   | FREE           |
      | DV   | OUT_OF_ORDER   |
    Then the number of chargers at location "Donauinsel" is 3
    And reading all the chargers at location "Donauinsel" as lists shows following output:
    """
    Charger (AC) - FREE
    Charger (AC) - FREE
    Charger (DV) - OUT_OF_ORDER
    """

  @US7.1
  @negative
  Scenario: Creating a charger with identical properties (duplicate charger)
    Given a location "Brigittenau, Wien" exists with address "Höchstädtplatz 6, 1200 Wien"
    And the following chargers exist at location "Brigittenau, Wien":
      | type | status |
      | AC   | FREE   |
    When I try to create a charger at location "Brigittenau, Wien" with type "AC" and status "FREE"
    Then I should see an error message saying "Charger already exists at this location"
    And reading the chargers at location "Brigittenau, Wien" as lists shows following output:
    """
    Charger (AC) - FREE
    """

  @US7.2
  Scenario: Read all chargers for a location
    Given a location "Karlsplatz Charging" exists with address "Karlsplatz 1, 1010 Wien"
    And the following chargers exist at location "Karlsplatz Charging":
      | type | status       |
      | AC   | FREE         |
      | AC   | OCCUPIED     |
      | DV   | FREE         |
      | DV   | OUT_OF_ORDER |
    When I view all chargers at location "Karlsplatz Charging"
    Then I should see the following chargers:
    """
    Charger (AC) - FREE
    Charger (AC) - OCCUPIED
    Charger (DV) - FREE
    Charger (DV) - OUT_OF_ORDER
    """