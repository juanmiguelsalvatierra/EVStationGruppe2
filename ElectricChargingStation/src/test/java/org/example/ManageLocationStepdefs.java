package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ManageLocationStepdefs {
    LocationManager lm = new LocationManager();
    String thrownExceptionMessage;

    public ManageLocationStepdefs(){
        LocationManager.locationRepo.clear();
    }

    //region @US6.1 Create a new location
    @Given("no location exists")
    public void noLocationExists() {
        assertTrue(lm.locationRepo.isEmpty());
    }

    //names of parameters have to be changed manually, they can not be set in the gherkin syntax
    @When("I create the location {string} with address {string}")
    public void iCreateALocationWithAddress(String name, String address) {
        lm.createLocation(name, address);
    }

    @Then("I see at ID {int} the location with name {string} with address {string}")
    public void iSeeALocationWithNameWithAddress(int Id, String name, String address) {
        assertEquals(lm.locationRepo.get(Id).getName(), name);
        assertEquals(lm.locationRepo.get(Id).getAddress(), address);
    }

    @And("the location with ID {int} initially has {int} chargers")
    public void itInitiallyHasChargers(int id, int chargerAmount) {
        assertEquals(lm.locationRepo.get(id).chargersRepo.size(), chargerAmount);
    }
    //endregion
    //region @US6.2 Create a list of new locations
    //Datatable has to be created manually
    @When("I create locations with following parameters:")
    public void iCreateLocationsWithFollowingParameters(DataTable locationsDataTable) {
        List<Map<String, String>> locations = locationsDataTable.asMaps(String.class, String.class);
        for (Map<String, String> locationMap : locations) {
                String name = locationMap.get("name");
                String address = locationMap.get("address");
                lm.createLocation(name, address);
        }
    }

    @Then("the number of locations is {int}")
    public void theNumberOfLocationsIs(int locationAmount) {
        assertEquals(lm.locationRepo.size(), locationAmount);
    }

    @And("location with ID {int} has the address {string}")
    public void locationHasTheAddress(int id, String address) {
        assertEquals(lm.locationRepo.get(id).getAddress(), address);
    }
    //endregion
    //region @US6.3 Creating a location with a duplicate name
    @Given("a location {string} exists with address {string}")
    public void aLocationExistsWithAddress(String name, String address) {
        lm.createLocation(name, address);
    }

    @When("I try to create a location {string} with address {string}")
    public void iTryToCreateALocationWithAddress(String name, String address) {
        try{
            lm.createLocation(name, address);
        }catch (Exception e){
            thrownExceptionMessage = e.getMessage();
        }
    }

    @And("reading the locations as lists shows following output:")
    public void readingTheLocationsAsListsShowsFollowingOutput(String currentLocationListAsString) {
        String actualOutput = lm.getAllLocationsAsString();

        // Remove the last newline and trim both for comparison
        String actual = actualOutput.trim();
        String expected = currentLocationListAsString.trim();

        assertEquals(expected, actual);
    }
    //endregion
    //region @US6.4 Read all existing locations
    @Given("the following locations exist:")
    public void theFollowingLocationsExist(DataTable locationsDataTable) {
        List<Map<String, String>> locations = locationsDataTable.asMaps(String.class, String.class);
        for (Map<String, String> locationMap : locations) {
            String name = locationMap.get("name");
            String address = locationMap.get("address");
            lm.createLocation(name, address);
        }
    }

    @When("I view all locations")
    public void iViewAllLocations() {
        String actualOutput = lm.getAllLocationsAsString();

        // Remove the last newline and trim both for comparison
        actualOutput.trim();
    }

    @Then("I should see the following locations:")
    public void iShouldSeeTheFollowingLocations(String currentLocationListAsString) {
        String actualOutput = lm.getAllLocationsAsString();

        // Remove the last newline and trim both for comparison
        String actual = actualOutput.trim();
        String expected = currentLocationListAsString.trim();

        assertEquals(expected, actual);
    }
    //endregion
    //region @US6.3 Update the name of an existing location
    @When("I update location with ID {int} to name {string}")
    public void iUpdateLocationWithIDWithNameAndAddress(int id, String name) {
        lm.updateLocationName(id, name);
    }

    @Then("the location with ID {int} should have name {string} and address {string}")
    public void theLocationWithIDShouldHaveNameAndAddress(int id, String name, String address) {
        assertEquals(lm.locationRepo.get(id).getName(), name);
        assertEquals(lm.locationRepo.get(id).getAddress(), address);
    }

    @And("the location with ID {int} remains unchanged with name {string} and address {string}")
    public void theLocationWithIDRemainsUnchangedWithNameAndAddress(int id, String name, String address) {
        assertEquals(lm.locationRepo.get(id).getName(), name);
        assertEquals(lm.locationRepo.get(id).getAddress(), address);
    }
    //endregion
    //region @US6.6 Update the address of an existing location
    @When("I update location with ID {int} to address {string}")
    public void iUpdateLocationWithIDToAddress(int id, String address) {
        lm.updateLocationAddress(id, address);
    }
    //endregion
    //region @US6.7 Delete an existing location @US6.8 Delete a non-existent location
    @And("location with ID {int} no longer exists")
    public void locationWithIDNoLongerExists(int id) {
        assertNull(lm.locationRepo.get(id));
    }

    @When("I try to delete location with ID {int}")
    public void iTryToDeleteLocationWithID(int id) {
        try{
            lm.deleteLocation(id);
        }catch (Exception e){
            thrownExceptionMessage = e.getMessage();
        }
    }

    @And("the number of locations remains {int}")
    public void theNumberOfLocationsRemains(int count) {
        assertEquals(lm.locationRepo.size(), count);
    }

    @And("the Exception Message will be displayed {string}")
    public void theExceptionMessageWillBeDisplayed(String exceptionMessage) {
        assertEquals(exceptionMessage, thrownExceptionMessage);
    }
    //endregion
}
