package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.fundacionjala.core.Environment;
import org.fundacionjala.core.ui.driver.DriverManager;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Header;
import org.fundacionjala.pivotal.pages.Login;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.asserts.SoftAssert;

/**
 * Common steps.
 */
public class CommonSteps {

    @Autowired
    private Login login;

    @Autowired
    private Dashboard dashboard;

    @Autowired
    private Header header;

    static SoftAssert Assert;

    /**
     * Logs in with user.
     *
     * @param key for start session.
     */
    @Given("logs in with user {string}")
    public void logsInWithUser(final String key) {
        final String userNameKey = String
                .format("credentials.%s.username", key);
        final String passwordKey = String
                .format("credentials.%s.password", key);
        DriverManager.getInstance().getDriver().get(Environment.getInstance()
                .getValue("url.login"));
        this.login.loginAs(Environment.getInstance().getValue(userNameKey),
                Environment.getInstance().getValue(passwordKey));
    }

    /**
     * This method reload page to go dashboard.
     *
     * @param tabName name of dashboard tab.
     **/
    @Given("goes to dashboard {string}")
    public void goToDashboardAndTab(final String tabName) {
        this.dashboard.reload();
        if (tabName.toLowerCase().contains("workspaces")) {
            this.dashboard.goToWorkSpaceTab();
        }
    }

    /**
     * Open the project given by name on dashboard page.
     *
     * @param projectKeyName is the name of the project
     */
    @And("opens a project {string}")
    public void opensAProject(final String projectKeyName) {
        final String projectName = StringUtil.getValue(projectKeyName);
        this.dashboard.goToProject(projectName);
    }

    /**
     * Opens the popover from header title.
     */
    @And("opens the popover from header title")
    public void opensThePopoverFromHeaderTitle() {
        this.header.openMenu();
    }

    @Before("@SoftAssert")
    public static void initialize() {
        Assert = new SoftAssert();
    }

    @And("asserts all")
    public static void assertAll() {
        Assert.assertAll();
    }
}
