package org.fundacionjala.pivotal.cucumber.steps.ui;

import java.util.Map;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.ui.forms.FormsElements;
import org.fundacionjala.core.util.ScenarioContext;
import org.fundacionjala.core.util.StringUtil;
import org.fundacionjala.pivotal.pages.common.Dashboard;
import org.fundacionjala.pivotal.pages.common.ToastMessage;
import org.fundacionjala.pivotal.pages.project.Project;
import org.fundacionjala.pivotal.pages.project.Tasks;


/**
 * This class will have steps for task feature.
 **/
public class TaskSteps {
    private static final Logger LOGGER =
            Logger.getLogger(TaskSteps.class.getName());

    private static final String TASK_NAME = "taskName";

    @Autowired
    private Dashboard dashboard;

    @Autowired
    private Project project;

    @Autowired
    private Tasks tasksPanel;

    @Autowired
    private ToastMessage toastMessage;

    private static final String BASE_URL = ScenarioContext.getInstance().getContextAsString(ScenarioContext.API_URL_KEY)
                    .concat("/projects/");

    /**
     * After steps for every feature.
     *
     * @param scenario Scenario
     */
    @After("@DeleteProject")
    public void after(final Scenario scenario) {
        LOGGER.info(String.format("@After.1 %s  Status - %s", scenario.getName(),
                scenario.getStatus()));
        final int projectId = (Integer) ScenarioContext.getInstance()
                .getContext("defaultProjectId");
        RequestManager.deleteRequest(BASE_URL + projectId);
        LOGGER.info(String.format("@After.2 %s  Status - %s", scenario.getName(),
                scenario.getStatus()));
    }

    /**
     * Step to create a task with text.
     *
     * @param taskAttributes Map.
     */
    @When("adds a task to current Story")
    public void addsATaskToCurrentStory(final Map<String, String> taskAttributes) {
        this.tasksPanel.clickOnAddTaskButton();
        this.tasksPanel.setTaskNewText(taskAttributes.get(FormsElements.NAME.toString()));
        this.tasksPanel.clickOnSave();
        ScenarioContext.getInstance().setContext(TASK_NAME, FormsElements.NAME.toString());
    }

    /**
     * Step to verify that the task was added to story.
     */
    @Then("validates task aggregation")
    public void verifyTheTaskWasAdded() {
        final String text = ScenarioContext.getInstance().getContextAsString(TASK_NAME);
        Assert.assertTrue(this.tasksPanel.existTask(text));
    }

    /**
     * Step to modify a task.
     *
     * @param taskAttributes Map of task attributes.
     **/
    @When("edits the task name")
    public void editTaskName(final Map<String, String> taskAttributes) {
        final String text = ScenarioContext.getInstance().getContextAsString(TASK_NAME);
        this.tasksPanel.selectTask(text);
        this.tasksPanel.setTaskEditText(taskAttributes.get(FormsElements.NAME.toString()));
        this.tasksPanel.clickOnSave();
        ScenarioContext.getInstance().setContext(TASK_NAME, taskAttributes.get(FormsElements.NAME.toString()));
    }

    /**
     * Step to verify that a task doesn't exist.
     */
    @Then("the old task should not be listed")
    public void verifyThatTaskNotListed() {
        Assert.assertFalse(this.tasksPanel.existTask(ScenarioContext.getInstance().getContextAsString(TASK_NAME)));
    }

    /**
     * Step to verify that a task with text exist.
     *
     * @param name string
     **/
    @And("Task with name {string} exist")
    public void taskWithNameExist(final String name) {
        Assert.assertTrue(this.tasksPanel.existTask(name));
    }

    /**
     * Deletes the task.
     *
     * @param taskName String
     */
    @When("deletes the task {string}")
    public void deleteTask(final String taskName) {
        final String name = StringUtil.getValue(taskName);
        this.tasksPanel.deleteTask(name);
    }

    /**
     * Step to open a project.
     **/
    @And("Go to default project")
    public void goToDefaultProject() {
        this.dashboard.goToProject("My test project");
    }

    /**
     * Step to open a story.
     **/
    @And("Open a story")
    public void openAStory() {
        this.project.expandOneStory();
    }

    /**
     * Validate non-listing of task name.
     *
     * @param taskName String
     */
    @Then("the {string} should not be listed")
    public void theShouldNotBeListed(final String taskName) {
        final String name = StringUtil.getValue(taskName);
        Assert.assertTrue(this.toastMessage.checkVisibilityOfMessage(name));
    }
}
