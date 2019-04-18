@wip
Feature: Story

  Background:
    Given sends a POST request "/projects"
      | name | a1 |
    And stores response as "project_response"

  Scenario: Create a story
    Given logs in with user "owner1"
    And Go to Dashboard
    And opens a project "project_response.name"
    When creates a story called "carlos test"
    Then verify the story is created
    And send a DELETE request "/projects/{project_response.id}"








#    Given a project called 'awt-02'
#    When click on the project link
#    And click on add Story
#    And creates a default story as
#      | NAME        | awt-02-story-01  |
#      | DESCRIPTION | Test             |
#
#    Then the story is created
#    And it appear listed
