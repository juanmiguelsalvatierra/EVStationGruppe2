# E6 – Manage Locations
@owner @locations
Feature: CRUD locations
  As the owner, I want to manage all charging locations, so that I can maintain and
  expand my station network effectively.

  Background:
    Given I am logged in as an owner

  @US6.1
  Scenario: Create a new location
    When I create a location "Brigittenau, Wien" with address "Höchstädtplatz 6, 1200 Wien"
    Then the location appears in the list of all locations
    And it initially has zero chargers

  @US6.2
  Scenario: Read locations
    When I view the list of locations
    Then I see all existing locations with their number of chargers

  @US6.3
  Scenario: Update locations
    Given a location "Brigittenau, Wien" exists
    When I change its address to "Höchstädtplatz 1, 1200 Wien"
    Then the updated address is now visible

  @US6.4
  Scenario: Delete location
    Given a location "Brigittenau, Wien" exists
    When I delete it
    Then it no longer appears in the list of all locations
