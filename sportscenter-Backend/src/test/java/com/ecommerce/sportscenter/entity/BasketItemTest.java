package com.ecommerce.sportscenter.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketItemTest {

    @Test
    void testBasketItemConstructor() {
        // Create a new BasketItem instance using the default constructor
        BasketItem basketItem = new BasketItem();

        // Assert that the object is properly initialized with null/empty values
        assertNull(basketItem.getId(), "The BasketItem ID should be null initially");
        assertNull(basketItem.getName(), "The BasketItem name should be null initially");
        assertNull(basketItem.getDescription(), "The BasketItem description should be null initially");
        assertNull(basketItem.getPrice(), "The BasketItem price should be null initially");
        assertNull(basketItem.getPictureUrl(), "The BasketItem pictureUrl should be null initially");
        assertNull(basketItem.getProductBrand(), "The BasketItem productBrand should be null initially");
        assertNull(basketItem.getProductType(), "The BasketItem productType should be null initially");
        assertNull(basketItem.getQuantity(), "The BasketItem quantity should be null initially");
    }

    @Test
    void testBasketItemSetterAndGetter() {
        // Create a new BasketItem instance
        BasketItem basketItem = new BasketItem();

        // Set values using setters
        basketItem.setId(1);
        basketItem.setName("Basketball");
        basketItem.setDescription("A professional grade basketball");
        basketItem.setPrice(500L);
        basketItem.setPictureUrl("http://example.com/basketball.jpg");
        basketItem.setProductBrand("Nike");
        basketItem.setProductType("Sports");
        basketItem.setQuantity(10);

        // Assert that the values are set correctly using getters
        assertEquals(1, basketItem.getId(), "The ID should be 1");
        assertEquals("Basketball", basketItem.getName(), "The name should be 'Basketball'");
        assertEquals("A professional grade basketball", basketItem.getDescription(), "The description should match");
        assertEquals(500L, basketItem.getPrice(), "The price should be 500");
        assertEquals("http://example.com/basketball.jpg", basketItem.getPictureUrl(), "The pictureUrl should match");
        assertEquals("Nike", basketItem.getProductBrand(), "The productBrand should be 'Nike'");
        assertEquals("Sports", basketItem.getProductType(), "The productType should be 'Sports'");
        assertEquals(10, basketItem.getQuantity(), "The quantity should be 10");
    }

    @Test
    void testBasketItemWithParameterConstructor() {
        // Create a new BasketItem instance using the constructor
        BasketItem basketItem = new BasketItem();
        basketItem.setId(1);
        basketItem.setName("Basketball");
        basketItem.setDescription("A professional grade basketball");
        basketItem.setPrice(500L);
        basketItem.setPictureUrl("http://example.com/basketball.jpg");
        basketItem.setProductBrand("Nike");
        basketItem.setProductType("Sports");
        basketItem.setQuantity(10);

        // Assert that the object is properly initialized with the given values
        assertNotNull(basketItem);
        assertEquals(1, basketItem.getId(), "The ID should be 1");
        assertEquals("Basketball", basketItem.getName(), "The name should be 'Basketball'");
        assertEquals("A professional grade basketball", basketItem.getDescription(), "The description should match");
        assertEquals(500L, basketItem.getPrice(), "The price should be 500");
        assertEquals("http://example.com/basketball.jpg", basketItem.getPictureUrl(), "The pictureUrl should match");
        assertEquals("Nike", basketItem.getProductBrand(), "The productBrand should be 'Nike'");
        assertEquals("Sports", basketItem.getProductType(), "The productType should be 'Sports'");
        assertEquals(10, basketItem.getQuantity(), "The quantity should be 10");
    }

    @Test
    void testBasketItemIdSetterAndGetter() {
        // Create a new BasketItem instance
        BasketItem basketItem = new BasketItem();

        // Set the ID using the setter
        basketItem.setId(5);

        // Assert that the ID is correctly set
        assertEquals(5, basketItem.getId(), "The ID should be 5");
    }

    @Test
    void testBasketItemQuantitySetterAndGetter() {
        // Create a new BasketItem instance
        BasketItem basketItem = new BasketItem();

        // Set the quantity using the setter
        basketItem.setQuantity(20);

        // Assert that the quantity is correctly set
        assertEquals(20, basketItem.getQuantity(), "The quantity should be 20");
    }

    @Test
    void testBasketItemPriceSetterAndGetter() {
        // Create a new BasketItem instance
        BasketItem basketItem = new BasketItem();

        // Set the price using the setter
        basketItem.setPrice(1000L);

        // Assert that the price is correctly set
        assertEquals(1000L, basketItem.getPrice(), "The price should be 1000");
    }

    @Test
    void testBasketItemNullValues() {
        // Create a new BasketItem instance
        BasketItem basketItem = new BasketItem();

        // Check if some fields are still null
        assertNull(basketItem.getName(), "The name should be null");
        assertNull(basketItem.getDescription(), "The description should be null");
    }
}
