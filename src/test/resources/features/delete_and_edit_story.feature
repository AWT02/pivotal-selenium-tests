Feature: Story feature allow edition and delete.
  This feature file represent all possible scenarios over story edition and
  story delete.

  Background:
    Given sends a POST request "/projects"
      | name | a1 |
    And stores response as "project_response"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | story1 |
      |
    And stores response as "story_response"
    And logs in with user "owner1"
    And goes to dashboard "project"
    And opens a project "project_response.name"

  Scenario: delete a story using delete button inside story page
    When expands the story "story1"
    And clicks delete button
    And clicks confirm delete button
    Then verifies that the stories deleted are not present on panel
    And opens the popover from header title
    And clicks show all projects
    And verifies the story count for project "project_response.name" is equal "0" in  project list

  @wip
  Scenario: edit a story
    Given