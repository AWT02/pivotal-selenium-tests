@wip
Feature: Task mangement

  Background:
  Given Project with one story

  Scenario: Create a task
  When Create a task with text "task1"
  Then Verify that task was created

  Scenario: Modify a task
  Given Create a task with text "task to rename"
  When Modify text by "task2"
  Then Verify that task "task to rename" doesn't exist
  And Task with name "task2" exist
  
  Scenario: Delete a task
  Given Create a task with text "task to delete"
  When Delete task
  Then Verify that task "task to delete" doesn't exist
  