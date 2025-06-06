package com.ecommerce.clothesstore.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("BasketItem")
public class BasketItem  implements Serializable {
    @Id
    private Integer id;
    private String name;
    private String description;
    private Long price;
    private String pictureUrl;
    private String productBrand;
    private String productType;
    private Integer quantity;
}
