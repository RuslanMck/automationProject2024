Feature: Log in

  As a non logged in user
  I want to login
  So I can use internet

  Scenario: Valid login test
    Given user is opened the Internet login page
    When user enters 'tomsmith' in login firld and 'SuperSecretPassword!' in password field
    And clicks 'login' button
    Then user is logged in

  Scenario Outline:
    Given user is opened the Internet login page
    When user enters '<login>' in login firld and '<password>' in password field
    And clicks 'login' button
    Then user sees error message

    Examples:
      | login    | password             |
      | qweqwe   | SuperSecretPassword! |
      | tomsmith | d43                  |
      | q12qweq  | 123123               |