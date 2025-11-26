package com.example.banking.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LoanAccount {
    private String accountId;
    private Double balance;
    private LocalDate maturityDate;
}
