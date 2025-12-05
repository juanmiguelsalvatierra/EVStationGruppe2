# E4 – Charge EV
@customer @charging
Feature: Charge EV and pay by consumption/duration
  As a customer
  I want to charge my electric vehicle (EV)
  so that I can replenish my vehicle’s battery using my prepaid account balance.

  Background:
    Given a customer "Hans Huber" with the email "hansi@gmail.com" exists
    And customer "Hans Huber" has id 1
    And a location "Karlsplatz charging" with the address "Karlsplatz 1, 1010 Wien" exists
    And the location "Karlsplatz charging" has a charger with Id 1 of type "AC" and status "IN_OPERATION_FREE"
    And the location "Karlsplatz charging" has the following current prices:
      | price_per_kWh_AC | price_per_kWh_DC| parking_price_AC|parking_price_DC|
      | 1.00             | 2.00            | 2               | 4              |

  @US4.1
  Scenario: Successful charging session
    Given a customer with id 1 has a balance of 100 €
    When the customer with id 1 charges at location "Karlsplatz charging" at the charger with id 1 using "DC" mode for 45 minutes
    Then the last invoice item from customer 1 reflects the correct price 78 and duration 45 minutes
    And the balance of customer with id 1 is 22 €

  @US4.1
  Scenario: Successful charging session - with topup afterwards
    Given a customer with id 1 has a balance of 100 €
    When the customer with id 1 charges at location "Karlsplatz charging" at the charger with id 1 using "DC" mode for 45 minutes
    When the customer with id 1 tops up his balance with 50 €
    And the balance of customer with id 1 is 72 €

  @US4.1 @negative
  Scenario: Unsuccessful charging session - due to insufficient balance
    Given a customer with id 1 has a balance of 5 €
    When the customer with id 1 charges at location "Karlsplatz charging" at the charger with id 1 using "AC" mode for 45 minutes
    Then  the balance for customer 1 remains 5 €


  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to wrong charger status
    Given a customer with id 1 has a balance of 100 €
    And at location "Karlsplatz charging" exists a charger with id 2 of type "DC" and status "<status>"
    When the customer with id 1 charges at location "Karlsplatz charging" at the charger with id 2 using "DC" mode for 45 minutes
    Then the balance for customer 1 remains 100 €
    Examples:
      | status        |
      | OCCUPIED      |
      | OUT_OF_ORDER  |


  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid time
    Given a customer with id 1 has a balance of 100 €
    When the customer with id 1 charges at location "Karlsplatz charging" at the charger with id 1 using "DC" mode for <minutes> minutes
    Then the balance for customer 1 remains 100 €

    Examples:
      | minutes |
      | 0       |
      | -10     |

  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid chargerId
    Given a customer with id 1 has a balance of 100 €
    When the customer with id 1 charges at location "Karlsplatz charging" at the charger with id <chargerId> using "DC" mode for 45 minutes
    Then the balance for customer 1 remains 100 €

    Examples:
      | chargerId |
      | -1        |
      | 0         |
