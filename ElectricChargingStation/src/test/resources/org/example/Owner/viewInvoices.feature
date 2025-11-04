# E11 – View Invoices (Owner)
@owner @invoices
Feature: View all customer invoices
  As an owner I want to access all invoices to track transaction histories.

  Background:
    Given I am logged in as an owner

  @US11.1
  Scenario: List and search all invoices
    When I open the Invoices overview
    Then I can filter by customer email, date range and location
      And open any invoice to see line items and totals
