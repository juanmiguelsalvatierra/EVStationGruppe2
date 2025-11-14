# E7 – Manage Chargers
@owner @chargers
Feature: CRUD chargers
  As the owner, I want to manage chargers, so that I can ensure accurate setup,
  maintenance, and availability of all charging stations.

  Background:
    Given I am logged in as an owner

  @US7.1
  Scenario Outline: Create charger successfully
    When I create a charger with "<chargerID>" of type "<chargerType>" at location "<location>"
    Then it appears under "<location>" with "<chargerID>" and "<chargerType>"
    Examples:
      | chargerID  | chargerType | location                     |
      | AC-1       | AC          | Brigittenau, Wien            |
      | DC-20      | DC          | Schwechat, Niederösterreich  |

  @US7.2
  Scenario Outline: Read charger successfully
    When I open the overview page for all chargers
    Then I see each charger with its "<chargerID>", "<chargerType>" and "<operationalStatus>"
    Examples:
      | chargerID  | chargerType | operationalStatus |
      | AC-1       | AC          | occupied          |
      | DC-20      | DC          | free              |

  @US7.3
  Scenario: Update charger
    Given charger "AC-1" exists at "Brigittenau, Wien"
    When I move it to "Schwechat, Niederösterreich"
    Then it shows location "Schwechat, Niederösterreich"

  @US7.4
  Scenario: Delete charger
    Given charger "AC-1" exists
    When I delete it
    Then it disappears from the list of all chargers
