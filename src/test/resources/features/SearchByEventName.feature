Feature: Search by event name

  Scenario: The user enters the event name they want to search for in the search bar
    Given exist a event with name "comic" im a list of events
    When the user search the event with name "comic"
    Then the event with the searched name is displayed