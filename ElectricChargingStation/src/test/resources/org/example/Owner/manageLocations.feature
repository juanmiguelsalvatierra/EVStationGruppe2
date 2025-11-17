# E6 – Manage Locations
@owner @locations
Feature: Manage locations
  As the owner, I want to manage all charging locations, so that I can maintain and
  expand my station network effectively

  Background:
    Given I am logged in as an owner

  @US6.1
  Scenario: Create a new location
    When I create a location "Brigittenau, Wien" with address "Höchstädtplatz 6, 1200 Wien"
    Then the location appears in the list of all locations
    And it initially has zero chargers

  @US6.2
  Scenario: Read all locations
    Given a location "Brigittenau, Wien" exists
    When I view locations
    Then I see all existing locations in a list

  @US6.3
  Scenario: Update locations
    Given a location "Brigittenau, Wien" exists
    When I change its address to "Höchstädtplatz 1, 1200 Wien"
    Then the updated address is now visible

  @US6.4 @positive
  Scenario: Delete a location without chargers
    Given "Brigittenau, Wien" exists with zero chargers
    When I request to delete it
    Then it no longer appears in the list

  @US6.4 @negative
  Scenario: Delete a location with existing chargers
    Given "Brigittenau, Wien" exists with two chargers
    When I request to delete it
    Then I will not be able to delete the location

