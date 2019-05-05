package org.fundacionjala.pivotal.pages.workspace;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

/**
 * This class represent page to create new workspace.
 */
@Component
public class WorkSpaceNew extends AbstractPage {

    @FindBy(xpath = "//span[contains(text(),'Workspaces')]")
    private WebElement dashboardLink;

    @FindBy(css = "#create-workspace-button")
    private WebElement createWorkSpaceButton;

    @FindBy(css = ".tc-form__input")
    private WebElement textBoxname;

    @FindBy(css = ".zWDds__Button.pvXpn__Button--positive")
    private WebElement createButton;

    @FindBy(css = ".raw_context_name")
    private WebElement labelWorkSpaceName;


    /**
     * Clicks the next button.
     */
    public void clickDashboardLink() {
        this.action.click(this.dashboardLink);
    }

    /**
     * this method represent click action on create button.
     */
    public void clickCreateWorkSpaceButton() {
        this.action.click(this.createWorkSpaceButton);
    }

    /**
     * this method write a name in the text box of workspace name.
     *
     * @param strname string
     */
    public void setName(final String strname) {
        this.action.setValue(this.textBoxname, strname);
    }

    /**
     * This method represent a click action on create button.
     */
    public void clickCreateButton() {
        this.action.click(this.createButton);
    }

    /**
     * This method get the workspace label.
     *
     * @return string
     */
    public String getWorkSpaceLabel() {
        return this.labelWorkSpaceName.getText();
    }

}
