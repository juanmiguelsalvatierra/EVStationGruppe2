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
    public void aLocationExistsWithTheAddress(String name1, String address1) {
        LocationManager.locationRepo.clear();
        lm.createLocation(name1, address1);
    }


    @And("a second location {string} exists with the address {string}")
    public void aSecondLocationExistsWithTheAddress(String name2, String address2) {

        lm.createLocation(name2, address2);
    }

    @Given("the following chargers exist at the location {string}:")
    public void theFollowingChargersExistAtTheLocation(String locationName1, DataTable dataTable) {
        Location location = LocationManager.getLocationByName(locationName1);

        for (var row : dataTable.asMaps()) {
            String type = row.get("type");
            String status = row.get("status");
            location.createCharger(type, status);
        }
    }

    @And("the following chargers exist at the second location {string}:")
    public void theFollowingChargersExistAtTheSecondLocation(String locationName2, DataTable dataTable) {
        Location location = LocationManager.getLocationByName(locationName2);

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
//        Location loc1 = LocationManager.getLocationByName(locationName1);
//        Location loc2 = LocationManager.getLocationByName(locationName2);
//        String actual = lm.viewLocationsInformation();
//        String expected = allLocationsWithChargers;
//
//        assertEquals(expected, actual);
        String actual = lm.viewLocationsInformation();
        assertEquals(expected.trim(), actual.trim());
    }
}
