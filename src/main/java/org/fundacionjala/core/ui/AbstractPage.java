package org.fundacionjala.core.ui;

import org.fundacionjala.core.ui.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Parent of Page Objects.
 */
public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverAction action;
    public static final int WAIT_TIME = 60;

    /**
     * Constructor of class.
     */
    protected AbstractPage() {
        this.driver = DriverManager.getInstance().getDriver();
        this.wait = DriverManager.getInstance().getWait();
        this.action = new WebDriverAction(this.driver, this.wait);
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Get parent element.
     * @param element web element.
     * @return parent web element.
     */
    public WebElement getParent(final WebElement element) {
        return element.findElement(By.xpath("./.."));
    }
}
