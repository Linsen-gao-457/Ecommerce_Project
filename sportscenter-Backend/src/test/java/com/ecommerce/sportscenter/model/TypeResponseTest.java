package com.ecommerce.sportscenter.model;

import com.ecommerce.sportscenter.entity.Product;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TypeResponseTest {

    @Test
    void testTypeResponseGettersAndSetters() {
        // Create a new TypeResponse instance using the default constructor
        TypeResponse typeResponse = new TypeResponse();

        // Test setter and getter for id
        typeResponse.setId(1);
        assertEquals(1, typeResponse.getId(), "The ID should be 1");

        // Test setter and getter for name
        typeResponse.setName("Sports");
        assertEquals("Sports", typeResponse.getName(), "The type name should be 'Sports'");

        // Test setter and getter for products
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Basketball");

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Football");

        typeResponse.setProducts(Arrays.asList(product1, product2));
        assertEquals(2, typeResponse.getProducts().size(), "The product list size should be 2");
        assertEquals("Basketball", typeResponse.getProducts().get(0).getName(), "The first product should be 'Basketball'");
    }

    @Test
    void testTypeResponseConstructor() {
        // Create a new TypeResponse instance using the all-args constructor
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Basketball");

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Football");

        TypeResponse typeResponse = new TypeResponse(
                1,
                "Sports",
                Arrays.asList(product1, product2)
        );

        // Test constructor assignment
        assertEquals(1, typeResponse.getId(), "The ID should be 1");
        assertEquals("Sports", typeResponse.getName(), "The type name should be 'Sports'");
        assertEquals(2, typeResponse.getProducts().size(), "The product list size should be 2");
        assertEquals("Basketball", typeResponse.getProducts().get(0).getName(), "The first product should be 'Basketball'");
    }

    @Test
    void testTypeResponseBuilder() {
        // Create a new TypeResponse instance using the builder
        Product product1 = new Product();
        product1.setId(1);
        product1.setName("Basketball");

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Football");

        TypeResponse typeResponse = TypeResponse.builder()
                .id(1)
                .name("Sports")
                .products(Arrays.asList(product1, product2))
                .build();

        // Test builder assignment
        assertEquals(1, typeResponse.getId(), "The ID should be 1");
        assertEquals("Sports", typeResponse.getName(), "The type name should be 'Sports'");
        assertEquals(2, typeResponse.getProducts().size(), "The product list size should be 2");
        assertEquals("Basketball", typeResponse.getProducts().get(0).getName(), "The first product should be 'Basketball'");
    }
}
