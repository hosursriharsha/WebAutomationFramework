Feature: HomePage

  @Regression
  Scenario: Check Balance
    Given when I launch Parabank page
    When I enter username and password as "admin" and "admin"
    When I click on "Login" button
    When I click on "Mail" button
    When I enter name as "ABC"
    And I enter Email as "abc@gmail.com"
    And I enter Phone as "+44123456778"
    And I enter message as "Thanks for service"
    When I click on "Send to Customer Care" button
    Then I verify success message
    
    
  @Regression
  Scenario: Verify links
    Given when I launch Parabank page
    When I verify links on left menu
    
    
  
