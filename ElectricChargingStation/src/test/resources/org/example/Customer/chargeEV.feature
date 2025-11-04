# E4 – Charge EV
@customer @charging
Feature: Charge EV and pay by consumption/duration
  As a customer I want to start and finish a charging session and be billed correctly.

  Background:
    Given I am logged in
      And my balance is at least "€5.00"
      And my RFID/app authorization is active

  @US4.1
  Scenario Outline: Successful charging session
    Given I connect to charger "<chargerId>" of type "<type>"
    When I start charging and consume "<kWh>" kWh in "<minutes>" minutes
    Then the invoice item reflects correct price and duration
      And my balance is reduced accordingly

    Examples:
      | chargerId | type | minutes | kWh  |
      | AC-001    | AC   | 45      | 12.3 |
      | DC-010    | DC   | 20      | 18.7 |

  @US4.1 @negative
  Scenario: Block start when insufficient balance
    Given my balance is "€0.80"
    When I attempt to start charging
    Then the session is not started
      And I see "Insufficient balance — please top up"
