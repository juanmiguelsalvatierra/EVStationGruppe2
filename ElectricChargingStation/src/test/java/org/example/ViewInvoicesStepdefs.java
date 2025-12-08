package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ViewInvoicesStepdefs {
    CustomerManager customerManager = new CustomerManager();
    LocationManager locationManager = new LocationManager();

    public ViewInvoicesStepdefs (){
        LocationManager.locationRepo.clear();
    }

    @Given("the customer {string} with email {string} exists")
    public void theCustomerWithEmailExists(String name, String email) {
        customerManager.createCustomer(name, email);
    }

    @And("the customer {string} has ID {int}")
    public void theCustomerHasID(String name, int id) {
        Customer foundCustomer = customerManager.customerRepo.get(id);

        assertNotNull(foundCustomer);
        assertEquals(id, foundCustomer.getId());
        assertEquals(name, foundCustomer.getName());
    }

    @And("the invoice item count for the customer with the ID {int} is {int}")
    public void theInvoiceItemCountForTheCustomerWithTheIDIs(int id, int invoiceitemcount) {
        assertEquals(customerManager.customerRepo.get(id).invoiceItems.size(), invoiceitemcount);
    }

    @And("the balance of the customer with the ID {int} is {int}")
    public void theBalanceOfTheCustomerWithTheIDIs(int id, int balance) {
        assertEquals(customerManager.customerRepo.get(id).getBalance(), balance);
    }

    @When("I view the invoice items of the customer with the ID {int}")
    public void iViewTheInvoiceItemsOfTheCustomerWithTheID(int id) {
        String actualOutput = customerManager.customerRepo.get(id).getAllInvoiceItemsAsString();
    }

    @And("the invoice items for the customer with the ID {int} show the following:")
    public void theInvoiceItemsForTheCustomerWithTheIDShowTheFollowing(int id, String expectedOutput) {
        String actualOutput = customerManager.customerRepo.get(id).getAllInvoiceItemsAsString();
        assertEquals(
                expectedOutput.replace("\r\n", "\n").trim(),
                actualOutput.replace("\r\n", "\n").trim()
        );
    }

    @Given("the customer with the ID {int} top-ups the amount {int}")
    public void theCustomerWithTheIDTopUpsTheAmount(int id, int topup) {
        Customer foundCustomer = customerManager.customerRepo.get(id);
        foundCustomer.topUp(topup);
    }

    @And("a location {string} with address {string} exists")
    public void aLocationWithAddressExists(String name, String address) {
        locationManager.createLocation(name, address);
    }

    @And("the location {string} has ID {int}")
    public void theLocationHasID(String name, int id) {
        Location foundLocation = locationManager.locationRepo.get(id);

        assertNotNull(foundLocation);
        assertEquals(id, foundLocation.getId());
        assertEquals(name, foundLocation.getName());
    }

    @And("the location with ID {int} has the following current prices:")
    public void theLocationWithIDHasTheFollowingCurrentPrices(int locationId, DataTable pricesDataTable) {
        Location location = LocationManager.locationRepo.get(locationId);
        location.priceList.clear();

        List<Map<String, String>> prices = pricesDataTable.asMaps();
        double parkingPriceDC = 0;
        double parkingPriceAC = 0;
        double pricePerKwhDC = 0;
        double pricePerKwhAC = 0;
        for (Map<String, String> priceRow : prices) {
            // Parse the values from the table
            pricePerKwhAC = Double.parseDouble(priceRow.get("price_per_kWh_AC"));
            pricePerKwhDC = Double.parseDouble(priceRow.get("price_per_kWh_DC"));
            parkingPriceAC = Double.parseDouble(priceRow.get("parking_price_AC"));
            parkingPriceDC = Double.parseDouble(priceRow.get("parking_price_DC"));
        }

        location.createPrices(pricePerKwhAC, pricePerKwhDC, parkingPriceAC, parkingPriceDC, LocalDateTime.now());
    }

    @And("the location with the ID {int} has a charger of type {string} with status {string}")
    public void theLocationWithTheIDHasAChargerOfTypeWithStatus(int id, String type, String status) {
        LocationManager.locationRepo.get(id).createCharger(type, status);
    }

    @And("the customer with the ID {int} performs a charging session of {int} minutes using {string} mode at charger ID {int} of the location with the ID {int}")
    public void theCustomerWithTheIDPerformsAChargingSessionOfMinutesUsingModeAtChargerIDOfTheLocationWithTheID(int customerId, int duration, String type, int chargerId, int locationId) {
        Customer foundCustomer = customerManager.customerRepo.get(customerId);
        Location location = locationManager.locationRepo.get(locationId);

        foundCustomer.chargeEv(location.getId(), chargerId, duration, type);
    }

    //---------------------------view all invoices----------------------------

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
