Feature: Story second part
  Test cases with common background.

  Background:
    Given sends a POST request "/projects"
      | name | a1 |
    And stores response as "project_response"
    And sends a POST request "/projects/{project_response.id}/stories"
      | name | story1 |
    And stores response as "story_response"

  Scenario: delete a story using delete button inside story page
    Given logs in with user "owner1"
    And Go to Dashboard
    And opens a project "project_response.name"
    #expands the story "story_response.name"
    When expands the story "story_response.name"
    And click delete button
    And click confirm delete button
    Then Verify that the story "story_response.name" is deleted
    And sends a DELETE request "/projects/{project_response.id}"


  Scenario: delete a story selecting bulk
    Given Go to Dashboard
    And opens a project "project_response.name"
    When selects the bulk of "story_response.name"
    And click delete button of Header container
    And click confirm delete button
    Then Verify that the story "story_response.name" is deleted
    And sends a DELETE request "/projects/{project_response.id}"