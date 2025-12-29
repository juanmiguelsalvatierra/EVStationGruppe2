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
    Given the customer "Alice" with email "alice@test.at" exists
    And the customer "Alice" has ID 1
    And the customer "Bob" with email "bob@test.at" exists
    And the customer "Bob" has ID 2
    And the customer with the ID 1 top-ups the amount 50 at "2025-03-15T14:30:00"
    And the customer with the ID 2 top-ups the amount 20 at "2025-03-15T15:40:00"
    And the customer with the ID 2 top-ups the amount 30 at "2025-03-16T15:50:00"
    When I view all invoices
    Then I see the following invoice overview:
    """
    Customer: Alice
    1 - TOPUP - 15.03.2025 14:30 - amount: 50,00 - balance after: 50,00

    Customer: Bob
    1 - TOPUP - 15.03.2025 15:40 - amount: 20,00 - balance after: 20,00
    2 - TOPUP - 16.03.2025 15:50 - amount: 30,00 - balance after: 50,00
    """

  @US11.1
  Scenario: View all invoices including charging sessions
    Given the customer "Alice" with email "alice@test.at" exists
    And the customer "Alice" has ID 1
    And the customer "Bob" with email "bob@test.at" exists
    And the customer "Bob" has ID 2
    And the customer with the ID 1 top-ups the amount 50 at "2025-03-15T14:30:00"
    And the customer with the ID 2 top-ups the amount 100 at "2025-03-17T15:30:00"
    And a location "Prater" with address "Praterallee 1, 1020 Wien" exists
    And the location "Prater" has ID 1
    And the location with ID 1 has the following current prices:
      | price_per_kWh_AC | price_per_kWh_DC| parking_price_AC|parking_price_DC|
      | 1.00             | 2.00            | 2.50            | 3.50           |
    And the location with the ID 1 has a charger of type "AC" with status "IN_OPERATION_FREE"
    And the customer with the ID 1 performs a charging session of 30 minutes using "AC" mode at charger ID 1 of the location with the ID 1 at "2025-03-16T14:50:00"
    When I view all invoices
    Then I see the following invoice overview:
  """
  Customer: Alice
  1 - TOPUP - 15.03.2025 14:30 - amount: 50,00 - balance after: 50,00
  2 - CHARGE - 16.03.2025 14:50 - duration: 30min - energy: 5,00kWh - price_per_kWh_AC: 1,00 - parking_price_AC: 2,50 - energy_cost: 5,00 - parking_cost: 1,25 - total: -6,25 - balance after: 43,75

  Customer: Bob
  1 - TOPUP - 17.03.2025 15:30 - amount: 100,00 - balance after: 100,00
  """

