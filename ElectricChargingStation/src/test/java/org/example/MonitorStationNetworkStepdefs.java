package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonitorStationNetworkStepdefs {
    LocationManager lm = new LocationManager();

    @Given("ten locations with the following parameters exist:")
    public void tenLocationsWithTheFollowingParametersExist(DataTable locationsDataTable) {
        List<Map<String, String>> locations = locationsDataTable.asMaps(String.class, String.class);
        for (Map<String, String> locationMap : locations) {
            String name = locationMap.get("name");
            String address = locationMap.get("address");
            lm.createLocation(name, address);
        }
    }

    // @US3.1
    @Given("the following chargers exist at each location:")
    public void theFollowingChargersExistAtEachLocation(DataTable chargersDataTable) {
        List<Map<String, String>> chargers = chargersDataTable.asMaps(String.class, String.class);

        for (Location location : LocationManager.locationRepo.values()) {

            for (Map<String, String> row : chargers) {
                String type = row.get("type");
                String status = row.get("status");
                location.createCharger(type, status);
            }
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
