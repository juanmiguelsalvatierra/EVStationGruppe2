package org.example;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class ManageCustomerStepdefs {
    ApplicationContext ac = new ApplicationContext();
    String currentEmail = "";
    String currentName = "";
    Customer c;
    @Given("I am a visitor without an existing account for {string}")
    public void iAmAVisitorWithoutAnExistingAccountFor(String arg0) {
    }

    @When("I create an account with name {string} , email {string}")
    public void iCreateAnAccountWithNameEmail(String arg0, String arg1) {
        this.c = ac.customerService.createCustomer(arg0, arg1);
        this.currentName = arg0;
        this.currentEmail = arg1;
    }

    @Then("my account is created with a unique Customer ID")
    public void myAccountIsCreatedWithAUniqueCustomerID() {
        assertTrue(this.c.customerId > 0);
    }

    @And("my initial balance is {string}")
    public void myInitialBalanceIs(String arg0) {
        throw new PendingException();
    }

    @And("my current username is {string}")
    public void myCurrentUsernameIs(String arg0) {
        assertEquals(arg0, this.currentName);
    }

    @When("I update my username to {string}")
    public void iUpdateMyUsernameTo(String arg0) {
        this.c.updateAccount(arg0, this.c.email);
        this.currentName = arg0;
    }

    @Then("my account shows “NewName” as my username")
    public void myAccountShowsNewNameAsMyUsername(String arg0) {
        assertEquals(arg0, this.c.name);
    }

    @And("my balance is {string}")
    public void myBalanceIs(String arg0) {
        throw new PendingException();
    }

    @When("I request to delete my account")
    public void iRequestToDeleteMyAccount() {
        throw new PendingException();
    }

    @Then("my account is deleted")
    public void myAccountIsDeleted() {
        throw new PendingException();
    }

    @And("I immediately lose access to the portal")
    public void iImmediatelyLoseAccessToThePortal() {
        throw new PendingException();
    }

    @Then("I won’t be able to delete my account")
    public void iWonTBeAbleToDeleteMyAccount() {
        throw new PendingException();
    }

    @When("I open my profile page")
    public void iOpenMyProfilePage() {
        throw new PendingException();
    }

    @Then("I see my account details")
    public void iSeeMyAccountDetails() {
        assertNotNull(this.c);
        assertNotNull(this.c.name);
        assertNotNull(this.c.email);
        assertTrue(this.c.customerId > 0);
    }



}
