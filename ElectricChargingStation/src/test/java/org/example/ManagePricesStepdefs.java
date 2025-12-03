package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ManagePricesStepdefs {
    @Given("the location {string} with address {string} exists")
    public void theLocationWithAddressExists(String name, String address) {
    }

    @And("the location {string} has id {int}")
    public void theLocationHasId(String arg0, int arg1) {
    }

    @When("I read all current prices of location with id {int}")
    public void iReadAllCurrentPricesOfLocationWithId(int arg0) {
    }

    @Then("I should see the following current prices for location with id {int}")
    public void iShouldSeeTheFollowingCurrentPricesForLocationWithId(int arg0) {
    }

    @Given("the current prices of location with id {int} are:")
    public void theCurrentPricesOfLocationWithIdAre(int arg0) {
    }

    @When("I create the new price at location with id {int}:")
    public void iCreateTheNewPriceAtLocationWithId(int arg0) {
    }
}
