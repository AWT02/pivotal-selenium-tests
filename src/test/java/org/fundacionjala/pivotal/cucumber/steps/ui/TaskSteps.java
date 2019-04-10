package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.fundacionjala.core.api.services.ProjectService;
import org.fundacionjala.core.api.services.StoryService;
import org.fundacionjala.pivotal.pages.Tasks;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** This class will have steps for task feature. **/
public class TaskSteps {
    private static final Logger LOGGER =
            Logger.getLogger(TaskSteps.class.getName());

    private static final String TASK_NAME = "taskName";

    @Autowired
    private Tasks tasksPanel;

    /** Step to set default project with story. **/
    @Given("Project with one story")
    public void projectWithOneStory() {
        final int id = ProjectService.createProject("My test project");
        ScenarioContext.getInstance().setContext("defaultProjectId", id);
        final int taskId = StoryService.createStory(id, "Task test");
        ScenarioContext.getInstance().setContext("defaultTaskId", taskId);
    }

    /**
     * Step to create a task with text.
     * @param text string.
     */
    @When("Create a task with text {string}")
    public void createATaskWithText(final String text) {
        this.tasksPanel.clickOnAddTaskButton();
        this.tasksPanel.setTaskText(text);
        this.tasksPanel.clickOnSave();
        ScenarioContext.getInstance().setContext(TASK_NAME, text);
    }

    /** Step to verify task created. */
    @Then("Verify that task was created")
    public void verifyThatTaskWasCreated() {
        final String text = (String) ScenarioContext.getInstance()
                .getContext(TASK_NAME);
        assertTrue(this.tasksPanel.existTask(text));
    }

    /**
     * Step to modify a task.
     * @param newText new task value.
     **/
    @When("Modify text by {string}")
    public void modifyTextBy(final String newText) {
        final String text = (String) ScenarioContext.getInstance()
                .getContext(TASK_NAME);
        this.tasksPanel.selectTask(text);
        this.tasksPanel.setTaskText(newText);
        this.tasksPanel.clickOnSave();
        ScenarioContext.getInstance().setContext(TASK_NAME, newText);
    }

    /**
     * Step to verify that a task doesn't exist.
     * @param text string.
     */
    @Then("Verify that task {string} doesn't exist")
    public void verifyThatTaskDoesnTExist(final String text) {
        assertFalse(this.tasksPanel.existTask(text));
    }

    /**
     * Step to verify that a task with text exist.
     * @param text string
     **/
    @And("Task with name {string} exist")
    public void taskWithNameExist(final String text) {
        assertTrue(this.tasksPanel.existTask(text));
    }

    /**
     * Before steps for every feature.
     * @param scenario Scenario
     **/
    @Before
    public void before(final Scenario scenario) {
        LOGGER.info(String.format("@Before %s  Status - %s", scenario.getName(),
                scenario.getStatus()));
    }

    /**
     * After steps for every feature.
     * @param scenario Scenario
     */
    @After
    public void after(final Scenario scenario) {
        LOGGER.info(String.format("@After.1 %s  Status - %s", scenario.getName(),
                scenario.getStatus()));
        final int projectId = (Integer) ScenarioContext.getInstance()
                .getContext("defaultProjectId");
        ProjectService.deleteProject(projectId);
        LOGGER.info(String.format("@After.2 %s  Status - %s", scenario.getName(),
                scenario.getStatus()));
    }

    /** Step to delete a task. **/
    @When("Delete task")
    public void deleteTask() {
        final String text = (String) ScenarioContext.getInstance()
                .getContext(TASK_NAME);
        this.tasksPanel.deleteTask(text);
    }
}
