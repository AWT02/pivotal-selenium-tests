package org.fundacionjala.pivotal.cucumber.steps.ui;

import cucumber.api.java.en.Given;
import org.fundacionjala.pivotal.pages.Project;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Map;

/**
 * Project steps.
 */
public class ProjectSteps {

    @Autowired
    private Project project;
    /**
     * Create a new project.
     *
     * @param projectAttributes for create project.
     */
    @Given("user creates a default project as")
    public void userCreatesNewProjectAs(final Map<String, String> projectAttributes) {
        project.createNewProject(projectAttributes);
    }
}
