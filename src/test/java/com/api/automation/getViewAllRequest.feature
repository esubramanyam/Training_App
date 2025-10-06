Feature: To fetch all the planInfo indivi's details

  Scenario: Testing a fetch API and the response data in JSON
    Given url 'http://localhost:9191/planInfo/'
    When method get
    Then status 202