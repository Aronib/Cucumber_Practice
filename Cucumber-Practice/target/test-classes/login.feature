Feature: Login to an e-commerce website.

  Scenario Outline: Verify users can login with valid credentials.
    Given User visits e-commerce website.
    When User enters valid "<email>" and valid "<password>".
    Then Users logged in succesfully.
    Examples:
    |email|password|
    |cucumbertest@aro.com|cucumber123|
