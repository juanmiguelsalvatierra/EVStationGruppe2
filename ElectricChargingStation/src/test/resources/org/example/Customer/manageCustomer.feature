# E1 – Customer Account Management
@customer @account
Feature: Manage customer account
  As a customer
  I want to manage my customer account
  so that I can keep my personal data and account information up to date.

  @US1.1
  Scenario: Create a new customer account
    Given no customer account exists
    When I create a customer account "John Doe" with email "john.doe@example.com"
    Then I see a customer account with name "John Doe" and email "john.doe@example.com"
    And it initially has 0 invoice items

  @US1.1
  Scenario: Create a list of new customer accounts
    Given no customer account exists
    When I create customer accounts with following parameters:
      | name       | email                    |
      | Alice Doe  | alice.doe@example.com    |
      | Bob Smith  | bob.smith@example.com    |
      | Carol Lee  | carol.lee@example.com    |
      | Dave King  | dave.king@example.com    |
    Then the number of customer accounts is 4
    And customer "Dave King" has email "dave.king@example.com"
    And reading the customer accounts as lists shows following output:
    """
    1 - Alice Doe - alice.doe@example.com
    2 - Bob Smith - bob.smith@example.com
    3 - Carol Lee - carol.lee@example.com
    4 - Dave King - dave.king@example.com
    """

  @US1.1
  @negative
  Scenario: Creating a customer with a duplicate name
    Given a customer account "John Doe" exists with email "john.doe@example.com"
    When I try to create a customer account "John Doe" with email "john.doe@example.com"
    Then I should see an error saying "Customer account already exists"
    And reading the customer account as lists shows following output:
    """
    1 - John Doe - john.doe@example.com
    """

  @US1.4
  Scenario: Read all existing customer accounts
    Given the following customer accounts exist:
      | name       | email                    |
      | Alice Doe  | alice.doe@example.com    |
      | Bob Smith  | bob.smith@example.com    |
      | Carol Lee  | carol.lee@example.com    |
      | Dave King  | dave.king@example.com    |
    When I view all customer accounts
    Then I should see the following customer accounts:
    """
    1 - Alice Doe - alice.doe@example.com
    2 - Bob Smith - bob.smith@example.com
    3 - Carol Lee - carol.lee@example.com
    4 - Dave King - dave.king@example.com
    """
