package com.demo.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_jwt_auth")
public class User {
    
    @Id
    private String id;

    private String username;
    
    private String email;
    
    private String password;
    
    private String roles;

    @PrePersist
    private void PrePersist(){
        if (id == null || id.isEmpty()) {
            id=generateUUID();
        }
    }

    private String generateUUID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
