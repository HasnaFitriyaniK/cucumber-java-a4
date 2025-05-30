package io.cucumber.a4.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Shared WebDriver to be used across all step definitions
 * Implements singleton pattern to ensure only one WebDriver instance exists
 * during test execution
 */
public class SharedDriver {
    private static WebDriver driver;

    private SharedDriver() {
        // Private constructor to prevent direct instantiation
    }    public static WebDriver getDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            
            // Add useful Chrome options for stability
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            
            // Enable headless mode for CI environment
            String headless = System.getProperty("headless", "false");
            if ("true".equals(headless) || System.getenv("CI") != null) {
                options.addArguments("--headless=new");
                System.out.println("Running in headless mode for CI environment");
            }
            
            // Uncomment for headless execution if needed locally
            // options.addArguments("--headless=new");
            // options.addArguments("--headless=new");
            
            try {
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                
                // Add implicit wait for better element finding
                driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
                
                // Print browser info for debugging
                System.out.println("Browser session started successfully");
                System.out.println("Browser: Chrome " + 
                    ((org.openqa.selenium.chrome.ChromeDriver)driver).getCapabilities().getBrowserVersion());
            } catch (Exception e) {
                System.err.println("Failed to initialize WebDriver: " + e.getMessage());
                throw e;
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}