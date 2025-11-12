# E11 – View Invoices (Owner)
@owner @invoices
Feature: View Invoices
  As the owner, I want to view all invoices,
  so that I can monitor customer billing and
  charging activity across the station network.

  Background:
    Given I am logged in as an owner

  @US11.1
  Scenario: View All Invoices
    When I fetch all invoices
    Then I get a list of all Invoices with following properties: customer ID, balance

    
  @US11.1
  Scenario: View specific Invoices
    When I fetch invoice for customer ID "1"
    Then I get a list of all Invoice-Items with following properties: date, total amount, type, time?, kwh?



