Feature: Project

  Background:
    Given logs in with user "owner1"
    And goes to dashboard "Project"

  Scenario: Create new project from Dashboard
    Given clicks on create new project button
    When creates project as
      | name    | at-01   |
      | account | one     |
      | privacy | private |
    And stores datatable as "project_datatable"
    Then validates "project_datatable.name" name on project's header title
    And opens the popover from header title
    And verifies that "project_datatable.name" appears on "Projects" group list
    And goes to dashboard "Projects"
    And verifies that "project_datatable.name" appears on dashboard tab
    And opens the popover from header title
    And clicks show all projects
    And verifies that project "project_datatable.name" appears on project list
    And stores "project_id" that matches with "project_datatable.name" from "/projects"
    And sends a DELETE request "/projects/{project_id}"

  Scenario: Create new project from header menu
    Given clicks new button on header menu
    When creates project as
      | name    | at-02 |
      | account | two   |
    And stores datatable as "project_datatable"
    Then validates "project_datatable.name" name on project's header title
    And opens the popover from header title
    And verifies that "project_datatable.name" appears on "Projects" group list
    And goes to dashboard "Projects"
    And verifies that "project_datatable.name" appears on dashboard tab
    And opens the popover from header title
    And clicks show all projects
    And verifies that project "project_datatable.name" appears on project list
    And stores "project_id" that matches with "project_datatable.name" from "/projects"
    And sends a DELETE request "/projects/{project_id}"


  Scenario: Create new project from project's section
    Given An option to create a new project on project's section
    When creates project as
      | name    | at-03   |
      | account | three   |
      | privacy | private |
    And stores datatable as "project_datatable"
    Then validates "project_datatable.name" name on project's header title
    And opens the popover from header title
    And verifies that "project_datatable.name" appears on "Projects" group list
    And goes to dashboard "Projects"
    And verifies that "project_datatable.name" appears on dashboard tab
    And opens the popover from header title
    And clicks show all projects
    And verifies that project "project_datatable.name" appears on project list
    And stores "project_id" that matches with "project_datatable.name" from "/projects"
    And sends a DELETE request "/projects/{project_id}"
