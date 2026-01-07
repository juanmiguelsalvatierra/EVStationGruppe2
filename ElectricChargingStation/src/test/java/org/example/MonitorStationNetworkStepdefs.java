package org.example;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonitorStationNetworkStepdefs {
    LocationManager lm = new LocationManager();

    public MonitorStationNetworkStepdefs(){
        lm = new LocationManager();
        LocationManager.locationRepo.clear();
    }

    @Given("ten locations with the following parameters exist:")
    public void tenLocationsWithTheFollowingParametersExist(DataTable locationsDataTable) {
        List<Map<String, String>> locations = locationsDataTable.asMaps(String.class, String.class);
        for (Map<String, String> locationMap : locations) {
            String name = locationMap.get("name");
            String address = locationMap.get("address");
            lm.createLocation(name, address);
        }
    }

    @And("the locations with id {int} to {int} have the following current prices:")
    public void theLocationsWithIdToHaveTheFollowingCurrentPrices(int startId, int endId, DataTable pricesDataTable) {
        double parkingPriceDC = 0;
        double parkingPriceAC = 0;
        double pricePerKwhDC = 0;
        double pricePerKwhAC = 0;
        LocalDateTime datetime = LocalDateTime.now();

        List<Map<String, String>> prices = pricesDataTable.asMaps();

        for (Map<String, String> priceRow : prices) {
            // Parse the values from the table
            pricePerKwhAC = Double.parseDouble(priceRow.get("price_per_kWh_AC"));
            pricePerKwhDC = Double.parseDouble(priceRow.get("price_per_kWh_DC"));
            parkingPriceAC = Double.parseDouble(priceRow.get("parking_price_AC"));
            parkingPriceDC = Double.parseDouble(priceRow.get("parking_price_DC"));
            datetime = LocalDateTime.parse(priceRow.get("Datetime"));
        }

        for (int i = startId; i <= endId; i++) {
            LocationManager.locationRepo.get(i).createPrices(pricePerKwhAC, pricePerKwhDC, parkingPriceAC, parkingPriceDC, datetime);
        }
    }

    // @US3.1
    @Given("the following chargers exist at each location:")
    public void theFollowingChargersExistAtEachLocation(DataTable chargersDataTable) {
        List<Map<String, String>> chargers = chargersDataTable.asMaps(String.class, String.class);

        for (Location location : LocationManager.locationRepo.values()) {

            for (Map<String, String> row : chargers) {
                String type = row.get("type");
                String status = row.get("status");
                location.createCharger(type, status);
            }
        }
    }

    @When("I attempt to access the locations information")
    public void iAttemptToAccessTheLocationsInformation() {
        lm.viewLocationsInformation();
    }

    @Then("I see a list of every location and their chargers:")
    public void iSeeAListOfEveryLocationAndTheirChargers(String expected) {
        String actual = lm.viewLocationsInformation();
        assertEquals(expected.trim(), actual.trim());
    }

    @Then("I see a list of every location:")
    public void iSeeAListOfEveryLocation(String expected) {
        String actual = lm.viewLocationsInformation();
        assertEquals(expected.trim(), actual.trim());
    }
}
