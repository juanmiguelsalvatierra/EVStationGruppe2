# E1 – Customer Account Management
@customer @account
Feature: Manage customer account
  As a customer
  I want to manage my customer account
  so that I can keep my personal data and account information up to date.

  @US1.1
  Scenario Outline: Create a new customer account successfully
    Given I am a visitor without an existing account for "<email>"
    When I create an account with name "<name>" , email "<email>"
    Then my account is created with a unique Customer ID
    And my initial balance is "0"

    Examples:
      | name     | email          |
      | alice    | alice@demo.com |
      | bob      | bob@demo.com   |

  @US1.1 @negative
  Scenario: Prevent duplicate account creation
    Given an account already exists for "alice@demo.com"
    When I try to register again with "alice@demo.com"
    Then I won’t be allowed to create a new account for "alice@demo.com"

  @US1.2
  Scenario: Update account details
    Given I am logged in as customer "alice@demo.com"
    And my current username is "Name"
    When I update my username to "NewName"
    Then my account shows “NewName” as my username
    #only name changeable in settings?

  @US1.3
  Scenario: Delete customer account successfully
    Given I am logged in as customer "alice@demo.com"
    And my balance is "0"
    When I request to delete my account
    Then my account is deleted
    And I immediately lose access to the portal

  @US1.3 @negative
  Scenario: Delete customer account with non-zero balance
    Given I am logged in as customer "alice@demo.com"
    And my balance is "10"
    When I request to delete my account
    Then I won’t be able to delete my account

  @US1.4
  Scenario: View customer account
    Given I am logged in as customer "alice@demo.com"
    When I open my profile page
    Then I see my account details