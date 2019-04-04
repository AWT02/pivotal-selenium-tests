package org.fundacionjala.pivotal.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

/**
 * this class represented a login page.
 */
@Component
public class Login extends AbstractPage {

    @FindBy(id = "credentials_username")
    private WebElement userNameTextField;

    @FindBy(css = ".app_signin_action_button")
    private WebElement nextButton;

    @FindBy(id = "credentials_password")
    private WebElement passwordTextField;

    @FindBy(css = ".app_signin_action_button")
    private WebElement loginButton;

    /**
     * This method add one value of username text field .
     *
     * @param strUserName value of input.
     */
    public void setUserNameTextField(final String strUserName) {
        action.setValue(userNameTextField, strUserName);
    }

    /**
     * Clicks the next button.
     */
    public void clickNextButton() {
        action.click(nextButton);
    }

    /**
     * This method add one value of password text field .
     *
     * @param strPassword value of input.
     */
    public void setPasswordTextField(final String strPassword) {
        action.setValue(passwordTextField, strPassword);
    }

    /**
     * Clicks the login button.
     */
    public void clickLoginButton() {
        action.click(loginButton);
    }

    /**
     * Login as any user.
     *
     * @param username value
     * @param pwd value
     */
    public void loginAs(final String username, final String pwd) {
        setUserNameTextField(username);
        clickNextButton();
        setPasswordTextField(pwd);
        clickLoginButton();
    }
}
