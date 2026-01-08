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
    And the locations with id 1 to 5 have the following current prices:
      | price_per_kWh_AC | price_per_kWh_DC| parking_price_AC|parking_price_DC| Datetime         |
      | 1.00             | 2.00            | 2.50            | 3.50           | 2025-03-15T14:30 |
    And the locations with id 6 to 10 have the following current prices:
      | price_per_kWh_AC | price_per_kWh_DC| parking_price_AC|parking_price_DC| Datetime         |
      | 2.00             | 4.00            | 5.00            | 7.00           | 2025-03-15T14:30 |

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
    1 - AC - IN_OPERATION_FREE - Charging Price: 1.0 € kWh - Parking Price: 2.5 per hour
    2 - AC - OCCUPIED - Charging Price: 1.0 € kWh - Parking Price: 2.5 per hour
    3 - DC - OUT_OF_ORDER - Charging Price: 2.0 € kWh - Parking Price: 3.5 per hour
    4 - DC - OUT_OF_ORDER - Charging Price: 2.0 € kWh - Parking Price: 3.5 per hour

    --- Stephansplatz ---
    1 - AC - IN_OPERATION_FREE - Charging Price: 1.0 € kWh - Parking Price: 2.5 per hour
    2 - AC - OCCUPIED - Charging Price: 1.0 € kWh - Parking Price: 2.5 per hour
    3 - DC - OUT_OF_ORDER - Charging Price: 2.0 € kWh - Parking Price: 3.5 per hour
    4 - DC - OUT_OF_ORDER - Charging Price: 2.0 € kWh - Parking Price: 3.5 per hour

    --- Prater ---
    1 - AC - IN_OPERATION_FREE - Charging Price: 1.0 € kWh - Parking Price: 2.5 per hour
    2 - AC - OCCUPIED - Charging Price: 1.0 € kWh - Parking Price: 2.5 per hour
    3 - DC - OUT_OF_ORDER - Charging Price: 2.0 € kWh - Parking Price: 3.5 per hour
    4 - DC - OUT_OF_ORDER - Charging Price: 2.0 € kWh - Parking Price: 3.5 per hour

    --- Mariahilf ---
    1 - AC - IN_OPERATION_FREE - Charging Price: 1.0 € kWh - Parking Price: 2.5 per hour
    2 - AC - OCCUPIED - Charging Price: 1.0 € kWh - Parking Price: 2.5 per hour
    3 - DC - OUT_OF_ORDER - Charging Price: 2.0 € kWh - Parking Price: 3.5 per hour
    4 - DC - OUT_OF_ORDER - Charging Price: 2.0 € kWh - Parking Price: 3.5 per hour

    --- Technikum ---
    1 - AC - IN_OPERATION_FREE - Charging Price: 1.0 € kWh - Parking Price: 2.5 per hour
    2 - AC - OCCUPIED - Charging Price: 1.0 € kWh - Parking Price: 2.5 per hour
    3 - DC - OUT_OF_ORDER - Charging Price: 2.0 € kWh - Parking Price: 3.5 per hour
    4 - DC - OUT_OF_ORDER - Charging Price: 2.0 € kWh - Parking Price: 3.5 per hour

    --- Wirtschaftsuni ---
    1 - AC - IN_OPERATION_FREE - Charging Price: 2.0 € kWh - Parking Price: 5.0 per hour
    2 - AC - OCCUPIED - Charging Price: 2.0 € kWh - Parking Price: 5.0 per hour
    3 - DC - OUT_OF_ORDER - Charging Price: 4.0 € kWh - Parking Price: 7.0 per hour
    4 - DC - OUT_OF_ORDER - Charging Price: 4.0 € kWh - Parking Price: 7.0 per hour

    --- Staatsoper ---
    1 - AC - IN_OPERATION_FREE - Charging Price: 2.0 € kWh - Parking Price: 5.0 per hour
    2 - AC - OCCUPIED - Charging Price: 2.0 € kWh - Parking Price: 5.0 per hour
    3 - DC - OUT_OF_ORDER - Charging Price: 4.0 € kWh - Parking Price: 7.0 per hour
    4 - DC - OUT_OF_ORDER - Charging Price: 4.0 € kWh - Parking Price: 7.0 per hour

    --- Rathaus ---
    1 - AC - IN_OPERATION_FREE - Charging Price: 2.0 € kWh - Parking Price: 5.0 per hour
    2 - AC - OCCUPIED - Charging Price: 2.0 € kWh - Parking Price: 5.0 per hour
    3 - DC - OUT_OF_ORDER - Charging Price: 4.0 € kWh - Parking Price: 7.0 per hour
    4 - DC - OUT_OF_ORDER - Charging Price: 4.0 € kWh - Parking Price: 7.0 per hour

    --- Belvedere ---
    1 - AC - IN_OPERATION_FREE - Charging Price: 2.0 € kWh - Parking Price: 5.0 per hour
    2 - AC - OCCUPIED - Charging Price: 2.0 € kWh - Parking Price: 5.0 per hour
    3 - DC - OUT_OF_ORDER - Charging Price: 4.0 € kWh - Parking Price: 7.0 per hour
    4 - DC - OUT_OF_ORDER - Charging Price: 4.0 € kWh - Parking Price: 7.0 per hour

    --- Zentralfriedhof ---
    1 - AC - IN_OPERATION_FREE - Charging Price: 2.0 € kWh - Parking Price: 5.0 per hour
    2 - AC - OCCUPIED - Charging Price: 2.0 € kWh - Parking Price: 5.0 per hour
    3 - DC - OUT_OF_ORDER - Charging Price: 4.0 € kWh - Parking Price: 7.0 per hour
    4 - DC - OUT_OF_ORDER - Charging Price: 4.0 € kWh - Parking Price: 7.0 per hour
    """



  @US3.1
  Scenario: View operational status by locations without chargers
    When I attempt to access the locations information
    Then I see a list of every location:
    """
    --- Donauinsel ---
    No chargers available.

    --- Stephansplatz ---
    No chargers available.

    --- Prater ---
    No chargers available.

    --- Mariahilf ---
    No chargers available.

    --- Technikum ---
    No chargers available.

    --- Wirtschaftsuni ---
    No chargers available.

    --- Staatsoper ---
    No chargers available.

    --- Rathaus ---
    No chargers available.

    --- Belvedere ---
    No chargers available.

    --- Zentralfriedhof ---
    No chargers available.
    """

