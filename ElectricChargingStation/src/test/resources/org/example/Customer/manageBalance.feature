# E2 – Manage Balance
@customer @balance
Feature: Manage prepaid account balance
  As a customer
  I want to manage the balance of my customer account
  so that I can control how much credit is available for charging sessions.

  Background:
    Given I am logged in as a customer

  @US2.1
  Scenario: View current balance
    When I open my profile page
    Then I see my current balance


  @US2.2
  Scenario Outline: Top up balance successfully
    Given my current balance is "<startbalance>"
    When I top up "<amount>"
    Then my balance becomes "<endbalance>"
    And I receive a receipt in my invoices

    Examples:
      | startbalance | amount | endbalance   |
      | 0.00         | 20.00  |        20.00 |
      | 5.00         | 30.00  |        35.00 |

  @US2.3
  Scenario: Withdraw balance
    Given my current balance is "12.50"
    When I request a withdrawal of "12.50"
    Then my available balance becomes "0.00"
    And I receive a receipt in my invoices
