package com.example.petstore.controller;

import com.example.petstore.entity.Order;
import com.example.petstore.entity.User;
import com.example.petstore.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreRepository storeRepository;
    private final List<Order> orders = new ArrayList<>();

    @PostMapping("/order")
    @ResponseBody
    public ResponseEntity<Order> save(@RequestBody Order order) {
        Order savedOrder = storeRepository.save(order);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/store/order/{orderId}")
    @ResponseBody
    public ResponseEntity<Order> findByOrderId(@PathVariable int orderId) {
        Optional<Order> byId = storeRepository.findById(orderId);
        if (byId.isPresent()) {
            Order order = byId.get();
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/order/{orderId}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable int orderId) {
        Optional<Order> byId = storeRepository.findById(orderId);
        if (byId.isPresent()) {
            Order order = byId.get();
            storeRepository.delete(order);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
