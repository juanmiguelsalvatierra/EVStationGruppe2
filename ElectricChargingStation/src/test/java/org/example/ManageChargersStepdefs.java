package org.example;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class ManageChargersStepdefs {
    LocationService ls = new LocationService();

    //region @US7.1 Create a new charger at a location
    @Given("a location {string} exists with address {string} for chargers")
    public void aLocationExistsWithAddressToCreateACharger(String name, String address) {
        ls.createLocation(name, address);
    }

    @And("the location {string} has {int} chargers")
    public void theLocationHasNoChargers(String locationName, int chargerAmount) {
        Location loc = ls.getLocationByName(locationName);
        assertEquals(chargerAmount, loc.chargersRepo.size());
    }

    @When("I create a charger at location {string} with type {string} and status {string}")
    public void iCreateAChargerAtLocationWithTypeAndStatus(String locationName, String type, String status) {
        Location loc = ls.getLocationByName(locationName);

        loc.createCharger(type, status);
    }

    @Then("I see a charger at location {string} with type {string} and status {string}")
    public void iSeeAChargerAtLocationWithTypeAndStatus(String locationName, String type, String status) {
        Location loc = ls.getLocationByName(locationName);

        Charger foundCharger = loc.chargersRepo.values().stream()
                .findFirst()
                .orElse(null);

        assertEquals(locationName, loc.getName());
        assertEquals(type, foundCharger.getChargerType().toString());
        assertEquals(status, foundCharger.getStatus().toString());

    }

    @And("the location {string} has {int} charger")
    public void theLocationHasChargers(String locationName, int chargerAmount) {
        Location loc = ls.getLocationByName(locationName);
        assertEquals(chargerAmount, loc.chargersRepo.size());
     }
    //endregion
    //region @US7.1 Create a list of chargers for a location
    // Datatable has to be created manually
    @When("I create chargers at location {string} with following parameters:")
    public void iCreateChargersAtLocationWithFollowingParameters(String locationName, DataTable chargersDataTable) {
        List<Map<String, String>> chargers = chargersDataTable.asMaps(String.class, String.class);
        Location loc = ls.getLocationByName(locationName);

        for (Map<String, String> charger : chargers) {
            loc.createCharger(charger.get("type"), charger.get("status"));
        }
    }

    @Then("the number of chargers at location {string} is {int}")
    public void theNumberOfChargersAtLocationIs(String locationName, int chargerAmount) {
        Location loc = ls.getLocationByName(locationName);

        assertEquals(chargerAmount, loc.chargersRepo.size());
    }

    @And("reading all the chargers at location {string} as lists shows following output:")
    public void readingAllTheChargersAtLocationAsListsShowsFollowingOutput(String locationName, String currentChargerListAsString) {
        Location loc = ls.getLocationByName(locationName);
        String actualOutput = loc.getAllChargersAsString();

        String actual = actualOutput.trim();
        String expected = currentChargerListAsString.trim();

        assertEquals(expected, actual);
    }
    //endregion
    //region @US7.1 Creating a charger with identical properties (duplicate charger)
    @And("the following chargers exist at location {string}:")
    public void theFollowingChargersExistAtLocation(String locationName, DataTable chargersDataTable) {
        List<Map<String, String>> chargers = chargersDataTable.asMaps(String.class, String.class);
        Location loc = ls.getLocationByName(locationName);

        for (Map<String, String> charger : chargers) {
            loc.createCharger(charger.get("type"), charger.get("status"));
        }
    }

    @When("I try to create a charger at location {string} with type {string} and status {string}")
    public void iTryToCreateAChargerAtLocationWithTypeAndStatus(String locationName, String type, String status) {
        Location loc = ls.getLocationByName(locationName);

        loc.createCharger(type, status);
    }

    @Then("the number of chargers at location {string} updates to {int}")
    public void theNumberOfChargersAtLocationUpdatesTo(String locationName, int chargerAmount) {
        Location loc = ls.getLocationByName(locationName);
        assertEquals(chargerAmount, loc.chargersRepo.size());
    }

    @And("reading the chargers at location {string} as lists shows following output:")
    public void readingTheChargersAtLocationAsListsShowsFollowingOutputDuplicate(String locationName, String currentChargerListAsString) {
        Location loc = ls.getLocationByName(locationName);
        String actualOutput = loc.getAllChargersAsString();

        String actual = actualOutput.trim();
        String expected = currentChargerListAsString.trim();

        assertEquals(expected, actual);
    }
    //endregion
    //region @US7.2 Read all chargers for a location
    @When("I view all chargers at location {string}")
    public void iViewAllChargersAtLocation(String locationName) {
        throw new PendingException();
    }

    @Then("I should see the following chargers:")
    public void iShouldSeeTheFollowingChargers(String currentChargerListAsString) {
        throw new PendingException();
    }
    //endregion
}
