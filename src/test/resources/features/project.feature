@bvt
Feature: Project

  Background:
    Given User is located on main page

  Scenario: Create new project
    When user creates project as
      | name    | awt-00 |
      | account | aleri  |
      | privacy | public |
    Then validate creation on project's dashboard
    #And validate creation on header project's list
    And validate creation on project's section

  Scenario: Edit project
    Given an existing project known as "awt-00" that user intends to edit
    And change values on form as
      | title       | AT8                 |
      | description | For testing purpose |
      | account     | david               |
      | taskEnable  | Enable              |
      | privacy     | public              |
    Then A successful message is displayed
    And Prior project's name no longer listed
    And New project's name contain attributes edited


  Scenario Outline: Delete a project
    Given an existing project named as "<name>" that user intends to delete
    And open project's settings
    When user click over delete project link
    Then The project no longer appear on projects section
    And the project is not present on active project
    Examples:
      | name            | user  |
      | Weird ñ Project | Pablo |

  Scenario Outline: Max two project assigned per account
    When user creates project as
      | name    | <name>  |
      | account | <user>  |
      | privacy | private |
    Then validate project first two assigned
    And validate third assigment launch error message
    Examples:
      | name | user  |
      | AT1  | Diego |
      | AT2  | Diego |
      | AT3  | Diego |

  Scenario Outline: No duplicate name of existing project name
    When user creates project as
      | name    | <name> |
      | account | <user> |
      | privacy | public |
    Then validate error message is displayed
    Examples:
      | name              | user  |
      | A weird Project   | Diego |
      | A Weird Project   | Pedro |
      | A  Weird Project  | Pedro |
      | A  weird  Project | Pablo |
      | A Wéird Project   | Pablo |


