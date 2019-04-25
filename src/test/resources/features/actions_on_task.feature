@wip
Feature: Task management

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
    And User Adds a task to current Story
      | title | Testing Task |

  Scenario: Edit title of task
    When User edit the task title
      | title | Edited Task |
    Then the old task should not be listed
    And The newly task should be listed
    And the task counter vary
      | operation | equal |
    And user deletes the task

  Scenario: Mark task as complete
    When User mark the task as complete
    Then the task should be colored
      | color | green |
    And the complete task counter vary
      | operation | increment |
    And user deletes the task

  Scenario: Mark task as incomplete
    Given User mark the task as complete
    When User mark the task as incomplete
    Then the task should be colored
      | color | default |
    And the complete task counter vary
      | operation | decrement |
    And user deletes the task

  Scenario: Delete a task
    When user deletes the task
    Then the old task should not be listed
    And the task counter vary
      | operation | decrement |

