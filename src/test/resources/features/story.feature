@wip
Feature: Story

  Scenario:
    Given a project called 'awt-02'
    When click on the project link
    And click on add Story
    And creates a default story as
      | NAME        | awt-02-story-01  |
      | DESCRIPTION | Test             |

    Then the story is created
    And it appear listed
