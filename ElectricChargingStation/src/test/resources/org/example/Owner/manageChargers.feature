# E7 – Manage Chargers
@owner @chargers
Feature: CRUD chargers
  As an owner I want to manage charger entries and their status.

  Background:
    Given I am logged in as an owner

  @US7.1
  Scenario: Create charger
    When I create charger "AC-001" of type "AC" at location "Vienna West"
    Then it appears under "Vienna West" with status "Available"

  @US7.2
  Scenario: Read all chargers
    When I open the Chargers page
    Then I see each charger with type and operational status

  @US7.3
  Scenario: Update charger
    Given charger "AC-001" exists at "Vienna West"
    When I move it to "Linz Center"
    Then it shows location "Linz Center"

  @US7.4
  Scenario: Delete charger
    Given charger "AC-001" exists
    When I delete it
    Then it disappears from the list
