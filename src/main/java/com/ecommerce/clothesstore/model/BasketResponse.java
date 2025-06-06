package com.ecommerce.clothesstore.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketResponse {
    private String id;
    private List<BasketItemResponse> Items=new ArrayList<>();
}
