Feature: Search by event name

  Scenario: The user enters the event name they want to search for in the search bar
    Given exist a event with name "comic" im a list of events
    When the user search the event with name "comic"
    Then the result is the event with name "comic"

  Scenario: The user does not enter a name in the search.
    Given the user wants to search for an event by name
    When do not enter any name or text of the event
    Then a list of the default events is displayed.