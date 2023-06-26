package com.example.petstore.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Lob;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Pet {
    private Integer id;
    private Category category;
    private String name;
    @Lob
    private byte[] image;
    private List<Tag> tags = new ArrayList<>();
    private String status;
}
