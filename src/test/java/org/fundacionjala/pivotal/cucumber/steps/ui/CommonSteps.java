package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import org.fundacionjala.core.Environment;
import org.fundacionjala.pivotal.pages.Login;

/**
 * Common steps.
 */
public class CommonSteps {

    @Autowired
    private Login login;

    /**
     * Logs in with user.
     *
     * @param key for start session.
     */
    @Given("logs in with user {string}")
    public void logsInWithUser(final String key) {
        String userNameKey = String.format("credentials.%s.username", key);
        String passwordKey = String.format("credentials.%s.password", key);
        login.loginAs(Environment.getInstance().getValue(userNameKey), Environment.getInstance().getValue(passwordKey));
    }
}
