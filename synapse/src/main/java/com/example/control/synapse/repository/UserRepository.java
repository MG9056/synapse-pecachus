package com.example.control.synapse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.control.synapse.models.User;

public interface UserRepository extends JpaRepository<User,Long> {

    
} 
