package org.example;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class viewAllInvoicesStepdefs {
    CustomerManager customerManager = new CustomerManager();

    @Given("no customers exist")
    public void noCustomersExist() {
        assertTrue(customerManager.customerRepo.isEmpty());
    }

    @When("I view all invoices")
    public void iViewAllInvoices() {
        String actualOutput = customerManager.viewAllInvoices();
    }

    @Then("I see the following invoice overview:")
    public void iSeeTheFollowingInvoiceOverview(String expectedOutput) {
        String actualOutput = customerManager.viewAllInvoices();
        assertEquals(
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim()
        );
    }
}
