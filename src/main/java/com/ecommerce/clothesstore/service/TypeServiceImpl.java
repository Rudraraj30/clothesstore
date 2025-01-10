package com.ecommerce.clothesstore.service;

import com.ecommerce.clothesstore.entity.Type;
import com.ecommerce.clothesstore.model.TypeResponse;
import com.ecommerce.clothesstore.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public List<TypeResponse> getAllTypes() {
        //fetch types from DB
        List<Type> typeList=typeRepository.findAll();
        //now use stream operator to map with response
        return typeList.stream().
                map(this::convertToTypeResponse).
                collect(Collectors.toList());
    }

    private TypeResponse convertToTypeResponse(Type type) {
        return TypeResponse.builder().id(type.getId()).name(type.getName()).build();
    }


}
