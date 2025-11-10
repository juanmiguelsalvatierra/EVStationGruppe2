# E5 – View Invoices
@customer @invoices
Feature: View invoices
  As a customer, I want to view my invoices,
  so that I can review all past charging sessions, top-ups, and my remaining balance.

  Background:
    Given I am logged in

  @US5.1
  Scenario: View invoice items with details
    When I open my Invoices page
    Then I see each charging session with, duration, energy consumed, rates and my top-ups and remaining balance
    # start time? active rates ist bei location nicht bei Invoices?
