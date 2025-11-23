package org.example;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class ManageChargersStepdefs {
    //region @US7.1 Create a new charger at a location
    @Given("a location {string} exists with address {string} to create a charger")
    public void aLocationExistsWithAddressToCreateACharger(String name, String address) {
        // This step already exists in your location stepdefinitions.
        // You can either reuse it there or implement the logic here if you prefer.
        throw new PendingException();
    }

    @And("the location {string} has no chargers")
    public void theLocationHasNoChargers(String locationName) {
        throw new PendingException();
    }

    // names of parameters have to be changed manually, they can not be set in the gherkin syntax
    @When("I create a charger at location {string} with type {string} and status {string}")
    public void iCreateAChargerAtLocationWithTypeAndStatus(String locationName, String type, String status) {
        throw new PendingException();
    }

    @Then("I see a charger at location {string} with type {string} and status {string}")
    public void iSeeAChargerAtLocationWithTypeAndStatus(String locationName, String type, String status) {
        throw new PendingException();
    }

    @And("the location {string} has {int} charger")
    public void theLocationHasChargers(String locationName, int chargerAmount) {
        throw new PendingException();
    }
    //endregion
    //region @US7.1 Create a list of chargers for a location
    // Datatable has to be created manually
    @When("I create chargers at location {string} with following parameters:")
    public void iCreateChargersAtLocationWithFollowingParameters(String locationName, DataTable chargersDataTable) {
        List<Map<String, String>> chargers = chargersDataTable.asMaps(String.class, String.class);
        String typeOfFirstCharger = chargers.get(0).get("type");
        String statusOfFirstCharger = chargers.get(0).get("status");
        throw new PendingException();
    }

    @Then("the number of chargers at location {string} is {int}")
    public void theNumberOfChargersAtLocationIs(String locationName, int chargerAmount) {
        throw new PendingException();
    }

    @And("reading all the chargers at location {string} as lists shows following output:")
    public void readingAllTheChargersAtLocationAsListsShowsFollowingOutput(String locationName, String currentChargerListAsString) {
        throw new PendingException();
    }
    //endregion
    //region @US7.1 Creating a charger with identical properties (duplicate charger)
    @And("the following chargers exist at location {string}:")
    public void theFollowingChargersExistAtLocation(String locationName, DataTable chargersDataTable) {
        // similar to locations: you can parse the table here
        List<Map<String, String>> chargers = chargersDataTable.asMaps(String.class, String.class);
        throw new PendingException();
    }

    @When("I try to create a charger at location {string} with type {string} and status {string}")
    public void iTryToCreateAChargerAtLocationWithTypeAndStatus(String locationName, String type, String status) {
        throw new PendingException();
    }

    @Then("I should see an error message saying {string}")
    public void iShouldSeeAnErrorMessageSaying(String exceptionMessage) {
        throw new PendingException();

    }

    @And("reading the chargers at location {string} as lists shows following output:")
    public void readingTheChargersAtLocationAsListsShowsFollowingOutputDuplicate(String locationName, String currentChargerListAsString) {
        throw new PendingException();
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
