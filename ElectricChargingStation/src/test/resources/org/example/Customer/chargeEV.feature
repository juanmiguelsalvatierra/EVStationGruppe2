# E4 – Charge EV
@customer @charging
Feature: Charge EV and pay by consumption/duration
  As a customer
  I want to charge my electric vehicle (EV)
  so that I can replenish my vehicle’s battery using my prepaid account balance.

  Background:
    Given I am logged in as a customer


  @US4.1
  Scenario Outline: Successful charging session
    Given A charger with chargerId "<chargerId>" and type "AC" exists
    And my balance is "100"
    When I attempt to connect to charger "<chargerId>"
    And I start charging for "<minutes>" minutes
    Then the invoice item reflects the correct "<price>" and "<duration>"
    And my balance is reduced accordingly to "<balanceAfterCharge>"

    Examples:
      | chargerId | minutes | price | duration | balanceAfterCharge |
      | 1         | 45      | 10    | 45       | 90                 |
      | 2         | 60      | 13.4  | 60       | 86.6               |

  @US4.1 @negative
  Scenario: Unsuccessful charging session - due to insufficient balance
    Given Given A charger with chargerId "1" and type "AC" exists
    And my balance is "0"
    When I attempt to start charging for "45" minutes
    Then the session is not started
    And I see an error message "Insufficient balance — please top up"

  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid time
    Given Given A charger with chargerId "1" and type "AC" exists
    And my balance is "100"
    When I attempt to start charging for "<minutes>" minutes
    Then the session is not started
    And I see an error message "Invalid time — please insert correct time"

    Examples:
      | minutes |
      | 0       |
      | -10     |

  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid chargerId
    When I attempt to start charging with charger "<chargerId>"
    And i want to charge for "40" minutes
    Then the session is not started
    And I see "Invalid chargerId — please insert correct chargerId"

    Examples:
      | chargerId |
      | -1        |
      | 0         |
