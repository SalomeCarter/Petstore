package com.example.petstore.repository;
import com.example.petstore.entity.Order;
import com.example.petstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository <Order, Integer> {
    Optional<Order> findById(Integer id);

}
