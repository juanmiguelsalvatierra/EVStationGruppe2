package org.example;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManageChargersStepDefs {

    ApplicationContext ac = new ApplicationContext();
    private Charger currentCharger;
    private List<Charger> allChargers;

    private Status mapStatus(String statusStr) {
        statusStr = statusStr.toLowerCase();
        if (statusStr.contains("free")) return Status.FREE;
        if (statusStr.contains("occupied")) return Status.OCCUPIED;
        if (statusStr.contains("out of order")) return Status.OUT_OF_ORDER;
        throw new IllegalArgumentException("Unknown status: " + statusStr);
    }

    // --- CREATE ---
    @When("I create a charger of type {string} at location {string}")
    public void iCreateAChargerOfTypeAtLocation(String typeStr, String locationName) {
        ChargerType type = ChargerType.valueOf(typeStr.toUpperCase());
        int locationId = getLocationId(locationName);

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
        int expectedLocationId = getLocationId(locationName);

        Charger found = ac.chargerService.getCharger(currentCharger.chargerId);

        assertNotNull(found);
        assertEquals(expectedLocationId, found.locationId);
        assertEquals(expectedStatus, found.status);
    }

    // --- LIST ---
    @When("I open the overview page for all chargers")
    public void iOpenTheOverviewPageForAllChargers() {
        allChargers = ac.chargerService.getChargers();
    }

    @Then("I see each charger with its {string} and {string}")
    public void iSeeEachChargerWithItsAnd(String typeStr, String statusStr) {
        ChargerType expectedType = ChargerType.valueOf(typeStr.toUpperCase());
        Status expectedStatus = mapStatus(statusStr);

        boolean exists = allChargers.stream()
                .anyMatch(c -> c.chargerType == expectedType
                        && c.status == expectedStatus);

        assertTrue(exists, "Expected charger was not found.");
    }

    // --- GIVEN: charger exists at location ---
    @Given("the charger with the charger ID {string} exists at {string}")
    public void theChargerWithTheChargerIDExistsAt(String ignoredId, String locationName) {
        int locationId = getLocationId(locationName);

        currentCharger = ac.chargerService.createCharger(
                locationId, ChargerType.AC, Status.FREE, 22.0, 0.39
        );

        assertNotNull(ac.chargerService.getCharger(currentCharger.chargerId));
    }

    // --- MOVE (not implemented) ---
    @When("I move it to {string}")
    public void iMoveItTo(String ignoredNewLocation) {
        // Move not implemented → do nothing
        throw new PendingException();
    }

    @Then("it shows location {string}")
    public void itShowsLocation(String ignoredLocation) {
        // Since move is not implemented, we assert that nothing changed
        throw new PendingException();
    }

    // --- TYPE ---
    @Given("the charger with the charger ID {string} currently has type {string}")
    public void theChargerWithTheChargerIDCurrentlyHasType(String ignoredId, String typeStr) {
        ChargerType type = ChargerType.valueOf(typeStr.toUpperCase());
        currentCharger = ac.chargerService.createCharger(
                1, type, Status.FREE, 22.0, 0.39
        );
    }

    @When("I assign it type {string}")
    public void iAssignItType(String ignoredType) {
        // No update logic → no-op
        throw new PendingException();
    }

    @Then("it shows charger-type {string}")
    public void itShowsChargerType(String expectedTypeStr) {
        // The type stays what it was on creation
        throw new PendingException();
    }

    // --- STATUS ---
    @Given("the charger with the charger ID {string} currently has charger status {string}")
    public void theChargerWithTheChargerIDCurrentlyHasChargerStatus(String ignoredId, String statusStr) {
        Status status = mapStatus(statusStr);
        currentCharger = ac.chargerService.createCharger(
                1, ChargerType.AC, status, 22.0, 0.39
        );
    }

    @When("I assign it charger status {string}")
    public void iAssignItChargerStatus(String ignoredStatus) {
        // No update logic → no-op
        throw new PendingException();
    }

    @Then("it shows charger status {string}")
    public void itShowsChargerStatus(String expectedStatusStr) {
        throw new PendingException();
    }

    // --- DELETE ---
    @Given("the charger with the charger ID {string} exists")
    public void theChargerWithTheChargerIDExists(String ignoredId) {
        currentCharger = ac.chargerService.createCharger(
                1, ChargerType.AC, Status.FREE, 22.0, 0.39
        );
    }

    @Then("it disappears from the list")
    public void itDisappearsFromTheList() {
        // No delete functionality exists → assert that it still exists
        throw new PendingException();
    }

    private int getLocationId(String locationName) {
        switch (locationName.toLowerCase()) {
            case "vienna west": return 1;
            case "linz center": return 2;
            case "graz central": return 3;
            default: return 1;
        }
    }
}
