# E2 – Manage Balance
@customer @balance
Feature: Manage prepaid account balance
  As a customer
  I want to manage the balance of my customer account
  so that I can control how much credit is available for charging sessions.

  Background:
    Given customer "Hans Hubert" with the email "hansi@gmail.com" exists
    And the customer "Hans Huber" hast id 1

  @US2.1
  Scenario: View initial balance
    Given customer with id 1 hasn't don any top ups
    When customer with id 1 gets his balance
    Then the customer with id 1 has a balance of 0 €

  @US2.2
  Scenario: Top up balance successfully
    When customer with id 1 tops up his balance with 50 €
    Then the customer with id 1 has a balance of 50 €

  @US2.2 @negative
  Scenario: Negative top up does not work
    Given customer with id 1 has a balance of 50 €
    When customer with id 1 tops up his balance with -10 €
    Then the customer with id 1 has a balance of 50 €
