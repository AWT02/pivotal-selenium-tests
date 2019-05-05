package org.fundacionjala.pivotal.cucumber.steps.ui;

import java.util.List;
import java.util.Map;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import org.fundacionjala.core.api.RequestManager;
import org.fundacionjala.core.util.ScenarioContext;
import org.fundacionjala.core.util.StringUtil;
import org.fundacionjala.pivotal.pages.common.ConfirmAction;
import org.fundacionjala.pivotal.pages.common.Dashboard;
import org.fundacionjala.pivotal.pages.common.Header;
import org.fundacionjala.pivotal.pages.common.HeaderMenu;
import org.fundacionjala.pivotal.pages.project.Project;
import org.fundacionjala.pivotal.pages.project.ProjectSettings;
import org.fundacionjala.pivotal.pages.project.ProjectWorkspaceList;
import org.fundacionjala.pivotal.pages.project.SavePanelProjectSettings;

/**
 * Project steps.
 */
public class ProjectSteps {

    static final String PROJECTURI = "/projects";
    private static final String PROJECT_NAME = "projectName";
    private static final String PROJECTS_IDS = "Projects_ids";

    @Autowired
    private Project project;
    @Autowired
    private Header header;
    @Autowired
    private Dashboard dashboard;
    @Autowired
    private ConfirmAction confirm;
    @Autowired
    private HeaderMenu menu;
    @Autowired
    private ProjectWorkspaceList projectList;
    @Autowired
    private ProjectSettings projectSettings;
    @Autowired
    private SavePanelProjectSettings savePanelProjectSettings;
    private Object resp;

    /**
     * Create a new project.
     *
     * @param projectAttributes for create project.
     */
    @When("creates project as")
    public void userCreatesNewProjectAs(final Map<String, String> projectAttributes) {
        this.project.createNewProject(projectAttributes);
    }

    /**
     * was project created with specific name.
     *
     * @param projectName name of the project, defined as a string.
     */
    @Then("validates {string} name on project's header title")
    public void validateTheProjectIsCreatedWithSpecifyName(final String projectName) {
        ScenarioContext.getInstance().setContext(PROJECT_NAME, projectName);
        final String actual = this.header.getTitleName();
        Assert.assertEquals(actual, ScenarioContext.getInstance().getContext(
                PROJECT_NAME), "Project name match");
    }

    /**
     * A project to delete.
     *
     * @param projectName the project name
     */
    @Given("an existing project named as {string} that user intends to delete")
    public void anExistingProjectNamedAsThatUserIntendsToDelete(final String projectName) {
        this.project.setProjectName(projectName);
    }

    /**
     * Open context on settings.
     *
     * @param projectKeyName name of the project.
     */
    @Given("opens a {string} settings")
    public void openProjectsSettings(final String projectKeyName) {
        final String projectName = StringUtil.getValue(projectKeyName);
        ScenarioContext.getInstance().setContext(PROJECT_NAME, projectName);
        this.dashboard.openProjectSettings(projectName);
    }

    /**
     * Delete project by link.
     */
    @When("clicks delete project link")
    public void userClickOverDeleteProjectLink() {
        this.projectSettings.clickOnDeleteProjectLink();
        this.confirm.clickOnDeleteButton();
    }

    /**
     * Check if project is still displayed.
     *
     * @param projectName project name.
     */
    @Then("verifies that project {string} doesn't appear on dashboard")
    public void theProjectNoLongerAppearOnProjectsSection(final String projectName) {
        Assert.assertFalse(this.dashboard.existProject(projectName),
                "False if project is not listed after deletion");
    }

    /**
     * Validate non-existance on active projects.
     *
     * @param projectName project name.
     */
    @And("verifies that project {string} doesn't appear on project list")
    public void theProjectIsNotPresentOnActiveProject(final String projectName) {
        final boolean actual = this.projectList
                .isProjectListedOnPage(projectName);
        Assert.assertFalse(actual,
                "Passed if project is no longer on active project list");
    }

    /**
     * Save on context value of project name.
     *
     * @param projectName the project name
     */
    @Given("an existing project known as {string} that user intends to edit")
    public void anExistingProjectKnownAsThatUserIntendsToEdit(final String projectName) {
        this.project.setProjectName(projectName);
        this.dashboard.openProjectSettings(projectName);
    }

