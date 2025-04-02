package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.OrderAggregate.Order;
import com.ecommerce.sportscenter.mapper.OrderMapper;
import com.ecommerce.sportscenter.model.BasketItemResponse;
import com.ecommerce.sportscenter.model.BasketResponse;
import com.ecommerce.sportscenter.model.OrderDto;
import com.ecommerce.sportscenter.model.OrderResponse;
import com.ecommerce.sportscenter.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private BasketService basketService;

    @InjectMocks
    private OrderServiceImpl orderService;

    // 测试数据
    private Order sampleOrder;
    private OrderResponse sampleOrderResponse;
    private OrderDto sampleOrderDto;
    private BasketResponse sampleBasketResponse;
    private BasketItemResponse sampleBasketItem;

    @BeforeEach
    void setUp() {
        // 构造一个简单的 Order 对象
        sampleOrder = new Order();
        sampleOrder.setId(1);
        // 如有需要，可继续设置 Order 对象的其他属性

        // 构造 OrderResponse（由 OrderMapper 进行映射）
        sampleOrderResponse = new OrderResponse();
        sampleOrderResponse.setId(1);
        // 如有需要，可继续设置其他字段

        // 构造 OrderDto，用于创建订单
        sampleOrderDto = new OrderDto();
        sampleOrderDto.setBasketId("100");

        // 构造 BasketItemResponse（篮子中的一项商品）
        sampleBasketItem = new BasketItemResponse();
        sampleBasketItem.setId(10);
        sampleBasketItem.setName("Test Product");
        sampleBasketItem.setPrice((long)50.0);
        sampleBasketItem.setQuantity(2);
        sampleBasketItem.setPictureUrl("test.jpg");

        // 构造 BasketResponse（购物篮）
        sampleBasketResponse = new BasketResponse();
        sampleBasketResponse.setId("100");
        sampleBasketResponse.setItems(Collections.singletonList(sampleBasketItem));
    }

    @Test
    void testGetOrderById_success() {
        // 模拟订单存在时的行为
        when(orderRepository.findById(1)).thenReturn(Optional.of(sampleOrder)); //当调用orderRepository.findById(1)时，返回sample order <-- 我的mock
        when(orderMapper.orderToOrderResponse(sampleOrder)).thenReturn(sampleOrderResponse);

        OrderResponse response = orderService.getOrderById(1);

        assertNotNull(response);
        assertEquals(1, response.getId());
        verify(orderRepository, times(1)).findById(1);
    }

    @Test
    void testGetOrderById_notFound() {
        // 模拟找不到订单的情况
        when(orderRepository.findById(2)).thenReturn(Optional.empty());

        OrderResponse response = orderService.getOrderById(2);

        assertNull(response);
        verify(orderRepository, times(1)).findById(2);
    }

    @Test
    void testGetAllOrdersList() {
        // 测试返回 List<OrderResponse> 版本
        List<Order> orders = Arrays.asList(sampleOrder);
        when(orderRepository.findAll()).thenReturn(orders);
        when(orderMapper.orderToOrderResponse(sampleOrder)).thenReturn(sampleOrderResponse);

        List<OrderResponse> responses = orderService.getAllOrders();

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(1, responses.get(0).getId());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testGetAllOrdersPage() {
        // 测试分页返回 Page<OrderResponse> 版本
        Pageable pageable = mock(Pageable.class);
        Page<Order> pageOrders = new PageImpl<>(Collections.singletonList(sampleOrder));
        when(orderRepository.findAll(pageable)).thenReturn(pageOrders);
        when(orderMapper.orderToOrderResponse(sampleOrder)).thenReturn(sampleOrderResponse);

        Page<OrderResponse> responsePage = orderService.getAllOrders(pageable);

        assertNotNull(responsePage);
        assertEquals(1, responsePage.getTotalElements());
        verify(orderRepository, times(1)).findAll(pageable);
    }

    @Test
    void testDeleteOrder() {
        // 测试删除订单
        orderService.deleteOrder(1);
        verify(orderRepository, times(1)).deleteById(1);
    }

    @Test
    void testCreateOrder_success() {
        // 模拟 basketService 返回有效的 BasketResponse
        when(basketService.getBasketById(sampleOrderDto.getBasketId())).thenReturn(sampleBasketResponse);
        // 模拟 OrderMapper 将 OrderDto 转换为 Order 对象
        when(orderMapper.orderDtoToOrder(sampleOrderDto)).thenReturn(sampleOrder);
        // 模拟保存订单返回的 Order 对象（设定订单 id 等信息）
        sampleOrder.setSubTotal(100.0);
        when(orderRepository.save(any(Order.class))).thenReturn(sampleOrder);

        Integer orderId = orderService.createOrder(sampleOrderDto);

        assertNotNull(orderId);
        assertEquals(1, orderId);
        verify(basketService, times(1)).getBasketById(sampleOrderDto.getBasketId());
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(basketService, times(1)).deleteBasketById(sampleOrderDto.getBasketId());
    }

    @Test
    void testCreateOrder_basketNotFound() {
        // 模拟 basketService 找不到购物篮
        when(basketService.getBasketById(sampleOrderDto.getBasketId())).thenReturn(null);

        Integer orderId = orderService.createOrder(sampleOrderDto);

        assertNull(orderId);
        verify(basketService, times(1)).getBasketById(sampleOrderDto.getBasketId());
        verify(orderRepository, never()).save(any(Order.class));
    }
}
