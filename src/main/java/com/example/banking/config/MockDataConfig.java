package com.example.banking.config;

import com.example.banking.model.LoanAccount;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "mock")
@Data
public class MockDataConfig {
    private List<LoanAccount> loans = new ArrayList<>();
}