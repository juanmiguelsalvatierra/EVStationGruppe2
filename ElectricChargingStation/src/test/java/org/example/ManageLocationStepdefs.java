package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
public class ManageLocationStepdefs {
    LocationService ls = new LocationService();

    //region @US6.1 Create a new location
    @Given("no location exists")
    public void noLocationExists() {
        assertTrue(ls.locationRepo.isEmpty());
    }

    //names of parameters have to be changed manually, they can not be set in the gherkin syntax
    @When("I create the location {string} with address {string}")
    public void iCreateALocationWithAddress(String name, String address) {
        ls.createLocation(name, address);
    }

    @Then("I see at ID {int} the location with name {string} with address {string}")
    public void iSeeALocationWithNameWithAddress(int Id, String name, String address) {
        assertEquals(ls.locationRepo.get(Id).getName(), name);
        assertEquals(ls.locationRepo.get(Id).getAddress(), address);
    }

    @And("the location with ID {int} initially has {int} chargers")
    public void itInitiallyHasChargers(int id, int chargerAmount) {
        assertEquals(ls.locationRepo.get(id).chargers.size(), chargerAmount);
    }
    //endregion
    //region @US6.1 Create a list of new locations
    //Datatable has to be created manually
    @When("I create locations with following parameters:")
    public void iCreateLocationsWithFollowingParameters(DataTable locationsDataTable) {
        List<Map<String, String>> locations = locationsDataTable.asMaps(String.class, String.class);
        for (Map<String, String> locationMap : locations) {
                String name = locationMap.get("name");
                String address = locationMap.get("address");
                ls.createLocation(name, address);
        }
    }

    @Then("the number of locations is {int}")
    public void theNumberOfLocationsIs(int locationAmount) {
        assertEquals(ls.locationRepo.size(), locationAmount);
    }

    @And("location with ID {int} has the address {string}")
    public void locationHasTheAddress(int id, String address) {
        assertEquals(ls.locationRepo.get(id).getAddress(), address);
    }

    //endregion
    //region @US6.1 Creating a location with a duplicate name
    @Given("a location {string} exists with address {string}")
    public void aLocationExistsWithAddress(String name, String address) {
        ls.createLocation(name, address);
    }

    @When("I try to create a location {string} with address {string}")
    public void iTryToCreateALocationWithAddress(String name, String address) {
        ls.createLocation(name, address);
    }

    @Then("I should get an error saying {string}")
    public void iShouldGetAnErrorSaying(String exceptionMessage) {
        assertEquals(exceptionMessage, ls.dupesStatus);
    }

    @And("reading the locations as lists shows following output:")
    public void readingTheLocationsAsListsShowsFollowingOutput(String currentLocationListAsString) {
        StringBuilder actualOutput = new StringBuilder();

        for (Location location : ls.locationRepo.values()) {
            actualOutput.append(location.toString()).append("\n");
        }

        // Remove the last newline and trim both for comparison
        String actual = actualOutput.toString().trim();
        String expected = currentLocationListAsString.trim();

        assertEquals(expected, actual);
    }
    //endregion
    //region @US6.2 Read all existing locations
    @Given("the following locations exist:")
    public void theFollowingLocationsExist(DataTable locationsDataTable) {
        List<Map<String, String>> locations = locationsDataTable.asMaps(String.class, String.class);
        for (Map<String, String> locationMap : locations) {
            String name = locationMap.get("name");
            String address = locationMap.get("address");
            ls.createLocation(name, address);
        }
    }

    @When("I view all locations")
    public void iViewAllLocations() {
        StringBuilder actualOutput = new StringBuilder();

        for (Location location : ls.locationRepo.values()) {
            actualOutput.append(location.toString()).append("\n");
        }

        // Remove the last newline and trim both for comparison
        actualOutput.toString().trim();
    }

    @Then("I should see the following locations:")
    public void iShouldSeeTheFollowingLocations(String currentLocationListAsString) {
        StringBuilder actualOutput = new StringBuilder();

        for (Location location : ls.locationRepo.values()) {
            actualOutput.append(location.toString()).append("\n");
        }

        // Remove the last newline and trim both for comparison
        String actual = actualOutput.toString().trim();
        String expected = currentLocationListAsString.trim();

        assertEquals(expected, actual);
    }
    //endregion
}
