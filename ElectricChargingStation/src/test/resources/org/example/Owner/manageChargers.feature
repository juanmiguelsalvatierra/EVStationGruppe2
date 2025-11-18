# E7 – Manage Chargers
@owner @chargers
Feature: Manage chargers
  As the owner, I want to manage chargers, so that I can ensure accurate setup,
  maintenance, and availability of all charging stations.

  Background:
    Given I am logged in as an owner

  @US7.1
  Scenario: Create charger
    When I create a charger with the charger ID "1" of type "AC" at location "Vienna West"
    Then it appears under "Vienna West" with status "in order free"

  @US7.2
  Scenario Outline: Read charger successfully
    When I open the overview page for all chargers
    Then I see each charger with its "<chargerID>", "<chargerType>" and "<operationalStatus>"
    Examples:
      | chargerID    | chargerType | operationalStatus |
      | 1           | AC           | occupied          |
      | 20          | DC           | in order free     |

  @US7.3
  Scenario: Update charger location
    Given the charger with the charger ID "<chargerID>" exists at "Vienna West"
    When I move it to "Linz Center"
    Then it shows location "Linz Center"

  Scenario: Update charger type
    Given the charger with the charger ID "1" currently has type "AC"
    When I assign it type "DC"
    Then it shows charger-type "DC"

  Scenario: Update charger status
    Given the charger with the charger ID "1" currently has charger status "in operation free"
    When I assign it charger status "out of order"
    Then it shows charger status "out of order"

  @US7.4
  Scenario: Delete charger
    Given the charger with the charger ID "1" exists
    When I request to delete it
    Then it disappears from the list
