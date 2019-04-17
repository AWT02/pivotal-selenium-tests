package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.Environment;
import org.fundacionjala.core.ui.AbstractPage;
import org.fundacionjala.core.ui.HtmlForms;
import org.fundacionjala.core.ui.forms.EditProjectForm;
import org.fundacionjala.core.ui.forms.NewProjectForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * this class represented a login page.
 */
@Component
public class Project extends AbstractPage {

    @FindBy(css = "#create-project-button")
    private WebElement createNewProjectButton;

    @FindBy(css = ".tc-form__input")
    private WebElement projectNameField;

    @FindBy(css = ".tc-account-selector")
    private WebElement accountSelector;

    @FindBy(css = ".zWDds__Button.pvXpn__Button--positive")
    private WebElement createButton;

    @FindBy(css = ".tc-account-selector__create-account-icon")
    private WebElement newAccountOption;

    @FindBy(css = ".tc-account-creator__name")
    private WebElement newAccountField;

    @FindBy(css = ".raw_context_name")
    private WebElement titleOnDashboard;

    @FindBy(linkText = "Delete")
    private WebElement deleteLink;

    @FindBy(css = "#confirm_delete")
    private WebElement confirmDelete;

    @FindBy(css = "#project_name")
    private WebElement editProjectNameField;

    @FindBy(css = "#project_description")
    private WebElement editProjectDescriptionField;

    @FindBy(xpath = "//input[@id='project_enable_tasks' and @type='checkbox']")
    private WebElement editProjectEnableTask;

    @FindBy(xpath = "//input[@id='project_public' and @type='checkbox']")
    private WebElement editProjectPrivacy;

    @FindBy(css = "#account_change_link")
    private WebElement editAccountLink;

    @FindBy(css = "#project_account_id_select")
    private WebElement editAccountComboBox;

    @FindBy(xpath = "//input[@name='commit' and @type='submit']")
    private WebElement saveButtonOnEditProject;

    @FindBy(css = "#save_success_bar")
    private WebElement successBar;

    @FindBy(css = ".button.button--lined.button--medium.button--full-width")
    private WebElement showMoreProjectsButton;

    private String projectName;

    /**
     * Clicks the create new project button.
     */
    public void clickCreateNewProjectButton() {
        action.click(createNewProjectButton);
    }

    /**
     * This method set value of project name text field.
     *
     * @param strProjectName value of input.
     */
    public void setProjectNameTextField(final String strProjectName) {
        this.projectName = strProjectName;
        action.setValue(projectNameField, strProjectName);
    }

    /**
     * Open account list.
     **/
    public void openSelectAccountCombobox() {
        action.click(accountSelector);
    }

    /**
     * Select an option from list.
     *
     * @param accountName to select an account from list
     */
    public void selectAccount(final String accountName) {

        final boolean onListAccount = action.isExistingSelector(
                By.xpath("//div[text()='" + accountName + "']"));

        if (!onListAccount) {
            action.click(newAccountOption);
            createAccount(accountName);
        } else {
            final WebElement accountSelectorByName = driver.findElement(
                    By.xpath("//div[text()='" + accountName + "']"));
            action.click(accountSelectorByName);
        }
    }

    /**
     * Create an account by given name.
     *
     * @param accountName name of the new account
     */
    private void createAccount(final String accountName) {

        action.setValue(newAccountField, accountName);
    }

    /**
     * Clicks the create button.
     */
    public void clickCreateButton() {
        action.click(createButton);
    }

    /**
     * Create new project by given name.
     *
     * @param projectElements value
     */
    public void createNewProject(final Map<String, String> projectElements) {
        clickCreateNewProjectButton();
        final HtmlForms newProjectForm = new NewProjectForm();
        newProjectForm.fillForm(projectElements);
        clickCreateButton();
    }

    /**
     * Method to select privacy based on election.
     *
     * @param privacy option public or private
     */
    public void selectProjectPrivacy(final String privacy) {
        final WebElement accountPrivacyOption = driver.findElement(
                By.xpath("//input[@type='radio' and @data-aid='" + privacy + "']"));
        action.click(accountPrivacyOption);
    }

    /**
     * Capture project's name on dashboard.
     *
     * @return title text displayed on dashboard
     */
    public String getProjectNameOnDashboard() {
        action.waitPresenceOfElement(By.className("raw_context_name"));
        return titleOnDashboard.getAttribute("innerHTML");
    }

    /**
     * Method to return project's name from context.
     *
     * @return context value on project name
     */
    public String getProjectName() {
        return this.projectName;
    }

