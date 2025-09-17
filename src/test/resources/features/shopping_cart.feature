Feature: Shopping Cart Functionality
    
  Scenario: Cart total validation
    Given I add multiple products to the cart
    And I remove one product from the cart
    When I proceed to the checkout page
    Then Price should match expected value
