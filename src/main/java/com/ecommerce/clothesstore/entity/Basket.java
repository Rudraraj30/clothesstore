package com.ecommerce.clothesstore.entity;

import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RedisHash("Basket")
public class Basket implements Serializable {
    @Id
    private String id;
    private List<BasketItem> Items=new ArrayList<>();
    public Basket(String id){
        this.id=id;
    }
}
