package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This is the story page with all the locators.
 */
@Component
public class Story extends AbstractPage {

    /**
     * This is the delete story button.
     */
    @FindBy(css = "button[title='Delete this story']")
    private WebElement deleteStoryButton;
    /**
     * This is the add story button.
     */
    @FindBy(css = "span[class='tracker_markup']")
    private List<WebElement> storyNames;
    /**
     * This is a list of the story checkboxes.
     * The ones that we are going to use for delete stories.
     */
    @FindBy(css = "a[title='Select this story for bulk actions']")
    private List<WebElement> storyCheckBoxes;


    /**
     * This is the add story button.
     */
    @FindBy(css = "a[data-aid='AddButton']")
    private WebElement addStoryButton;
    /**
     * This is the save button.
     */
    @FindBy(css = ".autosaves.button.std.save")
    private WebElement saveButton;
    /**
     * This is the cancel button.
     */
    //@FindBy(id = "story_submit_cancel_c205")
    //@FindBy(className = "autosaves cancel clear")
    @FindBy(css = ".autosaves.cancel.clear")
    private WebElement cancelButton;
    /**
     * This is the story page.
     */
    //@FindBy(name = "story[name]")
    @FindBy(css = "textarea[name='story[name]']")
    private WebElement storyName;
    /**
     * another locators for non required fields.
     */
    //@FindBy(id = "story_copy_id_value_c205")
    @FindBy(css = ".autosaves.id.text_value")
    private WebElement idStoryField;
    /**
     * another locators for non required fields.
     */
    //@FindBy(id = "story_type_dropdown_c517")
    @FindBy(css = ".selection.item_feature")
    private WebElement feature;
    /**
     * another locators for non required fields.
     */
    @FindBy(css = ".selection.item_1")
    private WebElement points;
    /**
     * another locators for non required fields.
     */
    @FindBy(css = ".name.hbsAvatarName")
    private WebElement requested;
    /**
     * another locators for non required fields.
     */
    @FindBy(how = How.XPATH, using = ".//*[@id='add_owner_c517']")
    private WebElement addStoryOwner;
    /**
     * another locators for non required fields.
     */
    @FindBy(xpath = ".//*[@id='blocker-edit-new']")
    private WebElement blockersTextField;
    /**
     * another locators for non required fields.
     */

    @FindBy(css = ".tracker_markup")
    private WebElement storyNameCreated;

    /**
     * Basic method with the minimum requerid for create a story.
     */
    public void clickAddButton() {
        this.action.click(this.addStoryButton);
    }

    /**
     * Basic method with the minimum requerid for create a story.
     */


    /**
     * Basic method with the minimum requerid for create a story.
     *
     * @param strStoryName of the story that you want to create
     */
    public void setStoryNameTextField(final String strStoryName) {
        this.action.setValue(this.storyName, strStoryName);
    }

    /**
     * Basic method with the minimum requerid for create a story.
     */
    public void clickSaveButton() {
        this.action.click(this.saveButton);
    }

    /**
     * Basic method with the minimum requerid for create a story.
     *
     * @param name of the story
     */
    public void createStory(final String name) {
        clickAddButton();
        setStoryNameTextField(name);
        clickSaveButton();
    }

    /**
     * this is an interesting method.
     *
     * @param text texto.
     * @return boolean true or false
     */
    public boolean existStory(final String text) {
        for (final WebElement element : this.storyNames) {
            if (element.getText().equals(text)) {
                return true;
            }
        }
        return false;
    }


    /**
     * click delete button.
     */
    public void clickDeleteButton() {
        this.action.click(this.deleteStoryButton);

    }
}
