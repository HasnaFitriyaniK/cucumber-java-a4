package io.cucumber.a4.pages;

import io.cucumber.a4.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

/**
 * Page Object representing the Login Page
 */
public class LoginPage extends BasePage {
    // Primary page elements    @FindBy(css = "form.space-y-10")
    private WebElement loginForm;
    
    @FindBy(css = "input[name='username'], input[id*='form-item'], input[type='text']")
    private WebElement usernameField;
    
    @FindBy(css = "input[type='password']")
    private WebElement passwordField;
    
    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = ".error-message, .alert-error, .alert-danger, .text-danger")
    private WebElement errorMessage;

    private final Login loginModel;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.loginModel = new Login();
    }
    
    /**
     * Navigate to login page
     */
    public void navigateTo(String url) {
        try {
            System.out.println("Navigating to: " + url);
            driver.get(url);
            
            // Print basic page info
            System.out.println("Page title: " + driver.getTitle());
            System.out.println("Current URL: " + driver.getCurrentUrl());
            
            // Find login form elements using finder methods instead of PageFactory
            findLoginElements();
            
        } catch (Exception e) {
            System.err.println("Warning: Issue finding elements on login page: " + e.getMessage());
            
            // Print page source for debugging
            String pageSource = driver.getPageSource();
            System.err.println("Page source snippet: " + 
                pageSource.substring(0, Math.min(500, pageSource.length())) + "...");
            
            // Take screenshot if possible
            try {
                ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
                System.err.println("Screenshot taken to help debug page structure issues");
            } catch (Exception screenshotError) {
                System.err.println("Could not take screenshot: " + screenshotError.getMessage());
            }
            
            // Print all input elements on the page to identify the form fields
            printPageElements();
        }
    }
    
    /**
     * Find login form elements directly using By locators
     */
    private void findLoginElements() {
        try {
            // Check for form
            List<WebElement> forms = driver.findElements(By.tagName("form"));
            if (forms.size() > 0) {
                System.out.println("Found " + forms.size() + " form elements");
            } else {
                System.out.println("No form elements found, will try to find input fields directly");
            }
            
            // Output found elements for diagnosis
            printPageElements();
        } catch (Exception e) {
            System.err.println("Error finding login elements: " + e.getMessage());
        }
    }
    
    /**
     * Print all relevant elements on the page for debugging
     */
    private void printPageElements() {
        try {
            // Find inputs
            List<WebElement> inputs = driver.findElements(By.tagName("input"));
            System.out.println("Found " + inputs.size() + " input elements on page");
            for (int i = 0; i < inputs.size(); i++) {
                WebElement input = inputs.get(i);
                String type = input.getAttribute("type") != null ? input.getAttribute("type") : "unknown";
                String name = input.getAttribute("name") != null ? input.getAttribute("name") : "unknown";
                String id = input.getAttribute("id") != null ? input.getAttribute("id") : "unknown";
                System.out.println("Input #" + i + " - Type: " + type + ", Name: " + name + ", ID: " + id);
            }
            
            // Find buttons
            List<WebElement> buttons = driver.findElements(By.tagName("button"));
            System.out.println("Found " + buttons.size() + " button elements on page");
            for (int i = 0; i < buttons.size(); i++) {
                WebElement button = inputs.get(i);
                String type = button.getAttribute("type") != null ? button.getAttribute("type") : "unknown";
                String text = button.getText() != null ? button.getText() : "unknown";
                System.out.println("Button #" + i + " - Type: " + type + ", Text: " + text);
            }
        } catch (Exception e) {
            System.err.println("Error printing page elements: " + e.getMessage());
        }
    }

    /**
     * Enter username using dynamic element finder if PageFactory element fails
     */
    public void enterUsername(String username) {
        try {
            System.out.println("Attempting to enter username: " + username);
            WebElement element = null;
            
            // Try using PageFactory element first
            try {
                if (usernameField != null && usernameField.isDisplayed()) {
                    element = usernameField;
                }
            } catch (Exception e) {
                System.out.println("PageFactory username field not found, trying direct locators");
            }
            
            // If PageFactory element failed, try direct locators
            if (element == null) {
                // Try various locators for username field
                try {
                    element = driver.findElement(By.cssSelector("input[type='text']"));
                } catch (Exception e) {
                    try {
                        element = driver.findElement(By.cssSelector("input[name='username']"));
                    } catch (Exception e2) {
                        try {
                            element = driver.findElement(By.cssSelector("input[id*='form-item']"));
                        } catch (Exception e3) {
                            System.err.println("All attempts to find username field failed");
                            throw new RuntimeException("Username field not found");
                        }
                    }
                }
            }
            
            // Now use the element
            element.clear();
            element.sendKeys(username);
            System.out.println("Username entered successfully");
        } catch (Exception e) {
            System.err.println("Could not find or interact with username field: " + e.getMessage());
            throw new RuntimeException("Failed to enter username: " + e.getMessage());
        }
    }

    /**
     * Enter password using dynamic element finder if PageFactory element fails
     */
    public void enterPassword(String password) {
        try {
            System.out.println("Attempting to enter password");
            WebElement element = null;
            
            // Try using PageFactory element first
            try {
                if (passwordField != null && passwordField.isDisplayed()) {
                    element = passwordField;
                }
            } catch (Exception e) {
                System.out.println("PageFactory password field not found, trying direct locators");
            }
            
            // If PageFactory element failed, try direct locators
            if (element == null) {
                // Try various locators for password field
                try {
                    element = driver.findElement(By.cssSelector("input[type='password']"));
                } catch (Exception e) {
                    try {
                        element = driver.findElement(By.cssSelector("input[name='password']"));
                    } catch (Exception e2) {
                        System.err.println("All attempts to find password field failed");
                        throw new RuntimeException("Password field not found");
                    }
                }
            }
            
            // Now use the element
            element.clear();
            element.sendKeys(password);
            System.out.println("Password entered successfully");
        } catch (Exception e) {
            System.err.println("Could not find or interact with password field: " + e.getMessage());
            throw new RuntimeException("Failed to enter password: " + e.getMessage());
        }
    }

    /**
     * Click login button using dynamic element finder if PageFactory element fails
     */
    public void clickLoginButton() {
        try {
            System.out.println("Attempting to click login button");
            WebElement element = null;
            
            // Try using PageFactory element first
            try {
                if (loginButton != null && loginButton.isDisplayed()) {
                    element = loginButton;
                }
            } catch (Exception e) {
                System.out.println("PageFactory login button not found, trying direct locators");
            }
            
            // If PageFactory element failed, try direct locators
            if (element == null) {
                // Try various locators for login button
                try {
                    element = driver.findElement(By.cssSelector("button[type='submit']"));
                } catch (Exception e) {
                    try {
                        element = driver.findElement(By.cssSelector("input[type='submit']"));
                    } catch (Exception e2) {
                        try {
                            List<WebElement> buttons = driver.findElements(By.tagName("button"));
                            if (!buttons.isEmpty()) {
                                element = buttons.get(0);  // Just use the first button as a last resort
                            } else {
                                System.err.println("No buttons found on page");
                                throw new RuntimeException("Login button not found");
                            }
                        } catch (Exception e3) {
                            System.err.println("All attempts to find login button failed");
                            throw new RuntimeException("Login button not found");
                        }
                    }
                }
            }
            
            // Now click the element
            safeClick(element);
            System.out.println("Login button clicked successfully");
        } catch (Exception e) {
            System.err.println("Could not click login button: " + e.getMessage());
            throw new RuntimeException("Failed to click login button: " + e.getMessage());
        }
    }

    /**
     * Login with provided credentials
     */
    public boolean login(String username, String password) {
        try {
            enterUsername(username);
            enterPassword(password);
            clickLoginButton();
            return true;
        } catch (Exception e) {
            System.err.println("Login failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Check if error message is displayed
     */
    public String getErrorMessage() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText();
        } catch (Exception e) {
            System.err.println("Error message not found: " + e.getMessage());
            return "";
        }
    }
    
    /**
     * Check if we're still on login page
     */
    public boolean isOnLoginPage() {
        try {
            // Check if username field is still visible
            return usernameField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
