package com.ecommerce.clothesstore.repository;

import com.ecommerce.clothesstore.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {

}
