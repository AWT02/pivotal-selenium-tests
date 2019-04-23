@bvt
Feature: Project

  Background:
    Given logs in with user "owner1"
    And Go to Dashboard

  Scenario: Create new project from Dashboard
    Given A create new button on dashboard
    When user creates project as
      | title   | at-01   |
      | account | one     |
      | privacy | private |
    Then validate creation on project's dashboard
    And validate creation on header project's list
    And validate creation on project's section

  Scenario: Create new project from header menu
    Given A create new button on header menu
    When user creates project as
      | title   | at-02 |
      | account | two   |
    Then validate creation on project's dashboard
    And validate creation on header project's list
    And validate creation on project's section

  Scenario: Create new project from project's section
    Given An option to create a new project on project's section
    When user creates project as
      | title   | at-03  |
      | account | three  |
      | privacy | public |
    Then validate creation on project's dashboard
    And validate creation on header project's list
    And validate creation on project's section

  Scenario: Edit project
    Given an existing project known as "at-00" that user intends to edit
    And change values on form as
      | title       | awt-00              |
      | description | For testing purpose |
      | account     | one                 |
      | taskEnable  | Enable              |
      | privacy     | public              |
    Then A successful message is displayed
    And Prior project's name no longer listed

  Scenario Outline: Delete a project
    Given an existing project named as "<name>" that user intends to delete
    And open project's settings
    When user click over delete project link
    Then The project no longer appear on projects section
    And the project is not present on active project
    Examples:
      | name  |
      | at-01 |
      | at-02 |
      | at-03 |

  Scenario Outline:Max two project assigned to a single member
    Given A create new button on dashboard
    When user creates project as
      | title   | <name>  |
      | account | <user>  |
      | privacy | private |
    Then validate the "<expected>" result on project account selection
    Examples:
      | name | user  | expected |
      | AT1  | David | Assigned |
      | AT2  | David | Assigned |
      | AT3  | David | Error    |

  Scenario Outline: No duplicate name of existing project name
    When user creates project as
      | name    | <name> |
      | account | <user> |
      | privacy | public |
    Then validate creation on project's dashboard
    And validate creation on header project's list
    And validate creation on project's section
    Examples:
      | name              | user  |
      | A weird Project   | one   |
      | A Weird project   | two   |
      | A  Weird  Project | three |
