package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ManageChargersStepdefs {

    ApplicationContext ac = new ApplicationContext();

    private Charger currentCharger;
    private List<Charger> allChargers;


    private Map<String, Integer> locationIds = Map.of(
            "Vienna West", 1,
            "Linz Center", 2
    );


    private Status mapStatus(String s) {
        s = s.toLowerCase();
        if (s.contains("free")) return Status.FREE;
        if (s.contains("occupied")) return Status.OCCUPIED;
        if (s.contains("out of order")) return Status.OUT_OF_ORDER;
        throw new IllegalArgumentException(s);
    }

    @Given("I am logged in as an owner")
    public void iAmLoggedInAsAnOwner() {
        //
    }

    @When("I create a charger with the charger ID {string} of type {string} at location {string}")
    public void iCreateAChargerWithTheChargerIDOfTypeAtLocation(String chargerId, String typeStr, String locationName) {

        ChargerType type = ChargerType.valueOf(typeStr.toUpperCase());
        int locationId = locationIds.get(locationName);

        currentCharger = ac.chargerService.createCharger(
                locationId,
                type,
                Status.FREE,
                22.0,
                0.39
        );
    }

    @Then("it appears under {string} with status {string}")
    public void itAppearsUnderWithStatus(String locationName, String expectedStatusStr) {

        Status expectedStatus = mapStatus(expectedStatusStr);

        Charger found = ac.chargerService.getCharger(currentCharger.getChargerId());

        assertNotNull(found);
        assertEquals(locationIds.get(locationName), found.getLocationId());
        assertEquals(expectedStatus, found.getStatus());
    }

    @When("I open the overview page for all chargers")
    public void iOpenTheOverviewPageForAllChargers() {
        allChargers = ac.chargerService.getChargers();
    }

    @Then("I see each charger with its {string}, {string} and {string}")
    public void iSeeEachChargerWithItsAnd(String idStr, String typeStr, String statusStr) {

        ChargerType expectedType = ChargerType.valueOf(typeStr.toUpperCase());
        Status expectedStatus = mapStatus(statusStr);

        boolean exists = allChargers.stream()
                .anyMatch(c ->
                        c.getChargerType() == expectedType &&
                                c.getStatus() == expectedStatus
                );

        assertTrue(exists, "A charger with the expected values should exist");
    }


    @Given("the charger with the charger ID {string} exists at {string}")
    public void theChargerWithTheChargerIDExistsAt(String chargerIdStr, String locationName) {
        /*int chargerId = Integer.parseInt(chargerIdStr);
        int locationId = currentCharger.getLocationId();

        Charger charger = ac.chargerRepository.findById(chargerId);
        assertNotNull(charger, "Charger with ID " + chargerId + " should exist");
        assertEquals(locationId, charger.getLocationId());
        this.currentCharger = charger;*/
    }

    @When("I move it to {string}")
    public void iMoveItTo(String newLocationName) {

    }

    @Then("it shows location {string}")
    public void itShowsLocation(String expectedLocationName) {

    }

    @Given("the charger with the charger ID {string} currently has type {string}")
    public void theChargerWithTheChargerIDCurrentlyHasType(String chargerIdStr, String typeStr) {
       /* int chargerId = Integer.parseInt(chargerIdStr);
        ChargerType expectedType = ChargerType.valueOf(typeStr.toUpperCase());

        Charger charger = ac.chargerRepository.findById(chargerId);
        assertNotNull(charger);
        assertEquals(expectedType, charger.getChargerType());
        this.currentCharger = charger;*/
    }

    @When("I assign it type {string}")
    public void iAssignItType(String newTypeStr) {

    }

    @Then("it shows charger-type {string}")
    public void itShowsChargerType(String expectedTypeStr) {

    }

    @Given("the charger with the charger ID {string} currently has charger status {string}")
    public void theChargerWithTheChargerIDCurrentlyHasChargerStatus(String chargerIdStr, String statusDisplayName) {

    }

    @When("I assign it charger status {string}")
    public void iAssignItChargerStatus(String newStatusDisplayName) {

    }

    @Then("it shows charger status {string}")
    public void itShowsChargerStatus(String chargerStatus) {

    }

    /*@Given("the charger with the charger ID {string} exists")
    public void theChargerWithTheChargerIDExists(String chargerIdStr) {
        /*int chargerId = Integer.parseInt(chargerIdStr);
        Charger charger = ac.chargerRepository.findById(chargerId);
        assertNotNull(charger, "Charger " + chargerId + " should exist before deletion");
        this.currentCharger = charger;
    }

    @When("I request to delete it")
    public void iRequestToDeleteIt() {
       // not implemented yet

    @Then("it disappears from the list")
    public void itDisappearsFromTheList() {
        // not implemented yet

    } */
}
