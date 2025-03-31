package com.ecommerce.sportscenter.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.ecommerce.sportscenter.entity.OrderAggregate.Order;
import com.ecommerce.sportscenter.model.OrderDto;
import com.ecommerce.sportscenter.model.OrderResponse;

@Mapper
public interface OrderMapper { // interface will be implemented automatically when compile
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "basketId", target = "basketId")
    @Mapping(source = "shippingAddress", target = "shippingAddress")
    @Mapping(source = "subTotal", target = "subTotal")
    @Mapping(source = "deliveryFee", target = "deliveryFee")
    @Mapping(target = "orderDate", expression = "java(java.time.LocalDateTime.now())") //create a new orderdate to prevent fake orderdate, record backend own creation time
    @Mapping(target = "total", expression = "java(order.getSubtotal() + order.getDeliveryFee())")
    @Mapping(target = "orderStatus", constant = "Pending")
    OrderResponse orderToOrderResponse(Order order);

    @Mapping(target = "orderStatus", constant = "Pending")
    @Mapping(target = "OrderDate", expression = "java(order.getOrderDate())") // type and name are the same, it should map automatically
    Order orderDtoToOrder(OrderDto orderDto);

    List<OrderResponse> ordersToOrderResponses(List<Order> orders); 

    void updateOrderFromOrderDto(OrderDto orderDto, @MappingTarget Order order);// map the OrderDto to the existing order instead of creatign a new one
}
