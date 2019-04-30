Feature: Edit and delete a Project

  Background:
    Given sends a POST request "/projects" with json
    """
    {"name":"a1"}
    """
    And stores response as "project_response"
    And logs in with user "owner1"
    And goes to Dashboard "project"

  Scenario: Delete a project
    Given opens a "project_response.name" settings
    When clicks delete project link
    Then verifies that project "a1" doesn't appear on dashboard
    And opens header menu
    And selects show all projects
    And verifies that project "a1" doesn't appear on project list

  Scenario: Edit a project
    Given opens a "project_response.name" settings
    And change values on form as
      | name        | awt-00              |
      | description | For testing purpose |
      | taskenable  | Disable             |
    Then A successful message is displayed
    And Previous project's name no longer listed
    And verifies that project "awt-00" doesn't appear on dashboard
    And opens header menu
    And selects show all projects
    And verifies that project "awt-00" doesn't appear on project list
    And sends a DELETE request "/projects/{project_response.id}"
