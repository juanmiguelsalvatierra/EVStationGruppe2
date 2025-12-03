# E4 – Charge EV
@customer @charging
Feature: Charge EV and pay by consumption/duration
  As a customer
  I want to charge my electric vehicle (EV)
  so that I can replenish my vehicle’s battery using my prepaid account balance.

  Background:
    Given I am logged into my customer account "Bertl"
    And a location "Karlsplatz charging" with a charger "1" of type "AC" exists


  @US4.1
  Scenario: Successful charging session
    Given My balance is "100"
    When I attempt to connect to charger "1"
    And I start charging for "45" minutes
    Then the invoice item reflects the correct price "10.5" and duration "45" minutes
    And my balance is reduced accordingly to "89.5"



  @US4.1 @negative
  Scenario: Unsuccessful charging session - due to insufficient balance
    Given My balance is "0"
    When I attempt to start charging for "45" minutes
    Then the session is not started


  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid time
    Given Given A charger with chargerId "1" and type "AC" exists
    And my balance is "100"
    When I attempt to start charging for "<minutes>" minutes
    Then the session is not started

    Examples:
      | minutes |
      | 0       |
      | -10     |

  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid chargerId
    When I attempt to start charging with charger "<chargerId>"
    And i want to charge for "40" minutes
    Then the session is not started

    Examples:
      | chargerId |
      | -1        |
      | 0         |
