package io.cucumber.a4.pages;

import io.cucumber.a4.Logout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page Object representing the Dashboard Page
 */
public class DashboardPage extends BasePage {

    @FindBy(id = "dashboardHeader")
    private WebElement dashboardHeader;

    @FindBy(id = "userRole")
    private WebElement userRoleElement;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "confirmationPopup")
    private WebElement confirmationPopup;

    @FindBy(id = "confirmYesButton")
    private WebElement confirmYesButton;

    @FindBy(id = "confirmNoButton")
    private WebElement confirmNoButton;

    private Logout logoutModel;

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.logoutModel = new Logout();
    }

    /**
     * Wait for dashboard page to load
     */
    public boolean waitForPageToLoad() {
        wait.until(ExpectedConditions.urlContains("dashboard"));
        wait.until(ExpectedConditions.visibilityOf(dashboardHeader));
        return true;
    }

    /**
     * Get the user role displayed on dashboard
     */
    public String getUserRole() {
        wait.until(ExpectedConditions.visibilityOf(userRoleElement));
        return userRoleElement.getText();
    }

    /**
     * Click logout button
     */
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
        logoutModel.initiateLogout();
    }

    /**
     * Check if confirmation popup is displayed
     */
    public boolean isConfirmationPopupDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(confirmationPopup));
        return confirmationPopup.isDisplayed();
    }

    /**
     * Get confirmation popup text
     */
    public String getConfirmationPopupText() {
        wait.until(ExpectedConditions.visibilityOf(confirmationPopup));
        return confirmationPopup.getText();
    }

    /**
     * Confirm logout (click Yes)
     */
    public void confirmLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton));
        confirmYesButton.click();
        logoutModel.confirmLogout(true);
    }

    /**
     * Cancel logout (click No)
     */
    public void cancelLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmNoButton));
        confirmNoButton.click();
        logoutModel.confirmLogout(false);
    }

    /**
     * Check if user is on dashboard page
     */
    public boolean isOnDashboardPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains("dashboard");
    }
}
