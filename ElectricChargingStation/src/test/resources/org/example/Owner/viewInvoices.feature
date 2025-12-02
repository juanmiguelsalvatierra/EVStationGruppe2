# E11 – View Invoices (Owner)
@owner @invoices
Feature: View Invoices
  As the owner, I want to view all invoices,
  so that I can monitor customer billing and
  charging activity across the station network.

  @US11.1
  Scenario: View all invoices when no customer exists
    Given no customers exist
    When I view all invoices
    Then I see the following invoice overview:
    """
    """

  @US11.1
  Scenario: View all invoices for multiple customers with only top-ups
    Given a customer "Alice" with email "alice@test.com" exists with balance 0
    And a customer "Bob" with email "bob@test.com" exists with balance 0
    And customer "Alice" top-ups the amount 50
    And customer "Bob" top-ups the amount 20
    And customer "Bob" top-ups the amount 30
    When I view all invoices
    Then I see the following invoice overview:
    """
    Customer: Alice
    1 - TOPUP - amount: 50 - balance after: 50

    Customer: Bob
    1 - TOPUP - amount: 20 - balance after: 20
    2 - TOPUP - amount: 30 - balance after: 50
    """

  @US11.1
  Scenario: View all invoices including charging sessions
    Given a customer "Alice" with email "alice@test.com" exists with balance 0
    And a customer "Bob" with email "bob@test.com" exists with balance 0
    And customer "Alice" top-ups the amount 50
    And customer "Bob" top-ups the amount 100
    And a location "Prater" at "Praterallee 1, 1020 Wien" exists with rates:
      | type | price_per_kwh | parking_price_per_hour |
      | AC   | 0.35          | 0.10                   |
      | DC   | 0.55          | 0.20                   |
    And location "Prater" has a charger of type "AC" with status "in operation free"
    And customer "Bob" performs a charging session at charger ID 1 of location "Prater" with:
      | start_time | duration_minutes |
      | 18:00      | 20               |
    When I view all invoices
    Then I see the following invoice overview:
  """
  Customer: Alice
  1 - TOPUP - amount: 50 - balance after: 50

  Customer: Bob
  1 - TOPUP - amount: 100 - balance after: 100
  2 - CHARGE - start: 18:00 - duration: 20min - energy: 3.67kWh - rate: 0.35 - parking_rate: 0.10 - energy_cost: 1.28 - parking_cost: 0.03 - total: 1.31 - balance after: 98.69
  """

