package org.fundacionjala.pivotal.pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

/**
 * this is the header container.
 */
@Component
public class HeaderContainer extends AbstractPage {
    /**
     * this is the delete button that appears at the top of the page into a
     * toast.
     */
    @FindBy(css = "button[title='Delete selected stories']")
    private WebElement deleteStoryButtonOfToast;

    /**
     * this method clicks the delete button.
     */
    public void clickDeleteButtonOfToast() {
        this.action.click(this.deleteStoryButtonOfToast);
    }
}
