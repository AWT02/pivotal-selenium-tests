@wip
Feature: Story

  Background:
    Given sends a POST request "/projects"
      | name | a1 |
    And stores response as "project_response"

  Scenario: create a story with the minimum required field
    Given logs in with user "owner1"
    And Go to Dashboard
    And opens a project "project_response.name"
    When creates a story called "carlos test"
    Then verify the story is created
    And sends a DELETE request "/projects/{project_response.id}"

