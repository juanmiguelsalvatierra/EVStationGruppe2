# E2 – Manage Balance
@customer @balance
Feature: Manage prepaid account balance
  As a customer
  I want to manage the balance of my customer account
  so that I can control how much credit is available for charging sessions.

  Background:
    Given customer "Hans Huber" with the email "hansi@gmail.com" exists
    And the customer "Hans Huber" has id 1

  @US2.1
  Scenario: View initial balance
    Given customer with id 1 has 0 invoices
    When customer with id 1 reads his balance
    Then the customer with id 1 has a balance of 0 €

  @US2.2
  Scenario: Top up balance successfully
    Given customer with id 1 has a balance of 0 €
    When customer with id 1 tops up his balance with 50 €
    When customer with id 1 tops up his balance with 17.23 €
    Then the customer with id 1 has a balance of 67.23 €

  @US2.2 @negative
  Scenario: Negative top up does not work
    Given customer with id 1 has a balance of 50 €
    When customer with id 1 tops up his balance with -10 €
    Then the customer with id 1 has a balance of 50 €
