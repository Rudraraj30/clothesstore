package com.ecommerce.clothesstore.repository;

import com.ecommerce.clothesstore.entity.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends CrudRepository<Basket,String> {

}
