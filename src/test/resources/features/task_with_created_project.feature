@wip
Feature: Task management

  Background:
    Given logs in with user "owner1"
    And Project with one story
    And Go to Dashboard
    And Go to default project
    And Open a story

  Scenario: Create a task
    When Create a task with text "task1"
    Then Verify that task was created
