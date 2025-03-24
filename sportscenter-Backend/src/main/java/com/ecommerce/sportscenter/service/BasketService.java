package com.ecommerce.sportscenter.service;

import com.ecommerce.sportscenter.entity.Basket;
import com.ecommerce.sportscenter.model.BasketResponse;

import java.util.List;

public interface BasketService {
    List<BasketResponse> getAllBasket();

    BasketResponse getBasketById(Integer id);

    void deleteBasket(Integer id);

    BasketResponse addBasket(Basket basket);
}
