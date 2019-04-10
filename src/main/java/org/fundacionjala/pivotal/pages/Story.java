package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * This is the story page.
 */
public class Story extends AbstractPage {
    /**
     * This is the add story button.
     */
    @FindBy(id = "panel_backlog_2324243")
    private WebElement addStoryButton;
    /**
     * This is the save button.
     */
    @FindBy(id = "story_close_c205")
    private WebElement saveButton;
    /**
     * This is the cancel button.
     */
    @FindBy(id = "story_submit_cancel_c205")
    private WebElement cancelButton;
    /**
     * This is the story page.
     */
    @FindBy(name = "story[name]")
    private WebElement storyName;
    /**
     * another locators for non required fields.
     */
    @FindBy(id = "story_copy_id_value_c205")
    private WebElement idStoryField;
    /**
     * another locators for non required fields.
     */
    //*[@id="story_type_dropdown_c517"]/span
    @FindBy(id = "story_type_dropdown_c517")
    private WebElement feature;
    /**
     * another locators for non required fields.
     */
    @FindBy(id = "story_estimate_dropdown_c517")
    private WebElement points;
    /**
     * another locators for non required fields.
     */
    @FindBy(id = "story_requested_by_id_dropdown_c517")
    private WebElement requested;
    /**
     * another locators for non required fields.
     */
    @FindBy(how = How.XPATH, using = ".//*[@id=\"add_owner_c517\"]")
    private WebElement addStoryOwner;
    /**
     * another locators for non required fields.
     */
    @FindBy(xpath = ".//*[@id=\"blocker-edit-new\"]")
    private WebElement blockersTextField;
    /**
     * another locators for non required fields.
     */
    @FindBy(className = "BlockerEdit__add___z26dSKQ8")
    private WebElement addblockersButton;


    /**
     * Basic method with the minimum requerid for create a story.
     */
    public void clickAddButton() {
        action.click(addStoryButton);
    }
    /**
     * Basic method with the minimum requerid for create a story.
     * @param strStoryName of the story that you want to create
     */
    public void setStoryNameTextField(final String strStoryName) {
        action.setValue(storyName, strStoryName);
    }
    /**
     * Basic method with the minimum requerid for create a story.
     */
    public void clickSaveButton() {
        action.click(saveButton);
    }
    /**
     * Basic method with the minimum requerid for create a story.
     * @param  name of the story
     */
    public void createStory(final String name) {
        clickAddButton();
        setStoryNameTextField(name);
        clickSaveButton();
    }

}
