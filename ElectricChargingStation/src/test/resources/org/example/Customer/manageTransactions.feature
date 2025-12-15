# E2 – Manage Transactinos
@customer @transactions
Feature: Manage account transactions
  As a customer
  I want to manage the transactions of my customer account
  so that I can control how much credit is available for charging sessions.

  Background:
    Given customer "Hans Huber" with the email "hansi@gmail.com" exists
    And the customer "Hans Huber" has id 1

  @US2.2
  Scenario: Top up Transaction successfully
    Given customer with id 1 has a balance of 0 €
    When customer with id 1 tops up 50 € at "2025-03-15T14:30:00"
    And customer with id 1 tops up 27.23 € at "2025-12-15T14:30:00"
    Then the customer with id 1 has a balance of 77.23 €

  @US2.2 @negative
  Scenario: Negative top up does not work
    Given customer with id 1 has a balance of 50 €
    When customer with id 1 does a top up with minus 10 € at "2025-03-15T14:30:00"
    Then the customer with id 1 has a balance of 50 €

  @US2.2 @negative
  Scenario: Top up with Date bevor other Invoice Item
    Given customer with id 1 has a balance of 0 €
    When customer with id 1 tops up 50 € at "2025-12-15T14:30:00"
    And customer with id 1 tops up 27.23 € at "2025-12-01T14:30:00"
    Then the customer with id 1 has a balance of 50 €
