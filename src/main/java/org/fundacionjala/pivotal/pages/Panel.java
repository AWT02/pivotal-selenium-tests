package org.fundacionjala.pivotal.pages;

import org.fundacionjala.core.ui.AbstractPage;
import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

/**
 * This is a page located between the dashboard and the story page.
 */
@Component
public class Panel extends AbstractPage {
    /**
     * click dropdown story button..
     *
     * @param text name of the history
     */
    public void expandStory(final String text) {
        final String xpathExpression = "//span[@data-aid=\"StoryPreviewItem__title\" "
                .concat("and text()='").concat(text)
                .concat("']/ancestor::header/child::button");
        action.click(By.xpath(xpathExpression));
    }

    /**
     * select an specific bulk.
     *
     * @param text is the name of a speceif story .
     */
    public void clickStoryCheckboxButton(final String text) {
        final String xpathExpression = "//span[@data-aid=\"StoryPreviewItem__title\" "
                .concat("and text()='").concat(text)
                .concat("']/ancestor::header/child::a");
        action.click(By.xpath(xpathExpression));

    }
}



