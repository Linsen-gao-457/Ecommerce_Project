package com.ecommerce.sportscenter.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductResponseTest {

    @Test
    void testProductResponseGettersAndSetters() {
        // Create a new ProductResponse instance using the default constructor
        ProductResponse productResponse = new ProductResponse();

        // Test setter and getter for id
        productResponse.setId(1);
        assertEquals(1, productResponse.getId(), "The ID should be 1");

        // Test setter and getter for name
        productResponse.setName("Basketball");
        assertEquals("Basketball", productResponse.getName(), "The product name should be 'Basketball'");

        // Test setter and getter for description
        productResponse.setDescription("A high-quality basketball.");
        assertEquals("A high-quality basketball.", productResponse.getDescription(), "The description should match");

        // Test setter and getter for price
        productResponse.setPrice(2999L);
        assertEquals(2999L, productResponse.getPrice(), "The price should be 2999L");

        // Test setter and getter for pictureUrl
        productResponse.setPictureUrl("http://example.com/basketball.jpg");
        assertEquals("http://example.com/basketball.jpg", productResponse.getPictureUrl(), "The picture URL should match");

        // Test setter and getter for productBrand
        productResponse.setProductBrand("Nike");
        assertEquals("Nike", productResponse.getProductBrand(), "The product brand should be 'Nike'");

        // Test setter and getter for productType
        productResponse.setProductType("Sports");
        assertEquals("Sports", productResponse.getProductType(), "The product type should be 'Sports'");
    }

    @Test
    void testProductResponseConstructor() {
        // Create a new ProductResponse instance using the all-args constructor
        ProductResponse productResponse = new ProductResponse(
                1,
                "Basketball",
                "A high-quality basketball.",
                2999L,
                "http://example.com/basketball.jpg",
                "Nike",
                "Sports"
        );

        // Test constructor assignment
        assertEquals(1, productResponse.getId(), "The ID should be 1");
        assertEquals("Basketball", productResponse.getName(), "The product name should be 'Basketball'");
        assertEquals("A high-quality basketball.", productResponse.getDescription(), "The description should match");
        assertEquals(2999L, productResponse.getPrice(), "The price should be 2999L");
        assertEquals("http://example.com/basketball.jpg", productResponse.getPictureUrl(), "The picture URL should match");
        assertEquals("Nike", productResponse.getProductBrand(), "The product brand should be 'Nike'");
        assertEquals("Sports", productResponse.getProductType(), "The product type should be 'Sports'");
    }

    @Test
    void testProductResponseBuilder() {
        // Create a new ProductResponse instance using the builder
        ProductResponse productResponse = ProductResponse.builder()
                .id(1)
                .name("Basketball")
                .description("A high-quality basketball.")
                .price(2999L)
                .pictureUrl("http://example.com/basketball.jpg")
                .productBrand("Nike")
                .productType("Sports")
                .build();

        // Test builder assignment
        assertEquals(1, productResponse.getId(), "The ID should be 1");
        assertEquals("Basketball", productResponse.getName(), "The product name should be 'Basketball'");
        assertEquals("A high-quality basketball.", productResponse.getDescription(), "The description should match");
        assertEquals(2999L, productResponse.getPrice(), "The price should be 2999L");
        assertEquals("http://example.com/basketball.jpg", productResponse.getPictureUrl(), "The picture URL should match");
        assertEquals("Nike", productResponse.getProductBrand(), "The product brand should be 'Nike'");
        assertEquals("Sports", productResponse.getProductType(), "The product type should be 'Sports'");
    }
}
