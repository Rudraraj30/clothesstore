package com.ecommerce.clothesstore.service;

import com.ecommerce.clothesstore.entity.Basket;
import com.ecommerce.clothesstore.model.BasketResponse;

import java.util.List;

public interface BasketService {
    List<BasketResponse> getAllBaskets();
    BasketResponse getBasketById(String basketId);
    void deleteBasketById(String basketId);
    BasketResponse createBasket(Basket basket);
}
