package com.ecommerce.sportscenter.entity.OrderAggregate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatusTest {

    @Test
    void testOrderStatusEnumValues() {
        assertEquals("Pending", OrderStatus.Pending.name());
        assertEquals("PaymentReceived", OrderStatus.PaymentReceived.name());
        assertEquals("PaymentFailed", OrderStatus.PaymentFailed.name());

        assertNotNull(OrderStatus.valueOf("Pending"));
        assertThrows(IllegalArgumentException.class, () -> OrderStatus.valueOf("INVALID"));
    }
}
