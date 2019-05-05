package org.fundacionjala.pivotal.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

/**
 * This class represent the menu opened on header when you click over.
 * project/workspace title.
 **/
@Component
public class HeaderMenu extends AbstractPage {
    @FindBy(css = "a[href='/projects']")
    private WebElement showAllProjectsWorkSpaces;

    @FindBy(css = "a[href='/dashboard'][class='tc_projects_menu_footer']")
    private WebElement goToDashboard;

    @FindBy(css = "a[data-aid='CreateProject']")
    private WebElement createProject;

    @FindBy(xpath = "//a[text()='Create Workspace']")
    private WebElement createWorkspace;

    /**
     * This method click on showAllProject/showAllWorkspaces.
     **/
    public void showAllProjectsWorkSpaces() {
        this.action.click(this.showAllProjectsWorkSpaces);
    }

    /**
     * To check if project is listed on header menu.
     *
     * @param name Project name.
     * @return Boolean true if it is seeing on contextual menu
     */
    public boolean isProjectListedOnMenu(final String name) {
        final String xpath =
                "//span[@class='raw_project_name' and contains(text(),'%s')]";
        return this.action.isExistingSelector(
                By.xpath(String.format(xpath, name)));
    }

    /**
     * To check if project is listed on header menu.
     *
     * @param name Workspace name.
     * @return Boolean true if it is seeing on contextual menu
     */
    public boolean isWorkspaceListedOnMenu(final String name) {
        final String xpath =
                "//span[@class='raw_workspace_name' and contains(text(),'%s')]";
        return this.action.isExistingSelector(
                By.xpath(String.format(xpath, name)));
    }

    /**
     * This method click on dashboard option menu.
     *
     * @return Dashboard page
     */
    public Dashboard goToDashboard() {
        this.action.click(this.goToDashboard);
        this.wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Projects']")));
        return new Dashboard();
    }

    /**
     * This method click on create Project option menu.
     **/
    public void createProject() {
        this.action.click(this.createProject);
    }

    /**
     * This method click on create workspace option menu.
     **/
    public void createWorkspace() {
        this.action.click(this.createWorkspace);
    }

    /**
     * This method open a workspace.
     *
     * @param name workspace name.
     */
    public void openWorkspace(final String name) {
        this.action.click(By.xpath("//span[text()='".concat(name)
                .concat("' and @class='raw_workspace_name']/parent::a")));
    }

    /**
     * This method open a project.
     *
     * @param name project name.
     */
    public void openProject(final String name) {
        this.action.click(By.xpath("//span[text()='".concat(name)
                .concat("' and @class='raw_project_name']/parent::a")));
    }
}
