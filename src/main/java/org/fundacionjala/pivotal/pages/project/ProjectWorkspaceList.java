package org.fundacionjala.pivotal.pages.project;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

import org.fundacionjala.core.ui.AbstractPage;

/** This class represent workspace and project list page. **/
@Component
public class ProjectWorkspaceList extends AbstractPage {

    /**
     * To check if project is listed on header menu.
     *
     * @param name project name.
     * @return Boolean true if it is seeing on contextual menu
     */
    public boolean isProjectListedOnPage(final String name) {
        final String xpath =
                "//a[@class='project_name' and contains(text(),'%s')]";
        return this.action.isExistingSelector(
                By.xpath(String.format(xpath, name)));
    }

    /**
     * This method get the story amount of a project.
     *
     * @param projectName name of project.
     * @return story amount.
     */
    public String getAmountOfStories(final String projectName) {
        final String xpath = "//a[@class='project_name' and text()='"
                .concat(projectName)
                .concat("']/ancestor::li[contains(@class,'project_row')]")
                .concat("/div[@class='stories column']");
        return this.action.getValue(By.xpath(xpath));
    }
}
