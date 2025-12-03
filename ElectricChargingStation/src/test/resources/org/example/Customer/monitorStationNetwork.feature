# E3 – Monitor Station Network
@customer @stations
Feature: Monitor station network
  As a customer, I want to monitor the station network,
  so that I can find available chargers and check their current status before arriving.

  Background:
    Given a location "Karlsplatz charging" exists


  @US3.1
  Scenario: View operational status by location
    When I open location "Karlsplatz charging"
    Then I see a list of the chargers at location "Karlsplatz charging" with the following output:
    """
    1 - AC - FREE
    2 - AC - OCCUPIED
    3 - DC - OUT_OF_ORDER
    """
