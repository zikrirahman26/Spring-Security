package com.demo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.dto.AuthRequest;

@Service
public class AuthService {
    
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String login(AuthRequest request){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        
        if (authentication.isAuthenticated()) {
                String token = jwtService.generateToken(request.getUsername());
                String key = request.getUsername();
                redisTemplate.opsForValue().set(key, token, 15, TimeUnit.MINUTES);
                return token;
            } else {
                throw new UsernameNotFoundException("invalid user request !");
        }
    }

    public String getTokenByUsername(String username) {
        String key = username;
        return redisTemplate.opsForValue().get(key);
    }
}
