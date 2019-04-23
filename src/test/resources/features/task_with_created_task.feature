@wip
Feature: Task management

  Background:
    Given logs in with user "owner1"
    And Project with one story
    And Go to Dashboard
    And Go to default project
    And Open a story
    And Create a task with text "task to modify"

  Scenario: Modify a task
    When Modify text by "task2"
    Then Verify that task "task to modify" doesn't exist
    And Task with name "task2" exist

  Scenario: Delete a task
    When Delete task
    Then Verify that task "task to modify" doesn't exist
  