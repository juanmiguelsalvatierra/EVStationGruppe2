# E6 – Manage Locations
@owner @locations
Feature: Manage locations
  As the owner, I want to manage all charging locations, so that I can maintain and
  expand my station network effectively

  @US6.1
  Scenario: Create a new location
    Given no location exists
    When I create a location "Brigittenau, Wien" with address "Höchstädtplatz 6, 1200 Wien"
    Then I see a location with name "Brigittenau, Wien" with address "Höchstädtplatz 6, 1200 Wien"
    And it initially has 0 chargers

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
    And location "Mariahilf" has the address "Mariahilfer Straße 50, 1060 Wien"
    And reading the locations als lists shows following output:
    """
    Donauinsel Charging - Donauinsel 1, 1220 Wien
    Stephansplatz Charging - Stephansplatz 3, 1010 Wien
    Prater Charging - Praterstraße 10, 1020 Wien
    Mariahilf Charging - Mariahilfer Straße 50, 1060 Wien
    """

  @US6.1
  @negative
  Scenario: Creating a location with a duplicate name
    Given a location "Brigittenau, Wien" exists with address "Höchstädtplatz 6, 1200 Wien"
    When I try to create a location "Brigittenau, Wien" with address "Höchstädtplatz 6, 1200 Wien"
    Then I should get an error saying "Location already exists"
    And reading the locations as lists shows following output:
    """
    Brigittenau, Wien - Höchstädtplatz 6, 1200 Wien
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
    Karlsplatz Charging - Karlsplatz 1, 1010 Wien
    MuseumsQuartier Charging - Museumsplatz 5, 1070 Wien
    Belvedere Charging - Rennweg 1, 1030 Wien
    Naschmarkt Charging - Naschmarkt 12, 1040 Wien
    """


