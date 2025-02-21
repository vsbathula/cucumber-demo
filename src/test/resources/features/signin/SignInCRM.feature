Feature: Sign in page CRM

  Background: Navigate to CRM SignIn page
    Given User navigates to crm signin page

  Scenario Outline: Sign in CRM with an valid credentials
    When User signin with valid username "<username>" and password "<password>"
    And Click SignIn button
#    Then User is redirected to the Dashboard page
    Examples:
      | username          | password |
      | admin@example.com | 123456   |

  Scenario Outline: Sign in CRM with an invalid credentials
    When User signin with invalid username "<username>" and password "<password>"
    And Click SignIn button
#    Then The user can not redirect to Dashboard page
#    And The error message is displays
    Examples:
      | username         | password |
      | demo@example.com | 123456   |