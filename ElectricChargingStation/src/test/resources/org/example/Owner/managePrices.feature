# E10 – Manage Prices
@owner @pricing
Feature: Manage charging prices
  As the owner, I want to manage charging prices,
  so that I can adjust rates dynamically for each location and
  ensure correct billing for all charging sessions. 



  @US10.1
  Scenario: Read prices
    Given the location "Donauinsel" with address "Donauinsel 1, 1220 Wien" exists
    When I read all prices of location with id 1 at Datetime "2025-01-01-11-00-00-00"
    Then I should see the following prices for location with id 1 at Datetime "2025-01-01-11-00-00-00":
    """
    ---Donauinsel---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """

  @US10.2
  Scenario: Update price_per_kWh_AC
    Given the following locations exist:
      | name          | address                          |
      | Donauinsel    | Donauinsel 1, 1220 Wien          |
      | Stephansplatz | Stephansplatz 3, 1010 Wien       |
    And location with id 1 has following prices at Datetime "2025-01-01-11-00-00-00":
    """
    ---Donauinsel at 2025-01-01-11-00-00-00---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """
    And location with id 2 has following prices at Datetime "2025-01-01-11-00-00-00":
    """
    ---Stephansplatz at 2025-01-01-11-00-00-00---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """
    When I set the new price for price per kWh to 2.00 for chargers of type AC at location with id 1 valid at Datetime "2025-01-02-11-00-00-00"
    Then I should see the following prices for location with id 1 at Datetime "2025-01-02-11-00-00-00":
    """
    ---Donauinsel at 2025-01-02-11-00-00-00---
    price_per_kWh_AC: 2.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """
    And I should see the following prices for location with id 2 at Datetime "2025-01-02-11-00-00-00":
    """
    ---Stephansplatz at 2025-01-02-11-00-00-00---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """

  @US10.2
  Scenario: Update parking_price_DC
    Given the following locations exist:
      | name          | address                          |
      | Donauinsel    | Donauinsel 1, 1220 Wien          |
      | Stephansplatz | Stephansplatz 3, 1010 Wien       |
    And location with id 1 has following prices at Datetime "2025-01-01-11-00-00-00":
    """
    ---Donauinsel at 2025-01-01-11-00-00-00---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """
    And location with id 2 has following prices at Datetime "2025-01-01-11-00-00-00":
    """
    ---Stephansplatz at 2025-01-01-11-00-00-00---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """
    When I set the new price for parking_price_DC to 4.00 for chargers of type DC at location with id 1 valid at Datetime "2025-01-02-11-00-00-00"
    Then I should see the following prices for location with id 1 at Datetime "2025-01-02-11-00-00-00":
    """
    ---Donauinsel at 2025-01-02-11-00-00-00---
    price_per_kWh_AC: 2.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 4.00
    """
    And I should see the following prices for location with id 2 at Datetime "2025-01-02-11-00-00-00":
    """
    ---Stephansplatz at 2025-01-02-11-00-00-00---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """

  @US10.2
  Scenario: Update prices - wrong price
    Given the following locations exist:
      | name          | address                          |
      | Donauinsel    | Donauinsel 1, 1220 Wien          |
      | Stephansplatz | Stephansplatz 3, 1010 Wien       |
    When I set the new price for price per kWh to -0.5 for chargers of type AC at location with id 1 at Datetime "2025-01-02-11-00-00-00"
    Then the price of location with id 1 for chargers of type AC stays 1.00 at Datetime "2025-01-02-11-00-00-00"
    And I should see the following prices for location with id 1 at Datetime "2025-01-02-11-00-00-00"
    """
    ---Donauinsel at 2025-01-02-11-00-00-00---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """
