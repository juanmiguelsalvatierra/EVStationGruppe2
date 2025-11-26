package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
public class ManageLocationStepdefs {
    //region @US6.1 Create a new location
    @Given("no location exists")
    public void noLocationExists() {
        throw new PendingException();
    }

    //names of parameters have to be changed manually, they can not be set in the gherkin syntax
    @When("I create a location {string} with address {string}")
    public void iCreateALocationWithAddress(String name, String address) {
        throw new PendingException();
    }

    @Then("I see a location with name {string} with address {string}")
    public void iSeeALocationWithNameWithAddress(String name, String address) {
        throw new PendingException();
    }

    @And("it initially has {int} chargers")
    public void itInitiallyHasChargers(int chargerAmount) {
        throw new PendingException();
    }
    //endregion
    //region @US6.1 Create a list of new locations
    //Datatable has to be created manually
    @When("I create locations with following parameters:")
    public void iCreateLocationsWithFollowingParameters(DataTable locationsDataTable) {
        List<Map<String, String>> locations = locationsDataTable.asMaps(String.class, String.class);
        String addresOfLocationOne = locations.get(1).get("address");
        throw new PendingException();
    }

    @Then("the number of locations is {int}")
    public void theNumberOfLocationsIs(int locationAmount) {
        throw new PendingException();
    }

    @And("location {string} has the address {string}")
    public void locationHasTheAddress(String name, String address) {
        throw new PendingException();
    }

    //the paramter of this method had also be created by hand
    @And("reading the locations als lists shows following output:")
    public void readingTheLocationsAlsListsShowsFollowingOutput(String currentLocationListAsString) {
        throw new PendingException();
    }
    //endregion
    //region @US6.1 Creating a location with a duplicate name
    @Given("a location {string} exists with address {string}")
    public void aLocationExistsWithAddress(String name, String address) {
        throw new PendingException();
    }

    @When("I try to create a location {string} with address {string}")
    public void iTryToCreateALocationWithAddress(String name, String address) {
        throw new PendingException();
    }

    @Then("I should get an error saying {string}")
    public void iShouldGetAnErrorSaying(String exceptionMessage) {
        throw new PendingException();
    }

    @And("reading the locations as lists shows following output:")
    public void readingTheLocationsAsListsShowsFollowingOutput(String currentLocationListAsString) {
        throw new PendingException();
    }
    //endregion
    //region @US6.2 Read all existing locations
    @Given("the following locations exist:")
    public void theFollowingLocationsExist(DataTable locationsDataTable) {
        throw new PendingException();
    }

    @When("I view all locations")
    public void iViewAllLocations() {
        throw new PendingException();
    }

    @Then("I should see the following locations:")
    public void iShouldSeeTheFollowingLocations(String currentLocationListAsString) {
        throw new PendingException();
    }
    //endregion
}
