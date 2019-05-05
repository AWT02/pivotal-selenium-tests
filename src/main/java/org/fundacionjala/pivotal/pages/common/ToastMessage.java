package org.fundacionjala.pivotal.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

/**
 * Represent all message toast that appear when an action was taken.
 */
@Component
public class ToastMessage extends AbstractPage {

    /**
     * Method to check the visibility of a message.
     *
     * @param message String
     * @return boolean
     */
    public boolean checkVisibilityOfMessage(final String message) {
        final WebElement textOnScreen = driver.findElement(By.xpath("//*[text()=\"" + message + "\"]"));
        return textOnScreen.getAttribute("innerHTML").equals(message);
    }

}
