package com.ecommerce.sportscenter.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketTest {

    @Test
    void testBasketConstructor() {
        // Create a new Basket instance using the parameterized constructor
        String basketId = "1";
        Basket basket = new Basket(basketId);

        // Assert that the Basket ID is set correctly
        assertEquals(basketId, basket.getId(), "The Basket ID should be '1'");
        // Assert that the Basket starts with an empty list of items
        assertTrue(basket.getItems().isEmpty(), "The Basket should have an empty list of items initially");
    }

    @Test
    void testAddItemsToBasket() {
        // Create a new Basket instance
        Basket basket = new Basket("1");

        // Create some BasketItem instances
        BasketItem item1 = new BasketItem();
        item1.setId(1);
        item1.setName("Basketball");

        BasketItem item2 = new BasketItem();
        item2.setId(2);
        item2.setName("Football");

        // Add items to the basket
        basket.getItems().add(item1);
        basket.getItems().add(item2);

        // Assert that the Basket has the correct number of items
        assertEquals(2, basket.getItems().size(), "The Basket should have 2 items");
        // Assert that the items in the basket are correct
        assertEquals("Basketball", basket.getItems().get(0).getName(), "The first item should be 'Basketball'");
        assertEquals("Football", basket.getItems().get(1).getName(), "The second item should be 'Football'");
    }

    @Test
    void testEmptyBasket() {
        // Create a new Basket instance
        Basket basket = new Basket("1");

        // Assert that the Basket is empty initially
        assertTrue(basket.getItems().isEmpty(), "The Basket should be empty initially");

        // Add a new item to the Basket
        BasketItem item = new BasketItem();
        item.setId(1);
        item.setName("Basketball");
        basket.getItems().add(item);

        // Assert that the Basket now contains 1 item
        assertFalse(basket.getItems().isEmpty(), "The Basket should not be empty after adding an item");
        assertEquals(1, basket.getItems().size(), "The Basket should have 1 item");
    }

    @Test
    void testBasketIdSetterAndGetter() {
        // Create a new Basket instance
        Basket basket = new Basket();

        // Set the basket ID using the setter
        basket.setId("2");

        // Assert that the ID is set correctly
        assertEquals("2", basket.getId(), "The Basket ID should be '2'");
    }

    @Test
    void testBasketItemsList() {
        // Create a new Basket instance
        Basket basket = new Basket("1");

        // Verify that the items list is initialized to an empty list
        assertNotNull(basket.getItems(), "The items list should not be null");
        assertTrue(basket.getItems().isEmpty(), "The items list should be empty");

        // Create a new BasketItem instance and add it to the basket
        BasketItem item = new BasketItem();
        item.setId(1);
        item.setName("Basketball");
        basket.getItems().add(item);

        // Verify that the item was added to the list
        assertEquals(1, basket.getItems().size(), "The items list should contain 1 item");
        assertEquals("Basketball", basket.getItems().get(0).getName(), "The item in the list should be 'Basketball'");
    }
}
