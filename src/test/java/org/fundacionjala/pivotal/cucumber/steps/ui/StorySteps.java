package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.pivotal.pages.Dashboard;
import org.fundacionjala.pivotal.pages.Project;
import org.fundacionjala.pivotal.pages.Story;
import org.fundacionjala.util.ScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;

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
     *
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

    @After
    public void after() {
        RequestManager.deleteRequest("/projects/{project_response.id}");
    }
}
