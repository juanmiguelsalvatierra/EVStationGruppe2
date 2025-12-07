package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonitorStationNetworkStepdefs {
    LocationManager lm = new LocationManager();


    @Given("A location {string} exists with the address {string}")
    public void aLocationExistsWithTheAddress(String name, String address) {

        lm.createLocation(name, address);

    }


    @Given("the following chargers exist at the location {string}:")
    public void theFollowingChargersExistAtTheLocation(String locationName, DataTable dataTable) {
        Location location = LocationManager.getLocationByName(locationName);

        for (var row : dataTable.asMaps()) {
            String type = row.get("type");
            String status = row.get("status");
            location.createCharger(type, status);
        }



    }

    @When("I attempt to access the locations information")
    public void iAttemptToAccessTheLocationsInformation() {
        lm.viewLocationsInformation();
    }

    @Then("I see a list of every location and their chargers:")
    public void iSeeAListOfEveryLocationAndTheirChargers(String expected) {

        String actual = lm.viewLocationsInformation();
        assertEquals(expected.trim(), actual.trim());
    }
}
