@wip
Feature: Deletion of workspaces

  Background:
    Given sends a POST request "/workspaces"
      | title | trash workspace |
    And stores response as "workspace_response"
    And logs in with user "owner1"
    And Go to Dashboard
      | tab | workspace |

  Scenario: Workspace can be deleted from projects section.

    Given the settings workspace page from projects section
      | title | workspace_response.name |
    When the user clicks on delete this workspace link
    Then validate deletion on workspace dashboard
    And validate deletion on header workspaces list
    And validate deletion on workspaces section

  Scenario: Workspace can be deleted from its dashboard.

    Given the settings workspace page from its dashboard
      | title | workspace_response.name |
    When the user clicks on delete this workspace link
    Then validate deletion on workspace dashboard
    And validate deletion on header workspaces list
    And validate deletion on workspaces section
