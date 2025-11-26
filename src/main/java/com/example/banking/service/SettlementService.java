package com.example.banking.service;

import com.example.banking.service.SettlementService;
import com.example.banking.config.MockDataConfig;
import com.example.banking.model.LoanAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class SettlementService {

    private final MockDataConfig mockDataConfig;

    @Value("${settlement.cutoff-days-limit}")
    private int cutoffDaysLimit;

    public SettlementService(MockDataConfig mockDataConfig) {
        this.mockDataConfig = mockDataConfig;
    }

    public String processSettlement(String accountId, LocalDate requestDate) {
        // 1. Find the mocked account
        Optional<LoanAccount> accountOpt = mockDataConfig.getLoans().stream()
                .filter(l -> l.getAccountId().equalsIgnoreCase(accountId))
                .findFirst();

        if (accountOpt.isEmpty()) {
            return "ACCOUNT_NOT_FOUND";
        }

        LoanAccount account = accountOpt.get();

        if (account.getMaturityDate() == null) {
            return "NO_MATURITY_DATE";
        }

        // 2. Calculate days until maturity
        long daysUntilMaturity = ChronoUnit.DAYS.between(requestDate, account.getMaturityDate());

        // 3. Apply Logic
        // If request is AFTER maturity date (negative days) -> Overdue (Assumed OK to settle to clear debt)
        // If request is BEFORE maturity date, check cutoff.

        if (daysUntilMaturity < 0) {
            return "DONE"; // Overdue, immediate settlement accepted
        }

        if (daysUntilMaturity < cutoffDaysLimit) {
            // e.g. 3 days left, but limit is 5. Too close to maturity.
            return "NOT_DONE";
        }

        return "DONE";
    }
}