    /**
     * Intend to change values by given attributes.
     *
     * @param projectAttributes table of attributes
     */
    @And("change values on form as")
    public void changeValuesOnFormAs(final Map<String, String> projectAttributes) {
        this.projectSettings.setValuesOnEditProjectForm(projectAttributes);
        this.savePanelProjectSettings.saveFormOnEditProject();
    }

    /**
     * Save button is pressed.
     */
    @When("user press save button")
    public void userPressSaveButton() {
        this.project.saveFormOnEditProject();
    }

    /**
     * Verifies is success message type was displayed on screen.
     */
    @Then("A successful message is displayed")
    public void aSuccessfulMessageIsDisplayed() {
        final boolean actual = this.projectSettings.getResponseMessage();
        Assert.assertTrue(actual, "Passed if edit project was processed");
    }

    /**
     * Check existance of project's name on header menu list.
     */
    @And("validate creation on header project's list")
    public void validateCreationOnHeaderProjectSList() {
        this.header.openProjectMenu();
        final boolean actual = this.menu.isProjectListedOnMenu(
                (String) ScenarioContext.getInstance().getContext("PROJECT_NAME"));
        Assert.assertTrue(actual, "Passed if project is on Header menu section");
    }

    /**
     * Validate existance on project's section.
     */
    @And("validate creation on project's section")
    public void validateCreationOnProjectsSection() {
        this.menu.showAllProjectsWorkSpaces();
        final boolean actual = this.projectList.isProjectListedOnPage(
                (String) ScenarioContext.getInstance().getContext("PROJECT_NAME"));
        Assert.assertTrue(actual, "Passed if project is on Project-s section");
    }

    /**
     * Validate non-existence of project's name on menu.
     */
    @And("Previous project's name no longer listed")
    public void priorProjectSNameNoLongerListed() {
        this.dashboard.reload();
        this.header.openProjectMenu();
        final boolean actual = this.menu.isProjectListedOnMenu(
                (String) ScenarioContext.getInstance().getContext(PROJECT_NAME));
        Assert.assertFalse(actual, "Passed if project was changed its name");
    }

    /**
     * Validate account assignation.
     *
     * @param expected String
     */
    @Then("validate the {string} result on project account selection")
    public void validateTheResultOnProjectAccountSelection(final String expected) {
        if ("Error".equals(expected)) {
            final String actual = this.project.getMessageOnNewProjectForm();
            Assert.assertEquals(actual, "This account has reached its limit of 2 projects.", "Passed if ");
        }
    }

    /**
     * New project button on dashboard.
     */
    @Given("clicks on create new project button")
    public void aCreateNewButtonOnDashboard() {
        this.dashboard.createProjectButton();
    }

    /**
     * New project button on header menu.
     */
    @Given("clicks new button on header menu")
    public void aCreateNewButtonOnHeaderMenu() {
        this.header.openProjectMenu();
        this.header.clickCreateNewProject();
    }

    /**
     * New project button on project's section.
     */
    @Given("An option to create a new project on project's section")
    public void anOptionToCreateANewProjectOnProjectSSection() {
        this.header.openMenu();
        this.menu.showAllProjectsWorkSpaces();
        this.project.clickCreateNewPRojectOption();
    }

    /**
     * set the id into context.
     *
     * @param keyContext is the key for the map.
     */
    @And("set {string}")
    public void set(final String keyContext) {
        ScenarioContext.getInstance().setContext(keyContext, this.resp);
    }

    /**
     * This is a method that loads all project Id's into a CONTEXT.
     */
    private void loadAllProjectIdsInContext() {
        final String url = StringUtil.getExplicitEndpoint(PROJECTURI);
        final JsonPath json =
                ((Response) RequestManager.getRequest(url).body()).jsonPath();
        ScenarioContext.getInstance().setContext(PROJECTS_IDS, json.get("id"));
    }

    /**
     * this method gets the id of the created project.
     */
    @And("get project id")
    public void getProjectId() {
        final List ids = (List) ScenarioContext.getInstance()
                .getContext(PROJECTS_IDS);
        loadAllProjectIdsInContext();
        final List ids2 = (List) ScenarioContext.getInstance()
                .getContext(PROJECTS_IDS);
        ids2.removeAll(ids);
        this.resp = ids2.get(0).toString();
    }
}
