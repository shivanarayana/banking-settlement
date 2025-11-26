package com.example.banking.steps;

import com.example.banking.service.SettlementService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettlementSteps {

    @Autowired //inject service
    private SettlementService settlementService;

    private String targetAccountId;
    private LocalDate requestDate;
    private String actualStatus;

    @Given("the settlement cut-off limit is configured to {int} days")
    public void theSettlementCutOffLimitIsConfiguredToDays(int days) {
        // configuration loaded by Spring
    }

    @Given("a loan account {string} exists with maturity date")
    public void aLoanAccountExistsWithMaturityDate(String accountId) {
        this.targetAccountId = accountId;
    }

    @When("a settlement request is submitted on {string}")
    public void aSettlementRequestIsSubmittedOn(String dateString) {
        this.requestDate = LocalDate.parse(dateString);
        this.actualStatus = settlementService.processSettlement(targetAccountId, requestDate);
    }

    @Then("the settlement status should be {string}")
    public void theSettlementStatusShouldBe(String expectedStatus) {
        assertEquals(expectedStatus, actualStatus);
    }
}