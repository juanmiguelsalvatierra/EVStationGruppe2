# E10 – Manage Prices
@owner @pricing
Feature: Manage charging prices
  As the owner, I want to manage charging prices,
  so that I can adjust rates dynamically for each location and
  ensure correct billing for all charging sessions. 

  Background:
    Given I am logged in as an owner

  @US10.1
  Scenario: Read prices
    When I fetch all prices
    Then I get a list with following properties location, charger, Type (AC/DC), kwh, price per kWh

  @US10.2
  Scenario: Update prices
    Given the current price is "0.4" of charger "1"
    When I set new  price "0.5" for charger "1"
    Then I get confirmation that price updated to "0.5" of charger "1"

  @US10.2
  Scenario: Update prices - wrong price
    Given the current price is "0.4" of charger "1"
    When I set new  price "-0.5" for charger "1"
    Then I see "Invalid price — please insert correct price"
