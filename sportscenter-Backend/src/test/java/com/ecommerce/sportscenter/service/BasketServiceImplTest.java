package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Basket;
import com.ecommerce.sportscenter.entity.BasketItem;
import com.ecommerce.sportscenter.model.BasketResponse;
import com.ecommerce.sportscenter.repository.BasketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasketServiceImplTest {

    @Mock
    private BasketRepository basketRepository;

    @InjectMocks
    private BasketServiceImpl basketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testGetAllBasket() {
        // Test for getAllBasket()
        Basket basket1 = new Basket();
        basket1.setId("1"); // Using String ID
        basket1.setItems(Arrays.asList(new BasketItem())); // Add some items to the basket

        Basket basket2 = new Basket();
        basket2.setId("2"); // Using String ID
        basket2.setItems(Arrays.asList(new BasketItem())); // Add some items to the basket

        when(basketRepository.findAll()).thenReturn(Arrays.asList(basket1, basket2));

        List<BasketResponse> basketResponses = basketService.getAllBasket();

        assertNotNull(basketResponses);
        assertEquals(2, basketResponses.size());
        verify(basketRepository, times(1)).findAll(); // Verify if the repository method was called once
    }

    @Test
    void testGetBasketByIdWhenFound() {
        // Test for getBasketById() when basket is found
        Basket basket1 = new Basket();
        basket1.setId("1"); // Using String ID
        basket1.setItems(Arrays.asList(new BasketItem())); // Add some items to the basket

        when(basketRepository.findById("1")).thenReturn(Optional.of(basket1));

        BasketResponse basketResponse = basketService.getBasketById("1");

        assertNotNull(basketResponse);
        assertEquals("1", basketResponse.getId()); // Assert String ID
        verify(basketRepository, times(1)).findById("1"); // Verify if the repository method was called once
    }

    @Test
    void testGetBasketByIdWhenNotFound() {
        // Test for getBasketById() when basket is not found
        when(basketRepository.findById("1")).thenReturn(Optional.empty());

        BasketResponse basketResponseNotFound = basketService.getBasketById("1");

        assertNull(basketResponseNotFound); // Expecting null when basket is not found
        verify(basketRepository, times(1)).findById("1"); // Verify if the repository method was called once
    }

    @Test
    void testDeleteBasket() {
        // Test for deleteBasket()
        doNothing().when(basketRepository).deleteById("1"); // Simulate the repository method with String ID

        basketService.deleteBasketById("1"); // Use String ID

        verify(basketRepository, times(1)).deleteById("1"); // Verify if the repository method was called once
    }

    @Test
    void testAddBasket() {
        // Test for addBasket()
        Basket newBasket = new Basket();
        newBasket.setId("3"); // Using String ID
        newBasket.setItems(Arrays.asList(new BasketItem())); // Add items to the basket

        Basket savedBasket = new Basket();
        savedBasket.setId("3"); // Using String ID
        savedBasket.setItems(Arrays.asList(new BasketItem())); // Add items to the saved basket

        when(basketRepository.save(newBasket)).thenReturn(savedBasket);

        BasketResponse addedBasketResponse = basketService.addBasket(newBasket);

        assertNotNull(addedBasketResponse);
        assertEquals("3", addedBasketResponse.getId()); // Assert String ID
        verify(basketRepository, times(1)).save(newBasket); // Verify if the repository method was called once
    }
}
