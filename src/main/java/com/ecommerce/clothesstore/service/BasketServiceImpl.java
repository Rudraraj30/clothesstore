package com.ecommerce.clothesstore.service;

import com.ecommerce.clothesstore.entity.Basket;
import com.ecommerce.clothesstore.entity.BasketItem;
import com.ecommerce.clothesstore.model.BasketItemResponse;
import com.ecommerce.clothesstore.model.BasketResponse;
import com.ecommerce.clothesstore.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService{

    @Autowired
    private BasketRepository basketRepository;

    @Override
    public List<BasketResponse> getAllBaskets() {
        List<Basket> basketList=(List<Basket>) basketRepository.findAll();
        //now we will use stream operator to map with response
        List<BasketResponse> basketResponses =basketList.stream().map(this::convertToBasketResponse).collect(Collectors.toList());
        return basketResponses;
    }



    @Override
    public BasketResponse getBasketById(String basketId) {
        Optional<Basket> basketOptional=basketRepository.findById(basketId);
        if(basketOptional.isPresent())
        {
            Basket basket=basketOptional.get();
            return convertToBasketResponse(basket);
        }
        return null;
    }

    @Override
    public void deleteBasketById(String basketId) {
    basketRepository.deleteById(basketId);
    }

    @Override
    public BasketResponse createBasket(Basket basket) {
        Basket savedBasket=basketRepository.save(basket);
        return convertToBasketResponse(basket);
    }

    private BasketResponse convertToBasketResponse(Basket basket) {
        if(basket==null)
        {
            return null;
        }
        List<BasketItemResponse> basketItemResponses=basket.getItems().stream()
                .map(this::convertToBasketItemResponse).collect(Collectors.toList());

        return BasketResponse.builder().id(basket.getId()).Items(basketItemResponses).build();
    }

    private BasketItemResponse convertToBasketItemResponse(BasketItem basketItem) {
        return BasketItemResponse.builder().id(basketItem.getId())
                .name(basketItem.getName())
                .description(basketItem.getDescription())
                .price(basketItem.getPrice())
                .pictureUrl(basketItem.getPictureUrl())
                .productBrand(basketItem.getProductBrand())
                .productType(basketItem.getProductType())
                .quantity(basketItem.getQuantity())
                .build();
    }
}
