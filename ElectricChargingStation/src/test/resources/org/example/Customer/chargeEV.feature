# E4 – Charge EV
@customer @charging
Feature: Charge EV and pay by consumption/duration
  As a customer
  I want to charge my electric vehicle (EV)
  so that I can replenish my vehicle’s battery using my prepaid account balance.

  Background:
    Given a customer "Hans Huber" with the email "hansi@gmail.com" exists
    And the customer "Hans Huber" has id 1
    And a location "Karlsplatz charging" with a chargerId 1 of type "AC" with status "FREE" exists


  @US4.1
  Scenario: Successful charging session
    Given a customer with id 1 has a balance of 100 €
    When the customer with id 1 attempts to connect to charger 1
    And the customer with id 1 starts charging for 45 minutes
    Then the last invoice item from customer 1 reflects the correct price 10.5 and duration 45 minutes
    And the customer with id 1 has a balance of 89.5 €



  @US4.1 @negative
  Scenario: Unsuccessful charging session - due to insufficient balance
    Given A customer with id 1 has a balance of 100 €
    When the customer with id 1 attempts to start charging for 45 minutes
    Then the session is not started


  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid time
    And a customer with id 1 has a balance of 100 €
    When the customer with id 1 attempts to start charging for "<minutes>" minutes
    Then the session is not started

    Examples:
      | minutes |
      | 0       |
      | -10     |

  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid chargerId
    When a customer with id 1 attempts to start charging with charger "<chargerId>"
    And the customer with id 1 wants to charge for 40 minutes
    Then the session is not started

    Examples:
      | chargerId |
      | -1        |
      | 0         |
