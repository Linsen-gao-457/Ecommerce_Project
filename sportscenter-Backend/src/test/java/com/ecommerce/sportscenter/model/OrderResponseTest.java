package com.ecommerce.sportscenter.model;

import com.ecommerce.sportscenter.entity.OrderAggregate.OrderStatus;
import com.ecommerce.sportscenter.entity.OrderAggregate.ShippingAddress;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class OrderResponseTest {

    @Test
    void testGettersAndSetters() {
        OrderResponse orderResponse = new OrderResponse();

        ShippingAddress address = new ShippingAddress();
        address.setCity("Ottawa");

        LocalDateTime orderDate = LocalDateTime.of(2024, 4, 1, 10, 0);

        orderResponse.setId(1);
        orderResponse.setBasketId("1001");
        orderResponse.setShippingAddress(address);
        orderResponse.setSubTotal(150.0);
        orderResponse.setDeliveryFee(10.0);
        orderResponse.setTotal(160.0);
        orderResponse.setOrderDate(orderDate);
        orderResponse.setOrderStatus(OrderStatus.Pending);

        assertEquals(1, orderResponse.getId());
        assertEquals("1001", orderResponse.getBasketId());
        assertEquals(address, orderResponse.getShippingAddress());
        assertEquals(150.0, orderResponse.getSubTotal());
        assertEquals(10.0, orderResponse.getDeliveryFee());
        assertEquals(160.0, orderResponse.getTotal());
        assertEquals(orderDate, orderResponse.getOrderDate());
        assertEquals(OrderStatus.Pending, orderResponse.getOrderStatus());
    }

    @Test
    void testAllArgsConstructor() {
        ShippingAddress address = new ShippingAddress();
        address.setCity("Toronto");

        LocalDateTime orderDate = LocalDateTime.of(2024, 4, 1, 9, 30);

        OrderResponse orderResponse = new OrderResponse(
                2,
                "2002",
                address,
                200.0,
                15.0,
                215.0,
                orderDate,
                OrderStatus.PaymentReceived
        );

        assertEquals(2, orderResponse.getId());
        assertEquals("2002", orderResponse.getBasketId());
        assertEquals(address, orderResponse.getShippingAddress());
        assertEquals(200.0, orderResponse.getSubTotal());
        assertEquals(15.0, orderResponse.getDeliveryFee());
        assertEquals(215.0, orderResponse.getTotal());
        assertEquals(orderDate, orderResponse.getOrderDate());
        assertEquals(OrderStatus.PaymentReceived, orderResponse.getOrderStatus());
    }

    @Test
    void testBuilder() {
        ShippingAddress address = new ShippingAddress();
        address.setCity("Vancouver");

        LocalDateTime orderDate = LocalDateTime.of(2024, 4, 1, 11, 45);

        OrderResponse orderResponse = OrderResponse.builder()
                .id(3)
                .basketId("3003")
                .shippingAddress(address)
                .subTotal(250.0)
                .deliveryFee(20.0)
                .total(270.0)
                .orderDate(orderDate)
                .orderStatus(OrderStatus.PaymentFailed)
                .build();

        assertEquals(3, orderResponse.getId());
        assertEquals("3003", orderResponse.getBasketId());
        assertEquals(address, orderResponse.getShippingAddress());
        assertEquals(250.0, orderResponse.getSubTotal());
        assertEquals(20.0, orderResponse.getDeliveryFee());
        assertEquals(270.0, orderResponse.getTotal());
        assertEquals(orderDate, orderResponse.getOrderDate());
        assertEquals(OrderStatus.PaymentFailed, orderResponse.getOrderStatus());
    }
}
