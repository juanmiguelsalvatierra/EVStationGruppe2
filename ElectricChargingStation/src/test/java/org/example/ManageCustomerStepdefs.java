package org.example;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class ManageCustomerStepdefs {
    CustomerManager cm = new CustomerManager();
    //region US1.1 Create a new customer account
    @Given("no customer account exists")
    public void noCustomerAccountExists() {
        LocationManager.locationRepo.clear();
        assertTrue(cm.customerRepo.isEmpty());
    }

    @When("I create a customer account {string} with email {string}")
    public void iCreateACustomerAccountWithEmail(String name, String email) {
        cm.createCustomer(name, email);
    }

    @Then("I see at ID {int} the customer account with name {string} and email {string}")
    public void iSeeAtIDTheCustomerAccountWithNameAndEmail(int id, String name, String email) {
        assertEquals(cm.customerRepo.get(id).getName(), name);
        assertEquals(cm.customerRepo.get(id).getEmail(), email);
    }

    @And("the customer with the ID {int} initially has {int} invoice items")
    public void theCustomerWithTheIDInitiallyHasInvoiceItems(int id, int invoiceitemsAmount) {
        assertEquals(cm.customerRepo.get(id).invoiceItems.size(), invoiceitemsAmount);
    }
    //endregion
    //region US1.1 Create a list of new customer accounts
    @When("I create customer accounts with following parameters:")
    public void iCreateCustomerAccountsWithFollowingParameters(DataTable customerDataTable) {
        List<Map<String, String>> customers = customerDataTable.asMaps(String.class, String.class);
        for (Map<String, String> customerMap : customers) {
            String name = customerMap.get("name");
            String email = customerMap.get("email");
            cm.createCustomer(name, email);
        }
    }

    @Then("the number of customer accounts is {int}")
    public void theNumberOfCustomerAccountsIs(int customerAccountAmount) {
        assertEquals(cm.customerRepo.size(), customerAccountAmount);
    }

    @And("the customer with the ID {int} has name {string} and email {string}")
    public void theCustomerWithTheIDHasNameAndEmail(int id, String name, String email) {
        assertEquals(cm.customerRepo.get(id).getName(), name);
        assertEquals(cm.customerRepo.get(id).getEmail(), email);
    }

    @And("reading the customer accounts as lists shows following output:")
    public void readingTheCustomerAccountsAsListsShowsFollowingOutput(String currentCustomerListAsString) {
        String actualOutput = cm.getAllCustomersAsString();

        // Remove the last newline and trim both for comparison
        actualOutput = actualOutput.trim();
        String expected = currentCustomerListAsString.trim();

        assertEquals(expected, actualOutput);
    }
    //endregion
    //region US1.1 Prevent duplicate account creation
    @Given("a customer account {string} exists with email {string}")
    public void aCustomerAccountExistsWithEmail(String name, String email) {
        cm.createCustomer(name, email);
    }

    @When("I try to create a customer account {string} with email {string}")
    public void iTryToCreateACustomerAccountWithEmail(String name, String email) {
        cm.createCustomer(name, email);
    }

    @Then("the number of customer accounts remains {int}")
    public void theNumberOfCustomerAccountsRemains(int customerAccountsAmount) {
        assertEquals(cm.customerRepo.size(), customerAccountsAmount); //maybe unify with "the number of customer accounts is.."
    }

    //endregion
    //region US1.4 Read all existing customer accounts
    @Given("the following customer accounts exist:")
    public void theFollowingCustomerAccountsExist(DataTable customerDataTable) {
        List<Map<String, String>> customers = customerDataTable.asMaps(String.class, String.class);
        for (Map<String, String> customerMap : customers) {
            String name = customerMap.get("name");
            String email = customerMap.get("email");
            cm.createCustomer(name, email);
        }
    }

    @When("I view all customer accounts")
    public void iViewAllCustomerAccounts() {
        String actualOutput = cm.getAllCustomersAsString(); //is it necessary to fill this WHEN?
    }

    @Then("I should see the following customer accounts:")
    public void iShouldSeeTheFollowingCustomerAccounts(String currentCustomerListAsString) {
        String actualOutput = cm.getAllCustomersAsString();

        // Remove the last newline and trim both for comparison
        actualOutput = actualOutput.trim();
        String expected = currentCustomerListAsString.trim();

        assertEquals(expected, actualOutput);
    }

    @When("I update the customer account with the ID {int} to name {string}")
    public void iUpdateTheCustomerAccountWithTheIDToName(int id, String newname) {
        cm.updateCustomerName(id, newname);
    }

    @Then("the customer account with the ID {int} should have name {string} and email {string}")
    public void theCustomerAccountWithTheIDShouldHaveNameAndEmail(int id, String name, String email) {
        assertEquals(cm.customerRepo.get(id).getName(), name);
        assertEquals(cm.customerRepo.get(id).getEmail(), email);
    }

    @And("the customer account with the ID {int} remains unchanged with the name {string} and email {string}")
    public void theCustomerAccountWithTheIDRemainsUnchangedWithTheNameAndEmail(int id, String name, String email) {
        assertEquals(cm.customerRepo.get(id).getName(), name);
        assertEquals(cm.customerRepo.get(id).getEmail(), email);
    }

    @When("I update the customer account with the ID {int} to email {string}")
    public void iUpdateTheCustomerAccountWithTheIDToEmail(int id, String newemail) {
        cm.updateCustomerEmail(id, newemail);
    }

    @When("I try to delete the customer account with the ID {int}")
    public void iTryToDeleteTheCustomerAccountWithTheID(int id) {
        cm.deleteCustomer(id);
    }

    @And("the customer account with the ID {int} no longer exists")
    public void theCustomerAccountWithTheIDNoLongerExists(int id) {
        assertNull(cm.customerRepo.get(id));
    }
    //endregion
}
