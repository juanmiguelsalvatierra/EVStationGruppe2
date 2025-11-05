# E4 – Charge EV
@customer @charging
Feature: Charge EV and pay by consumption/duration
  As a customer I want to start and finish a charging session and be billed correctly.

  Background:
    Given I am logged in

  @US4.1
  Scenario Outline: Successful charging session
    Given I connect to charger "<chargerId>"
    And my balance is "100"
    When I attempt to start charging for "<minutes>" minutes
    Then the invoice item reflects correct price and duration
      And my balance is reduced accordingly

    Examples:
      | chargerId | minutes |
      | 1         | 45      |
      | 2         | 60      |

  @US4.1 @negative
  Scenario: Unsuccessful charging session - due to insufficient balance
    Given I connect to charger "1"
    And my balance is "0"
    When I attempt to start charging for "45" minutes
    Then the session is not started
      And I see "Insufficient balance — please top up"

  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid time
    Given I connect to charger "1"
    And my balance is "100"
    When I attempt to start charging for "<minutes>" minutes
    Then the session is not started
    And I see "Invalid time — please insert correct time"

    Examples:
      | minutes |
      | 0       |
      | -10     |

  @US4.1 @negative
  Scenario Outline: Unsuccessful charging session - due to invalid chargerId
    Given I connect to charger "<chargerId>"
    When I attempt to start charging for "<minutes>" minutes
    Then the session is not started
    And I see "Invalid chargerId — please insert correct chargerId"

    Examples:
      | chargerId |
      | -1        |
      | 0         |
