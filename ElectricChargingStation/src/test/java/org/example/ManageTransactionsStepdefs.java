package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class ManageTransactionsStepdefs {
    CustomerManager customerManager = new CustomerManager();
    //region background
    @Given("customer {string} with the email {string} exists")
    public void customerWithTheEmailExists(String name, String email) {
        customerManager.createCustomer(name, email);
    }

    @And("the customer {string} has id {int}")
    public void theCustomerHasId(String name, int id) {
        Customer foundCustomer = customerManager.customerRepo.get(id);

        assertNotNull(foundCustomer);
        assertEquals(id, foundCustomer.getId());
        assertEquals(name, foundCustomer.getName());
    }
    //endregion
    @Given("customer with id {int} has {int} invoices")
    public void customerWithIdHasnTDonAnyTopUps(int id, int invoiceAmount) {
        Customer foundCustomer = customerManager.customerRepo.get(id);

        assertEquals(invoiceAmount, foundCustomer.invoiceItems.size());
    }

    @When("customer with id {int} reads his balance")
    public void customerWithIdReadsHisBalance(int id) {
        Customer foundCustomer = customerManager.customerRepo.get(id);

        foundCustomer.getBalance();
    }

    @Then("the customer with id {int} has a balance of {double} €")
    public void theCustomerWithIdHasABalanceOf€(int id, double expectedBalance) {
        Customer foundCustomer = customerManager.customerRepo.get(id);

        double actuelBalance = foundCustomer.getBalance();

        assertEquals(expectedBalance, actuelBalance);
    }

    @When("customer with id {int} tops up {double} €")
    public void customerWithIdTopsUpHisBalanceWith€(int id, double topUpValue) {
        assertTrue(topUpValue >= 0);
        Customer foundCustomer = customerManager.customerRepo.get(id);

        foundCustomer.topUp(topUpValue);
    }
    //region @US2.2 negativ top up does not worknegativ top up does not work
    @Given("customer with id {int} has a balance of {double} €")
    public void customerWithIdHasABalanceOf€(int id, double expectedBalance) {
        assertTrue(expectedBalance >= 0);
        Customer foundCustomer = customerManager.customerRepo.get(id);

        foundCustomer.topUp(expectedBalance);

        double actuelBalance = foundCustomer.getBalance();

        assertEquals(expectedBalance, actuelBalance);
    }

    @When("customer with id {int} does a top up with minus {double} €")
    public void customerWithIdReducesHisBalanceWith€(int id, double topUpValue) {
        assertTrue(topUpValue >= 0);
        Customer foundCustomer = customerManager.customerRepo.get(id);

        foundCustomer.topUp(topUpValue * -1);
    }

    //endregion
}
