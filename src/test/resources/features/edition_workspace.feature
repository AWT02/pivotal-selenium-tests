@wip
Feature: edition of workspaces

  Background:
    Given sends a POST request "/workspaces"
      | title | original workspace |
    And stores response as "workspace_response"
    And logs in with user "owner1"
    And Go to Dashboard

  Scenario: workspace can be edited from projects section.

    Given the settings workspace page from projects section
    When the user edit attributes from the workspace
      | title | edited workspace |
    Then validate new attributes on the workspace
    And sends a DELETE request "/workspace/{workspace_response.id}"


  Scenario: workspace can be edited from workspace dashboard.

    Given the settings workspace page from projects section
    When the user edit attributes from the workspace
      | title | edited workspace |
    Then validate new attributes on the workspace
    And sends a DELETE request "/workspace/{workspace_response.id}"
