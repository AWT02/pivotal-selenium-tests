@wip
Feature: Add task test

  Background:
    Given sends a POST request "/projects"
      | title | task project |
    And stores response as "project_response"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | task story |
    And stores response as "story_response"
    And logs in with user "owner1"
    And Go to Dashboard
      | tab | project |
    And Open the project
      | title | project_response.name |
    And Open the story
      | title | story_response.name |

  Scenario: Create a task on existing Story
    When User Adds a task to current Story
      | title | First Task |
    And The newly task should be listed
    And task counter vary
      | operation | increment |
    And user deletes the task
