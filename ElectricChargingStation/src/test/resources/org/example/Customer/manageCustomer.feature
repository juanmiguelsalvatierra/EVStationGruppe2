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
    Then I see at ID 1 the customer account with name "John Doe" and email "john.doe@example.com"
    And the customer with the ID 1 initially has 0 invoice items

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
    And the customer with the ID 4 has name "Dave King" and email "dave.king@example.com"
    And reading the customer accounts as lists shows following output:
    """
    1 - Alice Doe - alice.doe@example.com
    2 - Bob Smith - bob.smith@example.com
    3 - Carol Lee - carol.lee@example.com
    4 - Dave King - dave.king@example.com
    """

  @US1.1 @negative
  Scenario: Creating a customer with a duplicate name
    Given a customer account "John Doe" exists with email "john.doe@example.com"
    When I try to create a customer account "John Doe" with email "john.doe@example.com"
    Then the number of customer accounts remains 1
    And reading the customer accounts as lists shows following output:
    """
    1 - John Doe - john.doe@example.com
    """

  @US1.2
  Scenario: Update the name of an existing customer
    Given the following customer accounts exist:
      | name       | email                    |
      | Alice Doe  | alice.doe@example.com    |
      | Bob Smith  | bob.smith@example.com    |
    When I update the customer account with the ID 1 to name "Peter Parker"
    Then the customer account with the ID 1 should have name "Peter Parker" and email "alice.doe@example.com"
    And the customer account with the ID 2 remains unchanged with the name "Bob Smith" and email "bob.smith@example.com"
    And reading the customer accounts as lists shows following output:
    """
    1 - Peter Parker - alice.doe@example.com
    2 - Bob Smith - bob.smith@example.com
    """

  @US1.2
  Scenario: Update the email of an existing customer
    Given the following customer accounts exist:
      | name       | email                    |
      | Alice Doe  | alice.doe@example.com    |
      | Bob Smith  | bob.smith@example.com    |
    When I update the customer account with the ID 1 to email "test@test.com"
    Then the customer account with the ID 1 should have name "Alice Doe" and email "test@test.com"
    And the customer account with the ID 2 remains unchanged with the name "Bob Smith" and email "bob.smith@example.com"
    And reading the customer accounts as lists shows following output:
    """
    1 - Alice Doe - test@test.com
    2 - Bob Smith - bob.smith@example.com
    """

  @US1.3 @positive
  Scenario: Delete an existing customer account
    Given the following customer accounts exist:
      | name       | email                    |
      | Alice Doe  | alice.doe@example.com    |
      | Bob Smith  | bob.smith@example.com    |
      | Carol Lee  | carol.lee@example.com    |
    When I try to delete the customer account with the ID 2
    Then the number of customer accounts is 2
    And the customer account with the ID 2 no longer exists
    And reading the customer accounts as lists shows following output:
    """
    1 - Alice Doe - alice.doe@example.com
    3 - Carol Lee - carol.lee@example.com
    """

  @US1.3 @negative
  Scenario: Delete a non-existing customer account
    Given the following customer accounts exist:
      | name       | email                    |
      | Alice Doe  | alice.doe@example.com    |
    When I try to delete the customer account with the ID 5
    Then the number of customer accounts remains 1
    And reading the customer accounts as lists shows following output:
    """
    1 - Alice Doe - alice.doe@example.com
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