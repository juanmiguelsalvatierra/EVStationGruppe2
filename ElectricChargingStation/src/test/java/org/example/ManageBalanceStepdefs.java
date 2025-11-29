package org.example;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ManageBalanceStepdefs {
    //region background
    @Given("customer {string} with the email {string} exists")
    public void customerWithTheEmailExists(String name, String email) {
        throw new PendingException();
    }

    @And("the customer {string} hast id {int}")
    public void theCustomerHastId(String name, int id) {
        throw new PendingException();
    }

    @Given("customer with id {int} has {int} invoices")
    public void customerWithIdHasnTDonAnyTopUps(int id) {
        throw new PendingException();
    }

    @When("customer with id {int} reads his balance")
    public void customerWithIdReadsHisBalance(int id) {
        throw new PendingException();
    }

    @Then("the customer with id {int} has a balance of {int} €")
    public void theCustomerWithIdHasABalanceOf€(int id, int balance) {
        throw new PendingException();
    }

    @When("customer with id {int} tops up his balance with {int} €")
    public void customerWithIdTopsUpHisBalanceWith€(int id, int balance) {
        throw new PendingException();
    }

    //region @US2.2 negativ top up does not worknegativ top up does not work
    @Given("customer with id {int} has a balance of {int} €")
    public void customerWithIdHasABalanceOf€(int id, int balance) {
        throw new PendingException();
    }

}
