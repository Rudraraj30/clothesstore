package com.ecommerce.clothesstore.service;

import com.ecommerce.clothesstore.entity.AppUser;
import com.ecommerce.clothesstore.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder be=new BCryptPasswordEncoder(12);
    public void register(AppUser user)
    {
        user.setPassword(be.encode(user.getPassword()));
        userRepo.save(user);
    }

    public String verify(AppUser user) {
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
        System.out.println("here comess meee");
        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUserName());
        else
            return "failed";
    }
}
