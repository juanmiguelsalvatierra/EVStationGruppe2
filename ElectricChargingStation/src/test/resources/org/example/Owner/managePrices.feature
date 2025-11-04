# E10 – Manage Prices
@owner @pricing
Feature: Manage charging prices
  As an owner I want to read and update per-location prices.

  Background:
    Given I am logged in as an owner

  @US10.1
  Scenario: Read current prices per location
    When I open the Prices page for "Linz Center"
    Then I see the active AC/DC rates and effective dates

  @US10.2
  Scenario: Update prices
    Given the current AC price is "0.35" and DC price is "0.55"
    When I set new AC price "0.39" and DC price "0.59"
      And I publish changes effective "2025-11-05"
    Then new sessions started after that date use updated rates
