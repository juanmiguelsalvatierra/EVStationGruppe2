# E5 – View Invoices
@customer @invoices
Feature: View invoices
  As a customer, I want to view my invoices,
  so that I can review all past charging sessions, top-ups, and my remaining balance.

  @US5.1
  Scenario: View an empty invoice list
    Given a customer "Alice" with email "alice@test.com" exists with balance 0
    When I view the invoice items of customer "Alice"
    Then the invoice item count for customer "Alice" is 0
    And the invoice items for customer "Alice" show the following:
    """
    """

  @US5.1
  Scenario: View invoice items after a top-up
    Given a customer "Alice" with email "alice@test.com" exists with balance 0
    And customer "Alice" top-ups the amount 50
    When I view the invoice items of customer "Alice"
    Then the invoice item count for customer "Alice" is 1
    And the invoice items for customer "Alice" show the following:
    """
    1 - TOPUP - amount: 50 - balance after: 50
    """

  @US5.1
  Scenario: View invoice items after multiple top-ups
    Given a customer "Alice" with email "alice@test.com" exists with balance 0
    And customer "Alice" top-ups the amount 30
    And customer "Alice" top-ups the amount 20
    And customer "Alice" top-ups the amount 50
    When I view the invoice items of customer "Alice"
    Then the invoice item count for customer "Alice" is 3
    And the invoice items for customer "Alice" show the following:
    """
    1 - TOPUP - amount: 30 - balance after: 30
    2 - TOPUP - amount: 20 - balance after: 50
    3 - TOPUP - amount: 50 - balance after: 100
    """

  @US5.1
  Scenario: View invoice items containing top-ups and charges mixed
    Given a customer "Alice" with email "alice@test.com" exists with balance 0
    And customer "Alice" top-ups the amount 50
    And a location "Prater" at "Praterallee 1, 1020 Wien" exists with rates:
      | type | price_per_kwh | parking_price_per_hour |
      | AC   | 0.35          | 0.10                   |
      | DC   | 0.55          | 0.20                   |
    And location "Prater" has a charger of type "AC" with status "in operation free"
    And customer "Alice" performs a charging session at charger ID 1 of location "Prater" with:
      | start_time | duration_minutes |
      | 14:00      | 25               |
    When I view the invoice items of customer "Alice"
    Then the invoice item count for customer "Alice" is 2
    And the invoice items for customer "Alice" show the following:
  """
  1 - TOPUP - amount: 50 - balance after: 50
  2 - CHARGE - start: 14:00 - duration: 25min - energy: 4.58kWh - rate: 0.35 - parking_rate: 0.10 - energy_cost: 1.60 - parking_cost: 0.04 - total: 1.65 - balance after: 48.35
  """