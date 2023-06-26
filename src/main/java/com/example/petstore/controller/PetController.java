package com.example.petstore.controller;

import com.example.petstore.entity.Pet;
import com.example.petstore.entity.User;
import com.example.petstore.enums.PetStatus;
import com.example.petstore.repository.PetRepository;
import com.example.petstore.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    private final List<Pet> pets = new ArrayList<>();


    @PostMapping
    @ResponseBody
    public ResponseEntity<Pet> save(@RequestBody Pet pet) {
        Pet savedPet = petRepository.save(pet);
        return ResponseEntity.ok(savedPet);
    }


    @PutMapping
    public ResponseEntity<Pet> update(int id, @RequestBody Pet pet) {
        Optional<Pet> optionalPet = petRepository.findById(id);

        if (optionalPet.isPresent()) {
            Pet existingPet = optionalPet.get();

            existingPet.setName(pet.getName());
            existingPet.setCategory(pet.getCategory());
            existingPet.setStatus(pet.getStatus());

            Pet updatedPet = petRepository.save(existingPet);
            return ResponseEntity.ok(updatedPet);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{petId}")
    @ResponseBody
    public ResponseEntity<Pet> findById(@PathVariable int petId) {
        Optional<Pet> byId = petRepository.findById(petId);
        if (byId.isPresent()) {
            Pet pet = byId.get();
            return ResponseEntity.ok(pet);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{petId}")
    @ResponseBody
    public ResponseEntity<Pet> updateWithForm(@PathVariable int petId,
                                              @RequestParam String name,
                                              @RequestParam String status) {
        Optional<Pet> optionalPet = petRepository.findById(petId);

        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();

            pet.setName(name);
            pet.setStatus(status);

            Pet updatedPet = petRepository.save(pet);
            return ResponseEntity.ok(updatedPet);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{petId}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable int petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isPresent()) {
            Pet pet = optionalPet.get();
            petRepository.delete(pet);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findByStatus")
    @ResponseBody
    public ResponseEntity<List<Pet>> findByStatus(@RequestParam("status") PetStatus status) {
        List<Pet> petList = petRepository.findByStatus(status.name());
        return ResponseEntity.ok(petList);
    }

//    @SneakyThrows
//    @PostMapping("/{petId}/uploadImage")
//    public ResponseEntity<Pet> uploadImage(@PathVariable int petId,
//                                           MultipartFile file){

//    }


}
