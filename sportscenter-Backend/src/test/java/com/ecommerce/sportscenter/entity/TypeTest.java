package com.ecommerce.sportscenter.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TypeTest {

    private Type type;

    @Mock
    private Product product;

    @BeforeEach
    void setUp() {
        // Setup a new Type object before each test
        type = new Type();
    }

    @Test
    void testTypeConstructor() {
        // Test the default constructor
        assertNotNull(type, "The type should not be null after creation");
        assertNull(type.getId(), "The ID should be null initially");
        assertNull(type.getName(), "The name should be null initially");
        // assertTrue(type.getProducts().isEmpty(), "The products list should be empty initially");
    }

    @Test
    void testTypeSetterAndGetter() {
        // Set values using setters
        type.setId(1);
        type.setName("Type1");

        // Create a mock Product object
        product = new Product();
        product.setId(1);
        product.setName("Product1");

        // Add the product to the type's product list
        type.setProducts(List.of(product));

        // Assert that the values are set correctly using getters
        assertEquals(1, type.getId(), "The ID should be 1");
        assertEquals("Type1", type.getName(), "The name should be 'Type1'");
        assertFalse(type.getProducts().isEmpty(), "The products list should not be empty");
        assertEquals(product, type.getProducts().get(0), "The first product in the list should be the mock product");
    }

    @Test
    void testTypeProductsSetterAndGetter() {
        // Create and set a list of products
        product = new Product();
        product.setId(1);
        product.setName("Product1");

        type.setProducts(List.of(product));

        // Assert that the products list is set correctly
        assertNotNull(type.getProducts(), "The products list should not be null");
        assertEquals(1, type.getProducts().size(), "The products list should contain one product");
        assertEquals(product, type.getProducts().get(0), "The product in the list should be the mock product");
    }

    @Test
    void testTypeBuilder() {
        // Test the builder pattern
        type = Type.builder()
                .id(1)
                .name("Type1")
                .build();

        // Assert that the builder sets the fields correctly
        assertNotNull(type, "The type should not be null after building");
        assertEquals(1, type.getId(), "The ID should be 1");
        assertEquals("Type1", type.getName(), "The name should be 'Type1'");
    }

    @Test
    void testTypeWithProducts() {
        // Create mock products
        product = new Product();
        product.setId(1);
        product.setName("Product1");

        Product product2 = new Product();
        product2.setId(2);
        product2.setName("Product2");

        // Add products to the type
        type.setProducts(List.of(product, product2));

        // Assert the type has the correct number of products
        assertEquals(2, type.getProducts().size(), "The products list should contain two products");
        assertEquals(product, type.getProducts().get(0), "The first product should be the mock product");
        assertEquals(product2, type.getProducts().get(1), "The second product should be the second mock product");
    }

    @Test
    void testTypeNameSetterAndGetter() {
        // Set name using setter
        type.setName("New Type");

        // Assert that the name is correctly set
        assertEquals("New Type", type.getName(), "The name should be 'New Type'");
    }

    @Test
    void testTypeIdSetterAndGetter() {
        // Set ID using setter
        type.setId(100);

        // Assert that the ID is correctly set
        assertEquals(100, type.getId(), "The ID should be 100");
    }
}
