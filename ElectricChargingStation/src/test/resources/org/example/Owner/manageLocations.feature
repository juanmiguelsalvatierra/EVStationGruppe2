# E6 – Manage Locations
@owner @locations
Feature: CRUD locations
  As an owner I want to manage all charging locations.

  Background:
    Given I am logged in as an owner

  @US6.1
  Scenario: Create a new location
    When I create a location "Vienna West" with address "Mariahilfer Str. 1"
    Then the location appears in the list
      And it has zero chargers initially

  @US6.2
  Scenario: Read all locations
    When I view locations
    Then I see all existing locations with counts of AC/DC chargers and active prices

  @US6.3
  Scenario: Update a location
    Given a location "Vienna West" exists
    When I change its address to "Neubaugasse 5"
    Then the updated address is visible

  @US6.4
  Scenario: Delete a location without chargers
    Given "Temporary Site" exists with zero chargers
    When I delete it
    Then it no longer appears in the list
