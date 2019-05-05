package org.fundacionjala.pivotal.pages.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;
import org.fundacionjala.core.ui.forms.FormsElements;
import org.fundacionjala.pivotal.pages.ISteps;

/**
 * this class represented a login page.
 */
@Component
public class Project extends AbstractPage {

    private static final int W_TIME = 9000;

    @FindBy(css = ".tc-form__input")
    private WebElement projectNameField;

    @FindBy(css = ".tc-account-selector")
    private WebElement accountSelector;

    @FindBy(css = ".zWDds__Button.pvXpn__Button--positive")
    private WebElement createButton;

    @FindBy(css = ".tc-account-selector__create-account-icon")
    private WebElement createAccount;

    @FindBy(css = ".tc-account-creator__name")
    private WebElement newAccountField;

    @FindBy(linkText = "Delete")
    private WebElement deleteLink;

    @FindBy(css = "#confirm_delete")
    private WebElement confirmDelete;

    @FindBy(xpath = "//input[@name='commit' and @type='submit']")
    private WebElement saveButtonOnEditProject;

    @FindBy(css = "#save_success_bar")
    private WebElement successBar;

    @FindBy(css = ".tc_error_highlight")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[text()='+ Create Project']")
    private WebElement plusNewProjectOption;

    private String projectName;

    @FindBy(css = "a[data-aid='StoryPreviewItem__expander']")
    private List<WebElement> expandStoryButtons;


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
        openSelectAccountCombobox();
        final boolean onListAccount = action.isExistingSelector(
                By.xpath("//div[text()='" + accountName + "']"));

        if (!onListAccount) {
            action.click(createAccount);
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
        /**
         * This is a Workaround.
         * this need to be reviewed in the last selenium version. action.staleElement(backDrop);
         */
        this.action.click(createButton);
        action.pause(W_TIME);
    }

    /**
     * Create new project by given name.
     *
     * @param projectElements value
     */
    public void createNewProject(final Map<String, String> projectElements) {

        final Map<String, ISteps> strategy = new HashMap<>();
        strategy.put(FormsElements.NAME.toString(),
                () -> setProjectNameTextField(projectElements
                        .get(FormsElements.NAME.toString())));
        strategy.put(FormsElements.ACCOUNT.toString(),
                () -> selectAccount(projectElements
                        .get(FormsElements.ACCOUNT.toString())));
        strategy.put(FormsElements.PRIVACY.toString(),
                () -> selectProjectPrivacy(projectElements
                        .get(FormsElements.PRIVACY.toString())));

        projectElements.keySet()
                .forEach(key -> strategy.get(key).perform());
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
        this.projectName = strProjectName;
    }

    /**
     * Delete through link option.
     */
    public void clickOnDeleteProjectLink() {
        action.scrollToElement(deleteLink);
        action.click(deleteLink);
    }

    /**
     * Confirm deletion.
     */
    public void clickOnDeleteButton() {
        action.click(confirmDelete);
    }

    /**
     * Click to save button on edit project form.
     */
    public void saveFormOnEditProject() {
        action.click(saveButtonOnEditProject);
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
     * This method represent expand story action on first story find.
     **/
    public void expandOneStory() {
        if (!this.expandStoryButtons.isEmpty()) {
            this.action.click(this.expandStoryButtons.get(0));
        }
    }

    /**
     * Capture message.
     *
     * @return String with message
     */
    public String getMessageOnNewProjectForm() {
        action.waitVisibility(errorMessage);
        return errorMessage.getText();
    }

    /**
     * Click through plus new project option.
     */
    public void clickCreateNewPRojectOption() {
        action.click(plusNewProjectOption);
    }
}
