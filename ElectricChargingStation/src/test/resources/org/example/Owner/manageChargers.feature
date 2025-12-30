
# E7 – Manage Chargers
@owner @chargers
Feature: Manage chargers
  As the owner, I want to manage all chargers at each location,
  so that I can operate and monitor my station network effectively

  @US7.1
  Scenario: Create a new charger at a location
    Given a location "Brigittenau, Wien" exists with address "Höchstädtplatz 6, 1200 Wien" for chargers
    And the location "Brigittenau, Wien" has 0 chargers
    When I create a charger at location "Brigittenau, Wien" with type "AC" and status "IN_OPERATION_FREE"
    Then I see a charger at location "Brigittenau, Wien" with type "AC" and status "IN_OPERATION_FREE"
    And the location "Brigittenau, Wien" has 1 charger

  @US7.1
  Scenario: Create a list of chargers for a location
    Given a location "Donauinsel" exists with address "Donauinsel 1, 1220 Wien" for chargers
    And the location "Donauinsel" has 0 chargers
    When I create chargers at location "Donauinsel" with following parameters:
      | type | status             |
      | AC   | IN_OPERATION_FREE  |
      | AC   | IN_OPERATION_FREE  |
      | DC   | OUT_OF_ORDER       |
    Then the number of chargers at location "Donauinsel" is 3
    And reading all the chargers at location "Donauinsel" as lists shows following output:
    """
    1 - AC - IN_OPERATION_FREE
    2 - AC - IN_OPERATION_FREE
    3 - DC - OUT_OF_ORDER
    """

  @US7.1
  Scenario: Creating a charger with identical properties 
    Given a location "Brigittenau, Wien" exists with address "Höchstädtplatz 6, 1200 Wien" for chargers
    And the following chargers exist at location "Brigittenau, Wien":
      | type | status |
      | AC   | IN_OPERATION_FREE   |
    When I try to create a charger at location "Brigittenau, Wien" with type "AC" and status "IN_OPERATION_FREE"
    Then the number of chargers at location "Brigittenau, Wien" updates to 2
    And reading the chargers at location "Brigittenau, Wien" as lists shows following output:
    """
    1 - AC - IN_OPERATION_FREE
    2 - AC - IN_OPERATION_FREE
    """

  @US7.2
  Scenario: Read all chargers for a location
    Given a location "Karlsplatz Charging" exists with address "Karlsplatz 1, 1010 Wien" for chargers
    And the following chargers exist at location "Karlsplatz Charging":
      | type | status             |
      | AC   | IN_OPERATION_FREE  |
      | AC   | OCCUPIED           |
      | DC   | IN_OPERATION_FREE  |
      | DC   | OUT_OF_ORDER       |
    When I view all chargers at location "Karlsplatz Charging"
    Then I see at location "Karlsplatz Charging" the following chargers:
    """
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - IN_OPERATION_FREE
    4 - DC - OUT_OF_ORDER
    """

  @US7.3
  Scenario: Update a charger for a location
    Given a location "Karlsplatz Charging" exists with address "Karlsplatz 1, 1010 Wien" for chargers
    And the following chargers exist at location "Karlsplatz Charging":
      | type | status             |
      | AC   | IN_OPERATION_FREE  |
      | AC   | OCCUPIED           |
      | DC   | IN_OPERATION_FREE  |
      | DC   | OUT_OF_ORDER       |
    When I update the charger with ID 2 at location "Karlsplatz Charging" to Type "DC" Status "IN_OPERATION_FREE"
    Then I see at location "Karlsplatz Charging" the following chargers:
    """
    1 - AC - IN_OPERATION_FREE
    2 - DC - IN_OPERATION_FREE
    3 - DC - IN_OPERATION_FREE
    4 - DC - OUT_OF_ORDER
    """

  @US7.3
  Scenario: Delete a charger for a location
    Given a location "Karlsplatz Charging" exists with address "Karlsplatz 1, 1010 Wien" for chargers
    And the following chargers exist at location "Karlsplatz Charging":
      | type | status             |
      | AC   | IN_OPERATION_FREE  |
      | AC   | OCCUPIED           |
      | DC   | IN_OPERATION_FREE  |
      | DC   | OUT_OF_ORDER       |
    When I delete the charger with ID 2 at location "Karlsplatz Charging"
    Then I see at location "Karlsplatz Charging" the following chargers:
    """
    1 - AC - IN_OPERATION_FREE
    3 - DC - IN_OPERATION_FREE
    4 - DC - OUT_OF_ORDER
    """

  @US7.3 @negative
  Scenario: Delete a non existent charger for a location
    Given a location "Karlsplatz Charging" exists with address "Karlsplatz 1, 1010 Wien" for chargers
    And the following chargers exist at location "Karlsplatz Charging":
      | type | status             |
      | AC   | IN_OPERATION_FREE  |
      | AC   | OCCUPIED           |
      | DC   | IN_OPERATION_FREE  |
      | DC   | OUT_OF_ORDER       |
    When I delete the charger with ID 6 at location "Karlsplatz Charging"
    Then the Exception Message will be displayed with "Exception - Charger does not exist"