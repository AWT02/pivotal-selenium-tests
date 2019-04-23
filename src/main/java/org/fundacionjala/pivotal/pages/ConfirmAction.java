package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/**
 * this class represented a login page.
 */
@Component
public class ConfirmAction extends AbstractPage {

    @FindBy(css = "#delete_link")
    private WebElement deleteLink;

    @FindBy(css = "#archive_link")
    private WebElement archiveLink;

    @FindBy(css = "#confirm_delete")
    private WebElement confirmDelete;

    @FindBy(css = "#confirm_archive")
    private WebElement confirmArchive;

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
     * Delete through link option.
     */
    public void clickOnArchiveProjectLink() {
        action.scrollToElement(archiveLink);
        action.click(archiveLink);
    }

    /**
     * Confirm archive.
     */
    public void clickOnArchiveButton() {
        action.click(confirmArchive);
    }


}
