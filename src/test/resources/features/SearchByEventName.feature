Feature: Search by event name

  Scenario: The user enters the event name they want to search for in the search bar
    Given exist a event with name "comic" im a list of events
    When the user search the event with name "comic"
    Then the result is the event with name "comic"

  Scenario: The user does not enter a name in the search
    Given exist a event with name "comic" im a list of events
    When the user search the event without to insert a name
    Then a list of all events is displayed

  Scenario: User enters name in search bar that has no matches
    Given the user wants to search for an event by name
    When enter the event name "asd"
    And there are no matches with any system event
    Then the message "No result found" is displayed