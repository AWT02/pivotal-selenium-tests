package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fundacionjala.pivotal.pages.ConfirmAction;
import org.fundacionjala.pivotal.pages.Header;
import org.fundacionjala.pivotal.pages.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;

import java.util.Map;

/**
 * Project steps.
 */
public class ProjectSteps {

    static final String PROJECTURI = "/projects";
    @Autowired
    private Project project;
    @Autowired
    private Header header;
    @Autowired
    private ConfirmAction confirm;

    /**
     * Create a new project.
     *
     * @param projectAttributes for create project.
     */
    @When("user creates project as")
    public void userCreatesNewProjectAs(final Map<String, String> projectAttributes) {
        project.createNewProject(projectAttributes);
    }

    /**
     * was project created with specific name.
     */
    @Then("validate creation on project's dashboard")
    public void validateTheProjectIsCreatedWithSpecifyName() {
        final String actual = project.getProjectNameOnDashboard();
        Assert.assertEquals(actual, project.getProjectName(), "Project name match");
    }

    /**
     * A project to delete.
     *
     * @param projectName the project name
     */
    @Given("an existing project named as {string} that user intends to delete")
    public void anExistingProjectNamedAsThatUserIntendsToDelete(final String projectName) {
        project.setProjectName(projectName);
    }

    /**
     * Open context on settings.
     */
    @And("open project's settings")
    public void openProjectsSettings() {
        final String projectName = project.getProjectName();
        project.openProjectSettingsbyName(projectName);
    }

    /**
     * Delete project by link.
     */
    @When("user click over delete project link")
    public void userClickOverDeleteProjectLink() {
        confirm.clickOnDeleteProjectLink();
        confirm.clickOnDeleteButton();
    }

    /**
     * Check if project is still displayed.
     */
    @Then("The project no longer appear on projects section")
    public void theProjectNoLongerAppearOnProjectsSection() {
        Assert.assertFalse(project.isProjectListedOnMainPage(), "False if project is not listed after deletion");
    }

    /**
     * Validate non-existance on active projects.
     */
    @And("the project is not present on active project")
    public void theProjectIsNotPresentOnActiveProject() {
        project.loadMainPage(PROJECTURI);
        final boolean actual = project.isProjectListedOnProjectPage();
        Assert.assertFalse(actual, "Passed if project is no longer on active project list");
    }

    /**
     * Save on context value of project name.
     *
     * @param projectName the project name
     */
    @Given("an existing project known as {string} that user intends to edit")
    public void anExistingProjectKnownAsThatUserIntendsToEdit(final String projectName) {
        project.setProjectName(projectName);
        project.openProjectSettingsbyName(projectName);
    }

    /**
     * Intend to change values by given attributes.
     *
     * @param projectAttributes table of attributes
     */
    @And("change values on form as")
    public void changeValuesOnFormAs(final Map<String, String> projectAttributes) {
        project.setValuesOnEditProjectForm(projectAttributes);
        project.saveFormOnEditProject();
    }

    /**
     * Save button is pressed.
     */
    @When("user press save button")
    public void userPressSaveButton() {
        project.saveFormOnEditProject();
    }

    /**
     * Verifies is success message type was displayed on screen.
     */
    @Then("A successful message is displayed")
    public void aSuccessfulMessageIsDisplayed() {
        final boolean actual = project.getResponseMessage();
        Assert.assertTrue(actual, "Passed if edit project was processed");
    }

    /**
     * Check existance of project's name on header menu list.
     */
    @And("validate creation on header project's list")
    public void validateCreationOnHeaderProjectSList() {
        header.openProjectMenu();
        final boolean actual = project.isProjectListedOnMenu();
        Assert.assertTrue(actual, "Passed if project is on Header menu section");
    }

    /**
     * Validate existance on project's section.
     */
    @And("validate creation on project's section")
    public void validateCreationOnProjectsSection() {
        project.loadMainPage(PROJECTURI);
        final boolean actual = project.isProjectListedOnProjectPage();
        Assert.assertTrue(actual, "Passed if project is on Project-s section");
    }

    /**
     * Validate non-existance of project's name on menu.
     */
    @And("Prior project's name no longer listed")
    public void priorProjectSNameNoLongerListed() {
        header.openProjectMenu();
        final boolean actual = project.isProjectListedOnMenu();
        Assert.assertFalse(actual, "Passed if project was changed its name");
    }

    /**
     * Validate account asignation.
     *
     * @param expected String
     */
    @Then("validate the {string} result on project account selection")
    public void validateTheResultOnProjectAccountSelection(final String expected) {
        if ("Error".equals(expected)) {
            final String actual = project.getMessageOnNewProjectForm();
            Assert.assertEquals(actual, "This account has reached its limit of 2 projects.", "Passed if ");
        }
    }

    /**
     * New project button on dashboard.
     */
    @Given("A create new button on dashboard")
    public void aCreateNewButtonOnDashboard() {
        project.clickCreateNewProjectButton();
    }

    /**
     * New project button on header menu.
     */
    @Given("A create new button on header menu")
    public void aCreateNewButtonOnHeaderMenu() {
        header.openProjectMenu();
        header.clickCreateNewProject();
    }

    /**
     * New project button on project's section.
     */
    @Given("An option to create a new project on project's section")
    public void anOptionToCreateANewProjectOnProjectSSection() {
        project.loadMainPage(PROJECTURI);
        project.clickCreateNewPRojectOption();
    }
}
