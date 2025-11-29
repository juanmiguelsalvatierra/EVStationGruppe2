# E6 – Manage Locations
@owner @locations
Feature: Manage locations
  As the owner, I want to manage all charging locations, so that I can maintain and
  expand my station network effectively

  @US6.1
  Scenario: Create a new location
    Given no location exists
    When I create the location "Brigittenau, Wien" with address "Höchstädtplatz 6, 1200 Wien"
    Then I see at ID 1 the location with name "Brigittenau, Wien" with address "Höchstädtplatz 6, 1200 Wien"
    And the location with ID 1 initially has 0 chargers

  @US6.1
  Scenario: Create a list of new locations
    Given no location exists
    When I create locations with following parameters:
      | name          | address                          |
      | Donauinsel    | Donauinsel 1, 1220 Wien          |
      | Stephansplatz | Stephansplatz 3, 1010 Wien       |
      | Prater        | Praterstraße 10, 1020 Wien       |
      | Mariahilf     | Mariahilfer Straße 50, 1060 Wien |
    Then the number of locations is 4
    And location with ID 4 has the address "Mariahilfer Straße 50, 1060 Wien"
    And reading the locations as lists shows following output:
    """
    1 - Donauinsel - Donauinsel 1, 1220 Wien
    2 - Stephansplatz - Stephansplatz 3, 1010 Wien
    3 - Prater - Praterstraße 10, 1020 Wien
    4 - Mariahilf - Mariahilfer Straße 50, 1060 Wien
    """

  @US6.1
  @negative
  Scenario: Creating a location with a duplicate name
    Given a location "Brigittenau, Wien" exists with address "Höchstädtplatz 6, 1200 Wien"
    When I try to create a location "Brigittenau, Wien" with address "Höchstädtplatz 6, 1200 Wien"
    Then the number of locations remains 1
    And reading the locations as lists shows following output:
    """
    1 - Brigittenau, Wien - Höchstädtplatz 6, 1200 Wien
    """

  @US6.2
  Scenario: Read all existing locations
    Given the following locations exist:
      | name                  | address                          |
      | Karlsplatz Charging    | Karlsplatz 1, 1010 Wien          |
      | MuseumsQuartier Charging | Museumsplatz 5, 1070 Wien     |
      | Belvedere Charging     | Rennweg 1, 1030 Wien             |
      | Naschmarkt Charging    | Naschmarkt 12, 1040 Wien         |
    When I view all locations
    Then I should see the following locations:
    """
    1 - Karlsplatz Charging - Karlsplatz 1, 1010 Wien
    2 - MuseumsQuartier Charging - Museumsplatz 5, 1070 Wien
    3 - Belvedere Charging - Rennweg 1, 1030 Wien
    4 - Naschmarkt Charging - Naschmarkt 12, 1040 Wien
    """

  @US6.3
  Scenario: Update the name of an existing location
    Given the following locations exist:
      | name          | address                          |
      | Donauinsel    | Donauinsel 1, 1220 Wien          |
      | Stephansplatz | Stephansplatz 3, 1010 Wien       |
    When I update location with ID 1 to name "Donauinsel Parkplatz"
    Then the location with ID 1 should have name "Donauinsel Parkplatz" and address "Donauinsel 1, 1220 Wien"
    And the location with ID 2 remains unchanged with name "Stephansplatz" and address "Stephansplatz 3, 1010 Wien"
    And reading the locations as lists shows following output:
    """
    1 - Donauinsel Parkplatz - Donauinsel 1, 1220 Wien
    2 - Stephansplatz - Stephansplatz 3, 1010 Wien
    """

  @US6.3
  Scenario: Update the address of an existing location
    Given the following locations exist:
      | name          | address                          |
      | Donauinsel    | Donauinsel 1, 1220 Wien          |
      | Stephansplatz | Stephansplatz 3, 1010 Wien       |
    When I update location with ID 1 to address "Donauinsel 69, 1220 Wien"
    Then the location with ID 1 should have name "Donauinsel" and address "Donauinsel 69, 1220 Wien"
    And the location with ID 2 remains unchanged with name "Stephansplatz" and address "Stephansplatz 3, 1010 Wien"
    And reading the locations as lists shows following output:
    """
    1 - Donauinsel - Donauinsel 69, 1220 Wien
    2 - Stephansplatz - Stephansplatz 3, 1010 Wien
    """


  @US6.4
  Scenario: Delete an existing location
    Given the following locations exist:
      | name          | address                          |
      | Donauinsel    | Donauinsel 1, 1220 Wien          |
      | Stephansplatz | Stephansplatz 3, 1010 Wien       |
      | Prater        | Praterstraße 10, 1020 Wien       |
    When I try to delete location with ID 2
    Then the number of locations is 2
    And location with ID 2 no longer exists
    And reading the locations as lists shows following output:
    """
    1 - Donauinsel - Donauinsel 1, 1220 Wien
    3 - Prater - Praterstraße 10, 1020 Wien
    """

  @US6.4
  @negative
  Scenario: Delete a non-existent location
    Given the following locations exist:
      | name       | address                   |
      | Donauinsel | Donauinsel 1, 1220 Wien   |
    When I try to delete location with ID 5
    Then the number of locations remains 1
    And reading the locations as lists shows following output:
    """
    1 - Donauinsel - Donauinsel 1, 1220 Wien
    """

