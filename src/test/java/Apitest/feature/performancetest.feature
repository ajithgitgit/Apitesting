Feature: Reqres API performance test

  Background:
    * url 'https://reqres.in'

  Scenario: Get users page 2
    Given path '/api/users'
    And param page = 2
    When method GET
    Then status 200
