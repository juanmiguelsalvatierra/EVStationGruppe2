package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.DataTable;

public class ManageCustomerStepdefs {
    //region US1.1 Create a new customer account
    @Given("no customer account exists")
    public void noCustomerAccountExists() {
    }

    @When("I create a customer account {string} with email {string}")
    public void iCreateACustomerAccountWithEmail(String name, String email) {
    }

    @Then("I see a customer account with name {string} and email {string}")
    public void iSeeACustomerAccountWithNameAndEmail(String name, String email) {
    }

    @And("it initially has {int} invoice items")
    public void itInitiallyHasInvoiceItems(int invoiceItemAmount) {
    }
    //endregion
    //region US1.1 Create a list of new customer accounts
    @When("I create customer accounts with following parameters:")
    public void iCreateCustomerAccountsWithFollowingParameters(DataTable customerDataTable) {
    }

    @Then("the number of customer accounts is {int}")
    public void theNumberOfCustomerAccountsIs(int customerAccountAmount) {
    }

    @And("customer {string} has email {string}")
    public void customerHasEmail(String name, String email) {
    }

    @And("reading the customer accounts as lists shows following output:")
    public void readingTheCustomerAccountsAsListsShowsFollowingOutput(String currentCustomerListAsString) {
    }
    //endregion
    //region US1.1 Prevent duplicate account creation
    @Given("a customer account {string} exists with email {string}")
    public void aCustomerAccountExistsWithEmail(String name, String email) {
    }

    @When("I try to create a customer account {string} with email {string}")
    public void iTryToCreateACustomerAccountWithEmail(String name, String email) {
    }

    @Then("I should see an error saying {string}")
    public void iShouldSeeAnErrorSaying(String exceptionMessage) {
    }

    @And("reading the customer account as lists shows following output:")
    public void readingTheCustomerAccountAsListsShowsFollowingOutput(String currentCustomerListAsString) {
    }
    //endregion
    //region US1.4 Read all existing customer accounts
    @Given("the following customer accounts exist:")
    public void theFollowingCustomerAccountsExist(DataTable customerDataTable) {
    }

    @When("I view all customer accounts")
    public void iViewAllCustomerAccounts() {
    }

    @Then("I should see the following customer accounts:")
    public void iShouldSeeTheFollowingCustomerAccounts(String currentCustomerListAsString) {
    }
    //endregion
}
