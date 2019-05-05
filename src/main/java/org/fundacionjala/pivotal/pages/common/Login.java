package org.fundacionjala.pivotal.pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;
import org.fundacionjala.core.util.ScenarioContext;

/**
 * this class represented a login page.
 */
@Component
public class Login extends AbstractPage {

    @FindBy(css = "#credentials_username")
    private WebElement userNameTextField;

    @FindBy(css = ".app_signin_action_button")
    private WebElement nextButton;

    @FindBy(css = "#credentials_password")
    private WebElement passwordTextField;

    @FindBy(css = ".app_signin_action_button")
    private WebElement loginButton;

    /**
     * This method add one value of username text field .
     *
     * @param strUserName value of input.
     */
    public void setUserNameTextField(final String strUserName) {
        this.action.setValue(this.userNameTextField, strUserName);
    }

    /**
     * Clicks the next button.
     */
    public void clickNextButton() {
        this.action.click(this.nextButton);
    }

    /**
     * This method add one value of password text field .
     *
     * @param strPassword value of input.
     */
    public void setPasswordTextField(final String strPassword) {
        this.action.setValue(this.passwordTextField, strPassword);
    }

    /**
     * Clicks the login button.
     */
    public void clickLoginButton() {
        this.action.click(this.loginButton);
    }

    /**
     * Login as any user.
     *
     * @param username value
     * @param pwd      value
     */
    public void loginAs(final String username, final String pwd) {
        if (!ScenarioContext.getInstance().has("login_user", username)) {
            setUserNameTextField(username);
            clickNextButton();
            setPasswordTextField(pwd);
            clickLoginButton();
            ScenarioContext.getInstance().setContext("login_user", username);
        }
    }
}
