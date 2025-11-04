# E1 – Customer Account Management
@customer @account
Feature: Manage customer account
  So that I can keep my personal data current,
  As a customer
  I want to create, update, and delete my account.

  Background:
    Given the platform is online
    And no outages are reported
    And the customer portal supports email-based login and prepaid accounts

  @US1.1
  Scenario Outline: Create a new customer account successfully
    Given I am a visitor without an existing account for "<email>"
    When I register with email "<email>" and password "<password>"
      And I accept the terms and conditions
    Then my account is created with a unique Customer ID
      And I receive a verification email to "<email>"
      And my initial balance is "0"
      And my profile status is "Pending verification"

    Examples:
      | email             | password    |
      | alice@demo.com    | S3cur3!pass |
      | bob@demo.com      | 1Password!  |

  @US1.1 @negative
  Scenario: Prevent duplicate account creation
    Given an account already exists for "alice@demo.com"
    When I try to register again with "alice@demo.com"
    Then I am informed that the email is already in use
      And I am offered to reset my password

  @US1.2
  Scenario Outline: Update account details
    Given I am logged in as customer "<email>"
      And my current contact phone is "<oldPhone>"
    When I update my phone to "<newPhone>"
    Then my account shows phone "<newPhone>"
      And an audit log entry is recorded

    Examples:
      | email            | oldPhone | newPhone  |
      | alice@demo.com   | +4311111 | +4312222  |

  @US1.3
  Scenario: Delete customer account
    Given I am logged in as customer "alice@demo.com"
      And my balance is "0"
    When I request to delete my account
      And I confirm the deletion
    Then my account is scheduled for deletion within "30 days"
      And I immediately lose access to the portal
      And I receive a confirmation email
