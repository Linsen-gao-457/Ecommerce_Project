package com.ecommerce.sportscenter.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BasketResponseTest {

    @Test
    void testBasketResponseGettersAndSetters() {
        // Create a new BasketResponse instance using the default constructor
        BasketResponse basketResponse = new BasketResponse();

        // Test setter and getter for id
        basketResponse.setId("1");
        assertEquals("1", basketResponse.getId(), "The basket ID should be '1'");

        // Test setter and getter for items (list of BasketItemResponse)
        BasketItemResponse itemResponse = new BasketItemResponse(1, "Football", "A popular sport item.", 100L,
                "http://example.com/football.jpg", "Nike", "Sport", 5);
        basketResponse.setItems(Arrays.asList(itemResponse));
        assertEquals(1, basketResponse.getItems().size(), "The basket should contain 1 item");
        assertEquals("Football", basketResponse.getItems().get(0).getName(), "The item name should be 'Football'");
    }

    @Test
    void testBasketResponseConstructor() {
        // Create a new BasketResponse instance using the all-args constructor
        BasketItemResponse itemResponse = new BasketItemResponse(1, "Football", "A popular sport item.", 100L,
                "http://example.com/football.jpg", "Nike", "Sport", 5);
        BasketResponse basketResponse = new BasketResponse("1", Arrays.asList(itemResponse));

        // Test constructor assignment
        assertEquals("1", basketResponse.getId(), "The basket ID should be '1'");
        assertEquals(1, basketResponse.getItems().size(), "The basket should contain 1 item");
        assertEquals("Football", basketResponse.getItems().get(0).getName(), "The item name should be 'Football'");
    }

    @Test
    void testBasketResponseBuilder() {
        // Create a new BasketResponse using the builder
        BasketItemResponse itemResponse = new BasketItemResponse(1, "Football", "A popular sport item.", 100L,
                "http://example.com/football.jpg", "Nike", "Sport", 5);
        BasketResponse basketResponse = BasketResponse.builder()
                .id("1")
                .items(Arrays.asList(itemResponse))
                .build();

        // Test builder assignment
        assertEquals("1", basketResponse.getId(), "The basket ID should be '1'");
        assertEquals(1, basketResponse.getItems().size(), "The basket should contain 1 item");
        assertEquals("Football", basketResponse.getItems().get(0).getName(), "The item name should be 'Football'");
    }
}
