package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import io.restassured.path.json.JsonPath;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.ui.driver.DriverManager;
import org.fundacionjala.pivotal.pages.Project;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.fundacionjala.core.Environment;
import org.fundacionjala.pivotal.pages.Login;

import java.util.HashMap;
import java.util.Map;

/**
 * Common steps.
 */
public class CommonSteps {

    @Autowired
    private Login login;

    @Autowired
    private Project project;

    private JsonPath resp;

    /**
     * Logs in with user.
     *
     * @param key for start session.
     */
    @Given("logs in with user {string}")
    public void logsInWithUser(final String key) {
        String userNameKey = String.format("credentials.%s.username", key);
        String passwordKey = String.format("credentials.%s.password", key);
        DriverManager.getInstance().getDriver().get(Environment.getInstance().getValue("url.base"));
        login.loginAs(Environment.getInstance().getValue(userNameKey), Environment.getInstance().getValue(passwordKey));
    }

    /**
     * This is a generic API POST method.
     * @param endpoint is the strings that is needed to complete the url for the
     *             endpoint.
     * @param param is the attributes read on the feature
     *                          file.
     */
    @Given("sends a POST request {string}")
    public void sendsAPOSTRequest(String endpoint,
                                 final Map<String, String> param) {
        final String builtEndpoint = StringUtil.getExplicitEndpoint(endpoint);
        resp = RequestManager.postRequest(builtEndpoint, param).body()
                .jsonPath();
    }

    @And("stores response as {string}")
    public void storesResponseAs(String keyContext) {
        ScenarioContext.getInstance().setContext(keyContext, resp);
    }
}