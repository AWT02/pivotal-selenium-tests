package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.DeleteModal;
import org.fundacionjala.pivotal.pages.HeaderContainer;
import org.fundacionjala.pivotal.pages.Panel;
import org.fundacionjala.pivotal.pages.Project;
import org.fundacionjala.pivotal.pages.Story;
import org.fundacionjala.util.ScenarioContext;
import org.fundacionjala.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * this is an story steps.
 */
public class StorySteps {
    @Autowired
    private Project project;
    @Autowired
    private Dashboard dashboard;
    @Autowired
    private Story story;
    @Autowired
    private Panel panel;
    @Autowired
    private HeaderContainer headerContainer;
    @Autowired
    private DeleteModal deleteModal;


    /**
     * Given step for story feature.
     *
     * @param arg0 is the name of the project.
     */
    @Given("a project called {string}")
    public void aProjectCalled(final String arg0) {
        this.dashboard.goToProject(arg0);
        this.story.createStory("ird test");
    }

    /**
     * @param name the name of the story.
     */
    @When("creates a story called {string}")
    public void createsAStoryCalled(final String name) {
        this.story.createStory(name);
        ScenarioContext.getInstance().setContext("story_name", name);
    }

    /**
     * this method verifies that a story is created.
     */
    @Then("verify the story is created")
    public void verifytheStoryIsCreated() {
        final String storyName = ScenarioContext.getContextAsString("story_name");
        assertTrue(this.story.existStory(storyName));
    }

    /**
     * @param arg1 name of other story.
     */
    @When("creates other a story called {string}")
    public void createsOtherAStoryCalled(final String arg1) {
        this.story.createStory(arg1);
    }

    /**
     * This method clicks the expand button for a specific story.
     *
     * @param storyKeyName the name of the story.
     */
    @When("expands the story {string}")
    public void expandsTheStory(final String storyKeyName) {
        final String storyName = StringUtil.getValue(storyKeyName);
        panel.expandStory(storyName);
    }

    /**
     * This method clicks the delete button inside the story page.
     */
    @And("click delete button")
    public void clickDeleteButton() {
        story.clickDeleteButton();
    }

    /**
     * Not a reliable method.
     *
     * @param storyNameKey not really sure (it was in progress).
     */
    @When("selects the bulk of {string}")
    public void deletesSelectingTheCheckboxof(final String storyNameKey) {
        final String storyName = StringUtil.getValue(storyNameKey);
        panel.clickStoryCheckboxButton(storyName);


    }

    /**
     * @param storyNameKey is the name of the story.
     */
    @Then("Verify that the story {string} is deleted")
    public void verifyThatTheStoryIsDeleted(final String storyNameKey) {
        final String storyName = StringUtil.getValue(storyNameKey);
        assertFalse(this.story.existStory(storyName));
    }

    /** clicks the button of the header container. */
    @And("click delete button of Header container")
    public void clickDeleteButtonOfHeaderContainer() {
        headerContainer.clickDeleteButtonOfToast();
    }

    /** Confirm button. */
    @And("click confirm delete button")
    public void clickConfirmDeleteButton() {
        deleteModal.clickConfirmDeleteButton();
    }
}
