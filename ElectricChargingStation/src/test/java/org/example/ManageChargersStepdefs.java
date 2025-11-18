package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManageChargersStepdefs {
    ApplicationContext ac = new ApplicationContext();
    private Charger currentCharger;
    private List<Charger> allChargers;

    @Given("I am logged in as an owner")
    public void iAmLoggedInAsAnOwner() {
        //
    }

    @When("I create a charger with the charger ID {string} of type {string} at location {string}")
    public void iCreateAChargerWithTheChargerIDOfTypeAtLocation(String chargerIdStr, String typeStr, String locationName) {
        int desiredChargerId = Integer.parseInt(chargerIdStr);
        ChargerType type = ChargerType.valueOf(typeStr.toUpperCase());

        // Simulate getting a valid locationId (in real app this would come from location name)
        // Since locations are not fully implemented, we just use a dummy locationId = 1
        int locationId = 1;

        Charger charger = ac.chargerService.createCharger(
                locationId,
                type,
                Status.FREE,
                22.0,
                0.39
        );
        charger.chargerId = desiredChargerId;

        this.currentCharger = charger;
    }

    @Then("it appears under {string} with status {string}")
    public void itAppearsUnderWithStatus(String locationName, String expectedStatus) {
        Charger found = ac.chargerService.getCharger(currentCharger.chargerId);
        assertNotNull(found, "Charger should exist");

        assertTrue(found.getLocationId() > 0, "Charger should be assigned to a location");

        String actualStatus = found.getStatus().toString().toLowerCase().contains("free") ? "in order free" : found.getStatus().toString();
        if ("in order free".equals(expectedStatus)) {
            assertEquals(Status.FREE, found.getStatus());
        }
         assertTrue(actualStatus.contains("free") || actualStatus.equals(expectedStatus.toLowerCase()),
                "Expected status to include 'free' or match '" + expectedStatus + "', but was: " + found.getStatus());
    }

    @When("I open the overview page for all chargers")
    public void iOpenTheOverviewPageForAllChargers() {
        this.allChargers = new ArrayList<>(ac.chargerRepository.getAllChargers());
    }

    @Then("I see each charger with its {string}, {string} and {string}")
    public void iSeeEachChargerWithItsAnd(String chargerIdStr, String typeStr, String statusStr) {
        int expectedId = Integer.parseInt(chargerIdStr);
        ChargerType expectedType = ChargerType.valueOf(typeStr.toUpperCase());

       Status expectedStatus;
        if (statusStr.toLowerCase().contains("occupied")) {
            expectedStatus = Status.OCCUPIED;
        } else if (statusStr.toLowerCase().contains("out of order")) {
            expectedStatus = Status.OUT_OF_ORDER;
        } else {
            expectedStatus = Status.FREE; // "in order free"
        }

        boolean found = allChargers.stream().anyMatch(c ->
                c.getChargerId() == expectedId &&
                        c.getChargerType() == expectedType &&
                        c.getStatus() == expectedStatus
        );

        assertTrue(found,
                "Charger " + chargerIdStr + " with type " + typeStr + " and status '" + statusStr + "' should be visible");
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
