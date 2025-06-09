package com.ecommerce.clothesstore.repository;


import com.ecommerce.clothesstore.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<AppUser,Integer> {
    AppUser findByUserName(String username);
}
