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

public class ManagePricesStepdefs {
    LocationManager lm = new LocationManager();
    @Given("the location {string} with address {string} exists")
    public void theLocationWithAddressExists(String name, String address) {
        lm.createLocation(name, address);
    }

    @And("the location {string} has id {int}")
    public void theLocationHasId(String name, int id) {
        Location l = lm.getLocationByName(name);
        assertNotNull(l, "Location not found: " + name);
        assertEquals(id, l.getId(), "Unexpected location ID for location: " + name);
    }

    @And("the location with id {int} has the following current prices:")
    public void theLocationWithIdHasTheFollowingCurrentPrices(int id, DataTable pricesDataTable) {
        Location l = lm.locationRepo.get(id);
        l.priceList.clear();
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
        lm.locationRepo.get(id).createPrices(pricePerKwhAC, pricePerKwhDC, parkingPriceAC, parkingPriceDC, LocalDateTime.now());
    }

    @When("I read all current prices of location with id {int}")
    public void iReadAllCurrentPricesOfLocationWithId(int id) {
        lm.locationRepo.get(id).getPrices();
    }

    @Then("I should see the following current prices for location with id {int}:")
    public void iShouldSeeTheFollowingCurrentPricesForLocationWithId(int id, String expectedPrices) {
        String actual = lm.locationRepo.get(id).getPrices();

        // Normalisierung: CRLF -> LF, NBSP -> normal space, trim
        java.util.function.UnaryOperator<String> norm = s -> {
            if (s == null) return "";
            return s.replace("\r\n", "\n")
                    .replace('\u00A0', ' ')
                    .trim();
        };

        assertEquals(norm.apply(expectedPrices), norm.apply(actual));
    }

    @Given("the current prices of location with id {int} are:")
    public void theCurrentPricesOfLocationWithIdAre(int id, DataTable pricesDataTable) {
        Location l = lm.locationRepo.get(id);
        l.priceList.clear();
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
        lm.locationRepo.get(id).createPrices(pricePerKwhAC, pricePerKwhDC, parkingPriceAC, parkingPriceDC, LocalDateTime.now());
    }

    @When("I create the new price at location with id {int}:")
    public void iCreateTheNewPriceAtLocationWithId(int id, DataTable pricesDataTable) {
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
        lm.locationRepo.get(id).createPrices(pricePerKwhAC, pricePerKwhDC, parkingPriceAC, parkingPriceDC, LocalDateTime.now());
    }
}
