Feature: Reqres API testing

  Background:
    * url 'https://reqres.in'

  Scenario: Get list of users (page 2)
    Given path '/api/users'
    And param page = 2
    When method GET
    Then status 200
    And match response.data[0].id == 7

  Scenario: Create a new user
    Given path '/api/users'
    And request { name: 'Ajith', job: 'QA Engineer' }
    When method POST
    Then status 201
    And match response.name == 'Ajith'
    And match response.job == 'QA Engineer'

  Scenario: Update user info
    Given path '/api/users/2'
    And request { name: 'Ajith Palani', job: 'Senior QA' }
    When method PUT
    Then status 200
    And match response.job == 'Senior QA'

  Scenario: Delete a user
    Given path '/api/users/2'
    When method DELETE
    Then status 204
