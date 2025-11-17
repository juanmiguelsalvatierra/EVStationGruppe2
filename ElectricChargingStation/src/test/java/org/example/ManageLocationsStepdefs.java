package org.example;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ManageLocationsStepdefs {
    ApplicationContext ac = new ApplicationContext();
    String arg0 = "";
    String arg1 = "";

    @Given("I am logged in as an owner")
    public void iAmLoggedInAsAnOwner() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("I create a location {string} with address {string}")
    public void iCreateALocationWithAddress(String arg0, String arg1) {
        ac.locationService.createLocation(arg1, arg0);
        this.arg0 = arg0;
        this.arg1 = arg1;
    }

    @Then("the location appears in the list of all locations")
    public void theLocationAppearsInTheListOfAllLocations() {
        List<Location> locations = ac.locationService.getAllLocations();
        List<Location> temp = locations.stream().filter(item -> item.getDescription().equals(this.arg0) && item.getAddress().equals(this.arg1)).collect(Collectors.toList());
        assertEquals(1, temp.size());
    }

    @And("it initially has zero chargers")
    public void itInitiallyHasZeroChargers() {
        // Chargers still to be implemented
        //throw new PendingException();
    }

    @Given("a location {string} exists")
    public void aLocationExists(String arg0) {
        ac.locationService.createLocation("", arg0);
    }

    @When("I view locations")
    public void iViewLocations() {
        //no action
    }

    @Then("I see all existing locations in a list")
    public void iSeeAllExistingLocationsInAList() {
        assertFalse(ac.locationService.getAllLocations().isEmpty());
    }

    @When("I change its address to {string}")
    public void iChangeItsAddressTo(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("the updated address is now visible")
    public void theUpdatedAddressIsNowVisible() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("{string} exists with zero chargers")
    public void existsWithZeroChargers(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("I request to delete it")
    public void iRequestToDeleteIt() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("it no longer appears in the list")
    public void itNoLongerAppearsInTheList() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("{string} exists with two chargers")
    public void existsWithTwoChargers(String arg0) {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("I will not be able to delete the location")
    public void iWillNotBeAbleToDeleteTheLocation() {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
