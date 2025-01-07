@SmokeAPI
Feature: To view the restful-CoinDesk Bitcoin Price Index details

    @Regression
    Scenario: 1. To view all the CoinDesk Bitcoin Price Index [USD ,EUR,GB]
    Given user has access to endpoint "/v1/bpi/currentprice.json"
    When user makes a request to view BPI IDs
    Then user should get the successful response code
    And user should validate all the BPIs
    
    @smoke
     Scenario: 2. To Validate GBP coindesk Bitcoin Price Index description
      Given user has access to endpoint "/v1/bpi/currentprice.json"
      When user makes a request to view BPI IDs
    Then user should get the successful response code
      And user should validate GBP description