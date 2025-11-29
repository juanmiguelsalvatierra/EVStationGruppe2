
# E7 – Manage Chargers
@owner @chargers
Feature: Manage chargers
  As the owner, I want to manage all chargers at each location,
  so that I can operate and monitor my station network effectively

  @US7.1
  Scenario: Create a new charger at a location
    Given a location "Brigittenau, Wien" exists with address "Höchstädtplatz 6, 1200 Wien" for chargers
    And the location "Brigittenau, Wien" has 0 chargers
    When I create a charger at location "Brigittenau, Wien" with type "AC" and status "FREE"
    Then I see a charger at location "Brigittenau, Wien" with type "AC" and status "FREE"
    And the location "Brigittenau, Wien" has 1 charger

  @US7.1
  Scenario: Create a list of chargers for a location
    Given a location "Donauinsel" exists with address "Donauinsel 1, 1220 Wien" for chargers
    And the location "Donauinsel" has 0 chargers
    When I create chargers at location "Donauinsel" with following parameters:
      | type | status         |
      | AC   | FREE           |
      | AC   | FREE           |
      | DC   | OUT_OF_ORDER   |
    Then the number of chargers at location "Donauinsel" is 3
    And reading all the chargers at location "Donauinsel" as lists shows following output:
    """
    1 - AC - FREE
    2 - AC - FREE
    3 - DC - OUT_OF_ORDER
    """

  @US7.1
  Scenario: Creating a charger with identical properties 
    Given a location "Brigittenau, Wien" exists with address "Höchstädtplatz 6, 1200 Wien" for chargers
    And the following chargers exist at location "Brigittenau, Wien":
      | type | status |
      | AC   | FREE   |
    When I try to create a charger at location "Brigittenau, Wien" with type "AC" and status "FREE"
    Then the number of chargers at location "Brigittenau, Wien" updates to 2
    And reading the chargers at location "Brigittenau, Wien" as lists shows following output:
    """
    1 - AC - FREE
    2 - AC - FREE
    """

  @US7.2
  Scenario: Read all chargers for a location
    Given a location "Karlsplatz Charging" exists with address "Karlsplatz 1, 1010 Wien" for chargers
    And the following chargers exist at location "Karlsplatz Charging":
      | type | status       |
      | AC   | FREE         |
      | AC   | OCCUPIED     |
      | DC   | FREE         |
      | DC   | OUT_OF_ORDER |
    When I view all chargers at location "Karlsplatz Charging"
    Then I see at location "Karlsplatz Charging" the following chargers:
    """
    1 - AC - FREE
    2 - AC - OCCUPIED
    3 - DC - FREE
    4 - DC - OUT_OF_ORDER
    """
