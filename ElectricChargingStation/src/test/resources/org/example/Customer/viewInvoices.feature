# E5 – View Invoices
@customer @invoices
Feature: View invoices
  As a customer I want a detailed list of invoice items and remaining balance.

  Background:
    Given I am logged in

  @US5.1
  Scenario: View invoice items with details
    When I open my Invoices page
    Then I see each charging session with start time, duration, energy consumed, top-ups and active rates
      And I can export the invoice as PDF
