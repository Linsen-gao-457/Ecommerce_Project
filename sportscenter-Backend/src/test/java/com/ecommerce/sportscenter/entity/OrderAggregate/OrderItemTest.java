package com.ecommerce.sportscenter.entity.OrderAggregate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void testOrderItemSettersAndGetters() {
        ProductItemOrdered product = new ProductItemOrdered(1, "Shoes", "img.jpg");
        Order order = new Order();

        OrderItem item = new OrderItem();
        item.setId(101);
        item.setItemOrdered(product);
        item.setPrice(999L);
        item.setQuantity(2);
        item.setOrder(order);

        assertEquals(101, item.getId());
        assertEquals(product, item.getItemOrdered());
        assertEquals(999L, item.getPrice());
        assertEquals(2, item.getQuantity());
        assertEquals(order, item.getOrder());
    }

    @Test
    void testOrderItemAllArgsConstructor() {
        ProductItemOrdered product = new ProductItemOrdered(1, "Shoes", "img.jpg");
        Order order = new Order();
        OrderItem item = new OrderItem(1, product, 999L, 2, order);

        assertEquals(1, item.getId());
        assertEquals(product, item.getItemOrdered());
        assertEquals(999L, item.getPrice());
        assertEquals(2, item.getQuantity());
        assertEquals(order, item.getOrder());
    }

    @Test
    void testOrderItemBuilder() {
        Order order = new Order();
        ProductItemOrdered product = ProductItemOrdered.builder()
                .productId(5)
                .name("Hat")
                .pictureUrl("hat.jpg")
                .build();

        OrderItem item = OrderItem.builder()
                .id(10)
                .itemOrdered(product)
                .price(499L)
                .quantity(1)
                .order(order)
                .build();

        assertEquals(10, item.getId());
        assertEquals("Hat", item.getItemOrdered().getName());
        assertEquals(499L, item.getPrice());
        assertEquals(1, item.getQuantity());
        assertEquals(order, item.getOrder());
    }
}
