Feature: User explores London

  Scenario: A user can top up and check balance
  As a user of the Oyster Card system
    When I load my card with £27.00
    Then I have £27.00 remaining balance

  Scenario: A user travels in Zones 1-2 and checks balance
  As a user of the Oyster Card system
    When I load my card with £30.00
    # Tap in
    And I tap successfully at "Holborn"
    # Tap out
    And I tap successfully at "Earl's Court"
    And I take the 328 bus to "Chelsea"
    # Tap in
    And I tap successfully at "Earl's Court"
    # Tap out
    And I tap successfully at "Hammersmith"
    Then I have £27.00 remaining balance

  Scenario: A user is refused entry with low balance
  As a user of the Oyster Card system
    When I load my card with £1.79
    Then I tap unsuccessfully at "Holborn"
    And I have £1.79 remaining balance

  Scenario: A user spends more than their balance
  As a user of the Oyster Card system
    When I load my card with £1.81
    # Tap in
    Then I tap successfully at "Holborn"
     # Tap out
    And I tap successfully at "Earl's Court"
    And I take the 328 bus to "Chelsea"
    # Tap in
    And I tap successfully at "Earl's Court"
    # Tap out
    And I tap successfully at "Hammersmith"
    Then I have -£2.50 remaining balance

  Scenario: User is charged max fare when user does not tap in
  As a user of the Oyster Card system
    When I load my card with £30.00
    And I tap successfully at "Earl's Court"
    Then I have £26.80 remaining balance

  Scenario: User is charged max fare when user does not tap out
  As a user of the Oyster Card system
    When I load my card with £30.00
    And I tap successfully at "Earl's Court"
    Then I have £26.80 remaining balance

  Scenario: User takes one buses
  As a user of the Oyster Card system
    When I load my card with £30.00
    And I take the 328 bus to "Chelsea"
    Then I have £28.20 remaining balance

  Scenario: User takes three buses
  As a user of the Oyster Card system
    When I load my card with £30.00
    And I take the 328 bus to "Chelsea"
    And I take the 73 bus to "Earl's Court"
    And I take the 92 bus to "Buckingham Palace"
    Then I have £26.80 remaining balance

  # Further scenarios
  # User makes journeys on several days
