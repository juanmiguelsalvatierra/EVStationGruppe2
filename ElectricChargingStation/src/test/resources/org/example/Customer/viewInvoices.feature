# E5 – View Invoices
@customer @invoices
Feature: View invoices
  As a customer, I want to view my invoices,
  so that I can review all past charging sessions, top-ups, and my remaining balance.

  Background:
    Given the customer "Alice" with email "alice@test.at" exists
    And the customer "Alice" has ID 1
    And the invoice item count for the customer with the ID 1 is 0
    And the balance of the customer with the ID 1 is 0

  @US5.1
  Scenario: View an empty invoice list
    When I view the invoice items of the customer with the ID 1
    Then the invoice item count for the customer with the ID 1 is 0
    And the invoice items for the customer with the ID 1 show the following:
    """
    """

  @US5.1
  Scenario: View invoice items after a top-up
    Given the customer with the ID 1 top-ups the amount 50
    When I view the invoice items of the customer with the ID 1
    Then the invoice item count for the customer with the ID 1 is 1
    And the invoice items for the customer with the ID 1 show the following:
    """
    1 - TOPUP - amount: 50 - balance after: 50
    """

  @US5.1
  Scenario: View invoice items after multiple top-ups
    Given the customer with the ID 1 top-ups the amount 30
    And the customer with the ID 1 top-ups the amount 20
    And the customer with the ID 1 top-ups the amount 50
    When I view the invoice items of the customer with the ID 1
    Then the invoice item count for the customer with the ID 1 is 3
    And the invoice items for the customer with the ID 1 show the following:
    """
    1 - TOPUP - amount: 30 - balance after: 30
    2 - TOPUP - amount: 20 - balance after: 50
    3 - TOPUP - amount: 50 - balance after: 100
    """

  @US5.1
  Scenario: View invoice items containing top-ups and charges mixed
    Given the customer with the ID 1 top-ups the amount 100
    And a location "Prater" with address "Praterallee 1, 1020 Wien" exists
    And the location "Prater" has ID 1
    And the location with ID 1 has the following current prices:
      | price_per_kWh_AC | price_per_kWh_DC| parking_price_AC|parking_price_DC|
      | 1.00             | 2.00            | 2.50            | 3.50           |
    And the location with the ID 1 has a charger of type "AC" with status "IN_ORDER_FREE"
    And the customer with the ID 1 performs a charging session of 30 minutes at charger ID 1 of the location with the ID 1
    When I view the invoice items of the customer with the ID 1
    Then the invoice item count for the customer with the ID 1 is 2
    And the invoice items for the customer with the ID 1 show the following:
  """
  1 - TOPUP - amount: 50 - balance after: 50
  2 - CHARGE - duration: 30min - energy: watt of AC (needs to be implemented)x duration = kWh - price_per_kWh_AC - parking_price_AC - energy_cost: kWh * price_per_kWh_AC - parking_cost: duration * parking_price_AC - total: sum(ek + pc) - balance after: x
  """