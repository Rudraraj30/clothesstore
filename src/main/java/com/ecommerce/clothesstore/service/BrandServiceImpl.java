package com.ecommerce.clothesstore.service;

import com.ecommerce.clothesstore.entity.Brand;
import com.ecommerce.clothesstore.model.BrandResponse;
import com.ecommerce.clothesstore.repository.BrandRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<BrandResponse> getAllBrands() {
        //fetch Brands
        List<Brand> brandList =brandRepository.findAll();
        //now use stream operator to map with response
        return brandList.stream().
                map(this::convertToBrandResponse).
                collect(Collectors.toList());
    }

    private BrandResponse convertToBrandResponse(Brand brand) {
        return BrandResponse.builder().id(brand.getId()).name(brand.getName()).build();
    }
}
