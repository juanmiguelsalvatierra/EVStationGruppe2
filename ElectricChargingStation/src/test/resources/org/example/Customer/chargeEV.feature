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


  @US4.1
  Scenario: Successful charging session
    Given a customer with id 1 has a balance of 100 €
    When the customer with id 1 attempts to charge at location "Karlsplatz charging" at the charger with id 1 for 45 minutes
    Then the last invoice item from customer 1 reflects the correct price 10.5 and duration 45 minutes
    And the balance of customer with id 1 is 89.5 €



  @US4.1 @negative
  Scenario: Unsuccessful charging session - due to insufficient balance
    Given a customer with id 1 has a balance of 100 €
    When the customer with id 1 attempts to charge at location "Karlsplatz charging" at the charger with id 1 for 45 minutes
    Then  the balance for customer 1 remains 100 €


  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to wrong charger status
    Given a customer with id 1 has a balance of 100 €
    And at location "Karlsplatz charging" exists a charger with id 2 of type "DC" and status "<status>"
    When the customer with id 1 attempts to charge at location "Karlsplatz charging" at the charger with id 1 for 45 minutes
    Then the balance for customer 1 remains 100 €
    Examples:
      | status        |
      | OCCUPIED      |
      | OUT_OF_ORDER  |


  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid time
    Given a customer with id 1 has a balance of 100 €
    When the customer with id 1 attempts to charge at location "Karlsplatz charging" at the charger with id 1 for <minutes> minutes
    Then the balance for customer 1 remains 100 €

    Examples:
      | minutes |
      | 0       |
      | -10     |

  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid chargerId
    Given a customer with id 1 has a balance of 100 €
    When the customer with id 1 attempts to charge at location "Karlsplatz charging" at the charger with id <chargerId> for 45 minutes
    Then the balance for customer 1 remains 100 €

    Examples:
      | chargerId |
      | -1        |
      | 0         |
