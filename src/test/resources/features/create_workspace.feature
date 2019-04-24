@wip
Feature: Creating workspaces

  Background:
    Given logs in with user "owner1"
    And Go to Dashboard

  Scenario: Create new workspace from dashboard
    Given a create workspace button on workspace tab
    When user creates a workspace
      | title | from bab workspace |
    Then validate creation on workspace dashboard
    And validate creation on header workspace's list
    And validate creation on workspace's section
    And Set "workspace_response.id"
    And sends a DELETE request "/workspace/{workspace_response.id}"


  Scenario: Create new workspace from projects section
    Given a create workspace link on projects section
    When user creates a workspace
      | title | from projects workspace |
    Then validate creation on workspace dashboard
    And validate creation on header workspace's list
    And validate creation on workspace's section
    And Set "workspace_response.id"
    And sends a DELETE request "/workspace/{workspace_response.id}"

  Scenario: Create new workspace from header menu section
    Given a create workspace link on header menu section
    When user creates a workspace"
      | title | from projects workspace |
    Then validate creation on workspace dashboard
    And validate creation on header workspace's list
    And validate creation on workspace's section
    And Set "workspace_response.id"
    And sends a DELETE request "/workspace/{workspace_response.id}"
