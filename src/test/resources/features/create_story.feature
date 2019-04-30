Feature: Story
  This feature file contains scenarios to create story.

  Background:
    Given sends a POST request "/projects"
      | name | a1 |
    And stores response as "project_response"
    And logs in with user "owner1"

  Scenario: creates a story with the minimum required field
    When goes to Dashboard "project"
    And opens a project "project_response.name"
    And clicks on add story button
    And creates a story with:
      | name | carlos test |
    Then verifies the story is created in panel
    And expands the story "carlos test"
    And verifies the story is created in story
      | name            | carlos test       |
      | storyType       | Feature           |
      | estimatedPoints | Unestimated       |
      | requester       | owner1            |
      | owners          | <none>            |
      | description     | Add a description |
    And opens header menu
    And selects show all projects
    And verifies the story count for project "project_response.name" is equal "1" in  project list

  Scenario: creates a story with the all possible fields
    And goes to Dashboard "project"
    And opens a project "project_response.name"
    When clicks on add story button
    And creates a story with:
      | name            | new story         |
      | storyType       | feature           |
      | estimatedPoints | 2 points          |
      | requester       | owner1            |
      | description     | story description |
    Then verifies the story is created in panel
    And expands the story "new story"
    And verifies the story is created in story
      | name            | new story         |
      | storyType       | Feature           |
      | estimatedPoints | 2 Points          |
      | requester       | owner1            |
      | owners          | <none>            |
      | description     | story description |
    And opens header menu
    And selects show all projects
    And verifies the story count for project "project_response.name" is equal "1" in  project list
