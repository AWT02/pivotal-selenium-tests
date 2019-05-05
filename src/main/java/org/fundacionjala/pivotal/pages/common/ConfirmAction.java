package org.fundacionjala.pivotal.pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

/**
 * this class represented a login page.
 */
@Component
public class ConfirmAction extends AbstractPage {

    @FindBy(css = "#confirm_delete")
    private WebElement confirmDelete;

    @FindBy(css = "#confirm_archive")
    private WebElement confirmArchive;

    @FindBy(css = ".SMkCk__Button.SSqkh__Button--warning")
    private WebElement confirmDeleteStory;

    @FindBy(css = ".SMkCk__Button.ibMWB__Button--open")
    private WebElement cancelDeleteStory;

    /**
     * Confirm deletion projects and workspaces.
     */
    public void clickOnDeleteButton() {
        action.click(confirmDelete);
    }

    /**
     * Confirm archive projects and workspaces.
     */
    public void clickOnArchiveButton() {
        action.click(confirmArchive);
    }


    /**
     * Confirm delete Story.
     */
    public void confirmDeleteStoryButton() {
        action.click(confirmDeleteStory);
    }

    /**
     * Cancel delete Story.
     */
    public void cancelDeleteStoryButton() {
        action.click(cancelDeleteStory);
    }

}
