package com.example.petstore.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Category {
    private Integer id;
    private String name;
}
