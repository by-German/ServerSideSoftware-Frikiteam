Feature: Rate An Event

  Scenario: Rate an event that has already ended in the rated section
    Given the geek user wants to rate and event and it has ended
    When fill in the qualification data requested
    Then the system records the rating provided by the user