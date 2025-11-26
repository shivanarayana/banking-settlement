package com.example.banking.steps;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This class is the bridge between Cucumber and Spring Boot.
 * It tells Cucumber to start the Spring Context before running the tests.
 */
@CucumberContextConfiguration
@SpringBootTest
public class CucumberSpringConfiguration {
    // This class must remain empty.
    // It is used only for configuration.
}