package org.fundacionjala.pivotal.pages.project;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

/**
 * This is page for create accounts inside Project page options.
 */
@Component
public class CreateAccount extends AbstractPage {
    /**
     * this is a text fiel for the input of a new account name.
     */
    @FindBy(css = "input[class='tc-account-creator__name']")
    private WebElement newAccountNameTextField;

    /**
     * This method is used for set the text field.
     *
     * @param accountNameTextField is the new of the account.
     */
    public void setNewAccountNameTextField(final String accountNameTextField) {
        this.action.setValue(this.newAccountNameTextField,
                accountNameTextField);

    }
}
