package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.asserts.SoftAssert;

import org.fundacionjala.core.util.Assert;
import org.fundacionjala.core.util.ScenarioContext;
import org.fundacionjala.core.util.StringUtil;
import org.fundacionjala.pivotal.pages.common.Dashboard;
import org.fundacionjala.pivotal.pages.common.Header;
import org.fundacionjala.pivotal.pages.common.HeaderMenu;
import org.fundacionjala.pivotal.pages.common.ToastMessage;
import org.fundacionjala.pivotal.pages.workspace.WorkSpaceNew;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Assertion on common steps with soft assert.
 */
public class AssertSteps {

    @Autowired
    private Dashboard dashboard;

    @Autowired
    private WorkSpaceNew workSpaceNew;

    @Autowired
    private Header header;

    @Autowired
    private HeaderMenu headerMenu;

    @Autowired
    private ToastMessage toastMessage;

    @Autowired
    private Assert assertion;

    private static final String WORKSPACE = "Workspaces";

    /**
     * Final step validation for soft assert.
     */
    @Then("asserts all")
    public void assertAll() {
        if (assertion.getAssertion() instanceof SoftAssert) {
            ((SoftAssert) assertion.getAssertion()).assertAll();
        }
    }

    /**
     * This steps verify that workspace.
     *
     * @param name param.
     **/
    @Then("the workspace should be created: {string}")
    public void theWorkspaceShouldBeCreated(final String name) {
        final String actualResult = this.workSpaceNew.getWorkSpaceLabel();
        assertEquals(actualResult, name);
    }

    /**
     * Validate title match on header title.
     *
     * @param headerTitle String
     */
    @Then("validates {string} on header title")
    public void validatesOnHeaderTitle(final String headerTitle) {
        final String name = ScenarioContext.getInstance().getContextAsString(headerTitle);

        final String actual = this.header.getTitleName();
        assertEquals(actual, name, String.format(" %s match on header title", name));
    }

    /**
     * Validate title match on workspace header title.
     *
     * @param headerWSTitle String
     */
    @Then("validates {string} on workspace header title")
    public void validatesOnWSHeaderTitle(final String headerWSTitle) {
        final String name = ScenarioContext.getInstance().getContextAsString(headerWSTitle);

        final String actual = this.header.getTitleWSName();
        assertEquals(actual, name, String.format(" %s match on workspace header title", name));
    }

    /**
     * Validate listing of item on a specific group.
     *
     * @param key           String
     * @param specificGroup String
     */
    @And("validates {string} on {string} group list")
    public void validatesOnGroupList(final String key, final String specificGroup) {
        final String name = ScenarioContext.getInstance().getContextAsString(key);
        if (WORKSPACE.equals(specificGroup)) {
            assertTrue(this.headerMenu.isWorkspaceListedOnMenu(name),
                    String.format(" %s match inside group list on %s", name, specificGroup));
        } else {
            assertTrue(this.headerMenu.isProjectListedOnMenu(name),
                    String.format(" %s match inside group list on %s", name, specificGroup));
        }
    }

    /**
     * Validate listing of item on a specific dashboard tab.
     *
     * @param key          String
     * @param specificList String
     */
    @And("validates {string} on {string} dashboard tab")
    public void validatesOnDashboardTab(final String key, final String specificList) {
        final String name = ScenarioContext.getInstance().getContextAsString(key);
        if (WORKSPACE.equals(specificList)) {
            assertTrue(this.dashboard.existWorkSpace(name),
                    String.format(" %s found on dashboard page inside %s", name, specificList));
        } else {
            assertTrue(this.dashboard.existProject(name),
                    String.format(" %s found on dashboard page inside %s", name, specificList));
        }
    }

    /**
     * Validate not listing of item on a specific group.
     *
     * @param key           String
     * @param specificGroup String
     */
    @And("validates {string} not listed on {string} group list")
    public void validatesNotListedOnGroupList(final String key, final String specificGroup) {
        final String name = StringUtil.getValue(key);
        if (WORKSPACE.equals(specificGroup)) {
            assertFalse(this.headerMenu.isWorkspaceListedOnMenu(name),
                    String.format(" %s not listed on %s group", name, specificGroup));
        } else {
            assertFalse(this.headerMenu.isProjectListedOnMenu(name),
                    String.format(" %s not listed on %s group", name, specificGroup));
        }
    }

    /**
     * Validate not listing of item on a specific dashboard tab.
     *
     * @param key          String
     * @param specificList String
     */
    @And("validates {string} not listed on {string} dashboard tab")
    public void validatesNotListedOnDashboardTab(final String key, final String specificList) {
        final String name = StringUtil.getValue(key);

        if (WORKSPACE.equals(specificList)) {
            assertFalse(this.dashboard.existWorkSpace(name),
                    String.format(" %s not listed on %s dashboard tab", name, specificList));
        } else {
            assertFalse(this.dashboard.existProject(name),
                    String.format(" %s not listed on %s dashboard tab", name, specificList));
        }
    }

    /**
     * Validate the display of a given message.
     *
     * @param messageOnScreen String
     */
    @Then("a {string} message should be displayed")
    public void aMessageShouldBeDisplayed(final String messageOnScreen) {
        assertTrue(this.toastMessage.checkVisibilityOfMessage(messageOnScreen));
    }

    /**
     * Validate the display of a given message.
     *
     * @param key             String
     * @param messageOnScreen String
     */
    @Then("a {string} {string} message should be displayed")
    public void aPersonalizedMessageShouldBeDisplayed(final String key, final String messageOnScreen) {
        final String name = StringUtil.getValue(key);
        assertTrue(this.toastMessage.checkVisibilityOfMessage(String.format("%s %s", name, messageOnScreen)));
    }
}
