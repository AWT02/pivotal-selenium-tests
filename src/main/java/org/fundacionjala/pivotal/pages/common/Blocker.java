package org.fundacionjala.pivotal.pages.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

/** This class represents the blocker panel located at story page. */
@Component
public class Blocker extends AbstractPage {
    @FindBy(css = "div[data-aid='BlockerAdd']")
    private WebElement addBlocker;

    @FindBy(css = "#blocker-edit-new")
    private WebElement blockersTextField;

    @FindBy(css = "button[data-aid='BlockerEdit__addButton']")
    private WebElement addBlockerConfirm;
}
