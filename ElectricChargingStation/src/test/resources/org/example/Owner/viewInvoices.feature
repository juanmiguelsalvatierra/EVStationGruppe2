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
    And the customer with the ID 1 top-ups the amount 50
    And the customer with the ID 2 top-ups the amount 20
    And the customer with the ID 2 top-ups the amount 30
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
    Given the customer "Alice" with email "alice@test.at" exists
    And the customer "Alice" has ID 1
    And the customer "Bob" with email "bob@test.at" exists
    And the customer "Bob" has ID 2
    And the customer with the ID 1 top-ups the amount 50
    And the customer with the ID 2 top-ups the amount 100
    And a location "Prater" with address "Praterallee 1, 1020 Wien" exists
    And the location "Prater" has ID 1
    And the location with ID 1 has the following current prices:
      | price_per_kWh_AC | price_per_kWh_DC| parking_price_AC|parking_price_DC|
      | 1.00             | 2.00            | 2.50            | 3.50           |
    And the location with the ID 1 has a charger of type "AC" with status "IN_ORDER_FREE"
    And the customer with the ID 1 performs a charging session of 30 minutes at charger ID 1 of the location with the ID 1
    When I view all invoices
    Then I see the following invoice overview:
  """
  Customer: Alice
  1 - TOPUP - amount: 50 - balance after: 50

  Customer: Bob
  1 - TOPUP - amount: 100 - balance after: 100
  2 - CHARGE - duration: 30min - energy: watt of AC (needs to be implemented)x duration = kWh - price_per_kWh_AC - parking_price_AC - energy_cost: kWh * price_per_kWh_AC - parking_cost: duration * parking_price_AC - total: sum(ek + pc) - balance after: 50
  """

