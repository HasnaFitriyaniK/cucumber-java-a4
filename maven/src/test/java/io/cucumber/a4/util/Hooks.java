package io.cucumber.a4.util;

import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Hooks for setting up and tearing down test environment
 */
public class Hooks {    @Before
    public void setup() {
        try {
            System.out.println("Setting up WebDriver for test...");
            // Initialize the driver by getting it from SharedDriver
            SharedDriver.getDriver().manage().deleteAllCookies();
            System.out.println("WebDriver initialized successfully");
        } catch (Exception e) {
            System.err.println("Error setting up WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                System.out.println("Scenario failed: " + scenario.getName());
                
                // Take screenshot on failure
                try {
                    final byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) SharedDriver.getDriver())
                        .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Failed scenario screenshot");
                    System.out.println("Screenshot taken for failed scenario: " + scenario.getName());
                } catch (Exception e) {
                    System.err.println("Could not take failure screenshot: " + e.getMessage());
                }
            }
        } finally {
            // Clean up after test
            System.out.println("Tearing down WebDriver...");
            SharedDriver.quitDriver();
        }
    }
}