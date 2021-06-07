Feature: Get description of an event

  Scenario: Users get into event list section
    Given the user wants to view the description of the event
    When choose one of the events
    Then basic information about the event is appreciated