package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

/** This class represents memembers tab on projects. **/
@Component
public class ProjectMemebers extends AbstractPage {
    @FindBy(css = ".button.button--primary.button--medium")
    private WebElement invitePeopleButton;


}
