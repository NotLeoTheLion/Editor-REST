Feature: Client Management

  Scenario: Get All Clients
    When I get all clients
    Then I should get a list of clients

  Scenario: Get Client By Id
    When I get client by id 1
    Then I should get client with id 1

  Scenario: Add New Client
    When I add new client with first name "test" last name "testtest" phone number "1234567890" email "test@test.com"
    Then I should get client with first name "test" last name "testtest" phone number "1234567890" email "test@test.com"

  Scenario: Update Client
    When I update client with id 1 to first name "test1" last name "testtest1" phone number "0987654321" email "test1@test.com"
    Then I should get client with first name "test1" last name "testtest1" phone number "0987654321" email "test1@test.com"

  Scenario: Delete Client
    When I delete client with id 1
    Then I should not be able to get client with id 1
