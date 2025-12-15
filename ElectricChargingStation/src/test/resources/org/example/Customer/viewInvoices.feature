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
    Given the customer with the ID 1 top-ups the amount 50 at "2025-03-15T14:30:00"
    When I view the invoice items of the customer with the ID 1
    Then the invoice item count for the customer with the ID 1 is 1
    And the invoice items for the customer with the ID 1 show the following:
    """
    1 - TOPUP - amount: 50,00 - balance after: 50,00
    """

  @US5.1
  Scenario: View invoice items after multiple top-ups
    Given the customer with the ID 1 top-ups the amount 30 at "2025-03-15T14:30:00"
    And the customer with the ID 1 top-ups the amount 20 at "2025-03-15T14:30:00"
    And the customer with the ID 1 top-ups the amount 50 at "2025-03-15T14:30:00"
    When I view the invoice items of the customer with the ID 1
    Then the invoice item count for the customer with the ID 1 is 3
    And the invoice items for the customer with the ID 1 show the following:
    """
    1 - TOPUP - amount: 30,00 - balance after: 30,00
    2 - TOPUP - amount: 20,00 - balance after: 50,00
    3 - TOPUP - amount: 50,00 - balance after: 100,00
    """

  @US5.1
  Scenario: View invoice items containing top-ups and charges mixed
    Given the customer with the ID 1 top-ups the amount 100 at "2025-03-15T14:30:00"
    And a location "Prater" with address "Praterallee 1, 1020 Wien" exists
    And the location with ID 1 has the following current prices:
      | price_per_kWh_AC | price_per_kWh_DC| parking_price_AC|parking_price_DC|
      | 1.00             | 2.00            | 2.50            | 3.50           |
    And the location with the ID 1 has a charger of type "AC" with status "IN_OPERATION_FREE"
    And the customer with the ID 1 performs a charging session of 30 minutes using "AC" mode at charger ID 1 of the location with the ID 1
    When I view the invoice items of the customer with the ID 1
    Then the invoice item count for the customer with the ID 1 is 2
    And the invoice items for the customer with the ID 1 show the following:
  """
  1 - TOPUP - amount: 100,00 - balance after: 100,00
  2 - CHARGE - duration: 30min - energy: 5,00kWh - price_per_kWh_AC: 1,00 - parking_price_AC: 2,50 - energy_cost: 5,00 - parking_cost: 1,25 - total: -6,25 - balance after: 93,75
  """