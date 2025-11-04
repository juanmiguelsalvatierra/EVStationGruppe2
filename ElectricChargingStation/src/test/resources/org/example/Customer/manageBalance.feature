# E2 – Manage Balance
@customer @balance
Feature: Manage prepaid account balance
  In order to control available credit for charging sessions,
  As a customer
  I want to view, top up, and withdraw my balance.

  Background:
    Given I am logged in as a customer

  @US2.1
  Scenario: View current balance
    When I open the Balance page
    Then I see my current balance in EUR
      And the timestamp of the last update

  @US2.2
  Scenario Outline: Top up balance successfully
    Given my current balance is "<start>"
    When I top up "<amount>" via "<method>"
    Then my balance becomes "<end>"
      And I receive a receipt

    Examples:
      | start | amount | method     | end   |
      | 0.00  | 20.00  | creditcard | 20.00 |
      | 5.00  | 15.00  | SEPA       | 20.00 |

  @US2.3
  Scenario: Withdraw balance
    Given my current balance is "12.50"
      And my verified IBAN is "AT611904300234573201"
    When I request a withdrawal of "12.50"
    Then the payout is scheduled within "3 business days"
      And my available balance becomes "0.00"
