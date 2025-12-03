# E10 – Manage Prices
@owner @pricing
Feature: Manage charging prices
  As the owner, I want to manage charging prices,
  so that I can adjust rates dynamically for each location and
  ensure correct billing for all charging sessions.

  Background:
    Given the location "Donauinsel" with address "Donauinsel 1, 1220 Wien" exists
    And the location "Donausinsel" has id 1

  @US10.1
  Scenario: Read current prices
    When I read all current prices of location with id 1
    Then I should see the following current prices for location with id 1
    """
    ---Donauinsel---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """
    

  @US10.2
  Scenario: Update prices
    Given the current prices of location with id 1 are:
    """
    ---Donauinsel at 2025-01-01-11-00-00-00---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """
    When I create the new price at location with id 1:
      | price_per_kWh_AC | price_per_kWh_DC| parking_price_AC|parking_price_DC|
      | 2.00             | 3.00            | 3.50            | 4.50           |
    Then I should see the following current prices for location with id 1
    """
    ---Donauinsel at 2025-01-02-11-00-00-00---
    price_per_kWh_AC: 2.00
    price_per_kWh_DC: 3.00
    parking_price_AC: 3.50
    parking_price_DC: 4.50
    """


  @US10.2
  Scenario: Update prices - wrong price
    Given the current prices of location with id 1 are:
    """
    ---Donauinsel at 2025-01-01-11-00-00-00---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """
    When I create the new price at location with id 1:
      | price_per_kWh_AC | price_per_kWh_DC| parking_price_AC|parking_price_DC|
      | -2.00            | 3.00            | 3.50            | 4.50           |
    Then I should see the following current prices for location with id 1
    """
    ---Donauinsel at 2025-01-02-11-00-00-00---
    price_per_kWh_AC: 1.00
    price_per_kWh_DC: 2.00
    parking_price_AC: 2.50
    parking_price_DC: 3.50
    """
