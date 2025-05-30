package io.cucumber.a4;

/**
 * Domain model class representing Logout functionality
 */
public class Logout {
    private boolean logoutConfirmed = false;
    private boolean logoutSuccess = false;

    /**
     * Initiates logout process
     * 
     * @return true if confirmation should be shown
     */
    public boolean initiateLogout() {
        // In real implementation, this might prepare data or session for logout
        return true; // Always show confirmation in this example
    }

    /**
     * Confirms logout process
     * 
     * @param confirm true to confirm logout, false to cancel
     * @return true if logout was successful
     */
    public boolean confirmLogout(boolean confirm) {
        this.logoutConfirmed = confirm;

        if (confirm) {
            // In real implementation, this would perform actual logout operations
            this.logoutSuccess = true;
        }

        return this.logoutSuccess;
    }

    /**
     * @return true if logout was successful
     */
    public boolean isLogoutSuccessful() {
        return this.logoutSuccess;
    }
}
