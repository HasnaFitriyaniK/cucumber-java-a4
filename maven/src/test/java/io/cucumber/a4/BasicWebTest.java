package io.cucumber.a4;

import io.cucumber.a4.util.SharedDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BasicWebTest {
    private WebDriver driver;
    
    @BeforeEach
    public void setup() {
        driver = SharedDriver.getDriver();
    }
    
    @Test
    public void testLoginPageStructure() {
        // Navigate to the login page
        driver.get("http://ptbsp.ddns.net:6882/");
        
        // Output page details to diagnose the issue
        System.out.println("Page Title: " + driver.getTitle());
        System.out.println("Current URL: " + driver.getCurrentUrl());
        
        // Check if we can find input fields on the page
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        System.out.println("Input fields found: " + inputs.size());
        
        for (int i = 0; i < inputs.size(); i++) {
            WebElement input = inputs.get(i);
            String type = input.getAttribute("type");
            String name = input.getAttribute("name");
            String id = input.getAttribute("id");
            System.out.println("Input #" + i + " - Type: " + type + ", Name: " + name + ", ID: " + id);
        }
        
        // Check for forms
        List<WebElement> forms = driver.findElements(By.tagName("form"));
        System.out.println("Forms found: " + forms.size());
        
        // Check page source snippet
        String pageSource = driver.getPageSource();
        System.out.println("Page Source Preview: " + 
            pageSource.substring(0, Math.min(500, pageSource.length())) + "...");
    }
    
    @AfterEach
    public void tearDown() {
        SharedDriver.quitDriver();
    }
}
