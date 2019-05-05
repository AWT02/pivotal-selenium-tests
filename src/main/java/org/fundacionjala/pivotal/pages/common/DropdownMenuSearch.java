package org.fundacionjala.pivotal.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

/** This class represent a dorpdown search menu. **/
@Component
public class DropdownMenuSearch extends AbstractPage {

    @FindBy(xpath = "//div[@class='dropdown_menu search']/div/input")
    private WebElement inputSearch;

    /**
     * This method select and option in the dropdown menu.
     *
     * @param text option in dropdown.
     */
    public void selectItem(final String text) {
        final String xpath = "//div[@class='dropdown_menu search']/ul/li/a/"
                .concat("span[text()='").concat(text).concat("']/parent::a");
        this.action.click(By.xpath(xpath));
    }
}