    /**
     * Method to keep project's name to context.
     *
     * @param strProjectName project name
     */
    public void setProjectName(final String strProjectName) {

        if (action.isExistingSelector(
                By.xpath("//button[@class='button button--lined button--medium button--full-width']"))) {
            action.click(showMoreProjectsButton);
        }
        this.projectName = strProjectName;
    }

    /**
     * Given a project name open settings section.
     *
     * @param projectName Name of project
     */
    public void openProjectSettingsbyName(final String projectName) {
        if (action.isExistingSelector(
                By.xpath("//button[@class='button button--lined button--medium button--full-width']"))) {
            action.click(showMoreProjectsButton);
        }

        final WebElement projectItem = driver.findElement(
                By.xpath("//a[@data-aid='project-name' and contains(text(),'" + projectName + "')]"));
        final String linkText = projectItem.getAttribute("pathname");
        final WebElement projectItemSettingElement = driver.findElement(
                By.xpath("//a[@aria-label='settings' and @href='" + linkText + "/settings']")
        );
        action.click(projectItemSettingElement);
    }

    /**
     * Delete through link option.
     */
    public void clickOnDeleteProjectLink() {
        action.scrollToElement(deleteLink);
        action.click(deleteLink);
    }

    /**
     * Cofirm deletion.
     */
    public void clickOnDeleteButton() {
        action.click(confirmDelete);
    }

    /**
     * To check if project is listed on main page.
     *
     * @return Boolean true if it is seeing on page
     */
    public boolean isProjectListedOnMainPage() {
        return action.isExistingSelector(
                By.xpath("//a[@data-aid='project-name' and contains(text(),'" + this.getProjectName() + "')]"));
    }

    /**
     * To check if project is listed on header menu.
     *
     * @return Boolean true if it is seeing on contextual menu
     */
    public boolean isProjectListedOnMenu() {
        return action.isExistingSelector(
                By.xpath("//span[@class='raw_project_name' and contains(text(),'" + this.getProjectName() + "')]"));
    }

    /**
     * To check if project is listed on header menu.
     *
     * @return Boolean true if it is seeing on contextual menu
     */
    public boolean isProjectListedOnProjectPage() {
        return action.isExistingSelector(
                By.xpath("//a[@class='project_name' and contains(text(),'" + this.getProjectName() + "')]"));
    }

    /**
     * Set values on form as specified.
     *
     * @param projectAttributes Attributes to set on form
     */
    public static void setValuesOnEditProjectForm(final Map<String, String> projectAttributes) {
        final HtmlForms editProjectForm = new EditProjectForm();
        editProjectForm.fillForm(projectAttributes);
    }

    /**
     * change check status if different.
     *
     * @param privacy between private and public
     */
    public void setEditProjectPrivacy(final String privacy) {
        if (editProjectPrivacy.isSelected() && "private".equals(privacy)) {
            action.click(editProjectPrivacy);
        }
    }

    /**
     * change check status if different.
     *
     * @param taskEnable between allow or disallow
     */
    public void setEditProjectTaskEnable(final String taskEnable) {
        if (editProjectPrivacy.isSelected() && "Enable".equals(taskEnable)) {
            action.click(editProjectEnableTask);
        }
    }

    /**
     * Proccess to change account.
     *
     * @param account account name
     */
    public void setEditProjectAccount(final String account) {
        action.click(editAccountLink);
        action.click(editAccountComboBox);

    }

    /**
     * Set value to description field.
     *
     * @param description the desired value
     */
    public void setEditProjectDescription(final String description) {
        action.setValue(editProjectDescriptionField, description);

    }

    /**
     * Set value to title field.
     *
     * @param title desired title
     */
    public void setEditProjectTitle(final String title) {
        action.setValue(editProjectNameField, title);
    }

    /**
     * Click to save button on edit project form.
     */
    public void saveFormOnEditProject() {
        action.click(saveButtonOnEditProject);
        driver.switchTo().alert().accept();
    }

    /**
     * validate message of success.
     *
     * @return Boolean if message was displayed
     */
    public boolean getResponseMessage() {
        return successBar.isDisplayed();

    }

    /**
     * Each scenario start on main page.
     */
    public void loadMainPage() {
        driver.get(Environment.getInstance().getValue("url.main"));
    }

    /**
     * Each scenario start on main page.
     *
     * @param section String to specific URI
     */
    public void loadMainPage(final String section) {
        driver.get(Environment.getInstance().getValue("url.main").concat(section));
    }
}
