Feature: Event verification

  Scenario: The user views the event marked as verified in the event list
    Given the user is in the event list
    When viewing the status of each event
    Then the system indicates Verified

  Scenario: The user views the event marked as unverified in the event list
    Given Since the user is in the event list
    When you view the status of each event you choose
    Then the system indicates Not Verified