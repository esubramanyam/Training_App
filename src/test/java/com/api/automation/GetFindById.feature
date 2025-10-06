Feature: To the planinfo details by id
  Scenario: To verify the planinfo details by Id
    Given url 'http://localhost:9191/planInfo/9'
    When method get
    Then status 202
