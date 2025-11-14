# E6 – Manage Locations
@owner @locations
Feature: Manage locations
  As the owner, I want to manage all charging locations, so that I can maintain and
  expand my station network effectively

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
    Then I see all existing locations in a list

  @US6.3
  Scenario: Update a location
    Given a location "Vienna West" exists
    When I change its address to "Neubaugasse 5"
    Then the updated address is visible

  @US6.4 @positive
  Scenario: Delete a location without chargers
    Given "Temporary Site" exists with zero chargers
    When I request to delete it
    Then it no longer appears in the list

  @US6.4 @negative
  Scenario: Delete a location with existing chargers
    Given "Temporary Site" exists with two chargers
    When I request to delete it
    Then I will not be able to delete the location

