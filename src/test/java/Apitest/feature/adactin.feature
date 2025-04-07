Feature: Adactin Hotel App Login

  Background:
 * karate.configure('driver', { type: 'geckodriver', headless: true });


  Scenario: Successful login to Adactin
    Given driver 'http://adactinhotelapp.com/'
    And input('#username', 'rograven')
    And input('#password', '17XS72')
    And click('#login')