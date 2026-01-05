# E3 – Monitor Station Network
@customer @stations
Feature: Monitor station network
  As a customer, I want to monitor the station network,
  so that I can find available chargers and check their current status before arriving.

  Background:
    Given ten locations with the following parameters exist:
      | name            | address                                |
      | Donauinsel      | Donauinsel 1, 1220 Wien                |
      | Stephansplatz   | Stephansplatz 3, 1010 Wien             |
      | Prater          | Praterstraße 10, 1020 Wien             |
      | Mariahilf       | Mariahilfer Straße 50, 1060 Wien       |
      | Technikum       | Hochstaedtplatz, 1200 Wien             |
      | Wirtschaftsuni  | Welthandelsplatz, 1020 Wien            |
      | Staatsoper      | Opernring 2, 1010 Wien                 |
      | Rathaus         | Rathausplatz 1, 1010 Wien              |
      | Belvedere       | Prinz Eugen Straße 27, 1030 Wien       |
      | Zentralfriedhof | Simmeringer Hauptstraße 234, 1110 Wien |

  @US3.1
  Scenario: View operational status by location
    Given the following chargers exist at each location:
     | chargerId | type | status            |
     | 1         | AC   | IN_OPERATION_FREE |
     | 2         | AC   | OCCUPIED          |
     | 3         | DC   | OUT_OF_ORDER      |
     | 4         | DC   | OUT_OF_ORDER      |

    When I attempt to access the locations information
    Then I see a list of every location and their chargers:
    """
    --- Donauinsel ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    4 - DC - OUT_OF_ORDER

    --- Stephansplatz ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    4 - DC - OUT_OF_ORDER

    --- Prater ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    4 - DC - OUT_OF_ORDER

    --- Mariahilf ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    4 - DC - OUT_OF_ORDER

    --- Technikum ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    4 - DC - OUT_OF_ORDER

    --- Wirtschaftsuni ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    4 - DC - OUT_OF_ORDER

    --- Staatsoper ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    4 - DC - OUT_OF_ORDER

    --- Rathaus ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    4 - DC - OUT_OF_ORDER

    --- Belvedere ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    4 - DC - OUT_OF_ORDER

    --- Zentralfriedhof ---
    1 - AC - IN_OPERATION_FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    4 - DC - OUT_OF_ORDER
    """
