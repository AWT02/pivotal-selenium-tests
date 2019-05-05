package org.fundacionjala.pivotal.pages.common;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;
import org.fundacionjala.core.util.Environment;
import org.fundacionjala.pivotal.pages.workspace.WorkSpaceSettings;

/**
 * Dashboard page.
 **/
@Component
public class Dashboard extends AbstractPage {
    private static final Logger LOGGER =
            Logger.getLogger(Dashboard.class.getName());
    private static final String PROJECTXPATH =
            "//a[@data-aid='project-name' and contains(text(),'%s')]";
    @FindBys({
            @FindBy(css = ".Dashboard__Tabs__tab"),
            @FindBy(xpath = "//span[text()='Projects']")
    })
    private WebElement projects;
    @FindBys({
            @FindBy(css = ".Dashboard__Tabs__tab"),
            @FindBy(xpath = "//span[text()='Workspaces']")
    })
    private WebElement workspaces;
    @FindBy(css = "#create-project-button")
    private WebElement createProject;
    @FindBy(css = "#create-workspace-button")
    private WebElement createWorkSpace;
    @FindBy(css = "#projects-search-bar")
    private WebElement searchProject;
    @FindBy(css = ".WorkspaceTile__name")
    private List<WebElement> worksSpaceNames;
    @FindBy(css = ".projectPaneSection__header__heading--count")
    private WebElement amountOfProjects;
    @FindBy(css = ".projectTileHeader__projectName")
    private List<WebElement> projectNames;
    @FindBy(css = "button[data-aid='show-more-projects-button']")
    private WebElement showMoreProjects;
    @FindBy(css = "#twitter_link")
    private WebElement twitterLink;
    @FindBy(css = "#projects-search-bar")
    private WebElement searchInput;

    /**
     * Create project.
     **/
    public void createProjectButton() {
        this.action.click(this.createProject);
    }

    /**
     * Create workspace.
     **/
    public void createWorkSpaceButton() {
        this.action.click(this.createWorkSpace);
    }

    /**
     * Go to work space tab.
     **/
    public void goToWorkSpaceTab() {
        this.action.click(this.workspaces);
        this.wait.until(ExpectedConditions.visibilityOf(this.createWorkSpace));
    }

    /**
     * Go to project tab.
     **/
    public void goToProjectTab() {
        this.action.click(this.projects);
        this.wait.until(ExpectedConditions.visibilityOf(this.createProject));
    }

    /**
     * Check if exist a workspace by name.
     *
     * @param name string to search.
     * @return boolean.
     **/
    public boolean existWorkSpace(final String name) {
        return hasElementWithName(this.worksSpaceNames, name);
    }

    /**
     * Check if exist a project by name.
     *
     * @param name string to search.
     * @return boolean
     **/
    public boolean existProject(final String name) {
        if (this.action.isExistingSelector(By
                .cssSelector("button[data-aid='show-more-projects-button']"))) {
            this.action.click(this.showMoreProjects);
        }
        return this.action.isExistingSelector(
                By.xpath(String.format(PROJECTXPATH, name)));
    }

    /**
     * Check if exist and element in the list with name.
     *
     * @param list list where search.
     * @param name name to search
     * @return boolean.
     **/
    private static boolean hasElementWithName(final List<WebElement> list,
                                              final String name) {
        for (final WebElement element : list) {
            if (element.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Go to project with name.
     *
     * @param name string project name.
     */
    public void goToProject(final String name) {
        this.searchInput.sendKeys(name);
        this.action.scrollToElement(this.twitterLink);
        this.action.click(By.xpath(String.format(PROJECTXPATH, name)));
        this.action.waitPresenceOfElement(
                By.cssSelector("a[data-aid='navTab-stories']"));
    }

    /**
     * Go to workspace with name.
     *
     * @param name string workspace.
     */
    public void goToWorkspace(final String name) {
        try {
            final String xpath = "//a[@class='WorkspaceTile__name' and "
                    .concat("contains(text(),'%s')]");
            this.action.click(By.xpath(String.format(xpath, name)));
        } catch (final NoSuchElementException e) {
            LOGGER.warn("The Workspace web element was not found ", e);
        }
    }

    /**
     * Get amount of projects.
     *
     * @return int amount.
     */
    public int getAmountOfProjects() {
        return Integer.valueOf(this.amountOfProjects.getText());
    }

    /**
     * This method click on settings workspace with name provided.
     *
     * @param name string workspace name.
     * @return WorkSpaceSettings.
     */
    public WorkSpaceSettings clickWorkSpaceSettings(final String name) {
        for (final WebElement element : this.worksSpaceNames) {
            if (element.getText().equals(name)) {
                getParent(getParent(element))
                        .findElement(By.xpath("//div//span//a[contains(@class,"
                                .concat("'SettingsIcon__cog projectTileHeader")
                                .concat("__hoverable')]")))
                        .click();
                return new WorkSpaceSettings();
            }
        }
        throw new NoSuchElementException(String
                .format("The work space with name %s was not find", name));
    }

    /**
     * Given a project name open settings section.
     *
     * @param projectName Name of project
     */
    public void openProjectSettings(final String projectName) {
        if (this.action.isExistingSelector(By
                .cssSelector("button[data-aid='show-more-projects-button']"))) {
            this.action.click(this.showMoreProjects);
        }

        final String linkText = this.action.getAttribute(
                By.xpath(String.format(PROJECTXPATH, projectName)),
                "pathname");
        this.action.click(By.xpath("//a[@aria-label='settings' and @href='"
                .concat(linkText).concat("/settings']")));
    }

    /**
     * This method reload dashboard page.
     **/
    public void reload() {
        this.driver.get(Environment.getInstance().getValue("url.base")
                .concat("/dashboard"));
        this.wait.until(ExpectedConditions
                .visibilityOfAllElements(this.projects, this.workspaces));
    }

}
