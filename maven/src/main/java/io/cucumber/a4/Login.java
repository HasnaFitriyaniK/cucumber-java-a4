package io.cucumber.a4;

/**
 * Domain model class representing Login functionality
 */
public class Login {
    private String username;
    private String password;
    private boolean loginSuccess;
    private String errorMessage;

    public Login() {
        this.loginSuccess = false;
    }

    /**
     * Performs login with the given credentials
     * 
     * @param username Username for login
     * @param password Password for login
     * @return true if login successful, false otherwise
     */
    public boolean doLogin(String username, String password) {
        this.username = username;
        this.password = password;

        // In real implementation, this would contain logic to authenticate
        // For now simulate login based on hardcoded values
        if ("bendahara".equals(username) && "password123".equals(password)) {
            this.loginSuccess = true;
            this.errorMessage = null;
        } else {
            this.loginSuccess = false;
            this.errorMessage = "Incorrect username or password, please try again!";
        }

        return this.loginSuccess;
    }

    /**
     * @return true if login was successful
     */
    public boolean isLoginSuccessful() {
        return this.loginSuccess;
    }

    /**
     * @return the error message if login failed, null otherwise
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
