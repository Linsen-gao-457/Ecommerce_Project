package com.ecommerce.sportscenter.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketItemResponseTest {

    @Test
    void testBasketItemResponseGettersAndSetters() {
        // Create a new BasketItemResponse instance using the default constructor
        BasketItemResponse itemResponse = new BasketItemResponse();

        // Test setter and getter for id
        itemResponse.setId(1);
        assertEquals(1, itemResponse.getId(), "The item ID should be 1");

        // Test setter and getter for name
        itemResponse.setName("Football");
        assertEquals("Football", itemResponse.getName(), "The item name should be 'Football'");

        // Test setter and getter for description
        itemResponse.setDescription("A popular sport item.");
        assertEquals("A popular sport item.", itemResponse.getDescription(), "The item description should be 'A popular sport item.'");

        // Test setter and getter for price
        itemResponse.setPrice(100L);
        assertEquals(100L, itemResponse.getPrice(), "The item price should be 100");

        // Test setter and getter for pictureUrl
        itemResponse.setPictureUrl("http://example.com/football.jpg");
        assertEquals("http://example.com/football.jpg", itemResponse.getPictureUrl(), "The item picture URL should match");

        // Test setter and getter for productBrand
        itemResponse.setProductBrand("Nike");
        assertEquals("Nike", itemResponse.getProductBrand(), "The item product brand should be 'Nike'");

        // Test setter and getter for productType
        itemResponse.setProductType("Sport");
        assertEquals("Sport", itemResponse.getProductType(), "The item product type should be 'Sport'");

        // Test setter and getter for quantity
        itemResponse.setQuantity(5);
        assertEquals(5, itemResponse.getQuantity(), "The item quantity should be 5");
    }

    @Test
    void testBasketItemResponseConstructor() {
        // Create a new BasketItemResponse instance using the all-args constructor
        BasketItemResponse itemResponse = new BasketItemResponse(
                1, "Football", "A popular sport item.", 100L, "http://example.com/football.jpg", "Nike", "Sport", 5);

        // Test constructor assignment
        assertEquals(1, itemResponse.getId(), "The item ID should be 1");
        assertEquals("Football", itemResponse.getName(), "The item name should be 'Football'");
        assertEquals("A popular sport item.", itemResponse.getDescription(), "The item description should match");
        assertEquals(100L, itemResponse.getPrice(), "The item price should be 100");
        assertEquals("http://example.com/football.jpg", itemResponse.getPictureUrl(), "The picture URL should match");
        assertEquals("Nike", itemResponse.getProductBrand(), "The product brand should be 'Nike'");
        assertEquals("Sport", itemResponse.getProductType(), "The product type should be 'Sport'");
        assertEquals(5, itemResponse.getQuantity(), "The quantity should be 5");
    }

    @Test
    void testBasketItemResponseBuilder() {
        // Create a new BasketItemResponse using the builder
        BasketItemResponse itemResponse = BasketItemResponse.builder()
                .id(1)
                .name("Football")
                .description("A popular sport item.")
                .price(100L)
                .pictureUrl("http://example.com/football.jpg")
                .productBrand("Nike")
                .productType("Sport")
                .quantity(5)
                .build();

        // Test builder assignment
        assertEquals(1, itemResponse.getId(), "The item ID should be 1");
        assertEquals("Football", itemResponse.getName(), "The item name should be 'Football'");
        assertEquals("A popular sport item.", itemResponse.getDescription(), "The item description should match");
        assertEquals(100L, itemResponse.getPrice(), "The item price should be 100");
        assertEquals("http://example.com/football.jpg", itemResponse.getPictureUrl(), "The picture URL should match");
        assertEquals("Nike", itemResponse.getProductBrand(), "The product brand should be 'Nike'");
        assertEquals("Sport", itemResponse.getProductType(), "The product type should be 'Sport'");
        assertEquals(5, itemResponse.getQuantity(), "The quantity should be 5");
    }
}

