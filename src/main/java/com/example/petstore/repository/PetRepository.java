package com.example.petstore.repository;

import com.example.petstore.entity.Pet;
import com.example.petstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    Optional<Pet> findById(Integer petId);

    List<Pet> findByStatus(String status);

//    Optional<ImageData> findByName(String name);


}
