package org.example;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.DataTable;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ManageCustomerStepdefs {
    CustomerManager cm = new CustomerManager();
    //region US1.1 Create a new customer account
    @Given("no customer account exists")
    public void noCustomerAccountExists() {
        assertTrue(cm.customerRepo.isEmpty());
    }

    @When("I create a customer account {string} with email {string}")
    public void iCreateACustomerAccountWithEmail(String name, String email) {
        cm.createCustomer(name, email);
    }

    @Then("I see a customer account with name {string} and email {string}")
    public void iSeeACustomerAccountWithNameAndEmail(String name, String email) {
        throw new PendingException();
    }

    @And("it initially has {int} invoice items")
    public void itInitiallyHasInvoiceItems(int invoiceItemAmount) {
        throw new PendingException();
    }
    //endregion
    //region US1.1 Create a list of new customer accounts
    @When("I create customer accounts with following parameters:")
    public void iCreateCustomerAccountsWithFollowingParameters(DataTable customerDataTable) {
        throw new PendingException();
    }

    @Then("the number of customer accounts is {int}")
    public void theNumberOfCustomerAccountsIs(int customerAccountAmount) {
        throw new PendingException();
    }

    @And("customer {string} has email {string}")
    public void customerHasEmail(String name, String email) {
        throw new PendingException();
    }

    @And("reading the customer accounts as lists shows following output:")
    public void readingTheCustomerAccountsAsListsShowsFollowingOutput(String currentCustomerListAsString) {
        throw new PendingException();
    }
    //endregion
    //region US1.1 Prevent duplicate account creation
    @Given("a customer account {string} exists with email {string}")
    public void aCustomerAccountExistsWithEmail(String name, String email) {
        throw new PendingException();
    }

    @When("I try to create a customer account {string} with email {string}")
    public void iTryToCreateACustomerAccountWithEmail(String name, String email) {
        throw new PendingException();
    }

    @Then("I should see an error saying {string}")
    public void iShouldSeeAnErrorSaying(String exceptionMessage) {
        throw new PendingException();
    }

    @And("reading the customer account as lists shows following output:")
    public void readingTheCustomerAccountAsListsShowsFollowingOutput(String currentCustomerListAsString) {
        throw new PendingException();
    }
    //endregion
    //region US1.4 Read all existing customer accounts
    @Given("the following customer accounts exist:")
    public void theFollowingCustomerAccountsExist(DataTable customerDataTable) {
        throw new PendingException();
    }

    @When("I view all customer accounts")
    public void iViewAllCustomerAccounts() {
        throw new PendingException();
    }

    @Then("I should see the following customer accounts:")
    public void iShouldSeeTheFollowingCustomerAccounts(String currentCustomerListAsString) {
        throw new PendingException();
    }
    //endregion
}
